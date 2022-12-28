package com.stepanov.bbf.bugfinder.vertx

import io.vertx.core.AbstractVerticle

class Coordinator: AbstractVerticle() {

    override fun start() {
        println("Hello from Coordinator")
    }

}