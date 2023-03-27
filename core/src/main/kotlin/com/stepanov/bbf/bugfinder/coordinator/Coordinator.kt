package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.CommonCompiler
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.server.codecs.*
import com.stepanov.bbf.bugfinder.server.messages.CompilationResultHolder
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
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
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import java.io.File
import java.util.concurrent.TimeUnit

class Coordinator: CoroutineVerticle() {

    private lateinit var eb: EventBus
    private lateinit var transformationIterator: Iterator<Transformation>

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
            transformationIterator = strategy.transformations.iterator()
            log.debug("Created mutation strategy: $strategy")
            strategiesMap[strategy.number] = strategy
            startStrategyAndMutate(strategy)
        }

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { result ->
            log.debug("Got compilation result")
            val compileResult = result.body()
            val projectsToSend = mutableListOf<Project>()
            compileResult.results.forEach { status ->
                if (status.isCompileSuccess) {
                    projectsToSend.add(status.projectMessage)
                }
                if (status.hasCompilerCrash()) {
                    log.debug("Found some bug")
                    TODO()
                }
            }
            sendNextTransformation(projectsToSend, compileResult.)


//                vertx.eventBus().send(
//                    VertxAddresses.bugManager, CompilationResultHolder(
//                        compilationResultsProcessor.getCompilationResults(compileResult.request.mutationNumber)
//                    )
//                )
        }

        eb.consumer<MutationResult>(VertxAddresses.mutationResult) { result ->
            val mutationResult = result.body()
            log.debug("Got mutationResult with ${mutationResult.projects.size} results")
            sendProjectToCompilers(mutationResult)
            if (!transformationIterator.hasNext()) {
                log.debug("Got completed mutation result by strategy#${mutationResult.strategyNumber}")
                val strategy = strategiesMap[mutationResult.strategyNumber]!!
//                FooBarCompiler.tearDownMyEnv(strategy.project.env)
                val mutationProblem = strategiesMap.remove(strategy.number)!!.mutationProblem
                if (mutationProblem.repeatInf)
                    eb.send(VertxAddresses.mutationProblemExec, mutationProblem)
            }
        }
    }

    private fun startStrategyAndMutate(strategy: MutationStrategy) {
        log.debug("Starting strategy#${strategy.number}")
        sendNextTransformation(listOf(strategy.project), strategy.number)
    }

    private fun sendNextTransformation(projects: List<Project>, strategyNumber: Int) {
        if (transformationIterator.hasNext()) {
            val transformation = transformationIterator.next()
            eb.send(VertxAddresses.mutate,
                MutationRequest(transformation,
                    projects.map { FTarget(it, it.files.random()) },
                    strategyNumber)
            )
        }
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        log.debug("Sending project to compiler after/while mutating by strategy#${mutationResult.strategyNumber}")
        val compilers = strategiesMap[mutationResult.strategyNumber]!!.mutationProblem.compilers
        compilers.forEach { address ->
            eb.send(address,
                CompilationRequest(mutationResult.projects.toList(), mutationResult.mutationNumber)
            )
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