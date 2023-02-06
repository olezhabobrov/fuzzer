package com.stepanov.bbf.bugfinder.util

import org.apache.log4j.Logger
import org.slf4j.MDC

class MarkerLogger(
    loggerName: String,
    val markerName: String
) {
    private val log = Logger.getLogger(loggerName)

    init {
        MDC.put("pid",
            markerName + java.lang.management.ManagementFactory.getRuntimeMXBean().name.split("@")[0])
    }

    fun debug(msg: String) {
        log.debug(markerName + java.lang.management.ManagementFactory.getRuntimeMXBean().name.split("@")[0])
        MDC.put("pid",
            markerName + java.lang.management.ManagementFactory.getRuntimeMXBean().name.split("@")[0])
        log.debug(msg)
    }
}