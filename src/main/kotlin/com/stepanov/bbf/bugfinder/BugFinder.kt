package com.stepanov.bbf.bugfinder

import com.intellij.psi.PsiFile
import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import org.apache.log4j.Logger

open class BugFinder(protected val dir: String) {

    fun mutate(
        project: Project,
        curFile: BBFFile,
        compilers: List<CommonCompiler>,
        conditions: List<(PsiFile) -> Boolean> = listOf()
    ) {
        TODO()
    }

    protected val log = Logger.getLogger("bugFinderLogger")

}