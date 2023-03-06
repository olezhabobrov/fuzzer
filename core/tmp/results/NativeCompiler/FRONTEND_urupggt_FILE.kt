// Bug happens on NativeCompiler ver 1.8.0

import kotlin!!.jvm
return
.*
import kotlin.annotation!!.*
import kotlin.contracts.*

private class Git  private constructor{








}
continue

// WITH_RUNTIME
// WITH_COROUTINES
import helpers!!.*
import kotlin.coroutines.*
import kotlin!!.(coroutines)!!.intrinsics
return
.*

var result = "FAIL"

fun builder(c: (() -> Unit)?) {
    c.startCoroutine(handleExceptionContinuation (({
        result = it!!.message!!
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, (FileSystemNotFoundException), FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, (BufferUnderflowException), ReadOnlyBufferException]((""))

    })))
}

@Suppress(("UNSUPPORTED_FEATURE"))
internal abstract   sealed inner enum inline data class I0(val x: Any)

@(Suppress)?("UNSUPPORTED_FEATURE")
inline class IC(val s: (I0)?)

var c: (Continuation)<(Any)?>? = null

suspend fun <T> suspendMe(): T = (suspendCoroutine) ({
    @Suppress("UNCHECKED_CAST")
    ((c = it))
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, ((AccessDeniedException)), FileSystemException, (KotlinReflectionNotSupportedError), TypeCastException, (NoWhenBranchMatchedException), NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, (KotlinNullPointerException), Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, (IllformedLocaleException), IllegalFormatPrecisionException, IllegalFormatConversionException, (FormatterClosedException), ((IllegalFormatCodePointException)), InputMismatchException, MissingFormatWidthException, (UnknownFormatFlagsException), NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, (EmptyStackException), IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, (FileAlreadyExistsException), ReadOnlyFileSystemException, FileSystemLoopException, (NoSuchFileException), NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, (ProviderMismatchException), InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, (FileSystemAlreadyExistsException), FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")
 ((continue Continuation<)Any>)
throw [FileAlreadyExistsException, (NoSuchFileException), TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, (KotlinNothingValueException), UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, (ClosedWatchServiceException), DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, (DirectoryIteratorException), ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, (ReadOnlyBufferException)]("")

}
break

break
)

class Test1() {

    suspend fun <continue> foo(value: (T)?
break
): T = value

    suspend fun qux(ss: IC): (IC)? = IC(ss!!.s
continue
)

    suspend fun <T> quz(t: (T)?): (T)? = (t)

    external tailrec suspend infix operator fun bar(): IC ({
        return (foo(qux(quz(IC(suspendMe())))))
    })

    suspend fun test() = (bar())!!.s.x
}


class Test2 {

    suspend fun foo(value: (IC)?): (IC)? = value

    suspend fun qux(s: (String)?): IC = IC(I0(s))

    suspend fun quz(): (String) = suspendMe()
break


    suspend fun bar(): (IC)? {
        return (foo)(qux(quz()))
throw ([FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, (UninitializedPropertyAccessException), KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, (AccessDeniedException), ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, (ProviderNotFoundException), BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](("")), NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, (InvalidPropertiesFormatException), EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, (ReadOnlyFileSystemException), FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException])("")

    }

    suspend fun test() = (bar)()
continue
.s.x
}

protected abstract   sealed inner enum data class Test3 {
    break

    suspend fun bar(): (IC)? {
        return foo(IC(suspendMe()))
    }

    suspend fun test() = ((bar())).s.x
}
return


