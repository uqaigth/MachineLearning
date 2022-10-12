package com.uqaigth.dl.core.layer

import com.uqaigth.dl.core.Layer
import com.uqaigth.dl.core.Node
import com.uqaigth.dl.core.node.InputLayerNode
import java.lang.Exception

class InputLayer(override val nodeCount: Int) : Layer {
    override val layerIndex: Int = 0
    override val layerType: String = "input"

    override val nodes: MutableList<Node> = mutableListOf()

    init {
        for (i in 0 until nodeCount) {
            nodes.add(InputLayerNode(layerIndex, i))
        }
    }

    fun setSample(sample: List<Double>) {
        if (sample.size != nodeCount) {
            throw Exception("The number of sample must equal nodeCount!")
        }
        nodes.forEachIndexed { i, node ->
            node.output = sample[i]
        }
    }

    override fun calcOutput() {}

    override fun dump() {}
}
