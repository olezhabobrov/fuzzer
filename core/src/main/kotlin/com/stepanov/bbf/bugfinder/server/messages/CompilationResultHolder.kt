package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.messages.CompilationResult

data class CompilationResultHolder(val results: List<CompilationResult>)