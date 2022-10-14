package com.uqaigth.network.core.layer

import com.uqaigth.network.core.Layer
import com.uqaigth.network.core.Node
import com.uqaigth.network.core.node.OutputLayerNode

class OutputLayer(
    // 层在网络中的 index
    override val layerIndex: Int,
    // 节点个数
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
