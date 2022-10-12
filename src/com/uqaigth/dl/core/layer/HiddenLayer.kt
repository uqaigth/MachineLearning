package com.uqaigth.dl.core.layer

import com.uqaigth.dl.core.Layer
import com.uqaigth.dl.core.Node
import com.uqaigth.dl.core.node.ConstNode
import com.uqaigth.dl.core.node.HiddenLayerNode

class HiddenLayer(
    override val layerIndex: Int,
    override val nodeCount: Int,
    activator: (Double) -> Double
) : Layer {
    override val layerType: String = "hidden"

    override val nodes: MutableList<Node> = mutableListOf()

    init {
        for (i in 0 until nodeCount) {
            nodes.add(HiddenLayerNode(layerIndex, i, activator))
        }
        nodes.add(ConstNode(layerIndex, nodeCount))
    }

    override fun calcOutput() {
        for (node in nodes) {
            node.calcOutput()
        }
    }

    override fun dump() {
    }
}
