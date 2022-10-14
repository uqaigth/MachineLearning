package com.uqaigth.network.core

interface Node {
    // 层在网络中的 index
    val layerIndex: Int
    // 节点在层中的 index
    val nodeIndex: Int

    val downstream: List<Connection>

    val upstream: List<Connection>

    var output: Double

    var delta: Double

    fun calcOutput()

    fun calcDelta(label: Double)

    fun addDownstreamConnection(connection: Connection)

    fun addUpstreamConnection(connection: Connection)
}
