//NativeCompiler ver 1.8.0
//failed with arguments: result:[-p, library, -o, projectTmp/kt10934-1720735735, projectTmp/kt10934.kt]
//failed with arguments: result:[-p, library, -Xpartial-linkage, -o, projectTmp/kt10934-1720735735, projectTmp/kt10934.kt]

// files
// kt10934.kt
// isKlib=false

internal class Epj <T, out S>  {


infix public  fun   UShort.lbvbp(a: UInt): Function2<String, Zcx, Short> = TODO()



}
public final class Zcx  (var tmo: Float? = 0.27483368f, vararg yys: Map<Parser<Parser<Int?, Long>, Char?>, Parser<Int?, String>>, val vpm: Function1<Result<Parser<Result<Float, Float>, Parser<UShort, Result<UByte, UByte>>>?, Array<Short>>, Parser<String, ArrayList<Result<Short?, Long>?>>>, val igm: Float = 57.803253f, var cjr: Result<Parser<List<Long>, Char>, Parser<Int, Result<Result<Long, Float?>, UShort?>>>){


external   fun <T, S>  pvrqx(a: UByte?, b: Array<ULong?>): Int

private  fun  vwdit(a: Result<Set<Parser<Function1<Array<Function1<UShort, Parser<Result<ULong, Function1<Int, Parser<Short, ULong>>>, Byte>>>, Result<UInt, Result<HashSet<UByte>, Result<UInt, Double>>>>, Parser<Long, UShort>>>, Float>, b: UByte, c: Result<Boolean?, Function2<Boolean, Long, Parser<Float, Int>>>?): Parser<Result<MutableMap<Result<Byte, String>, Result<Int, UShort>>, ULong>?, Boolean> = TODO()



tailrec private  fun  kmman(a: Parser<Result<LinkedHashMap<Short, Byte>, Double>, Char>, b: ArrayDeque<Char>): UInt = 43.toUInt()
}
//KT-10934 compiler throws UninferredParameterTypeConstructor in when block that covers all types

public class Parser<
val w = true
when (w) {
 true -> {TInput}
 else -> {TInput}
}
, TValue>(val f: (TInput) -> Result<TInput, TValue>) {

    operator fun invoke(input: TInput): Result<TInput, TValue> = f(input)

    fun <TIntermediate, TValue2> mapJoin(
            selector: (TValue) -> Parser<TInput, TIntermediate>,
            projector: (TValue, TIntermediate) -> TValue2
    ): Parser<TInput, TValue2> {
        return Parser({ input ->
                          val res = this(input)
                          when (res) {
                              is Result.ParseError -> Result.ParseError(res.productionLabel, res.child, res.rest)
                              is Result.Value -> {
                                  val v = res.value
                                  val res2 = selector(v)(res.rest)
                                  when (res2) {
                                      is Result.ParseError -> Result.ParseError(res2.productionLabel, res2.child, res2.rest)
                                      is Result.Value -> Result.Value(projector(v, res2.value), res2.rest)
                                  }
                              }
                          }
                      })
    }
}

/** A parser can return one of two Results */
sealed class Result<TInput, TValue> {

    class Value<TInput, TValue>(val value: TValue, val rest: TInput) : Result<TInput, TValue>() {}

    class ParseError<TInput, TValue>(val productionLabel: String,
                                     val child: ParseError<TInput, *>?,
                                     val rest: TInput) : Result<TInput, TValue>() {}
}

fun box() = "OK"

// STACKTRACE:
// org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression in (37,16) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/kt10934.kt
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
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.getResultingApplicability(ResolutionCandidate.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getGroupApplicability(TowerResolver.kt:490)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getFinalCandidates(TowerResolver.kt:483)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:315)
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
// 	at org.jetbrains.kotlin.types.expressions.FunctionsTypingVisitor.visitNamedFunction(FunctionsTypingVisitor.kt:103)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtNamedFunction.accept(KtNamedFunction.java:51)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// Parser({ input ->
//                           val res = this(input)
//                           when (res) {
//                               is Result.ParseError -> Result.ParseError(res.productionLabel, res.child, res.rest)
//                               is Result.Value -> {
//                                   val v = res.value
//                                   val res2 = selector(v)(res.rest)
//                                   when (res2) {
//                                       is Result.ParseError -> Result.ParseError(res2.productionLabel, res2.child, res2.rest)
//                                       is Result.Value -> Result.Value(projector(v, res2.value), res2.rest)
//                                   }
//                               }
//                           }
//                       })
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:261)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:225)
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
// 	at org.jetbrains.kotlin.types.expressions.FunctionsTypingVisitor.visitNamedFunction(FunctionsTypingVisitor.kt:103)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtNamedFunction.accept(KtNamedFunction.java:51)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
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
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.getResultingApplicability(ResolutionCandidate.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getGroupApplicability(TowerResolver.kt:490)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getFinalCandidates(TowerResolver.kt:483)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:315)
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
// 	... 82 more
// Compilation failed: Exception while analyzing expression in (37,16) in /home/olezhka/fuzzer/NativeCompiler/projectTmp/kt10934.kt
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
// 	at org.jetbrains.kotlin.resolve.calls.components.candidate.ResolutionCandidate.getResultingApplicability(ResolutionCandidate.kt:41)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getGroupApplicability(TowerResolver.kt:490)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$SuccessfulResultCollector.getFinalCandidates(TowerResolver.kt:483)
// 	at org.jetbrains.kotlin.resolve.calls.tower.TowerResolver$Task.run(TowerResolver.kt:315)
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
// 	at org.jetbrains.kotlin.types.expressions.FunctionsTypingVisitor.visitNamedFunction(FunctionsTypingVisitor.kt:103)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:165)
// 	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitNamedFunction(ExpressionTypingVisitorForStatements.java:71)
// 	at org.jetbrains.kotlin.psi.KtNamedFunction.accept(KtNamedFunction.java:51)
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
// 	at com.stepanov.bbf.NativeCompiler.compile$lambda$1(NativeCompiler.kt:40)
// 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
// 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130)
// 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630)
// 	at java.base/java.lang.Thread.run(Thread.java:831)
// 
// ----
// expression.kt
// Parser({ input ->
//                           val res = this(input)
//                           when (res) {
//                               is Result.ParseError -> Result.ParseError(res.productionLabel, res.child, res.rest)
//                               is Result.Value -> {
//                                   val v = res.value
//                                   val res2 = selector(v)(res.rest)
//                                   when (res2) {
//                                       is Result.ParseError -> Result.ParseError(res2.productionLabel, res2.child, res2.rest)
//                                       is Result.Value -> Result.Value(projector(v, res2.value), res2.rest)
//                                   }
//                               }
//                           }
//                       })
// 
//  * Source files: kt10934.kt
//  * Compiler version info: Konan: 1.8.0 / Kotlin: 1.8.0
//  * Output kind: LIBRARY
// 