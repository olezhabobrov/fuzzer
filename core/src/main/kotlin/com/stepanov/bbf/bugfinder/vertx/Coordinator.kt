package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.ExpressionReplacer
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.vertx.codecs.*
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.bugfinder.vertx.serverMessages.MutationProblem
import com.stepanov.bbf.bugfinder.vertx.serverMessages.parseMutationProblem
import com.stepanov.bbf.reduktor.executor.CompilationResult
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import java.io.File
import kotlin.reflect.full.primaryConstructor

class Coordinator: CoroutineVerticle() {

    private val mutators = mutableListOf<Mutator>()
    private val compilers = mutableListOf<CommonCompiler>()
    private lateinit var eb: EventBus

    override suspend fun start() {
//        foo()
        eb = vertx.eventBus()
        localPreparations()
        registerCodecs()
//        setExceptionHandlers()
        establishConsumers()
        createServer()
        deployMutators()
        deployCompilers()
        deployBugManager()
        log.debug("Coordinator deployed")
    }

    private fun establishConsumers() {
        eb.consumer<MutationResult>(Mutator.resultAddress) { result ->
            log.debug("Got mutation result")
            val mutatedProject = result.body()
            sendProjectToCompiler(mutatedProject.project)
        }

        eb.consumer<CompilationResult>(CommonCompiler.resultAddress) { result ->
            log.debug("Got compilation result")
            val compileResult = result.body()
            if (compileResult.invokeStatus.hasCompilerCrash()) {
                log.debug("Found some bug, sending it to BugManager")
                val bug = Bug(compileResult)
                vertx.eventBus().send(VertxAddresses.bugManager, bug)
            }
        }
    }

    private fun createServer() {
        // TODO: should make it suspend. Takes a lot of time
        val router = Router.router(vertx)
        router.route("/mutation-problem")
            .consumes("application/json")
            .handler(BodyHandler.create())
            .handler { context ->
                try {
                    val mutationProblem = parseMutationProblem(context.body().asString())
                    mutationProblem.validate()
                    val strategies = createStrategyFromMutationProblem(mutationProblem)
                    TODO("send strategies")
                } catch (e: Exception) {
                    log.debug(e.message)
                }
            }

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8888)
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }

    private fun createStrategyFromMutationProblem(mutationProblem: MutationProblem): List<MutationStrategy> {
        val result = mutableListOf<MutationStrategy>()
        mutationProblem.tasks.forEach { task ->
            val trans = task.listOfTransformations
            val project = Project.createFromCode(File(mutationProblem.projectPath + task.file).readText())
            val bbfFile = project.files.first()
            result.add(MutationStrategy(List(trans.size) { id ->
                        val tran = trans[id]
                        // TODO: wtf is that. Refactor smh!!!
                        tran.primaryConstructor!!.call(project, bbfFile, 1, 100)
                    }
                )
            )
        }
        return result
    }

    private fun sendStrategyAndMutate(index: Int = 0) {
//        vertx.eventBus().send(mutators[index].mutateAddress, "Some message")
        eb.send(Mutator.mutateAddress, getExampleStrategy())
    }

    private fun sendProjectToCompiler(project: Project) {
        eb.send(CommonCompiler.compileAddress, project)
    }

    private suspend fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file
        val mutator = Mutator()
        mutators.add(mutator)

//        val res = awaitResult<String> {
//            vertx.deployVerticle(mutator,
//                workerOptions().setMaxWorkerExecuteTime(10L)
//            )
//        }

        vertx.deployVerticle(mutator,
            workerOptions().setWorkerPoolName("my-super-awesome-worker-pool") //.setMaxWorkerExecuteTime(10L)
        ) { res ->
            if (res.succeeded()) {
//                sendStrategyAndMutate()
            } else {
                log.debug("Deployment of mutators failed with exception: ${res.cause().stackTraceToString()}")
                error("Mutator wasn't deployed")
            }
        }
        log.debug("Mutators deployed")
    }

    private fun deployCompilers() {
        // TODO: case of several mutators

        val compiler = JVMCompiler()
        compilers.add(compiler)
        vertx.deployVerticle(compiler,
            workerOptions()
        ) { res ->
            if (res.succeeded()) {
                log.debug("Compilers deployed")
            } else {
                log.debug("Deployment of compilers failed with exception: ${res.cause().stackTraceToString()}")
                error("Compiler wasn't deployed")
            }
        }
        log.debug("Compilers deployed")
    }

    private fun deployBugManager() {
        vertx.deployVerticle(BugManager(), workerOptions())
    }
    private fun getExampleStrategy(): MutationStrategy {
        // TODO: create strategy from smth
//        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
        val project = Project.createFromCode(File("./tmp/arrays/MultiDeclForComponentMemberExtensions1.kt").readText())
        return MutationStrategy(listOf(ExpressionReplacer(project, project.files.first(), 100)))
    }

    private fun getRandomStrategy(): Nothing = TODO()

    private fun registerCodecs() {
        eb.registerDefaultCodec(MutationStrategy::class.java, MutationStrategyCodec())
        eb.registerDefaultCodec(MutationResult::class.java, MutationResultCodec())
        eb.registerDefaultCodec(CompilationResult::class.java, CompilationResultCodec())
        eb.registerDefaultCodec(Project::class.java, ProjectCodec())
        eb.registerDefaultCodec(Bug::class.java, BugCodec())
    }

    private fun localPreparations() {
        File(CompilerArgs.pathToMutatedDir).deleteRecursively()
    }

    private fun setExceptionHandlers() {
        vertx.exceptionHandler { throwable ->
            log.debug("Caught throwable: ${throwable.stackTraceToString()}")
        }
    }

    private fun workerOptions() = DeploymentOptions().setWorker(true) // TODO: exception handling, timeouts, etc

    private val log = Logger.getLogger("coordinatorLogger")
}
