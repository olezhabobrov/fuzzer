package com.stepanov.bbf.bugfinder.mutator.vertxMessages

import com.stepanov.bbf.information.MutationStat
import com.stepanov.bbf.messages.ProjectMessage
import java.util.concurrent.atomic.AtomicInteger

data class MutationResult(
    val projects: Set<ProjectMessage>,
    val strategyNumber: Int,
    val mutationStat: MutationStat,
) {

    val mutationNumber = mutationCounter.incrementAndGet()

    companion object {

        val mutationCounter = AtomicInteger(0)

    }

}