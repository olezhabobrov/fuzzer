//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses0221886292702, -o, projectTmp/returnInParentheses0172978197, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses0221886292702, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses0221886292702, -o, projectTmp/returnInParentheses0172978197, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses0221886292702, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses0221886292702, -Xpartial-linkage, -o, projectTmp/returnInParentheses0172978197, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses0221886292702, projectTmp/returnInParentheses022.kt]
//failed with arguments: result:[-p, library, -l, projectTmp/returnInParentheses0221886292702, -Xpartial-linkage, -o, projectTmp/returnInParentheses0172978197, projectTmp/returnInParentheses01.kt, projectTmp/returnInParentheses021.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/returnInParentheses0221886292702, projectTmp/returnInParentheses022.kt]

// files
// returnInParentheses01.kt
// isKlib=false

inline private final class Etr <T>  (. ooa: Char?){


suspend   fun   ULong.ylerg(a: Long, b: T, c: T, d: Hsn): Set<Int> = TODO()

protected var ncmf: Hsn  
get() = TODO()
set(value) = TODO()


private val vmik: Hsn  
get() = TODO()




}
internal  class Hsn  (var zyb: Int, val bzt: LinkedHashMap<Int, String>, val rfm: Boolean, val xln: HashSet<Collection<Double>>){

external private  fun   Int.dmueb(a: MutableMap<Boolean, Sequence<Byte>>, b: Sequence<String>) = object {
external internal  fun <T>  higwd(a: Float, b: T, c: HashSet<Short>): Boolean = TODO()

inline    fun  jjhv(a: Char): Double = TODO()

tailrec   fun  pcpaj(a: Double, b: Triple<UByte, List<Long>, Boolean>, c: List<Float>): Char? = TODO()

private var ksqwn: UShort  = 1806330252valtoUShort()
}
internal val rjac: Int  = zyb



}




// DONT_RUN_GENERATED_CODE: JS


// returnInParentheses021.kt
// isKlib=false




fun box(): String {
    
val w = true
try
{foo(1000000)}
catch(e: Exception){}
finally{}

    return "OK"
}


// returnInParentheses022.kt
// isKlib=true




tailrec fun foo(x: Int) {
    (if (x == 0) return)
    (return foo(x - (1)))
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
//  * Source files: returnInParentheses01.kt, returnInParentheses021.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 