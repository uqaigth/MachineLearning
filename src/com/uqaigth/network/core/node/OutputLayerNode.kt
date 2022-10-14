package com.uqaigth.network.core.node

import com.uqaigth.network.core.Connection
import com.uqaigth.network.core.Node
import com.uqaigth.network.exception.NoSuchStreamConnection

class OutputLayerNode(
    override val layerIndex: Int,
    override val nodeIndex: Int,
    private val activator: (Double) -> Double
) : Node {
    override var output = 0.0
    override var delta = 0.0

    override val upstream: MutableList<Connection> = mutableListOf()
    override val downstream: List<Connection> = listOf()

    override fun addUpstreamConnection(connection: Connection) {
        upstream.add(connection)
    }

    override fun addDownstreamConnection(connection: Connection) {
        throw NoSuchStreamConnection("OutputLayerNode doesn't has DownStreamConnection.")
    }

    override fun calcOutput() {
        // (w1 * x1) + (w2 * x2) + ... + (wi * xi) 上层的输出作为本层的输入
        val upstreamOutput = upstream.sumOf { it.weight * it.upstreamNode.output }
        // 使用激活函数计算输出
        output = activator(upstreamOutput)
    }

    override fun calcDelta(label: Double) {
        delta = output * (1 - output) * (label - output)
    }
}
