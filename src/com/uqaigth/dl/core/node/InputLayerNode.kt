package com.uqaigth.dl.core.node

import com.uqaigth.dl.core.Connection
import com.uqaigth.dl.core.Node
import com.uqaigth.dl.exception.NoSuchStreamConnection

class InputLayerNode(override val layerIndex: Int, override val nodeIndex: Int) : Node {
    override var delta = 0.0
    override var output = 0.0

    override val downstream: MutableList<Connection> = mutableListOf()
    override val upstream: List<Connection> = listOf()

    override fun addDownstreamConnection(connection: Connection) {
        downstream.add(connection)
    }

    override fun addUpstreamConnection(connection: Connection) {
        throw NoSuchStreamConnection("InputLayerNode doesn't has UpStreamConnection.")
    }

    override fun calcOutput() {}

    override fun calcDelta(label: Double) {}
}
