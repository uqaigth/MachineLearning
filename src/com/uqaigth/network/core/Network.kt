package com.uqaigth.network.core

import com.uqaigth.network.core.layer.InputLayer
import com.uqaigth.network.core.node.ConstNode

class Network(private val layers: List<Layer>) {

    private val connections: MutableList<Connection> = mutableListOf()

    private val outputNodes: List<Node>

    /**
     * 初始化网络
     */
    init {
        for (i in 0..layers.size - 2) { // 逐层处理
            for (upStreamNode in layers[i].nodes) { // 每个节点进行处理
                for (downStreamNode in layers[i + 1].nodes) { //
                    if (downStreamNode !is ConstNode) {
                        val conn = Connection(upStreamNode, downStreamNode)
                        conn.upstreamNode.addDownstreamConnection(conn)
                        conn.downstreamNode.addUpstreamConnection(conn)
                        connections.add(conn)
                    }
                }
            }
        }
        outputNodes = layers.last().nodes
    }

    /**
     * @param labels 标签集
     * @param data_set 数据集
     * @param rate 训练速率
     * @param iteration 训练次数
     */
    fun train(labels: List<List<Double>>, data_set: List<List<Double>>, rate: Double, iteration: Int) {
        for (i in 1..iteration) {
            data_set.forEachIndexed { index, sample ->
                trainOneSample(labels[index], sample, rate)
            }
        }
    }

    /**
     * @param label 标签
     * @param sample 样本
     * @param rate 训练速率
     */
    private fun trainOneSample(label: List<Double>, sample: List<Double>, rate: Double) {
        // 单次预测
        predict(sample)
        // 计算误差
        calcDelta(label)
        // 更新权重
        updateWeight(rate)
    }

    /**
     * 基于样本预测结果
     */
    fun predict(sample: List<Double>): List<Double> {
        // 输入层设置样本
        (layers.first() as InputLayer).setSample(sample)
        // 逐层计算输出
        for (i in 1 until layers.size) {
            layers[i].calcOutput()
        }
        return outputNodes.map { it.output }
    }

    /**
     *
     */
    private fun calcDelta(label: List<Double>) {
        // 从输出层向前计算 delta
        outputNodes.forEachIndexed { i, node -> node.calcDelta(label[i]) }
//        label.forEachIndexed { i, sampleLabel -> outputNodes[i].calcDelta(sampleLabel) }
//        outputNodes[label.size].calcDelta(0.0)
        for (i in layers.size - 2 downTo 0) {
            layers[i].nodes.forEach { it.calcDelta(0.0) }
        }
    }

    private fun updateWeight(rate: Double) {
        for (i in 0..layers.size - 2) {
            layers[i].nodes.forEach {
                it.downstream.forEach { conn -> conn.updateWeight(rate) }
            }
        }
    }


    fun getGradient(label: List<Double>, sample: List<Double>) {
        predict(sample)
        calcDelta(label)
        connections.forEach { it.calcGradient() }
    }

    private fun calcError(l1: List<Double>, l2: List<Double>): Double {
        return 0.5 * l1.zip(l2)
            .map { (it.first - it.second) * (it.first - it.second) }
            .reduce { acc, d -> acc + d }
    }

    fun gradientCheck(label: List<Double>, sample: List<Double>) {
        val epsilon = 0.0001
        for (conn in connections) {
            val actualGradient = conn.gradient
            conn.weight += epsilon
            val error1 = calcError(predict(sample), label)
            conn.weight -= 2 * epsilon
            val error2 = calcError(predict(sample), label)
            val expectedGradient = (error2 - error1) / (2 * epsilon)
            println("  actualGradient: $actualGradient\nexpectedGradient: $expectedGradient")
        }
    }
}
