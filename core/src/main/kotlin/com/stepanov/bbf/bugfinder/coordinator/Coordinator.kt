package com.stepanov.bbf.bugfinder.coordinator

import com.stepanov.bbf.bugfinder.mutator.transformations.FTarget
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.eventbus.EventBus
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger

class Coordinator: CoroutineVerticle() {

    private lateinit var eb: EventBus
    private lateinit var transformationIterator: Iterator<Transformation>

    override suspend fun start() {
        eb = vertx.eventBus()
        establishConsumers()
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
            val projectsToSend = mutableListOf<ProjectMessage>()
            compileResult.results.forEach { status ->
                if (status.isCompileSuccess) {
                    projectsToSend.add(status.projectMessage)
                }
                if (status.hasCompilerCrash()) {
                    log.debug("Found some bug")
                    TODO()
                }
            }
            sendNextTransformation(projectsToSend, compileResult.strategyNumber)


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

    private fun sendNextTransformation(projects: List<ProjectMessage>, strategyNumber: Int) {
        if (transformationIterator.hasNext()) {
            val transformation = transformationIterator.next()
            eb.send(VertxAddresses.mutate,
                MutationRequest(transformation,
                    projects,
                    strategyNumber)
            )
        }
    }

    private fun sendProjectToCompilers(mutationResult: MutationResult) {
        log.debug("Sending project to compiler after/while mutating by strategy#${mutationResult.strategyNumber}")
        val compilers = strategiesMap[mutationResult.strategyNumber]!!.mutationProblem.compilers
        compilers.forEach { address ->
            eb.send(address,
                CompilationRequest(mutationResult.projects.toList(), mutationResult.strategyNumber)
            )
        }
    }

    private val strategiesMap = mutableMapOf<Int, MutationStrategy>()
    private val checkedProjects = mutableSetOf<ProjectMessage>()

    private val log = Logger.getLogger("coordinatorLogger")
}