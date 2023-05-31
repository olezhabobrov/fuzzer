package com.stepanov.bbf.bugfinder.server

import com.stepanov.bbf.bugfinder.coordinator.Coordinator
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.tce.StdLibraryGenerator
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationRequest
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.reducer.ResultsFilter
import com.stepanov.bbf.bugfinder.server.codecs.*
import com.stepanov.bbf.bugfinder.server.messages.CompilationResultHolder
import com.stepanov.bbf.bugfinder.server.messages.MutationProblem
import com.stepanov.bbf.bugfinder.server.messages.parseMutationProblem
import com.stepanov.bbf.bugfinder.statistics.TransformationStatistics
import com.stepanov.bbf.codecs.CompilationRequestCodec
import com.stepanov.bbf.codecs.CompilationResultCodec
import com.stepanov.bbf.information.CompilerArgs
import com.stepanov.bbf.information.VertxAddresses
import com.stepanov.bbf.messages.CompilationRequest
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.reduktor.parser.PSICreator
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.apache.log4j.Logger
import java.io.File
import java.util.concurrent.TimeUnit

class Server: CoroutineVerticle() {

    private lateinit var eb: EventBus

    override suspend fun start() {
        eb = vertx.eventBus()
        localPreparations()
        registerCodecs()
        createServer()
        establishConsumers()
        deployMutators()
        deployBugManager()
        deployStatistics()
        log.debug("Server deployed")
    }

    private fun createServer() {
        val router = Router.router(vertx)
        router.route("/mutation-problem")
            .consumes("application/json")
            .handler(BodyHandler.create())
            .handler { context ->
                try {
                    val input = context.body().asString()
                    log.debug("Got mutation request: $input")
                    val mutationProblem = parseMutationProblem(input)
                    deployCoordinator(mutationProblem)
                    context.request().response()
                        .setStatusCode(200)
                        .send()
                } catch (e: Exception) {
                    log.debug(e.message)
                    context.request().response()
                        .setStatusCode(400)
                        .setStatusMessage("An error occurred: ${e.message}")
                        .send()
                }
            }
        router.route("/filter-results")
            .handler(BodyHandler.create())
            .handler { context ->
                try {
                    ResultsFilter.filter()
                    context.request().response()
                        .setStatusCode(200)
                        .send()
                } catch (e: Exception) {
                    log.debug(e.message)
                    context.request().response()
                        .setStatusCode(400)
                        .setStatusMessage("An error occurred: ${e.message}")
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

    private fun establishConsumers() {
        eb.consumer<Int>(VertxAddresses.mutationProblemCompleted) { msg ->
            val coordinatorNumber = msg.body()
            vertx.undeploy(coordinatorIdToDeploymentId[coordinatorNumber]) { result ->
                if (result.succeeded()) {
                    log.debug("Successfully undeployed coordinator with id=$coordinatorNumber")
                } else {
                    log.debug("Couldn't undeploy coordinator with id=$coordinatorNumber")
                }
            }
        }
    }

    private fun deployCoordinator(mutationProblem: MutationProblem) {
        val coordinator = Coordinator(mutationProblem)
        vertx.deployVerticle(coordinator, DeploymentOptions().setWorker(true)
            .setWorkerPoolName("coordinators-pool")
            .setMaxWorkerExecuteTimeUnit(TimeUnit.DAYS)
            .setMaxWorkerExecuteTime(1L)) { res ->
            if (res.failed()) {
                log.debug("Deployment of coordinator failed with exception: ${res.cause().stackTraceToString()}")
                error("Coordinator wasn't deployed")
            }
            coordinatorIdToDeploymentId[coordinator.coordinatorNumber] = res.result()
        }
        log.debug("Coordinator deployed")
    }

    private fun deployMutators() {
        val mutator = Mutator()
        vertx.deployVerticle(mutator,
            DeploymentOptions().setWorker(true)
                .setWorkerPoolName("mutators-pool")
                .setMaxWorkerExecuteTimeUnit(TimeUnit.DAYS)
                .setMaxWorkerExecuteTime(1L)
        ) { res ->
            if (res.failed()) {
                log.debug("Deployment of mutators failed with exception: ${res.cause().stackTraceToString()}")
                error("Mutator wasn't deployed")
            }
        }
        log.debug("Mutators deployed")
    }

    private fun deployBugManager() {
        vertx.deployVerticle(BugManager(), DeploymentOptions().setWorker(true))
    }

    private fun deployStatistics() {
        vertx.deployVerticle(TransformationStatistics(), DeploymentOptions().setWorker(true) )
    }

    private fun registerCodecs() {
        eb.registerDefaultCodec(MutationProblem::class.java, MutationProblemCodec())
        eb.registerDefaultCodec(MutationRequest::class.java, MutationRequestCodec())
        eb.registerDefaultCodec(MutationResult::class.java, MutationResultCodec())
        eb.registerDefaultCodec(CompilationResult::class.java, CompilationResultCodec())
        eb.registerDefaultCodec(CompilationRequest::class.java, CompilationRequestCodec())
        eb.registerDefaultCodec(Bug::class.java, BugCodec())
        eb.registerDefaultCodec(CompilationResultHolder::class.java, CompilationResultHolderCodec())
    }

    private fun localPreparations() {
        File(CompilerArgs.pathToMutatedDir).deleteRecursively()
        // initialize objects. if not initialized now, would take a lot of time later!
        PSICreator.init()
        StdLibraryGenerator.init()
    }

    private val coordinatorIdToDeploymentId = mutableMapOf<Int, String>()


    private val log = Logger.getLogger("coordinatorLogger")
}