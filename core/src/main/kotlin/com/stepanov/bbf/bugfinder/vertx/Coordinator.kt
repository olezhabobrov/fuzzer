package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.vertx.codecs.*
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.bugfinder.vertx.serverMessages.MutationProblem
import com.stepanov.bbf.bugfinder.vertx.serverMessages.parseMutationProblem
import com.stepanov.bbf.reduktor.executor.CompilationResult
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult
import org.apache.log4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class Coordinator: CoroutineVerticle() {

    private lateinit var eb: EventBus

    override suspend fun start() {
        eb = vertx.eventBus()
        localPreparations()
        registerCodecs()
//        setExceptionHandlers()
        establishConsumers()
        createServer()
        deployMutators()
        deployBugManager()
        log.debug("Coordinator deployed")
    }

    private fun establishConsumers() {
        eb.consumer<MutationProblem>(VertxAddresses.mutationProblemExec) { msg ->
            val mutationProblem = msg.body()
            val strategy = mutationProblem.createMutationStrategy()
            strategiesMap[strategy.number] = mutationProblem
            sendStrategyAndMutate(strategy)
        }

        eb.consumer<MutationResult>(VertxAddresses.mutatedProject) { result ->
            log.debug("Got mutation result")
            val mutatedProject = result.body()
            sendProjectToCompilers(mutatedProject.project, mutatedProject.strategyNumber)
        }

        eb.consumer<CompilationResult>(VertxAddresses.compileResult) { result ->
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
                    vertx.eventBus().send(VertxAddresses.mutationProblemExec, mutationProblem)
                    context.request().response()
                        .setStatusCode(200)
                        .send()
                } catch (e: Exception) {
                    log.debug(e.message)
                    context.request().response()
                        .setStatusCode(400)
                        .setStatusMessage("An error occured: ${e.message}")
                        .send()
                }
            }


        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8888)
            .onSuccess { server ->
                println("HTTP server started on port " + server.actualPort())
            }
    }

    private fun sendStrategyAndMutate(strategy: MutationStrategy) {
        eb.send(VertxAddresses.mutate, strategy)
    }

    private fun sendProjectToCompilers(project: Project, strategyN: Int) {
        strategiesMap[strategyN]!!.compilers.forEach { address ->
            eb.send(address, project)
        }
    }

    private suspend fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file
        val mutator = Mutator()

//        val res = awaitResult<String> {
//            vertx.deployVerticle(mutator,
//                workerOptions().setMaxWorkerExecuteTime(10L)
//            )
//        }

        vertx.deployVerticle(mutator,
            workerOptions().setWorkerPoolName("my-super-awesome-worker-pool") //.setMaxWorkerExecuteTime(10L)
        ) { res ->
            if (res.succeeded()) {
            } else {
                log.debug("Deployment of mutators failed with exception: ${res.cause().stackTraceToString()}")
                error("Mutator wasn't deployed")
            }
        }
        log.debug("Mutators deployed")
    }

    private fun deployBugManager() {
        vertx.deployVerticle(BugManager(), workerOptions())
    }

    private fun registerCodecs() {
        eb.registerDefaultCodec(MutationProblem::class.java, MutationProblemCodec())
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

    private val strategiesMap = hashMapOf<Int, MutationProblem>()

    private fun workerOptions() = DeploymentOptions().setWorker(true) // TODO: exception handling, timeouts, etc

    private val log = Logger.getLogger("coordinatorLogger")
}
