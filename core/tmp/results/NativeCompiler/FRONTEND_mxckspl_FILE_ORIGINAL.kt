// Bug happens on NativeCompiler ver 1.8.0

import kotlin.jvm.*
import kotlin.annotation
continue
.*
import kotlin.coroutines.*
import kotlin.experimental.*
import kotlin.contracts.*



// TARGET_BACKEND: JVM
// !LANGUAGE: +InlineClasses
// MODULE: lib
// USE_OLD_INLINE_CLASSES_MANGLING_SCHEME
// FILE: 1.kt


val b = true
if (b) {inline class IC(val s: (String)?)}
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](""), IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")
continue

 else {inline class IC(throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, continue, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, return, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")
continue
)
break
}



val o = true
if (o) {abstract class A {
    fun foo(s: String = "vhdop") = IC(s)
}} else {abstract class A {
    fun foo(s: (String)? = "vxgas") = IC(s)
}}



val x = false
if (x) {open class C : A()} else {open class C : (A)?()}



public open       const lateinit val t = false
continue

try
{class D: (C)?()}
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, return, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")

catch(e: Exception){}
finally{}


// MODULE: main(lib)
// FILE: 2.kt


val t = true
when (t) {
 true -> {fun box(): (String)? {
    var res = C().foo("OK")!!.s
    if (res != "OK") return "FAIL 1 $res"
    res = breakthrow [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("").s
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")

    return res
}}
 else -> w@{fun box(): (String)? {
    var res = C().foo("OK")!!.s
    if (res != "OK"
break
) return "FAIL 1 $res"
    res = D()!!.foo("OK")!!.s
break

    return res
}
break
}
}
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, continue]("")




// STACKTRACE:
// org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression in (24,8) in /home/olezhka/fuzzer/core/projectTmp/inlineClassFakeOverrideMangling0.kt
// 
// Attachments:
// causeThrowable
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
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.computeExtraDescriptors(LazyClassMemberScope.kt:117)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.doDescriptors(LazyClassMemberScope.kt:72)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.access$doDescriptors(LazyClassMemberScope.kt:51)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:63)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:62)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.getContributedDescriptors(LazyClassMemberScope.kt:105)
// 	at org.jetbrains.kotlin.resolve.DescriptorUtils.getAllDescriptors(DescriptorUtils.java:599)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor.resolveMemberHeaders(LazyClassDescriptor.java:740)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.resolveAllHeadersInClasses(LazyTopDownAnalyzer.kt:241)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:214)
// 	at org.jetbrains.kotlin.types.expressions.LocalClassifierAnalyzer.processClassOrObject(LocalClassifierAnalyzer.kt:120)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:170)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtClass.accept(KtClass.kt:22)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:342)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:207)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:184)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:571)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:79)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:179)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:166)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1049)
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
// 	at com.stepanov.bbf.NativeCompiler.tryToCompile$lambda$0(NativeCompiler.kt:25)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// inline class IC(throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, continue, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, return, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:261)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:225)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:342)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:207)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:184)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:571)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:79)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:179)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:166)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1049)
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
// 	at com.stepanov.bbf.NativeCompiler.tryToCompile$lambda$0(NativeCompiler.kt:25)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// Caused by: java.lang.IllegalArgumentException: Some properties have the same names
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
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.computeExtraDescriptors(LazyClassMemberScope.kt:117)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.doDescriptors(LazyClassMemberScope.kt:72)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.access$doDescriptors(LazyClassMemberScope.kt:51)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:63)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:62)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.getContributedDescriptors(LazyClassMemberScope.kt:105)
// 	at org.jetbrains.kotlin.resolve.DescriptorUtils.getAllDescriptors(DescriptorUtils.java:599)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor.resolveMemberHeaders(LazyClassDescriptor.java:740)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.resolveAllHeadersInClasses(LazyTopDownAnalyzer.kt:241)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:214)
// 	at org.jetbrains.kotlin.types.expressions.LocalClassifierAnalyzer.processClassOrObject(LocalClassifierAnalyzer.kt:120)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:170)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtClass.accept(KtClass.kt:22)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	... 43 more
// Compilation failed: Exception while analyzing expression in (24,8) in /home/olezhka/fuzzer/core/projectTmp/inlineClassFakeOverrideMangling0.kt
// 
// Attachments:
// causeThrowable
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
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.computeExtraDescriptors(LazyClassMemberScope.kt:117)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.doDescriptors(LazyClassMemberScope.kt:72)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.access$doDescriptors(LazyClassMemberScope.kt:51)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:63)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope$allDescriptors$1.invoke(LazyClassMemberScope.kt:62)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedLazyValue.invoke(LockBasedStorageManager.java:408)
// 	at org.jetbrains.kotlin.storage.LockBasedStorageManager$LockBasedNotNullLazyValue.invoke(LockBasedStorageManager.java:527)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassMemberScope.getContributedDescriptors(LazyClassMemberScope.kt:105)
// 	at org.jetbrains.kotlin.resolve.DescriptorUtils.getAllDescriptors(DescriptorUtils.java:599)
// 	at org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor.resolveMemberHeaders(LazyClassDescriptor.java:740)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.resolveAllHeadersInClasses(LazyTopDownAnalyzer.kt:241)
// 	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:214)
// 	at org.jetbrains.kotlin.types.expressions.LocalClassifierAnalyzer.processClassOrObject(LocalClassifierAnalyzer.kt:120)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:170)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitClass(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtClass.accept(KtClass.kt:22)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:342)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:207)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:184)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:571)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:79)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:179)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:166)
// 	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:1049)
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
// 	at com.stepanov.bbf.NativeCompiler.tryToCompile$lambda$0(NativeCompiler.kt:25)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// inline class IC(throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, continue, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, return, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](
// 
//  * Source files: inlineClassFakeOverrideMangling0.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 