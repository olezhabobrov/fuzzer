//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -l, projectTmp/kt396921635021373, -o, projectTmp/kt396911685315202, projectTmp/kt39691.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt396921635021373, projectTmp/kt39692.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/kt396921635021373, -o, projectTmp/kt396911685315202, projectTmp/kt39691.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt396921635021373, projectTmp/kt39692.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/kt396921635021373, -Xpartial-linkage, -o, projectTmp/kt396911685315202, projectTmp/kt39691.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt396921635021373, projectTmp/kt39692.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/kt396921635021373, -Xpartial-linkage, -o, projectTmp/kt396911685315202, projectTmp/kt39691.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt396921635021373, projectTmp/kt39692.kt]

// files
// kt39691.kt
// isKlib=false




private inline  final class Mnu  (val jon<T, S> Function2<Char?, Float, Boolean> = {a: Char?, b: Float -> false}){


private val sbkq: @UnsafeVariance()
A?  
get() = TODO()




fun :  rfqej(a: ArrayList<Function2<Triple<Triple<Mnu?, Mnu, UShort?>, Mnu, Mnu>, Map<Mnu, Mnu>, Function2<UShort, Mnu, Float>>>, b: LinkedHashSet<Double>): Int = 45
}


// kt39692.kt
// isKlib=true




var result = "Fail"

class A{
suspend private  fun  rzpva(a: UInt?): A = TODO()
}

operator fun A.inc(s: String = "OK"): A {
    result = s
    return this
}

fun box(): String {
    var a = A()
    a++
    if (@Suppress("bxrry")
result != "OK") return "Fail 1"

    a.inc("zhggr")
result = "Fail"
    ++a

    @Suppress("zxsri")
return result
}



// STACKTRACE:
// java.lang.IllegalArgumentException: Some properties have the same names
// 	at org.jetbrains.kotlin.descriptors.MultiFieldValueClassRepresentation.<init>(MultiFieldValueClassRepresentation.kt:16)
// 	at org.jetbrains.kotlin.descriptors.ValueClassRepresentationKt.createValueClassRepresentation(ValueClassRepresentation.kt:48)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor.getValueClassRepresentation(LazyClassDescriptor.java:676)
// 	at org.jetbrains.kotlin.resolve.InlineClassesUtilsKt.isInlineClass(inlineClassesUtils.kt:23)
// 	at org.jetbrains.kotlin.resolve.InlineClassesUtilsKt.isValueClass(inlineClassesUtils.kt:28)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.generateFunctionsFromAnyForValueClass(LazyClassMemberScope.kt:312)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.getNonDeclaredFunctions(LazyClassMemberScope.kt:299)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.AbstractLazyMemberScope.doGetFunctions(AbstractLazyMemberScope.kt:103)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.AbstractLazyMemberScope.access$doGetFunctions(AbstractLazyMemberScope.kt:38)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.AbstractLazyMemberScope$functionDescriptors$1.invoke(AbstractLazyMemberScope.kt:51)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.AbstractLazyMemberScope$functionDescriptors$1.invoke(AbstractLazyMemberScope.kt:51)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$MapBasedMemoizedFunction.invoke(LockBasedStorageManager.java:578)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull.invoke(LockBasedStorageManager.java:651)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.AbstractLazyMemberScope.getContributedFunctions(AbstractLazyMemberScope.kt:97)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.getContributedFunctions(LazyClassMemberScope.kt:280)
// 	at org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver$resolveToDescriptor$1.visitNamedFunction(LazyDeclarationResolver.kt:125)
// 	at org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver$resolveToDescriptor$1.visitNamedFunction(LazyDeclarationResolver.kt:94)
// 	at org.jetbrains.kotlin.psi.KtNamedFunction.accept(KtNamedFunction.java:51)
// 	at org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver.resolveToDescriptor(LazyDeclarationResolver.kt:94)
// 	at org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver.resolveToDescriptor(LazyDeclarationResolver.kt:91)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.createFunctionDescriptors(LazyTopDownAnalyzer.kt:284)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:206)
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
// Compilation failed: Some properties have the same names
// 
//  * Source files: kt39691.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 