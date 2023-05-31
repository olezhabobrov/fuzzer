package com.stepanov.bbf.bugfinder.gitinfocollector

import coverage.CoverageEntry

internal interface SignatureCollectorInterface<T> {
    fun collect(funcs: List<T>): List<CoverageEntry>
}


