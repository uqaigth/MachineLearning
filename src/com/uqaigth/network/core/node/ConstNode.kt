package com.uqaigth.network.core.node

import com.uqaigth.network.core.Connection
import com.uqaigth.network.core.Node
import com.uqaigth.network.exception.NoSuchStreamConnection

class ConstNode(override val layerIndex: Int, override val nodeIndex: Int) : Node {
    override var output = 1.0
    override var delta = 0.0

    override val downstream: MutableList<Connection> = mutableListOf()
    override val upstream: List<Connection> = listOf()

    override fun addDownstreamConnection(connection: Connection) {
        downstream.add(connection)
    }

    override fun addUpstreamConnection(connection: Connection) {
        throw NoSuchStreamConnection("ConstNode doesn't has UpStreamConnection.")
    }

    override fun calcOutput() {
        output = 1.0
    }

    override fun calcDelta(label: Double) {
        val downstreamDelta = downstream.sumOf { it.weight * it.downstreamNode.delta }
        delta = output * (1 - output) * downstreamDelta
    }
}
