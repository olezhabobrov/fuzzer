package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.Mutator
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import java.io.File
import kotlin.system.exitProcess

class Coordinator: AbstractVerticle() {

    override fun start() {
        deployMutators()
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file
        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
        vertx.deployVerticle(Mutator())
    }


}