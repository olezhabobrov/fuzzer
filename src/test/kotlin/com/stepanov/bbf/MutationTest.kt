package com.stepanov.bbf

import com.stepanov.bbf.bugfinder.executor.checkers.MutationChecker
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.MutationExample
import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import org.junit.Test
import java.io.File

class MutationTest {

    val pathToTestFile = "src/test/testData/myTest.kt"

    @Test
    fun checkMutation() {

    }
}