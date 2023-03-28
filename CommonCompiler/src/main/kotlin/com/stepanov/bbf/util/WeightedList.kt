package com.stepanov.bbf.util

import kotlin.random.Random

class WeightedList<T> {
    private data class Element<T>(
        val element: T,
        val accumulatedWeight: Double
    )

    private val elements = mutableListOf<Element<T>>()
    private var accumulatedWeight = 0.0

    fun add(element: T, weight: Double) {
        accumulatedWeight += weight
        elements.add(Element(element, accumulatedWeight))
    }

    fun getRandom(): T? {
        val r = Random.nextDouble() * accumulatedWeight
        require(r in 0.0..accumulatedWeight)
        elements.forEach { (element, weight) ->
            if (weight >= r) {
                return element
            }
        }
        return null
    }
}