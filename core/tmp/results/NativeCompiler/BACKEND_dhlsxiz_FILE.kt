// Bug happens on NativeCompiler ver 1.8.0

fun box() {
    label@ return@label
}


// STACKTRACE:
// java.lang.NullPointerException: Cannot invoke "org.jetbrains.kotlin.com.intellij.psi.PsiElement.getParent()" because "current" is null
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.jumpCrossesTryCatchBoundary(ControlFlowProcessor.kt:923)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.returnCrossesTryCatchBoundary(ControlFlowProcessor.kt:914)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.visitReturnExpression(ControlFlowProcessor.kt:950)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitReturnExpression(KtVisitorVoid.java:727)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitReturnExpression(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.psi.KtElementImpl.accept(KtElementImpl.java:51)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.generateInstructions(ControlFlowProcessor.kt:181)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.visitLabeledExpression(ControlFlowProcessor.kt:306)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitLabeledExpression(KtVisitorVoid.java:697)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitLabeledExpression(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtLabeledExpression.accept(KtLabeledExpression.kt:29)
// 	at org.jetbrains.kotlin.psi.KtElementImpl.accept(KtElementImpl.java:51)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.generateInstructions(ControlFlowProcessor.kt:181)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.visitBlockExpression(ControlFlowProcessor.kt:1027)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitBlockExpression(KtVisitorVoid.java:865)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitBlockExpression(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:79)
// 	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:86)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor$CFPVisitor.generateInstructions(ControlFlowProcessor.kt:181)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor.generate(ControlFlowProcessor.kt:94)
// 	at org.jetbrains.kotlin.cfg.ControlFlowProcessor.generatePseudocode(ControlFlowProcessor.kt:79)
// 	at org.jetbrains.kotlin.cfg.ControlFlowInformationProviderImpl.<init>(ControlFlowInformationProviderImpl.kt:82)
// 	at org.jetbrains.kotlin.cfg.ControlFlowInformationProviderImpl$Factory.createControlFlowInformationProvider(ControlFlowInformationProviderImpl.kt:1336)
// 	at org.jetbrains.kotlin.resolve.ControlFlowAnalyzer.checkFunction(ControlFlowAnalyzer.java:126)
// 	at org.jetbrains.kotlin.resolve.ControlFlowAnalyzer.process(ControlFlowAnalyzer.java:79)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBodies(BodyResolver.java:257)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:227)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations$default(LazyTopDownAnalyzer.kt:58)
// 	at org.jetbrains.kotlin.backend.konan.TopDownAnalyzerFacadeForKonan.analyzeFilesWithGivenTrace(TopDownAnalyzerFacadeForKonan.kt:97)
// 	at org.jetbrains.kotlin.backend.konan.TopDownAnalyzerFacadeForKonan.analyzeFiles(TopDownAnalyzerFacadeForKonan.kt:58)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt$frontendPhase$1.invoke(KonanDriver.kt:123)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt$frontendPhase$1.invoke(KonanDriver.kt:122)
// 	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:115)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt.frontendPhase(KonanDriver.kt:122)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt.runTopLevelPhases(KonanDriver.kt:97)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriverKt.access$runTopLevelPhases(KonanDriver.kt:1)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriver.runTopLevelPhases(KonanDriver.kt:63)
// 	at org.jetbrains.kotlin.backend.konan.KonanDriver.run(KonanDriver.kt:43)
// 	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:92)
// 	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:38)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:101)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:47)
// 	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:101)
// 	at com.stepanov.bbf.NativeCompiler.tryToCompile$lambda$0(NativeCompiler.kt:25)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// Compilation failed: Cannot invoke "org.jetbrains.kotlin.com.intellij.psi.PsiElement.getParent()" because "current" is null
// 
//  * Source files: breakInFinally.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 
