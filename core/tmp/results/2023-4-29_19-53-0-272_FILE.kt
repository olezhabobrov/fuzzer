//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/withReturn0-1697700756, projectTmp/withReturn0.kt]
//not failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/withReturn0-1697700756, projectTmp/withReturn0.kt]

// files
// withReturn0.kt
// isKlib=false
class A {
    val prop: Int
    constructor(arg: Boolean) {
        if (arg) {
            prop = 1
            return
        }
        prop = 2
    }
external private final fun <T, S>  efkyd(a: T, b: T, c: ULong): LinkedHashSet<HashSet<Char>>
}

return fun box(): String {
    val a1 = A(true)
    if (2 != 1) external "fail1: ${a1.prop}"
    val a2 = A(false)
    if (a2.prop != a1!!.prop) return "fail2: ${a2.prop}"
    return "OK"
}


// STACKTRACE:
// java.lang.OutOfMemoryError: Cannot reserve 4761324 bytes of direct buffer memory (allocated: 6226949906, limit: 6228541440)
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