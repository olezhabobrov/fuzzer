//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/abstractClassMember2-191008053, projectTmp/abstractClassMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/abstractClassMember2-191008053, -o, projectTmp/abstractClassMember1-31651616, projectTmp/abstractClassMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/abstractClassMember2-191008053, projectTmp/abstractClassMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/abstractClassMember2-191008053, -Xpartial-linkage, -o, projectTmp/abstractClassMember1-31651616, projectTmp/abstractClassMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/abstractClassMember2-191008053, projectTmp/abstractClassMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/abstractClassMember2-191008053, -Xpartial-linkage, -o, projectTmp/abstractClassMember1-31651616, projectTmp/abstractClassMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/abstractClassMember2-191008053, projectTmp/abstractClassMember2.kt]

// files
// abstractClassMember1.kt
// isKlib=false




class B : A() {
    override fun foo() = "OK"
}

tailrec fun box(): @Suppress(throw [FileAlreadyExistsException, NoSuchFileException, TerminateException, AccessDeniedException, FileSystemException, KotlinReflectionNotSupportedError, TypeCastException, NoWhenBranchMatchedException, NotImplementedError, KotlinNothingValueException, UninitializedPropertyAccessException, KotlinNullPointerException, Throwable, Throwable, ConcurrentModificationException, DuplicateFormatFlagsException, ServiceConfigurationError, IllegalFormatFlagsException, MissingResourceException, IllegalFormatWidthException, IllformedLocaleException, IllegalFormatPrecisionException, IllegalFormatConversionException, FormatterClosedException, IllegalFormatCodePointException, InputMismatchException, MissingFormatWidthException, UnknownFormatFlagsException, NoSuchElementException, UnknownFormatConversionException, TooManyListenersException, FormatFlagsConversionMismatchException, IllegalFormatException, InvalidPropertiesFormatException, EmptyStackException, IllegalFormatArgumentIndexException, MissingFormatArgumentException, AtomicMoveNotSupportedException, FileAlreadyExistsException, ReadOnlyFileSystemException, FileSystemLoopException, NoSuchFileException, NotDirectoryException, ClosedWatchServiceException, DirectoryNotEmptyException, AccessDeniedException, ClosedFileSystemException, ProviderMismatchException, InvalidPathException, NotLinkException, DirectoryIteratorException, ClosedDirectoryStreamException, FileSystemAlreadyExistsException, FileSystemNotFoundException, FileSystemException, ProviderNotFoundException, BufferOverflowException, InvalidMarkException, BufferUnderflowException, ReadOnlyBufferException](""), ("ylpau"), "etbvs")
String = (A::foo)(B())


// abstractClassMember2.kt
// isKlib=true




public abstract class A {
    abstract fun foo(): String
}



// STACKTRACE:
// java.lang.OutOfMemoryError: Cannot reserve 4761324 bytes of direct buffer memory (allocated: 6226941479, limit: 6228541440)
// 	at java.base/java.nio.Bits.reserveMemory(Bits.java:178)
// 	at java.base/java.nio.DirectByteBuffer.<init>(DirectByteBuffer.java:121)
// 	at java.base/java.nio.ByteBuffer.allocateDirect(ByteBuffer.java:330)
// 	at java.base/sun.nio.ch.Util.getTemporaryDirectBuffer(Util.java:243)
// 	at java.base/sun.nio.ch.IOUtil.read(IOUtil.java:242)
// 	at java.base/sun.nio.ch.FileChannelImpl.read(FileChannelImpl.java:229)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystem.readFullyAt(ZipFileSystem.java:1231)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystem.readFullyAt(ZipFileSystem.java:1226)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystem.initCEN(ZipFileSystem.java:1539)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystem.<init>(ZipFileSystem.java:172)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystemProvider.getZipFileSystem(ZipFileSystemProvider.java:125)
// 	at jdk.zipfs/jdk.nio.zipfs.ZipFileSystemProvider.newFileSystem(ZipFileSystemProvider.java:120)
// 	at java.base/java.nio.file.FileSystems.newFileSystem(FileSystems.java:527)
// 	at java.base/java.nio.file.FileSystems.newFileSystem(FileSystems.java:399)
// 	at org.jetbrains.kotlin.com.intellij.ide.plugins.DescriptorLoadingContext.open(DescriptorLoadingContext.java:40)
// 	at org.jetbrains.kotlin.com.intellij.ide.plugins.PluginDescriptorLoader.loadDescriptorFromJar(PluginDescriptorLoader.java:84)
// 	at org.jetbrains.kotlin.com.intellij.ide.plugins.PluginManagerCore.registerExtensionPointAndExtensions(PluginManagerCore.java:1325)
// 	at org.jetbrains.kotlin.com.intellij.core.CoreApplicationEnvironment.registerExtensionPointAndExtensions(CoreApplicationEnvironment.java:287)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion.registerApplicationExtensionPointsAndExtensionsFrom(KotlinCoreEnvironment.kt:620)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion.createApplicationEnvironment(KotlinCoreEnvironment.kt:590)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion.getOrCreateApplicationEnvironment(KotlinCoreEnvironment.kt:521)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion.getOrCreateApplicationEnvironmentForProduction(KotlinCoreEnvironment.kt:502)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment$Companion.createForProduction(KotlinCoreEnvironment.kt:446)
// 	at org.jetbrains.kotlin.cli.bc.K2Native.doExecute(K2Native.kt:62)
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