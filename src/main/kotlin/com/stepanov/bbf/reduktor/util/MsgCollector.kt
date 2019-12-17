package com.stepanov.bbf.reduktor.util

import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity
import org.jetbrains.kotlin.cli.common.messages.MessageCollector

object MsgCollector : MessageCollector {
    var hasException = false
    var hasCompileError = false
    var crashMessages = mutableListOf<String>()
    var compileErrorMessages = mutableListOf<String>()

    override fun clear() {
        hasException = false
        hasCompileError = false
        crashMessages.clear()
        compileErrorMessages.clear()
    }

    override fun hasErrors(): Boolean {
        return hasException
    }

    override fun report(severity: CompilerMessageSeverity, message: String, location: CompilerMessageLocation?) {
        if (severity == CompilerMessageSeverity.EXCEPTION) {
            hasException = true
            crashMessages.add(message)
        }
        if (severity == CompilerMessageSeverity.ERROR) {
            compileErrorMessages.add(message)
            hasCompileError = true
        }
    }
}