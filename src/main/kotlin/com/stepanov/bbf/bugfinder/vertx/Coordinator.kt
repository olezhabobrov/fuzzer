package com.stepanov.bbf.bugfinder.vertx

import com.stepanov.bbf.bugfinder.executor.CommonCompiler
import com.stepanov.bbf.bugfinder.executor.compilers.JVMCompiler
import com.stepanov.bbf.bugfinder.executor.project.Project
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationStrategy
import com.stepanov.bbf.bugfinder.mutator.Mutator
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import com.stepanov.bbf.bugfinder.mutator.vertxMessages.MutationResult
import com.stepanov.bbf.bugfinder.vertx.codecs.KotlincInvokeStatusCodec
import com.stepanov.bbf.bugfinder.vertx.codecs.MutationResultCodec
import com.stepanov.bbf.bugfinder.vertx.codecs.MutationStrategyCodec
import com.stepanov.bbf.bugfinder.vertx.codecs.ProjectCodec
import com.stepanov.bbf.reduktor.executor.KotlincInvokeStatus
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
        registerCodecs()
        establishConsumers()
        deployMutators()
        deployCompilers()
        log.debug("Coordinator deployed")
    }

    private fun establishConsumers() {
        eb.consumer<MutationResult>(Mutator.resultAddress) { result ->
            log.debug("Got mutation result")
            val mutatedProject = result.body()
            sendProjectToCompiler(mutatedProject.project)
        }

        eb.consumer<KotlincInvokeStatus>(CommonCompiler.resultAddress) { result ->
            log.debug("Got compilation result")
            val compileResult = result.body()
            // TODO: report bugs
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
        // TODO: not one random file

        val compiler = JVMCompiler()
        compilers.add(compiler)
        vertx.deployVerticle(compiler,
            workerOptions()
        ) { res ->
            if (res.succeeded()) {
                vertx.eventBus().send(CommonCompiler.compileAddress, getExampleStrategy().project)
            } else {
                error("Compiler wasn't deployed")
            }
        }
        log.debug("Compilers deployed")
    }

    private fun getExampleStrategy(): MutationStrategy {
        // TODO: create strategy from smth
//        val file = File(CompilerArgs.baseDir).listFiles()?.filter { it.path.endsWith(".kt") }?.random() ?: exitProcess(0)
        val project = Project.createFromCode(File("/home/olezhka/fuzzer/tmp/arrays/MultiDeclForComponentMemberExtensions1.kt").readText())
        return MutationStrategy(listOf(ExpressionReplacer(project, project.files.first(), 1)))
    }

    private fun registerCodecs() {
        eb.registerDefaultCodec(MutationStrategy::class.java, MutationStrategyCodec())
        eb.registerDefaultCodec(MutationResult::class.java, MutationResultCodec())
        eb.registerDefaultCodec(KotlincInvokeStatus::class.java, KotlincInvokeStatusCodec())
        eb.registerDefaultCodec(Project::class.java, ProjectCodec())
    }

    private fun workerOptions() = DeploymentOptions().setWorker(true) // TODO: exception handling, timeouts, etc

    private val log = Logger.getLogger("coordinatorLogger")
}