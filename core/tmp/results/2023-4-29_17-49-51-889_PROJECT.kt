//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/companionObjectMember2-556826924, projectTmp/companionObjectMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/companionObjectMember2-556826924, -o, projectTmp/companionObjectMember11593827186, projectTmp/companionObjectMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/companionObjectMember2-556826924, projectTmp/companionObjectMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/companionObjectMember2-556826924, -Xpartial-linkage, -o, projectTmp/companionObjectMember11593827186, projectTmp/companionObjectMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/companionObjectMember2-556826924, projectTmp/companionObjectMember2.kt]
//not failed with arguments: result:[-p, library, -l, projectTmp/companionObjectMember2-556826924, -Xpartial-linkage, -o, projectTmp/companionObjectMember11593827186, projectTmp/companionObjectMember1.kt] klib:result:[-p, library, -Xpartial-linkage, -o, projectTmp/companionObjectMember2-556826924, projectTmp/companionObjectMember2.kt]

// files
// companionObjectMember1.kt
// isKlib=false




// MODULE: lib

// FILE: A.kt

class A {
    companion object {
        fun foo() = 42
        val bar = "OK"
    }
}

// MODULE: main(lib)

fun box(): (String)? {
    if (A.foo() != 42) return "Fail foo"
    return (A)!!.bar!!
}


// companionObjectMember2.kt
// isKlib=true




// FILE: B.kt



// STACKTRACE:
// java.lang.OutOfMemoryError: Cannot reserve 4761324 bytes of direct buffer memory (allocated: 6226933776, limit: 6228541440)
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