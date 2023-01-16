package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.CompilerArgs
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.manager.Bug
import com.stepanov.bbf.bugfinder.manager.BugManager
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.vertx.codecs.*
import com.stepanov.bbf.bugfinder.vertx.information.VertxAddresses
import com.stepanov.bbf.reduktor.executor.CompilationResult
import io.vertx.core.AbstractVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.eventbus.EventBus
import org.apache.log4j.Logger
import java.io.File

class Coordinator: AbstractVerticle() {

    private val mutators = mutableListOf<Mutator>()
    private val compilers = mutableListOf<CommonCompiler>()
    private lateinit var eb: EventBus

    override fun start() {
        eb = vertx.eventBus()
        localPreparations()
        registerCodecs()
        establishConsumers()
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

    private fun sendStrategyAndMutate(index: Int = 0) {
//        vertx.eventBus().send(mutators[index].mutateAddress, "Some message")
        eb.send(Mutator.mutateAddress, getExampleStrategy())
    }

    private fun sendProjectToCompiler(project: Project) {
        eb.send(CommonCompiler.compileAddress, project)
    }

    private fun deployMutators() {
        // TODO: case of several mutators
        // TODO: not one random file

        val mutator = Mutator()
        mutators.add(mutator)
        vertx.deployVerticle(mutator,
            workerOptions()
        ) { res ->
            if (res.succeeded()) {
                sendStrategyAndMutate()
            } else {
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
        return MutationStrategy(listOf(ExpressionReplacer(project, project.files.first(), 1)))
    }

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

    private fun workerOptions() = DeploymentOptions().setWorker(true) // TODO: exception handling, timeouts, etc

    private val log = Logger.getLogger("coordinatorLogger")
}