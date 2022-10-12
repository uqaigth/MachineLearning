package com.uqaigth.dl.core.node

import com.uqaigth.dl.core.Connection
import com.uqaigth.dl.core.Node
import com.uqaigth.dl.exception.NoSuchStreamConnection

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
        val downstreamDelta = downstream.map { it.weight * it.downstreamNode.delta }.sum()
        delta = output * (1 - output) * downstreamDelta
    }
}
