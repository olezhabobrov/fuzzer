package com.stepanov.bbf

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.vertx.codecs.CompilationResultCodec
import com.stepanov.bbf.bugfinder.vertx.codecs.ProjectCodec
import com.stepanov.bbf.reduktor.executor.CompilationResult
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import org.apache.log4j.PropertyConfigurator
import java.io.File

fun main() {
    PropertyConfigurator.configure("log4j.properties")
    val manager = HazelcastClusterManager()
    Vertx.clusteredVertx(VertxOptions().setClusterManager(manager)) { res ->
        if (res.succeeded()) {
            val vertx = res.result()
            vertx.deployVerticle(JVMCompiler())
        } else {
            error("Failed: " + res.cause())
        }
    }
}