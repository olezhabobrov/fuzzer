package com.stepanov.bbf.bugfinder.vertx

import io.vertx.core.Vertx
import org.apache.log4j.PropertyConfigurator

fun main() {
    PropertyConfigurator.configure("src/main/resources/bbfLog4j.properties")
    val vertx = Vertx.vertx()
    val coordinator = Coordinator()
    vertx.deployVerticle(coordinator)
}