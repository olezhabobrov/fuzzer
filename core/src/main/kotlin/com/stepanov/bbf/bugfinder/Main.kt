package com.stepanov.bbf.bugfinder

import com.stepanov.bbf.bugfinder.generator.targetsgenerators.typeGenerators.KotlinTypeCreator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.UsagesSamplesGenerator
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.server.Server
import com.stepanov.bbf.bugfinder.server.messages.parseMutationProblem
import com.stepanov.bbf.kootstrap.FooBarCompiler
import com.stepanov.bbf.messages.FileData
import com.stepanov.bbf.messages.ProjectMessage
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import org.apache.log4j.PropertyConfigurator

fun main() {
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
    val manager = HazelcastClusterManager()
    Vertx.clusteredVertx(VertxOptions().setClusterManager(manager)) { res ->
        if (res.succeeded()) {
            val vertx = res.result()
            vertx.exceptionHandler(GlobalExceptionHandler)
            val server = Server()
            vertx.deployVerticle(server).onFailure(GlobalExceptionHandler)
        } else {

            println("Failed: " + res.cause())
        }
    }
}

//fun main() {
//    val projectMessage = ProjectMessage(
//        listOf(
//            FileData(
//                "test.kt",
//                """
//                    fun box() {
//                    }
//
//                    class Aboba {
//
//                    }
//
//                    fun main() {
//                        val aboba = "some text"
//                    }
//                """.trimIndent()
//            )
//        )
//    )
//    repeat(1000) {
//        val project = Project(projectMessage)
////        KotlinTypeCreator.createType(project.files.first(), "String")
//        UsagesSamplesGenerator.generate(project.files.first(), project)
//        FooBarCompiler.tearDownMyEnv(project.env)
//        println(it)
//    }
//    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
//    val manager = HazelcastClusterManager()
//    Vertx.clusteredVertx(VertxOptions().setClusterManager(manager)) { res ->
//        if (res.succeeded()) {
//            val vertx = res.result()
//            vertx.exceptionHandler(GlobalExceptionHandler)
//            val server = Server()
//            vertx.deployVerticle(server).onFailure(GlobalExceptionHandler)
//        } else {
//
//            println("Failed: " + res.cause())
//        }
//    }
//}

object GlobalExceptionHandler : Handler<Throwable> {
    override fun handle(event: Throwable) {
        event.printStackTrace()
    }
}