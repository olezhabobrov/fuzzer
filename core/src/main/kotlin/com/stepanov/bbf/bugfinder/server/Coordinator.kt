package com.stepanov.bbf.bugfinder.server

import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.server.codecs.BugCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationProblemCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationResultCodec
import com.stepanov.bbf.bugfinder.server.codecs.MutationStrategyCodec
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.server.messages.parseMutationProblem
import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.codecs.ProjectCodec
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import java.io.File

class Coordinator: CoroutineVerticle() {

    private lateinit var eb: EventBus

    override suspend fun start() {
        eb = vertx.eventBus()
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

//    fun <T> EventBus.consumerWithLogging(address: String, handler: Handler<Message<T>>) {
//        consumer<T>(address) {
//            // log.in
//            handler.handle(it)
//            // log.out
//        }
//    }

    private fun establishConsumers() {
        eb.consumer<MutationProblem>(VertxAddresses.mutationProblemExec) { msg ->
            log.debug("Consumer got parsed MutationProblem")
            val mutationProblem = msg.body()
            val strategy = mutationProblem.createMutationStrategy()
            log.debug("Created mutation strategy: $strategy")
            strategiesMap[strategy.number] = mutationProblem
            sendStrategyAndMutate(strategy)
        }

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { result ->
            log.debug("Got compilation result")
            val compileResult = result.body()
            if (compileResult.invokeStatus.hasCompilerCrash()) {
                log.debug("Found some bug, sending it to BugManager")
                val bug = Bug(compileResult)
                vertx.eventBus().send(VertxAddresses.bugManager, bug)
            }
        }
    }

    private fun sendStrategyAndMutate(strategy: MutationStrategy) {
        log.debug("Sending strategy#${strategy.number}")
        eb.request<MutationResult>(VertxAddresses.mutate, strategy) { result ->
            if (result.succeeded()) {
                log.debug("Got mutation result")
                val mutatedProject = result.result().body()
                sendProjectToCompilers(mutatedProject.project, mutatedProject.strategyNumber)
            } else {
                log.debug("Caught exception, while mutating strategy#${strategy.number}: ${result.cause().message}")
            }
        }
    }

    private fun sendProjectToCompilers(project: Project, strategyN: Int) {
        if (project.isSyntaxCorrect()) {
            strategiesMap[strategyN]!!.compilers.forEach { address ->
                eb.send(address, project.getProjectMessage())
            }
        } else {
            log.debug("resulted project is not syntax correct. Not sending to compilers")
        }
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        val mutator = Mutator()

//        val res = awaitResult<String> {
//            vertx.deployVerticle(mutator,
//                workerOptions().setMaxWorkerExecuteTime(10L)
//            )
//        }

        vertx.deployVerticle(mutator,
            DeploymentOptions().setWorker(true)
                .setWorkerPoolName("mutators-pool") //.setMaxWorkerExecuteTime(10L)
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

    private val strategiesMap = hashMapOf<Int, MutationProblem>()

    private val log = Logger.getLogger("coordinatorLogger")
}