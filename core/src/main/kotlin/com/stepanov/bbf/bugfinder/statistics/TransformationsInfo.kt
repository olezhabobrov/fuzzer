package com.stepanov.bbf.bugfinder.statistics

import com.stepanov.bbf.messages.CompilationDescription
import com.stepanov.bbf.messages.CompilationResult
import com.stepanov.bbf.messages.KotlincInvokeStatus
import kotlinx.serialization.Serializable

@Serializable
internal data class TransformationFullStat(
    val transformation: String,
    var klibInvalid: Int = 0,
    var invocatorFail: Int = 0,
    var compatibleNotLinking: Int = 0,
    var incompatibleLinking: Int = 0,
    var compilerCrashed: Int = 0,
    var expectedBehaviour: Int = 0,
    var unkownBehaviour: Int = 0,
    var totalMutationCount: Int = 0,
    var successfulMutations: Int = 0,
    var uselessMutations: Int = 0, // no new projects produced
    var unSuccessfulMutations: Int = 0, // failed with exception
    var newProjectsProducedDelta: Double = 0.0,
    var avgTimeInMS: Long = 0L,
    var mutationTimeouts: Int = 0,
) {
    fun add(result: CompilationResult) {
        val mutationStat = result.mutationStat
        require(transformation == mutationStat.transformation)
        result.results.forEach {
            when (it.getDescription()) {
                CompilationDescription.KLIB_INVALID -> klibInvalid++
                CompilationDescription.INVOCATOR_FAIL -> invocatorFail++
                CompilationDescription.COMPATIBLE_NOT_LINKING -> compatibleNotLinking++
                CompilationDescription.INCOMPATIBLE_LINKING -> incompatibleLinking++
                CompilationDescription.COMPILER_CRASHED -> compilerCrashed++
                CompilationDescription.EXPECTED_BEHAVIOUR -> expectedBehaviour++
                CompilationDescription.NOT_COMPILING -> unkownBehaviour++
            }
        }

        avgTimeInMS = (avgTimeInMS * totalMutationCount +
                mutationStat.avgTimeInMS * mutationStat.totalMutationCount) /
                (totalMutationCount + mutationStat.totalMutationCount)
        newProjectsProducedDelta = (newProjectsProducedDelta * totalMutationCount +
                mutationStat.newProjectsProducedDelta * mutationStat.totalMutationCount) /
                (totalMutationCount + mutationStat.totalMutationCount)
        successfulMutations += mutationStat.successfulMutations
        uselessMutations += mutationStat.uselessMutations
        unSuccessfulMutations += mutationStat.unsuccessfulMutations
        mutationTimeouts += mutationStat.timeouts
        totalMutationCount += mutationStat.totalMutationCount
    }

    @Serializable
    internal data class ExtendedStat(
        var total: Int,
    ) {
        fun add(result: CompilationResult, predicate: (KotlincInvokeStatus) -> Boolean) {
            val statuses = getAllWithCondition(result, predicate)
            total += statuses.size
        }

        private fun getAllWithCondition(compilationResult: CompilationResult,
                                        predicate: (KotlincInvokeStatus) -> Boolean) = compilationResult.results
            .map { it.results }.flatten().filter(predicate)
    }

}
