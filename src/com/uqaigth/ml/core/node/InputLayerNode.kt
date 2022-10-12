package com.uqaigth.ml.core.node

import com.uqaigth.ml.core.Connection
import com.uqaigth.ml.core.Node
import com.uqaigth.ml.exception.NoSuchStreamConnection

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
