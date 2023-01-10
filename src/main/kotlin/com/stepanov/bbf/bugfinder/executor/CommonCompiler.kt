package com.stepanov.bbf.bugfinder.executor

import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.util.Stream
import com.stepanov.bbf.bugfinder.vertx.CompileRequestMessage
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
import com.stepanov.bbf.reduktor.util.MsgCollector
import io.vertx.core.AbstractVerticle
import kotlinx.serialization.Serializable
import org.apache.commons.exec.*
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
import java.io.ByteArrayOutputStream

enum class COMPILE_STATUS {
    OK, ERROR, BUG
}

abstract class CommonCompiler: AbstractVerticle() {

    override fun start() {
        TODO()
    }

    abstract fun tryToCompile(project: Project): KotlincInvokeStatus

}

