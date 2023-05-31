//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses022-78047521, -o, projectTmp/returnInParentheses01-1102318987, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses022-78047521, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses022-78047521, -o, projectTmp/returnInParentheses01-1102318987, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses022-78047521, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses022-78047521, -Xpartial-linkage, -o, projectTmp/returnInParentheses01-1102318987, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses022-78047521, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses022-78047521, -Xpartial-linkage, -o, projectTmp/returnInParentheses01-1102318987, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses022-78047521, projectTmp/returnInParentheses022.kt]

// files
// returnInParentheses01.kt
// isKlib=false

val foo: Int = (:)





// DONT_RUN_GENERATED_CODE: JS


// returnInParentheses021.kt
// isKlib=false






// returnInParentheses022.kt
// isKlib=true



// STACKTRACE:
// java.lang.NullPointerException
// 	at org.jetbrains.kotlin.resolve.calls.checkers.NewSchemeOfIntegerOperatorResolutionChecker.checkArgument(NewSchemeOfIntegerOperatorResolutionChecker.kt:64)
// 	at org.jetbrains.kotlin.resolve.VariableTypeAndInitializerResolver$setConstantForVariableIfNeeded$1$1.invoke(VariableTypeAndInitializerResolver.kt:131)
// 	at org.jetbrains.kotlin.resolve.VariableTypeAndInitializerResolver$setConstantForVariableIfNeeded$1$1.invoke(VariableTypeAndInitializerResolver.kt:121)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.descriptors.impl.VariableDescriptorWithInitializerImpl.getCompileTimeInitializer(VariableDescriptorWithInitializerImpl.java:58)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolvePropertyInitializer(BodyResolver.java:950)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveProperty(BodyResolver.java:807)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolvePropertyDeclarationBodies(BodyResolver.java:845)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:123)
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
//  * Source files: returnInParentheses01.kt, returnInParentheses021.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 