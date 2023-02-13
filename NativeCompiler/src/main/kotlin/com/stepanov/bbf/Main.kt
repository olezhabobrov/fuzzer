package com.stepanov.bbf

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
//import org.apache.log4j.PropertyConfigurator

fun main() {
//    PropertyConfigurator.configure("log4j.properties")
    val manager = HazelcastClusterManager()
    Vertx.clusteredVertx(VertxOptions().setClusterManager(manager)) { res ->
        if (res.succeeded()) {
            val vertx = res.result()
            vertx.deployVerticle(NativeCompiler(),
                DeploymentOptions()
                    .setWorkerPoolName("NativeCompilers")
                    .setWorker(true)
            )
        } else {
            error("Failed: " + res.cause())
        }
    }
}