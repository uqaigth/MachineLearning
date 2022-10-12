package com.uqaigth.dl.core.layer

import com.uqaigth.dl.core.Layer
import com.uqaigth.dl.core.Node
import com.uqaigth.dl.core.node.OutputLayerNode

class OutputLayer(
    override val layerIndex: Int,
    override val nodeCount: Int,
    activator: (Double) -> Double
) : Layer {
    override val layerType: String = "output"

    override val nodes: MutableList<Node> = mutableListOf()

    init {
        for (i in 0 until nodeCount) {
            nodes.add(OutputLayerNode(layerIndex, i, activator))
        }
//        nodes.add(ConstNode(layerIndex, nodeCount))
    }

    override fun calcOutput() {
        for (node in nodes) {
            node.calcOutput()
        }
    }

    override fun dump() {
    }
}
