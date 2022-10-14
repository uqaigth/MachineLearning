package com.uqaigth.network.core.layer

import com.uqaigth.network.core.Layer
import com.uqaigth.network.core.Node
import com.uqaigth.network.core.node.InputLayerNode
import java.lang.Exception

/**
 * 输入层 节点个数应和样本采样率一致
 */
class InputLayer(override val nodeCount: Int) : Layer {
    // 层在网络中的 index
    override val layerIndex: Int = 0

    // 层类型
    override val layerType: String = "input"

    // 神经元节点
    override val nodes: MutableList<Node> = mutableListOf()

    /**
     * 初始化输入层
     */
    init {
        for (i in 0 until nodeCount) {
            nodes.add(InputLayerNode(layerIndex, i))
        }
    }

    /**
     * 输入层输入样本，样本直接作为输出
     */
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
