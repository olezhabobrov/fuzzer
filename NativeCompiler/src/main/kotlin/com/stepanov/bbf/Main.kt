package com.stepanov.bbf

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import java.util.concurrent.TimeUnit

fun main() {
    val manager = HazelcastClusterManager()
    Vertx.clusteredVertx(VertxOptions().setClusterManager(manager)) { res ->
        if (res.succeeded()) {
            val vertx = res.result()
            vertx.deployVerticle(NativeCompiler(),
                DeploymentOptions()
                    .setWorkerPoolName("NativeCompilers")
                    .setWorker(true)
                    .setMaxWorkerExecuteTimeUnit(TimeUnit.HOURS)
                    .setMaxWorkerExecuteTime(1)
            )
        } else {
            error("Failed: " + res.cause())
        }
    }
}