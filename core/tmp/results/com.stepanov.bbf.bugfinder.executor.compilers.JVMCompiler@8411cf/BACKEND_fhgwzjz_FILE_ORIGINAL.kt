// Bug happens on com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler@8411cf ver 1.5.30
// FILE: tmp0.kt

class M {
  operator fun Long.component1() = (component2()).plus(-41) + 1
  operator fun Long.component2() = component1()

  fun doTest(l : ArrayList<Long>): String {
      var s = ""
      for ((a, b) in l) {
        s += "$a:$b;"
      }
      return s
  }
}

fun box(): String {
  val l = ArrayList<Long>()
  l.add(0)
  l.add(1)
  l.add(2)
  val s = (l).firstNotNullOf({a: Long -> '갟'}).uppercase(Locale.lookup(arrayOf<M>(M(), M(), M()).map<M, LanguageRange?>({a: M -> null}).reversed<LanguageRange?>(), emptyArray<Locale?>().take<Locale?>((l).max()?.countLeadingZeroBits()!!)))
  return if (s == "1:2;2:3;3:4;") "OK" else "fail: $s"
}

// STACKTRACE:
// java.util.ServiceConfigurationError: org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages$Extension: org.jetbrains.kotlin.fir.analysis.diagnostics.FirDefaultErrorMessages not a subtype
// 	at java.base/java.util.ServiceLoader.fail(ServiceLoader.java:589)
// 	at java.base/java.util.ServiceLoader$LazyClassPathLookupIterator.hasNextService(ServiceLoader.java:1236)
// 	at java.base/java.util.ServiceLoader$LazyClassPathLookupIterator.hasNext(ServiceLoader.java:1264)
// 	at java.base/java.util.ServiceLoader$2.hasNext(ServiceLoader.java:1299)
// 	at java.base/java.util.ServiceLoader$3.hasNext(ServiceLoader.java:1383)
// 	at kotlin.collections.CollectionsKt___CollectionsKt.map(_Collections.kt:3640)
// 	at org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages.<clinit>(DefaultErrorMessages.java:44)
// 	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport$Companion.reportDiagnostic(AnalyzerWithCompilerReport.kt:149)
// 	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport$Companion.reportDiagnostics(AnalyzerWithCompilerReport.kt:159)
// 	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport$Companion.reportDiagnostics(AnalyzerWithCompilerReport.kt:165)
// 	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:120)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.analyze(KotlinToJVMBytecodeCompiler.kt:243)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:90)
// 	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli$default(KotlinToJVMBytecodeCompiler.kt:56)
// 	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:169)
// 	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:52)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:92)
// 	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:44)
// 	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:98)
// 	at com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler.executeCompiler$lambda-2(JVMCompiler.kt:74)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 