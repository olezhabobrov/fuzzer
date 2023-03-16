package com.stepanov.bbf.bugfinder.manager

interface Reporter {
    fun dump(bug: Bug): String
}