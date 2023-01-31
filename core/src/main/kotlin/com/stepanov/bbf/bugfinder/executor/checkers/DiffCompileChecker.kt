package com.stepanov.bbf.bugfinder.executor.checkers

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.project.BBFFile
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.BugType

class DiffCompileChecker(
    override val project: Project,
    override var curFile: BBFFile,
    private val compilers: List<String>
) : MultiCompilerCrashChecker(project, curFile, null, BugType.DIFFBEHAVIOR) {

    override fun checkTest(): Boolean {
        val preCheck = isAlreadyCheckedOrWrong()
        if (preCheck.first) return preCheck.second
        if (compilers.any { TODO() }) {
            alreadyChecked[projectHash] = false
            return false
        }
        val res = isDiffCompile()
        alreadyChecked[projectHash] = res
        return res
    }

    private fun isDiffCompile(): Boolean = isDiffCompile(project)

    private fun isDiffCompile(project: Project): Boolean {
        val compilersToStatus = compilers.map { TODO() }
        return compilersToStatus.map { TODO() }.toSet().size != 1
    }

    @Deprecated("")
    override fun checkTest(text: String): Boolean {
        val tmpProject = Project.createFromCode(text)
        tmpProject.configuration = project.configuration
        val projectHash = text.trim().hashCode()
        val preCheck = isAlreadyCheckedOrWrong(projectHash, tmpProject.files.first())
        if (preCheck.first) return preCheck.second
        val res = isDiffCompile(tmpProject)
        alreadyChecked[projectHash] = res
        return res
    }
}