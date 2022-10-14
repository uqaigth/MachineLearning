package com.uqaigth.network.core.node

import com.uqaigth.network.core.Connection
import com.uqaigth.network.core.Node

class HiddenLayerNode(
    override val layerIndex: Int,
    override val nodeIndex: Int,
    private val activator: (Double) -> Double
) : Node {
    override var output = 0.0
    override var delta = 0.0

    override val downstream: MutableList<Connection> = mutableListOf()
    override val upstream: MutableList<Connection> = mutableListOf()

    override fun addDownstreamConnection(connection: Connection) {
        downstream.add(connection)
    }

    override fun addUpstreamConnection(connection: Connection) {
        upstream.add(connection)
    }

    override fun calcOutput() {
        // (w1 * x1) + (w2 * x2) + ... + (wi * xi) 上层的输出作为本层的输入
        val upstreamOutput = upstream.sumOf { it.weight * it.upstreamNode.output }
        // 使用激活函数计算输出
        output = activator(upstreamOutput)
    }

    override fun calcDelta(label: Double) {
        val downstreamDelta = downstream.sumOf { it.weight * it.downstreamNode.delta }
        delta = output * (1 - output) * downstreamDelta
    }
}
