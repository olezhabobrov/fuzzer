package com.stepanov.bbf.bugfinder.mutator.transformations.tce

import com.stepanov.bbf.bugfinder.project.BBFFile
import com.stepanov.bbf.bugfinder.project.Project
import com.stepanov.bbf.bugfinder.util.filterDuplicatesBy
import com.stepanov.bbf.reduktor.util.getAllPSIChildrenOfType
import org.jetbrains.kotlin.KtNodeTypes
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.calls.callUtil.getType
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.isError

object TCEUsagesCollector {

//    fun collectUsageCases(
//        project: Project,
//        ctx: BindingContext,
//        node: KtExpression? = null
//    ): List<Triple<KtExpression, String, KotlinType?>> =
//        project.files.flatMap { collectUsageCases(it.psiFile, ctx, node) }

    fun collectUsageCases(
        file: BBFFile,
        project: Project? = null,
        node: KtExpression? = null
    ): List<Triple<KtExpression, String, KotlinType?>> {
        val psi = file.psiFile
        val generatedSamples = UsagesSamplesGenerator.generate(file, project)
        if (node == null) return generatedSamples
        val destrDecl = psi.getAllPSIChildrenOfType<KtDestructuringDeclaration>()
            .map { Triple(it, it.text, it.initializer?.getType(file.ctx!!)) }
            .filter { it.third != null && !it.third!!.isError }
        val exprs = psi.getAllPSIChildrenOfType<KtExpression>()
            .filter { it !is KtProperty }
            .filterDuplicatesBy { it.text }
            .map { Triple(it, it.text, it.getType(file.ctx!!)) }
            .filter {
                it.third != null &&
                        !it.third!!.isError &&
                        it.first !is KtStringTemplateEntry &&
                        it.first !is KtConstantExpression &&
                        it.first.node.elementType != KtNodeTypes.STRING_TEMPLATE &&
                        !it.first.parent.text.endsWith(it.first.text)
            }
        return (destrDecl + exprs).map { Triple(it.first, it.second, it.third!!) } + generatedSamples
    }

}