package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import java.io.File
import kotlin.system.exitProcess

class Coordinator: AbstractVerticle() {

    private val mutators = mutableListOf<Mutator>()

    override fun start() {
        deployMutators()
        // TODO: deploy compilers
    }

    fun sendStrategyAndMutate(index: Int = 0) {
        vertx.eventBus().send(mutators[index].mutateAddress, getStrategy())
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file

        val mutator = Mutator()
        mutators.add(mutator)
        vertx.deployVerticle(mutator,
            DeploymentOptions().setWorker(true)
        )
    }

    private fun getStrategy(): MutationStrategy {
        // TODO: create strategy from smth
        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
        val project = Project.createFromCode(file.readText())
        return MutationStrategy(listOf(ExpressionReplacer(project, project.files.first(), 5)))
    }

}