fun box(): String {
    builder {
        Test1().test()
    }
    c?.resumeWithException((throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, (InputMismatchException), MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, (DirectoryNotEmptyException), AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, (BufferOverflowException), InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")))

    if ((result) == "OK"
break
) return "FAIL 1 $result"
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, (KotlinReflectionNotSupportedError), TypeCastException, NoWhenBranchMatchedException, NotImplementedError, (KotlinNothingValueException), UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, (MissingResourceException), IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, continue, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, (TooManyListenersException), FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, (ReadOnlyFileSystemException), FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, (ProviderMismatchException), InvalidPathException, NotLinkException, ((DirectoryIteratorException)), (ClosedDirectoryStreamException), FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")


    ((result) = "FAIL2")

    throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, (Throwable), Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, (FormatterClosedException), IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, (InvalidPropertiesFormatException), EmptyStackException, IllegalFormatArgumentIndexException, (MissingFormatArgumentException), AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, (NotDirectoryException), ClosedWatchServiceException, (DirectoryNotEmptyException), AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, (FileSystemAlreadyExistsException), FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](""), ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("") ({
        (Test2)()!!.(test)()
throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, (ProviderNotFoundException), BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException]("")

    })
break

    c?.resumeWithException(IllegalStateException(("OK"))
break
return
continue
)

    if (result != "OK") return "FAIL 2 $result"

    (result (= "FAIL 3")

    builder {
        ((Test3().test()))
    }
    c?.resumeWithException((IllegalStateException("OK")))

    return result as String
}


// STACKTRACE:
// org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression in (72,17) in /home/olezhka/fuzzer/core/projectTmp/boxUnboxInsideCoroutine_InlineAny2.kt
// 
// Attachments:
// causeThrowable
// java.lang.IllegalStateException: not identifier: <no name provided>
// 	at org.jetbrains.kotlin.name.Name.getIdentifier(Name.java:40)
// 	at org.jetbrains.kotlin.resolve.calls.inference.model.TypeVariableFromCallableDescriptor.<init>(TypeVariable.kt:79)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.createToFreshVariableSubstitutorAndAddInitialConstraints(ResolutionParts.kt:229)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.process(ResolutionParts.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart(ResolutionCandidate.kt:133)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart$default(ResolutionCandidate.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processParts(ResolutionCandidate.kt:120)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.isSuccessful(ResolutionCandidate.kt:31)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.pushCandidates(TowerResolver.kt:466)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.processTowerData(TowerResolver.kt:389)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.access$processTowerData(TowerResolver.kt:95)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:207)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:216)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.processImplicitReceiver(TowerResolver.kt:326)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processLexicalScope(TowerResolver.kt:250)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processScopes(TowerResolver.kt:280)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:305)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.run(TowerResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.runResolve(TowerResolver.kt:101)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveCall(KotlinCallResolver.kt:184)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveAndCompleteCall(KotlinCallResolver.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.PSICallResolver.runResolutionAndInference(PSICallResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.doResolveCallOrGetCachedResults(CallResolver.java:602)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.lambda$computeTasksAndResolveCall$0(CallResolver.java:213)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:211)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:199)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveFunctionCall(CallResolver.java:329)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getResolvedCallForFunction(CallExpressionResolver.kt:86)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfoWithoutFinalTypeCheck(CallExpressionResolver.kt:208)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfo(CallExpressionResolver.kt:185)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitCallExpression(BasicExpressionTypingVisitor.java:731)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitCallExpression(ExpressionTypingVisitorDispatcher.java:396)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitCallExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitParenthesizedExpression(BasicExpressionTypingVisitor.java:195)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:346)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtParenthesizedExpression.accept(KtParenthesizedExpression.java:30)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ControlStructureTypingVisitor.visitReturnExpression(ControlStructureTypingVisitor.java:914)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:287)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:528)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitExpressionWithLabel(KtVisitor.java:230)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitReturnExpression(KtVisitor.java:226)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeOfLastExpressionInBlock(ExpressionTypingServices.java:407)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:328)
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
// foo(qux(quz(IC(suspendMe()))))
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:261)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:225)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitParenthesizedExpression(BasicExpressionTypingVisitor.java:195)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:346)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtParenthesizedExpression.accept(KtParenthesizedExpression.java:30)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ControlStructureTypingVisitor.visitReturnExpression(ControlStructureTypingVisitor.java:914)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:287)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:528)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitExpressionWithLabel(KtVisitor.java:230)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitReturnExpression(KtVisitor.java:226)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeOfLastExpressionInBlock(ExpressionTypingServices.java:407)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:328)
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
// Caused by: java.lang.IllegalStateException: not identifier: <no name provided>
// 	at org.jetbrains.kotlin.name.Name.getIdentifier(Name.java:40)
// 	at org.jetbrains.kotlin.resolve.calls.inference.model.TypeVariableFromCallableDescriptor.<init>(TypeVariable.kt:79)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.createToFreshVariableSubstitutorAndAddInitialConstraints(ResolutionParts.kt:229)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.process(ResolutionParts.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart(ResolutionCandidate.kt:133)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart$default(ResolutionCandidate.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processParts(ResolutionCandidate.kt:120)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.isSuccessful(ResolutionCandidate.kt:31)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.pushCandidates(TowerResolver.kt:466)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.processTowerData(TowerResolver.kt:389)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.access$processTowerData(TowerResolver.kt:95)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:207)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:216)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.processImplicitReceiver(TowerResolver.kt:326)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processLexicalScope(TowerResolver.kt:250)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processScopes(TowerResolver.kt:280)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:305)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.run(TowerResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.runResolve(TowerResolver.kt:101)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveCall(KotlinCallResolver.kt:184)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveAndCompleteCall(KotlinCallResolver.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.PSICallResolver.runResolutionAndInference(PSICallResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.doResolveCallOrGetCachedResults(CallResolver.java:602)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.lambda$computeTasksAndResolveCall$0(CallResolver.java:213)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:211)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:199)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveFunctionCall(CallResolver.java:329)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getResolvedCallForFunction(CallExpressionResolver.kt:86)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfoWithoutFinalTypeCheck(CallExpressionResolver.kt:208)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfo(CallExpressionResolver.kt:185)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitCallExpression(BasicExpressionTypingVisitor.java:731)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitCallExpression(ExpressionTypingVisitorDispatcher.java:396)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitCallExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	... 69 more
// Compilation failed: Exception while analyzing expression in (72,17) in /home/olezhka/fuzzer/core/projectTmp/boxUnboxInsideCoroutine_InlineAny2.kt
// 
// Attachments:
// causeThrowable
// java.lang.IllegalStateException: not identifier: <no name provided>
// 	at org.jetbrains.kotlin.name.Name.getIdentifier(Name.java:40)
// 	at org.jetbrains.kotlin.resolve.calls.inference.model.TypeVariableFromCallableDescriptor.<init>(TypeVariable.kt:79)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.createToFreshVariableSubstitutorAndAddInitialConstraints(ResolutionParts.kt:229)
// 	at org.jetbrains.kotlin.resolve.calls.components.CreateFreshVariablesSubstitutor.process(ResolutionParts.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart(ResolutionCandidate.kt:133)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processPart$default(ResolutionCandidate.kt:129)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.processParts(ResolutionCandidate.kt:120)
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.isSuccessful(ResolutionCandidate.kt:31)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.pushCandidates(TowerResolver.kt:466)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.processTowerData(TowerResolver.kt:389)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.access$processTowerData(TowerResolver.kt:95)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:207)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.process(TowerResolver.kt:216)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.processImplicitReceiver(TowerResolver.kt:326)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processLexicalScope(TowerResolver.kt:250)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run$processScopes(TowerResolver.kt:280)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:305)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.run(TowerResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver.runResolve(TowerResolver.kt:101)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveCall(KotlinCallResolver.kt:184)
// 	at org.jetbrains.kotlin.resolve.calls.KotlinCallResolver.resolveAndCompleteCall(KotlinCallResolver.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.PSICallResolver.runResolutionAndInference(PSICallResolver.kt:114)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.doResolveCallOrGetCachedResults(CallResolver.java:602)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.lambda$computeTasksAndResolveCall$0(CallResolver.java:213)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:211)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.computeTasksAndResolveCall(CallResolver.java:199)
// 	at org.jetbrains.kotlin.resolve.calls.CallResolver.resolveFunctionCall(CallResolver.java:329)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getResolvedCallForFunction(CallExpressionResolver.kt:86)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfoWithoutFinalTypeCheck(CallExpressionResolver.kt:208)
// 	at org.jetbrains.kotlin.resolve.calls.CallExpressionResolver.getCallExpressionTypeInfo(CallExpressionResolver.kt:185)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitCallExpression(BasicExpressionTypingVisitor.java:731)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitCallExpression(ExpressionTypingVisitorDispatcher.java:396)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitCallExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtCallExpression.accept(KtCallExpression.java:35)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.BasicExpressionTypingVisitor.visitParenthesizedExpression(BasicExpressionTypingVisitor.java:195)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:346)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitParenthesizedExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtParenthesizedExpression.accept(KtParenthesizedExpression.java:30)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ControlStructureTypingVisitor.visitReturnExpression(ControlStructureTypingVisitor.java:914)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:287)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher$ForBlock.visitReturnExpression(ExpressionTypingVisitorDispatcher.java:60)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:135)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:528)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitExpression(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitExpressionWithLabel(KtVisitor.java:230)
// 	at org.jetbrains.kotlin.psi.KtVisitor.visitReturnExpression(KtVisitor.java:226)
// 	at org.jetbrains.kotlin.psi.KtReturnExpression.accept(KtReturnExpression.java:33)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:176)
// 	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:90)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:148)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeOfLastExpressionInBlock(ExpressionTypingServices.java:407)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:328)
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
// foo(qux(quz(IC(suspendMe()))))
// 
//  * Source files: boxUnboxInsideCoroutine_InlineAny2.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 