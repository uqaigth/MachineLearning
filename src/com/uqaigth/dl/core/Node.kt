package com.uqaigth.dl.core

interface Node {
    val layerIndex: Int

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
