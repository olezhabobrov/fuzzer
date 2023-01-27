package com.stepanov.bbf.bugfinder.vertx

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
//            vertx.exceptionHandler(GlobalExceptionHandler)
            val coordinator = Coordinator()
            vertx.deployVerticle(coordinator).onFailure(GlobalExceptionHandler)
        } else {

            println("Failed: " + res.cause())
        }
    }
}

object GlobalExceptionHandler : Handler<Throwable> {
    override fun handle(event: Throwable) {
        event.printStackTrace()
    }
}