package com.uqaigth.sensor

/**
 * 神经元
 */
class Sensor(
    // 输入向量长度
    private val vecLength: Int,
    // 激活函数
    private val activator: (Double) -> Double
) {
    // 权重
    private var weights: List<Double> = List(vecLength) { 0.0 }
    // 偏移量
    private var bias: Double = 0.0


    fun predict(inputVec: DoubleArray): Double {
        return predict(inputVec.asList())
    }


    fun predict(inputVec: List<Double>): Double {
        return activator(inputVec.zip(weights).map { it.first * it.second }.sum() + bias)
    }

    fun train(inputVecArray: Array<DoubleArray>, labels: DoubleArray, iteration: Int, rate: Double) {
        train(inputVecArray.map { it.asList() }, labels.asList(), iteration, rate)
    }

    fun train(inputVecArray: List<List<Double>>, labels: List<Double>, iteration: Int, rate: Double) {
        if (inputVecArray.size != labels.size) {
            throw RuntimeException("The number of vectors and labels must be the same")
        }
        for (i in iteration downTo 1) {
            oneIteration(inputVecArray, labels, rate)
        }
    }

    private fun oneIteration(inputVecArray: List<List<Double>>, labels: List<Double>, rate: Double) {
        val sample = labels.zip(inputVecArray)
        for ((label, inputVec) in sample) {
            val output = predict(inputVec)
            updateWeight(inputVec, output, label, rate)
        }
    }

    private fun updateWeight(inputVec: List<Double>, output: Double, label: Double, rate: Double) {
        val delta = label - output
        weights = inputVec.zip(weights).map { rate * delta * it.first + it.second }
        bias += rate * delta
    }

    override fun toString(): String {
        return "weights:\t${weights}\n " +
                "  bias:\t${bias} "
    }

}
