package com.stepanov.bbf.bugfinder.server

import com.stepanov.bbf.CommonCompiler
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.reducer.ResultsFilter
import com.stepanov.bbf.bugfinder.server.codecs.BugCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationProblemCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationResultCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationStrategyCodec
import com.stepanov.bbf.bugfinder.server.messages.CompilationResultsProcessor
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.server.messages.parseMutationProblem
import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.codecs.ProjectCodec
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.kootstrap.FooBarCompiler
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.DeploymentOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import org.jetbrains.kotlin.backend.common.push
import java.io.File
import java.util.concurrent.TimeUnit

class Coordinator: CoroutineVerticle() {

    private val eb = vertx.eventBus()

    override suspend fun start() {
        localPreparations()
        registerCodecs()
        establishConsumers()
        createServer()
        deployMutators()
        deployBugManager()
        log.debug("Coordinator deployed")
    }

    private fun createServer() {
        // TODO: should make it suspend. Takes a lot of time
        val router = Router.router(vertx)
        router.route("/mutation-problem")
            .consumes("application/json")
            .handler(BodyHandler.create())
            .handler { context ->
                try {
                    val input = context.body().asString()
                    log.debug("Got mutation request: $input")
                    val mutationProblem = parseMutationProblem(input)
                    sendMutationProblem(mutationProblem)
                    context.request().response()
                        .setStatusCode(200)
                        .send()
                } catch (e: Exception) {
                    log.debug(e.message)
                    context.request().response()
                        .setStatusCode(400)
                        .setStatusMessage("An error occurred: ${e.message}")
                        .send()
                }
            }
        router.route("/filter-results")
            .handler(BodyHandler.create())
            .handler { context ->
                try {
                    ResultsFilter.filter()
                    context.request().response()
                        .setStatusCode(200)
                        .send()
                } catch (e: Exception) {
                    log.debug(e.message)
                    context.request().response()
                        .setStatusCode(400)
                        .setStatusMessage("An error occurred: ${e.message}")
                        .send()
                }
            }

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8888)
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }

    private fun sendMutationProblem(mutationProblem: MutationProblem) {
        vertx.eventBus().send(VertxAddresses.mutationProblemExec, mutationProblem)
    }

    private fun establishConsumers() {
        eb.consumer<MutationProblem>(VertxAddresses.mutationProblemExec) { msg ->
            log.debug("Consumer got parsed MutationProblem")
            val mutationProblem = msg.body()
            val strategy = mutationProblem.createMutationStrategy()
            log.debug("Created mutation strategy: $strategy")
            strategiesMap[strategy.number] = strategy
            sendStrategyAndMutate(strategy)
        }

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { result ->
            log.debug("Got compilation result")
            val compileResult = result.body()
            if (compileResult.invokeStatus.hasCompilerCrash()) {
                log.debug("Found some bug")
                log.debug("Bug recreated by: ${compileResult.project.logInfo}")
            }
            compilationResultsProcessor.processCompilationResult(compileResult)
            if (compilationResultsProcessor.shouldSendToBugManager(compileResult.project)) {
                log.debug("Sending results to BugManager")
                vertx.eventBus().send(VertxAddresses.bugManager,
                    compilationResultsProcessor.getCompilationResults(compileResult.project))
                compilationResultsProcessor.removeProject(compileResult.project)
            }
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutatedProject = result.body()
            log.debug("Got mutationResult, " +
                    "mutated by strategy#${mutatedProject.strategyNumber} " +
                    "${mutatedProject.usefulTransformations.size} times")
            sendProjectToCompilers(mutatedProject)
            if (mutatedProject.isFinal) {
                log.debug("Got completed mutation result by strategy#${mutatedProject.strategyNumber}")
                val strategy = strategiesMap[mutatedProject.strategyNumber]!!
                FooBarCompiler.tearDownMyEnv(strategy.project.env)
                val mutationProblem = strategiesMap.remove(strategy.number)!!.mutationProblem
                if (mutationProblem.repeatInf)
                    sendMutationProblem(mutationProblem)
            }
        }
    }

    private fun sendStrategyAndMutate(strategy: MutationStrategy) {
        log.debug("Sending strategy#${strategy.number}")
        eb.send(VertxAddresses.mutate, strategy)
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        if (mutationResult.project in checkedProjects) {
            log.debug("Received project has already been compiled, going back")
            return
        }

        checkedProjects.add(mutationResult.project)
        log.debug("Sending project to compiler after/while mutating by strategy#${mutationResult.strategyNumber}")
        strategiesMap[mutationResult.strategyNumber]!!.mutationProblem.compilers.forEach { address ->
            CommonCompiler.compilerToConfigMap[address]?.forEach { config ->
                val projectMessage = mutationResult.project.getProjectMessage(
                    mutationResult.logInfo(),
                    config
                )
                compilationResultsProcessor.increaseCounter(projectMessage)
                eb.send(address, projectMessage)
            }
        }
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        val mutator = Mutator()
        vertx.deployVerticle(mutator,
            DeploymentOptions().setWorker(true)
                .setWorkerPoolName("mutators-pool")
                .setMaxWorkerExecuteTimeUnit(TimeUnit.DAYS)
                .setMaxWorkerExecuteTime(1L)
        ) { res ->
            if (res.failed()) {
                log.debug("Deployment of mutators failed with exception: ${res.cause().stackTraceToString()}")
                error("Mutator wasn't deployed")
            }
        }
        log.debug("Mutators deployed")
    }

    private fun deployBugManager() {
        vertx.deployVerticle(BugManager(), DeploymentOptions().setWorker(true))
    }

    private fun registerCodecs() {
        eb.registerDefaultCodec(MutationProblem::class.java, MutationProblemCodec())
        eb.registerDefaultCodec(MutationStrategy::class.java, MutationStrategyCodec())
        eb.registerDefaultCodec(MutationResult::class.java, MutationResultCodec())
        eb.registerDefaultCodec(CompilationResult::class.java, CompilationResultCodec())
        eb.registerDefaultCodec(ProjectMessage::class.java, ProjectCodec())
        eb.registerDefaultCodec(Bug::class.java, BugCodec())
    }

    private fun localPreparations() {
        File(CompilerArgs.pathToMutatedDir).deleteRecursively()
    }

    private val strategiesMap = mutableMapOf<Int, MutationStrategy>()
    private val compilationResultsProcessor = CompilationResultsProcessor()
    private val checkedProjects = mutableSetOf<Project>()

    private val log = Logger.getLogger("coordinatorLogger")
}