package com.stepanov.bbf.bugfinder.vertx

import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import org.apache.log4j.PropertyConfigurator
import java.util.concurrent.TimeUnit

fun main() {
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
    val vertx = Vertx.vertx()
    val coordinator = Coordinator()
    vertx.deployVerticle(coordinator)
}