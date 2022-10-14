package com.uqaigth

import kotlin.math.max
import kotlin.math.pow

/**
 * 常用激活函数
 */

fun ReLU(x: Double): Double {
    return max(0.0, x)
}

fun LeakyReLU(x: Double): Double {
    return if (x < 0) 0.05 * x else x
}

fun sigmoid(x: Double): Double {
    return 1 / (1 + Math.E.pow(-x))
}

fun tanh(x: Double): Double {
    return kotlin.math.tanh(x)
}
