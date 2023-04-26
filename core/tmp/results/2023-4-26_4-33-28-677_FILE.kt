//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/safeCallElvis31537680, projectTmp/safeCallElvis.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/safeCallElvis31537680, projectTmp/safeCallElvis.kt]

// files
// safeCallElvis.kt
// isKlib=false
// WITH_RUNTIME

import kotlin.coroutines.*

var res: String = "FAIL"

class Log {
    fun error(message: Any?) {
        res = message as String
    }
private var jhii: String  = "cnjhp"
}

private val log = Log()

class C {
    fun method() {}
lateinit var qmpw: C
}

fun <T : (Any)?> df(t: T, r: suspend (T) -> Unit) {
    return.startCoroutine(t, Continuation(EmptyCoroutineContext) {
        it!!.getOrThrow()
    })
}

fun foo(s: String, c: C?) {
    df(res) {
        
val e = true
if (e) {c?.method() ?: log!!.error(it)} else {c?.method() ?: log!!.error(it)}

    }
}

fun box(): String {
    foo("OK", null)
    return (res).plus(null)
}

// STACKTRACE:
// org.jetbrains.kotlin.backend.common.BackendException: Backend Internal error: Exception during psi2ir
// File being compiled: (23,9) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/safeCallElvis.kt
// The root cause java.lang.NullPointerException was thrown at: org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.isExported(DescriptorExportCheckerVisitor.kt:29)
// getContainingDeclaration…lDeclarationType.REGULAR) must not be null: KtPostfixExpression:
// it!!
// 	at org.jetbrains.kotlin.backend.common.CodegenUtil.reportBackendException(CodegenUtil.kt:241)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator.generateMemberDeclaration(DeclarationGenerator.kt:75)
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
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.visitClassDescriptor(DescriptorExportCheckerVisitor.kt:47)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.visitClassDescriptor(DescriptorExportCheckerVisitor.kt:14)
// 	at org.jetbrains.kotlin.descriptors.impl.AbstractClassDescriptor.accept(AbstractClassDescriptor.java:185)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.check(DescriptorExportCheckerVisitor.kt:18)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.check(DescriptorExportCheckerVisitor.kt:14)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl.isExported(DescriptorBasedKotlinManglerImpl.kt:32)
// 	at org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl.isExported(DescriptorBasedKotlinManglerImpl.kt:18)
// 	at org.jetbrains.kotlin.backend.common.serialization.signature.IdSignatureDescriptor.composeSignature(IdSignatureDescriptor.kt:175)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable$FlatSymbolTable.signature(SymbolTable.kt:221)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable$SymbolTableBase.access$signature(SymbolTable.kt:95)
// 	at org.jetbrains.kotlin.ir.util.SymbolTable.referenceClass(SymbolTable.kt:1318)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:156)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateTypeArguments(TypeTranslator.kt:312)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:163)
// 	at org.jetbrains.kotlin.ir.util.TypeTranslator.translateType(TypeTranslator.kt:90)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.toIrType(StatementGenerator.kt:66)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGeneratorExtension.toIrType(StatementGenerator.kt:562)
// 	at org.jetbrains.kotlin.psi2ir.generators.OperatorExpressionGenerator.generateExclExclOperator(OperatorExpressionGenerator.kt:517)
// 	at org.jetbrains.kotlin.psi2ir.generators.OperatorExpressionGenerator.generatePostfixExpression(OperatorExpressionGenerator.kt:89)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitPostfixExpression(StatementGenerator.kt:498)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitPostfixExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtPostfixExpression.accept(KtPostfixExpression.java:37)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatement(StatementGenerator.kt:70)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt$generateReceiver$1.load(ArgumentsGenerationUtils.kt:127)
// 	at org.jetbrains.kotlin.psi2ir.generators.CallGenerator.generateFunctionCall$lambda$25(CallGenerator.kt:330)
// 	at org.jetbrains.kotlin.psi2ir.generators.CallGenerator.generateFunctionCall$ir_psi2ir(CallGenerator.kt:339)
// 	at org.jetbrains.kotlin.psi2ir.generators.CallGenerator.generateCall(CallGenerator.kt:55)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:406)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitDotQualifiedExpression(StatementGenerator.kt:427)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitDotQualifiedExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtDotQualifiedExpression.accept(KtDotQualifiedExpression.kt:32)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatement(StatementGenerator.kt:70)
// 	at org.jetbrains.kotlin.psi2ir.generators.BodyGenerator.generateLambdaBody(BodyGenerator.kt:120)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateLambdaFunctionDeclaration(FunctionGenerator.kt:67)
// 	at org.jetbrains.kotlin.psi2ir.generators.LocalFunctionGenerator.generateLambda(LocalFunctionGenerator.kt:40)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitLambdaExpression(StatementGenerator.kt:531)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitLambdaExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtLambdaExpression.accept(KtLambdaExpression.java:40)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatement(StatementGenerator.kt:70)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateExpression(StatementGenerator.kt:83)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt$pregenerateCall$1.invoke(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt$pregenerateCall$1.invoke(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.generateValueArgumentUsing(ArgumentsGenerationUtils.kt:377)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateValueArgumentsUsing(ArgumentsGenerationUtils.kt:765)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateCallUsing(ArgumentsGenerationUtils.kt:524)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateCall(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:409)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatement(StatementGenerator.kt:70)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateExpression(StatementGenerator.kt:83)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt$pregenerateCall$1.invoke(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt$pregenerateCall$1.invoke(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.generateValueArgumentUsing(ArgumentsGenerationUtils.kt:377)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateValueArgumentsUsing(ArgumentsGenerationUtils.kt:765)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateCallUsing(ArgumentsGenerationUtils.kt:524)
// 	at org.jetbrains.kotlin.psi2ir.generators.ArgumentsGenerationUtilsKt.pregenerateCall(ArgumentsGenerationUtils.kt:514)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:409)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitCallExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitDotQualifiedExpression(StatementGenerator.kt:427)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.visitDotQualifiedExpression(StatementGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi.KtDotQualifiedExpression.accept(KtDotQualifiedExpression.kt:32)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatement(StatementGenerator.kt:70)
// 	at org.jetbrains.kotlin.psi2ir.generators.StatementGenerator.generateStatements(StatementGenerator.kt:80)
// 	at org.jetbrains.kotlin.psi2ir.generators.BodyGenerator.generateFunctionBody(BodyGenerator.kt:78)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateFunctionDeclaration(FunctionGenerator.kt:54)
// 	at org.jetbrains.kotlin.psi2ir.generators.FunctionGenerator.generateFunctionDeclaration$default(FunctionGenerator.kt:41)
// 	at org.jetbrains.kotlin.psi2ir.generators.DeclarationGenerator.generateMemberDeclaration(DeclarationGenerator.kt:49)
// 	... 28 more
// Compilation failed: Backend Internal error: Exception during psi2ir
// File being compiled: (23,9) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/safeCallElvis.kt
// The root cause java.lang.NullPointerException was thrown at: org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor.isExported(DescriptorExportCheckerVisitor.kt:29)
// getContainingDeclaration…lDeclarationType.REGULAR) must not be null: KtPostfixExpression:
// it!!
// 
//  * Source files: safeCallElvis.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 