package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.CommonCompiler
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.reducer.ResultsFilter
import com.stepanov.bbf.bugfinder.server.codecs.*
import com.stepanov.bbf.bugfinder.server.messages.CompilationResultHolder
import com.stepanov.bbf.bugfinder.server.messages.CompilationResultsProcessor
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.server.messages.parseMutationProblem
import com.stepanov.bbf.codecs.CompilationRequestCodec
import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.information.CompilationConfiguration
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import com.stepanov.bbf.reduktor.parser.PSICreator
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import java.io.File
import java.util.concurrent.TimeUnit

class Coordinator(
    val mutationProblem: MutationProblem
): CoroutineVerticle() {

    private lateinit var eb: EventBus

    override suspend fun start() {
        eb = vertx.eventBus()
        localPreparations()
        registerCodecs()
        establishConsumers()
        deployMutators()
        deployBugManager()
        log.debug("Coordinator deployed")
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
                log.debug("Bug recreated by: ${compileResult.request.logInfo}")
            }
            if (compilationResultsProcessor.shouldSendToBugManager(compileResult.request.mutationNumber)) {
                log.debug("Sending results to BugManager")
                vertx.eventBus().send(
                    VertxAddresses.bugManager, CompilationResultHolder(
                        compilationResultsProcessor.getCompilationResults(compileResult.request.mutationNumber)
                    )
                )
                compilationResultsProcessor.removeProject(compileResult.request.mutationNumber)
            }
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutationResult = result.body()
            log.debug("Got mutationResult with ${mutationResult.projects.size} results")
            sendProjectToCompilers(mutatedProject)
            if (mutatedProject.isFinal) {
                log.debug("Got completed mutation result by strategy#${mutatedProject.strategyNumber}")
                val strategy = strategiesMap[mutatedProject.strategyNumber]!!
//                FooBarCompiler.tearDownMyEnv(strategy.project.env)
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

        log.debug("Sending project to compiler after/while mutating by strategy#${mutationResult.strategyNumber}")
        val compilers = strategiesMap[mutationResult.strategyNumber]!!.mutationProblem.compilers
        compilers.forEach { address ->
            CommonCompiler.compilerToConfigMap[address]?.forEach config@{ config ->
                val project = getProjectMessageByConfig(mutationResult.projects, config)
                if (project == null) {
                    log.warn("Couldn't find projectMessage for configuration $config")
                    return@config
                }
                if (project in checkedProjects) {
                    log.debug("Received project has already been compiled, going back")
                    return@config
                }
                checkedProjects.add(project)

                compilationResultsProcessor.increaseCounter(mutationResult.mutationNumber)
                // TODO: achtung, it's incorrect, ask Marat how to do that
                eb.send(address,
                    CompilationRequest(project, config, mutationResult.logInfo(), mutationResult.mutationNumber)
                )
            }
        }

    }

    private fun getProjectMessageByConfig(projects: List<ProjectMessage>,
                                          config: CompilationConfiguration
    ): ProjectMessage? {
        return when (config) {
            CompilationConfiguration.Split -> projects.firstOrNull { it.isSplit }
            else -> projects.firstOrNull { !it.isSplit }
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
        eb.registerDefaultCodec(CompilationRequest::class.java, CompilationRequestCodec())
        eb.registerDefaultCodec(Bug::class.java, BugCodec())
        eb.registerDefaultCodec(CompilationResultHolder::class.java, CompilationResultHolderCodec())
    }

    private fun localPreparations() {
        File(CompilerArgs.pathToMutatedDir).deleteRecursively()
        // initialize objects. if not initialized now, would take a lot of time later!
        PSICreator.init()
        StdLibraryGenerator.init()
    }

    private val strategiesMap = mutableMapOf<Int, MutationStrategy>()
    private val checkedProjects = mutableSetOf<ProjectMessage>()

    private val log = Logger.getLogger("coordinatorLogger")
}