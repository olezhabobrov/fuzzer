package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.vertx.codecs.MutationResultCodec
import com.stepanov.bbf.bugfinder.vertx.codecs.MutationStrategyCodec
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import java.io.File

class Coordinator: AbstractVerticle() {

    private val mutators = mutableListOf<Mutator>()

    override fun start() {
        registerCodecs()
        establishConsumers()
        deployMutators()
        // TODO: deploy compilers
    }

    private fun establishConsumers() {
        vertx.eventBus().consumer<MutationResult>(Mutator.resultAddress) { result ->
            val mutatedProject = result.body()
            // TODO: do smth
        }
    }

    private fun sendStrategyAndMutate(index: Int = 0) {
//        vertx.eventBus().send(mutators[index].mutateAddress, "Some message")
        vertx.eventBus().send(mutators[index].mutateAddress, getStrategy())
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file

        val mutator = Mutator()
        mutators.add(mutator)
        vertx.deployVerticle(mutator,
            DeploymentOptions().setWorker(true)
        ) { res ->
            if (res.succeeded()) {
                println("Deployed")
                sendStrategyAndMutate()
            } else {
                error("Mutator wasn't deployed")
            }

        }
    }

    private fun getStrategy(): MutationStrategy {
        // TODO: create strategy from smth
//        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
        val project = Project.createFromCode(File("/home/olezhka/fuzzer/tmp/arrays/MultiDeclForComponentMemberExtensions1.kt").readText())
        return MutationStrategy(listOf(ExpressionReplacer(project, project.files.first(), 1)))
    }

    private fun registerCodecs() {
        vertx.eventBus().registerDefaultCodec(MutationStrategy::class.java, MutationStrategyCodec())
        vertx.eventBus().registerDefaultCodec(MutationResult::class.java, MutationResultCodec())

    }

}