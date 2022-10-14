package com.uqaigth.network.core

interface Layer {
    val layerIndex: Int

    val nodeCount: Int

    val layerType: String

    val nodes: MutableList<Node>

    fun calcOutput()

    fun dump()
}
