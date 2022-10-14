package com.uqaigth.network.core.node

import com.uqaigth.network.core.Connection
import com.uqaigth.network.core.Node
import com.uqaigth.network.exception.NoSuchStreamConnection

class InputLayerNode(
    // 层在网络中的 index
    override val layerIndex: Int,
    // 节点在层中的 index
    override val nodeIndex: Int
) : Node {
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
