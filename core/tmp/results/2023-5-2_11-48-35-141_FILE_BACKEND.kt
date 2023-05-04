//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/FakeOverrideBuilder-2063650276, projectTmp/FakeOverrideBuilder.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/FakeOverrideBuilder-2063650276, projectTmp/FakeOverrideBuilder.kt]

// files
// FakeOverrideBuilder.kt
// isKlib=false
// TARGET_BACKEND: JVM
// MODULE: lib
// FILE: FakeOverrideBuilder_lib.kt

class Foo :
    Bar<Int>()

class Bar<break>

//fun <break> foo()
//
//fun box(){
//    foo()
//}




// MODULE: main(lib)
// FILE: FakeOverrideBuilder_main.kt



// STACKTRACE:
// java.lang.IllegalStateException: not identifier: <no name provided>
// 	at org.jetbrains.kotlin.name.Name.getIdentifier(Name.java:40)
// 	at org.jetbrains.kotlin.resolve.calls.inference.model.TypeVariableFromCallableDescriptor.<init>(TypeVariable.kt:79)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.createToFreshVariableSubstitutorAndAddInitialConstraints(ResolutionParts.kt:229)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.process(ResolutionParts.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart(ResolutionCandidate.kt:133)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart$default(ResolutionCandidate.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processParts(ResolutionCandidate.kt:120)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.getResultingApplicability(ResolutionCandidate.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerUtilsKt.forceResolution(TowerUtils.kt:38)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveAndCompleteGivenCandidates(KotlinCallResolver.kt:74)
// 	at org.jetbrains.kotlin.resolve.calls.tower.PSICallResolver.runResolutionAndInferenceForGivenCandidates(PSICallResolver.kt:170)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.doResolveCallOrGetCachedResults(CallResolver.java:608)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.lambda$computeTasksFromCandidatesAndResolvedCall$1(CallResolver.java:237)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksFromCandidatesAndResolvedCall(CallResolver.java:233)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksFromCandidatesAndResolvedCall(CallResolver.java:223)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveConstructorCall(CallResolver.java:422)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveCallForConstructor(CallResolver.java:407)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveFunctionCall(CallResolver.java:334)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveFunctionCall(CallResolver.java:303)
// 	at org.jetbrains.kotlin.resolve.BodyResolver$1.visitSuperTypeCallEntry(BodyResolver.java:358)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitSuperTypeCallEntry(KtVisitorVoid.java:625)
// 	at org.jetbrains.kotlin.psi.KtVisitorVoid.visitSuperTypeCallEntry(KtVisitorVoid.java:21)
// 	at org.jetbrains.kotlin.psi.KtSuperTypeCallEntry.accept(KtSuperTypeCallEntry.java:40)
// 	at org.jetbrains.kotlin.psi.KtElementImplStub.accept(KtElementImplStub.java:49)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveSuperTypeEntryList(BodyResolver.java:424)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveSuperTypeEntryLists(BodyResolver.java:269)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:121)
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
// Compilation failed: not identifier: <no name provided>
// 
//  * Source files: FakeOverrideBuilder.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 