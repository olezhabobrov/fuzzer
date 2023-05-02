//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/whenNoSubject_properIeeeComparisons873175149, projectTmp/whenNoSubject_properIeeeComparisons.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/whenNoSubject_properIeeeComparisons873175149, projectTmp/whenNoSubject_properIeeeComparisons.kt]

// files
// whenNoSubject_properIeeeComparisons.kt
// isKlib=false

import kotlin.annotation.*
// !LANGUAGE: +ProperIeee754Comparisons

fun box(xcpt: HashMap<(@UseExperimental(Annotation::class)
Byte)?, LinkedHashMap<UByte?, String?>>,mzmd: Float): String {
    val plusZero: Any = @Suppress("ybsbr", "znder", "kaqvk")
0.0
    val minusZero: Any = -0.0
    if (plusZero is Double && minusZero is (Double)?) {
        when {
            plusZero < minusZero -> {
                return "fail 3"
            }

            plusZero > minusZero -> {
                return "fail 2"
            }
            else -> {}
        }


        when {
            plusZero == minusZero -> {}
            else -> {
                return "fail 1"
            }
        }
    }

    return "OK"
}

// STACKTRACE:
// org.jetbrains.kotlin.backend.common.BackendException: Backend Internal error: Exception during psi2ir
// File being compiled: /home/olezhka/fuzzer/NativeCompiler/projectTmp/whenNoSubject_properIeeeComparisons.kt
// The root cause java.lang.NullPointerException was thrown at: org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.isExported(DescriptorExportCheckerVisitor.kt:29)
// 	at org.jetbrains.kotlin.backend.common.CodegenUtil.reportBackendException(CodegenUtil.kt:241)
// 	at org.jetbrains.kotlin.backend.common.CodegenUtil.reportBackendException$default(CodegenUtil.kt:236)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator.generateMemberDeclaration(DeclarationGenerator.kt:78)
// 	at org.jetbrains.kotlin.psi2ir.generators.ModuleGenerator.generateSingleFile(ModuleGenerator.kt:67)
// 	at org.jetbrains.kotlin.psi2ir.generators.ModuleGenerator.generateModuleFragment(ModuleGenerator.kt:47)
// 	at org.jetbrains.kotlin.psi2ir.Psi2IrTranslator.generateModuleFragment(Psi2IrTranslator.kt:94)
// 	at org.jetbrains.kotlin.psi2ir.Psi2IrTranslator.generateModuleFragment$default(Psi2IrTranslator.kt:81)
// 	at org.jetbrains.kotlin.backend.konan.PsiToIrKt.psiToIr(PsiToIr.kt:202)
// 	at org.jetbrains.kotlin.backend.konan.ToplevelPhasesKt$psiToIrPhase$1.invoke(ToplevelPhases.kt:111)
// 	at org.jetbrains.kotlin.backend.konan.ToplevelPhasesKt$psiToIrPhase$1.invoke(ToplevelPhases.kt:109)
// 	at org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt$namedOpUnitPhase$1.invoke(PhaseBuilders.kt:96)
// 	at org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt$namedOpUnitPhase$1.invoke(PhaseBuilders.kt:94)
// 	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:96)
// 	at org.jetbrains.kotlin.backend.common.phaser.CompositePhase.invoke(PhaseBuilders.kt:29)
// 	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:96)
// 	at org.jetbrains.kotlin.backend.common.phaser.CompilerPhaseKt.invokeToplevel(CompilerPhase.kt:43)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt.runTopLevelPhases(KonanDriver.kt:102)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt.access$runTopLevelPhases(KonanDriver.kt:1)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriver.runTopLevelPhases(KonanDriver.kt:63)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriver.run(KonanDriver.kt:43)
// 	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:92)
// 	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:38)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:101)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:47)
// 	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:101)
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// Caused by: java.lang.NullPointerException: getContainingDeclaration…lDeclarationType.REGULAR) must not be null
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.isExported(DescriptorExportCheckerVisitor.kt:29)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.visitConstructorDescriptor(DescriptorExportCheckerVisitor.kt:56)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.visitConstructorDescriptor(DescriptorExportCheckerVisitor.kt:14)
// 	at org.jetbrains.kotlin.descriptors.impl.ClassConstructorDescriptorImpl.accept(ClassConstructorDescriptorImpl.java:135)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.check(DescriptorExportCheckerVisitor.kt:18)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.check(DescriptorExportCheckerVisitor.kt:14)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl.isExported(DescriptorBasedKotlinManglerImpl.kt:32)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl.isExported(DescriptorBasedKotlinManglerImpl.kt:18)
// 	at org.jetbrains.kotlin.backend.common.serialization.signature.IdSignatureDescriptor.composeSignature(IdSignatureDescriptor.kt:175)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable$FlatSymbolTable.signature(SymbolTable.kt:221)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable$SymbolTableBase.access$signature(SymbolTable.kt:95)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable.referenceConstructor(SymbolTable.kt:1393)
// 	at org.jetbrains.kotlin.ir.util.ConstantValueGenerator.generateAnnotationConstructorCall(ConstantValueGenerator.kt:168)
// 	at org.jetbrains.kotlin.ir.util.ConstantValueGenerator.generateAnnotationConstructorCall$default(ConstantValueGenerator.kt:155)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateTypeAnnotations(TypeTranslator.kt:264)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:155)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateTypeArguments(TypeTranslator.kt:312)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:163)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:90)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator.toIrType(DeclarationGenerator.kt:43)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGeneratorExtension.toIrType(DeclarationGenerator.kt:207)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.declareParameter(FunctionGenerator.kt:432)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateValueParameterDeclarations(FunctionGenerator.kt:342)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateValueParameterDeclarations$default(FunctionGenerator.kt:314)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateFunctionParameterDeclarationsAndReturnType(FunctionGenerator.kt:114)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateFunctionDeclaration(FunctionGenerator.kt:473)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateFunctionDeclaration$default(FunctionGenerator.kt:41)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator.generateMemberDeclaration(DeclarationGenerator.kt:49)
// 	... 28 more
// Compilation failed: Backend Internal error: Exception during psi2ir
// File being compiled: /home/olezhka/fuzzer/NativeCompiler/projectTmp/whenNoSubject_properIeeeComparisons.kt
// The root cause java.lang.NullPointerException was thrown at: org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.isExported(DescriptorExportCheckerVisitor.kt:29)
// 
//  * Source files: whenNoSubject_properIeeeComparisons.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 