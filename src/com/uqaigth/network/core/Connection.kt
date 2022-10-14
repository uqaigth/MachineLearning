package com.uqaigth.network.core

import kotlin.random.Random


class Connection(
    // 上层节点
    val upstreamNode: Node, val downstreamNode: Node) {
    private val random = Random(System.currentTimeMillis())
    var weight: Double
    var gradient = 0.0

    //
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
