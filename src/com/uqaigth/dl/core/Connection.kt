package com.uqaigth.dl.core

import kotlin.random.Random


class Connection(val upstreamNode: Node, val downstreamNode: Node) {
    private val random = Random(System.currentTimeMillis())
    var weight: Double
    var gradient = 0.0

    init {
        this.weight = random.nextDouble(-0.01, 0.01)
    }

    constructor(upstreamNode: Node, downstreamNode: Node, weight: Double) : this(upstreamNode, downstreamNode) {
        this.weight = weight
    }

    fun calcGradient(): Double {
        gradient = upstreamNode.output * downstreamNode.delta
        return gradient
    }

    fun updateWeight(rate: Double) {
        calcGradient()
        weight += gradient * rate
    }
}
