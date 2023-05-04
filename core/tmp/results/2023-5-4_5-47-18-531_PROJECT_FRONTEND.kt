//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable2222126201910, -o, projectTmp/rangeCheckOnSubjectVariable1-2086564366, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt, projectTmp/rangeCheckOnSubjectVariable221.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable2222126201910, projectTmp/rangeCheckOnSubjectVariable222.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable2222126201910, -o, projectTmp/rangeCheckOnSubjectVariable1-2086564366, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt, projectTmp/rangeCheckOnSubjectVariable221.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable2222126201910, projectTmp/rangeCheckOnSubjectVariable222.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable2222126201910, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable1-2086564366, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt, projectTmp/rangeCheckOnSubjectVariable221.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable2222126201910, projectTmp/rangeCheckOnSubjectVariable222.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/rangeCheckOnSubjectVariable2222126201910, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable1-2086564366, projectTmp/rangeCheckOnSubjectVariable1.kt, projectTmp/rangeCheckOnSubjectVariable21.kt, projectTmp/rangeCheckOnSubjectVariable221.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/rangeCheckOnSubjectVariable2222126201910, projectTmp/rangeCheckOnSubjectVariable222.kt]

// files
// rangeCheckOnSubjectVariable1.kt
// isKlib=false
import kotlin.annotation.*




// !LANGUAGE: +VariableDeclarationInWhenSubject


// rangeCheckOnSubjectVariable21.kt
// isKlib=false




fun box() =
    when (val 0 = x) {
        in y..2 -> "OK"
        else -> "Fail: $y"
    }


// rangeCheckOnSubjectVariable221.kt
// isKlib=false




@OptIn(Annotation::class, Annotation::class, Annotation::class)
val x = 1


// rangeCheckOnSubjectVariable222.kt
// isKlib=true




private abstract class Hig  (var lyr: Float? = 84.159805f, val oys: MutableMap<Float, Double>, dgj: Char, neb: Float?){


fun <T>  melir(a: T, b: Function2<UByte, Byte, UShort>, c: Collection<Double>, d: Sequence<Boolean?>): Array<UByte> = TODO()

abstract var geqi: UByte



inline  private  fun  jafzx(a: List<UInt>, b: Hig?, c: Hig): Long = TODO()
inline  private  fun <reified T, reified S: CharIterator>  wttch(a: T, b: Triple<UShort, Int, Hig>, c: Long): Hig = TODO()
}



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
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:147)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBodyExpressionType(ExpressionTypingServices.java:240)
// 	at org.jetbrains.kotlin.resolve.DescriptorResolver.lambda$inferReturnTypeFromExpressionBody$5(DescriptorResolver.java:1300)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.types.DeferredType.getDelegate(DeferredType.java:108)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.computeDeferredType(BodyResolver.java:1081)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:990)
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
// when (val 0 = x) {
//         in y..2 -> "OK"
//         else -> "Fail: $y"
//     }
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:261)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:225)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:147)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBodyExpressionType(ExpressionTypingServices.java:240)
// 	at org.jetbrains.kotlin.resolve.DescriptorResolver.lambda$inferReturnTypeFromExpressionBody$5(DescriptorResolver.java:1300)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.types.DeferredType.getDelegate(DeferredType.java:108)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.computeDeferredType(BodyResolver.java:1081)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:990)
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
// 	... 37 more
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
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:147)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBodyExpressionType(ExpressionTypingServices.java:240)
// 	at org.jetbrains.kotlin.resolve.DescriptorResolver.lambda$inferReturnTypeFromExpressionBody$5(DescriptorResolver.java:1300)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.types.DeferredType.getDelegate(DeferredType.java:108)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.computeDeferredType(BodyResolver.java:1081)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:990)
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
// when (val 0 = x) {
//         in y..2 -> "OK"
//         else -> "Fail: $y"
//     }
// 
//  * Source files: rangeCheckOnSubjectVariable1.kt, rangeCheckOnSubjectVariable21.kt, rangeCheckOnSubjectVariable221.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 