package com.uqaigth.dl.core.node

import com.uqaigth.dl.core.Connection
import com.uqaigth.dl.core.Node

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
        val upstreamOutput = upstream.map { it.weight * it.upstreamNode.output }.sum()
        // 使用激活函数计算输出
        output = activator(upstreamOutput)
    }

    override fun calcDelta(label: Double) {
        val downstreamDelta = downstream.map { it.weight * it.downstreamNode.delta }.sum()
        delta = output * (1 - output) * downstreamDelta
    }
}
