// COMPILER_CRASHED
// result:[-p, library, -o, lib.klib, projectTmp/oldKlib.kt]
// result:[-p, library, -l, lib.klib, -o, main.klib, projectTmp/main.kt]
// result:[-Xinclude=main.klib, -l, lib.klib, -Xpartial-linkage-loglevel=error]

// files
// main.kt
// isKlib=false
fun main() {



val xeqar: String = box()
val ncusq: UShort = qveug<Boolean>()

}
// oldKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    val result  = (1..5).fold(0) { x, y -> x + y }

    assertEquals(15, result)

    return "OK"
}
external  fun <T>  qveug(): UShort
// newKlib.kt
// isKlib=true
// KJS_WITH_FULL_RUNTIME
// WITH_RUNTIME

import kotlin.test.assertEquals

fun box(): String {
    val result  = (1..5).fold(0) { x, y -> x + y }

    assertEquals(15, result)

    return "OK"
}
external  fun <T>  qveug(): UShort
class Zeuks


Combined output:
====================
====================
java.lang.Error: external function <root>.qveug must have @TypedIntrinsic, @SymbolName, @GCUnsafeCall or @ObjCMethod annotation
	at org.jetbrains.kotlin.backend.konan.descriptors.DescriptorUtilsKt.externalSymbolOrThrow(DescriptorUtils.kt:277)
	at org.jetbrains.kotlin.backend.konan.llvm.KonanBinaryInterface.funSymbolNameImpl(BinaryInterface.kt:62)
	at org.jetbrains.kotlin.backend.konan.llvm.KonanBinaryInterface.getSymbolName(BinaryInterface.kt:43)
	at org.jetbrains.kotlin.backend.konan.llvm.BinaryInterfaceKt.computeSymbolName(BinaryInterface.kt:125)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitFunction(LlvmDeclarations.kt:370)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitSimpleFunction(IrElementVisitorVoid.kt:175)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitSimpleFunction(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitSimpleFunction(IrElementVisitorVoid.kt:173)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitSimpleFunction(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitSimpleFunction(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.ir.declarations.IrSimpleFunction.accept(IrSimpleFunction.kt:37)
	at org.jetbrains.kotlin.ir.declarations.IrFile.acceptChildren(IrFile.kt:37)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitElement(LlvmDeclarations.kt:183)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitPackageFragment(IrElementVisitorVoid.kt:190)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitPackageFragment(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFile(IrElementVisitorVoid.kt:200)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitFile(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFile(IrElementVisitorVoid.kt:198)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitFile(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.backend.konan.llvm.DeclarationsGeneratorVisitor.visitFile(LlvmDeclarations.kt:132)
	at org.jetbrains.kotlin.ir.declarations.IrFile.accept(IrFile.kt:31)
	at org.jetbrains.kotlin.backend.konan.serialization.KonanIrModuleFragmentImpl.acceptChildren(KonanIrlinker.kt:1045)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.konan.llvm.LlvmDeclarationsKt.createLlvmDeclarations(LlvmDeclarations.kt:31)
	at org.jetbrains.kotlin.backend.konan.driver.phases.BitcodeGenerationKt$CreateLLVMDeclarationsPhase$1.invoke(BitcodeGeneration.kt:29)
	at org.jetbrains.kotlin.backend.konan.driver.phases.BitcodeGenerationKt$CreateLLVMDeclarationsPhase$1.invoke(BitcodeGeneration.kt:23)
	at org.jetbrains.kotlin.backend.konan.driver.phases.PhaseBuildersKt$createSimpleNamedCompilerPhase$3.phaseBody(PhaseBuilders.kt:54)
	at org.jetbrains.kotlin.backend.konan.driver.phases.PhaseBuildersKt$createSimpleNamedCompilerPhase$3.phaseBody(PhaseBuilders.kt:42)
	at org.jetbrains.kotlin.backend.common.phaser.SimpleNamedCompilerPhase.phaseBody(CompilerPhase.kt:207)
	at org.jetbrains.kotlin.backend.common.phaser.AbstractNamedCompilerPhase.invoke(CompilerPhase.kt:94)
	at org.jetbrains.kotlin.backend.konan.driver.PhaseEngine.runPhase(Machinery.kt:139)
	at org.jetbrains.kotlin.backend.konan.driver.PhaseEngine.runPhase$default(Machinery.kt:130)
	at org.jetbrains.kotlin.backend.konan.driver.phases.TopLevelPhasesKt.runCodegen(TopLevelPhases.kt:375)
	at org.jetbrains.kotlin.backend.konan.driver.phases.TopLevelPhasesKt.runBackendCodegen(TopLevelPhases.kt:322)
	at org.jetbrains.kotlin.backend.konan.driver.phases.TopLevelPhasesKt.compileModule(TopLevelPhases.kt:252)
	at org.jetbrains.kotlin.backend.konan.driver.phases.TopLevelPhasesKt.runBackend$lambda$9$runAfterLowerings(TopLevelPhases.kt:106)
	at org.jetbrains.kotlin.backend.konan.driver.phases.TopLevelPhasesKt.runBackend(TopLevelPhases.kt:125)
	at org.jetbrains.kotlin.backend.konan.driver.DynamicCompilerDriver.produceBinary(DynamicCompilerDriver.kt:133)
	at org.jetbrains.kotlin.backend.konan.driver.DynamicCompilerDriver.access$produceBinary(DynamicCompilerDriver.kt:31)
	at org.jetbrains.kotlin.backend.konan.driver.DynamicCompilerDriver$run$1$1$1.invoke(DynamicCompilerDriver.kt:39)
	at org.jetbrains.kotlin.backend.konan.driver.DynamicCompilerDriver$run$1$1$1.invoke(DynamicCompilerDriver.kt:36)
	at org.jetbrains.kotlin.backend.konan.driver.PhaseEngine$Companion$startTopLevel$topLevelPhase$1.phaseBody(Machinery.kt:98)
	at org.jetbrains.kotlin.backend.konan.driver.PhaseEngine$Companion$startTopLevel$topLevelPhase$1.phaseBody(Machinery.kt:92)
	at org.jetbrains.kotlin.backend.common.phaser.SimpleNamedCompilerPhase.phaseBody(CompilerPhase.kt:207)
	at org.jetbrains.kotlin.backend.common.phaser.AbstractNamedCompilerPhase.invoke(CompilerPhase.kt:94)
	at org.jetbrains.kotlin.backend.konan.driver.PhaseEngine$Companion.startTopLevel(Machinery.kt:105)
	at org.jetbrains.kotlin.backend.konan.driver.DynamicCompilerDriver.run(DynamicCompilerDriver.kt:36)
	at org.jetbrains.kotlin.backend.konan.KonanDriver.run(KonanDriver.kt:78)
	at org.jetbrains.kotlin.cli.bc.K2Native.runKonanDriver(K2Native.kt:136)
	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:71)
	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:37)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:100)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:46)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:101)
	at com.stepanov.bbf.NativeCompiler.compile$lambda$2(NativeCompiler.kt:41)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
	at java.base/java.lang.Thread.run(Thread.java:831)
Compilation failed: external function <root>.qveug must have @TypedIntrinsic, @SymbolName, @GCUnsafeCall or @ObjCMethod annotation

 * Source files: 
 * Compiler version: 1.9.0-Beta
 * Output kind: PROGRAM
