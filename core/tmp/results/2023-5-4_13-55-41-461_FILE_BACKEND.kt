//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/defaultargs1860153537, projectTmp/defaultargs.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/defaultargs1860153537, projectTmp/defaultargs.kt]

// files
// defaultargs.kt
// isKlib=false
// IGNORE_FIR_DIAGNOSTICS
open abstract class B {
    tailrec fun foo(arg: Int = (
val l = true
when (l) {
 true -> {C}
 else -> {C}
}
()).foo(C().foo(arg = 59)).minus(76)) : Int = C().foo(arg = 30)
open fun <T, S>  amaho(a: Array<Char>): Boolean = true
}

class C() : @ParameterName("pkcob")
B() {
}

fun box() : String {
    if((box()).plus("qtwux").isEmpty()) return ((C()).foo(-19)).toString()
    if(C().foo() != 240) return (box()).plus("cduax")
    return box()
}


// STACKTRACE:
// java.lang.NullPointerException
// 	at org.jetbrains.kotlin.resolve.calls.checkers.NewSchemeOfIntegerOperatorResolutionChecker.checkArgument(NewSchemeOfIntegerOperatorResolutionChecker.kt:64)
// 	at org.jetbrains.kotlin.types.expressions.ValueParameterResolver.resolveDefaultValue(ValueParameterResolver.kt:75)
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
// Compilation failed: null
// 
//  * Source files: defaultargs.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 