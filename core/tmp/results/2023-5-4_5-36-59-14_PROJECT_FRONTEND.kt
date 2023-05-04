//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable221430767076, -o, projectTmp/rangeCheckOnSubjectVariable1-546978231, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable221430767076, projectTmp/rangeCheckOnSubjectVariable22.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable221430767076, -o, projectTmp/rangeCheckOnSubjectVariable1-546978231, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable221430767076, projectTmp/rangeCheckOnSubjectVariable22.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable221430767076, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable1-546978231, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable221430767076, projectTmp/rangeCheckOnSubjectVariable22.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable221430767076, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable1-546978231, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable221430767076, projectTmp/rangeCheckOnSubjectVariable22.kt]

// files
// rangeCheckOnSubjectVariable1.kt
// isKlib=false
private abstract class Ccw  (val dwd: Triple<String, HashMap<Function2<String, UByte, UShort>, UByte>?, Byte>, val eyl: Double){


fun <T, S: List<Double?>>  kxskk(a: UInt?, b: S, c: T): Double = TODO()

abstract var nbnu: @ParameterName("jszyu")
UShort

abstract var tzha: ULong

internal var rpqe: Float  
get() = TODO()
set(value) = TODO()




}




// !LANGUAGE: +VariableDeclarationInWhenSubject


// rangeCheckOnSubjectVariable21.kt
// isKlib=false




fun box(y =
    when (val ) = x) {
        in 0..2 -> ("OK")
        else -> "Fail: $y"
    }


// rangeCheckOnSubjectVariable22.kt
// isKlib=true
import kotlin.annotation.*




val x = 1



// STACKTRACE:
// org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression in (6,5) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/rangeCheckOnSubjectVariable21.kt
// 
// Attachments:
// causeThrowable
// java.lang.NullPointerException
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$Subject$Variable.makeValueArgument(PatternMatchingTypingVisitor.kt:149)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$checkWhenCondition$1.visitWhenConditionInRange(PatternMatchingTypingVisitor.kt:509)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:1015)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtWhenConditionInRange.accept(KtWhenConditionInRange.java:52)
// 	at org.jetbrains.kotlin.psi.KtElementImpl.accept(KtElementImpl.java:51)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.checkWhenCondition(PatternMatchingTypingVisitor.kt:498)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeWhenEntryConditions(PatternMatchingTypingVisitor.kt:480)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeConditionsInWhenEntries(PatternMatchingTypingVisitor.kt:351)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:223)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:91)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:334)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForDeclarations.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:47)
// 	at org.jetbrains.kotlin.psi.KtWhenExpression.accept(KtWhenExpression.java:50)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeInfo(ExpressionTypingServices.java:126)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveDefaultValue(ValueParameterResolver.kt:74)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveValueParameters(ValueParameterResolver.kt:62)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1016)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:992)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBodies(BodyResolver.java:977)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:129)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBodies(BodyResolver.java:256)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// when (val ) = x) {
//         in 0..2 -> ("OK")
//         else -> "Fail: $y"
//     }
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:261)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:225)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeInfo(ExpressionTypingServices.java:126)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveDefaultValue(ValueParameterResolver.kt:74)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveValueParameters(ValueParameterResolver.kt:62)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1016)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:992)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBodies(BodyResolver.java:977)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:129)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBodies(BodyResolver.java:256)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// Caused by: java.lang.NullPointerException
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$Subject$Variable.makeValueArgument(PatternMatchingTypingVisitor.kt:149)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$checkWhenCondition$1.visitWhenConditionInRange(PatternMatchingTypingVisitor.kt:509)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:1015)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtWhenConditionInRange.accept(KtWhenConditionInRange.java:52)
// 	at org.jetbrains.kotlin.psi.KtElementImpl.accept(KtElementImpl.java:51)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.checkWhenCondition(PatternMatchingTypingVisitor.kt:498)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeWhenEntryConditions(PatternMatchingTypingVisitor.kt:480)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeConditionsInWhenEntries(PatternMatchingTypingVisitor.kt:351)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:223)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:91)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:334)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForDeclarations.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:47)
// 	at org.jetbrains.kotlin.psi.KtWhenExpression.accept(KtWhenExpression.java:50)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	... 34 more
// Compilation failed: Exception while analyzing expression in (6,5) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/rangeCheckOnSubjectVariable21.kt
// 
// Attachments:
// causeThrowable
// java.lang.NullPointerException
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$Subject$Variable.makeValueArgument(PatternMatchingTypingVisitor.kt:149)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor$checkWhenCondition$1.visitWhenConditionInRange(PatternMatchingTypingVisitor.kt:509)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:1015)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitWhenConditionInRange(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtWhenConditionInRange.accept(KtWhenConditionInRange.java:52)
// 	at org.jetbrains.kotlin.psi.KtElementImpl.accept(KtElementImpl.java:51)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.checkWhenCondition(PatternMatchingTypingVisitor.kt:498)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeWhenEntryConditions(PatternMatchingTypingVisitor.kt:480)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.analyzeConditionsInWhenEntries(PatternMatchingTypingVisitor.kt:351)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:223)
// 	at org.jetbrains.kotlin.types.expressions.PatternMatchingTypingVisitor.visitWhenExpression(PatternMatchingTypingVisitor.kt:91)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:334)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForDeclarations.visitWhenExpression(ExpressionTypingVisitorDispatcher.java:47)
// 	at org.jetbrains.kotlin.psi.KtWhenExpression.accept(KtWhenExpression.java:50)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeInfo(ExpressionTypingServices.java:126)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveDefaultValue(ValueParameterResolver.kt:74)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveValueParameters(ValueParameterResolver.kt:62)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1016)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:992)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBodies(BodyResolver.java:977)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:129)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBodies(BodyResolver.java:256)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// when (val ) = x) {
//         in 0..2 -> ("OK")
//         else -> "Fail: $y"
//     }
// 
//  * Source files: rangeCheckOnSubjectVariable1.kt, rangeCheckOnSubjectVariable21.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 