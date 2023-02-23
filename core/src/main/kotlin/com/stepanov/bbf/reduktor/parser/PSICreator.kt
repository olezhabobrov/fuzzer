package com.stepanov.bbf.reduktor.parser

import com.intellij.openapi.extensions.ExtensionPoint
import com.intellij.openapi.extensions.Extensions
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.TreeCopyHandler
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.kootstrap.FooBarCompiler.setupMyCfg
import com.stepanov.bbf.kootstrap.FooBarCompiler.setupMyEnv
import com.stepanov.bbf.kootstrap.util.opt
import org.jetbrains.kotlin.cli.common.config.addKotlinSourceRoots
import org.jetbrains.kotlin.cli.jvm.compiler.*
import org.jetbrains.kotlin.cli.jvm.config.addJavaSourceRoots
import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoots
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.JVMConfigurationKeys
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.lazy.JvmResolveUtil
import java.io.File


@Suppress("DEPRECATION")
object PSICreator {

    fun createEnv(fileNameList: List<String>): KotlinCoreEnvironment {
        val cmd = opt.parse(arrayOf("-t", fileNameList.first()))
        val cfg = setupMyCfg(cmd)

//        cfg.put(JVMConfigurationKeys.INCLUDE_RUNTIME, true)
//        cfg.put(JVMConfigurationKeys.JDK_HOME, File(System.getProperty("java.home")))
//        cfg.addJvmClasspathRoots(
//            listOf(
//                CompilerArgs.getStdLibPath("kotlin-test"),
//                CompilerArgs.getStdLibPath("kotlin-test-common"),
//                CompilerArgs.getStdLibPath("kotlin-test-annotations-common"),
//                //CompilerArgs.getStdLibPath("kotlin-test-junit"),
//                CompilerArgs.getStdLibPath("kotlin-reflect"),
//                CompilerArgs.getStdLibPath("kotlin-stdlib-common"),
//                CompilerArgs.getStdLibPath("kotlin-stdlib"),
//                CompilerArgs.getStdLibPath("kotlin-stdlib-jdk8")
//            ).map { File(it) }
//        )
//        val kotlinSources = fileNameList.filter { it.endsWith(".kt") }
//        val javaSources = fileNameList.filter { it.endsWith(".java") }
//        cfg.addJavaSourceRoots(javaSources.map { File(it) })
//        cfg.addKotlinSourceRoots(kotlinSources)

        val env = setupMyEnv(cfg)

        if (!Extensions.getRootArea().hasExtensionPoint(TreeCopyHandler.EP_NAME.name)) {
            Extensions.getRootArea().registerExtensionPoint(
                TreeCopyHandler.EP_NAME.name,
                TreeCopyHandler::class.java.canonicalName,
                ExtensionPoint.Kind.INTERFACE
            )
        }

        env.getSourceFiles().map {
            val f = KtPsiFactory(it).createFile(it.virtualFile.path, it.text)
            f.originalFile = it
            f
        }
        return env
    }

    fun updateBindingContext(psiFile: PsiFile, env: KotlinCoreEnvironment): BindingContext {
        val configuration = env.configuration.copy()
        configuration.put(CommonConfigurationKeys.MODULE_NAME, "root")
        return if (psiFile is KtFile) {
            JvmResolveUtil.analyze(listOf(psiFile), env, configuration).bindingContext
        } else {
            JvmResolveUtil.analyze(env).bindingContext
        }
    }
}