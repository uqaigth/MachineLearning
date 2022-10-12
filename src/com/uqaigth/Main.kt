package com.uqaigth

import com.uqaigth.ml.core.Network
import com.uqaigth.ml.core.layer.HiddenLayer
import com.uqaigth.ml.core.layer.InputLayer
import com.uqaigth.ml.core.layer.OutputLayer
import com.uqaigth.sensor.Sensor

class Main {

    companion object {
        fun testSensor() {
            val inputVecs = listOf(listOf(1.0, 1.0), listOf(0.0, 0.0), listOf(1.0, 0.0), listOf(0.0, 1.0))
            val labels = listOf(1.0, 0.0, 0.0, 0.0)
//            val sensor = Sensor(2) { if (it > 0) 1.0 else 0.0 }
            val sensor = Sensor(2, ::ReLU)

            sensor.train(inputVecs, labels, 100, 0.1)
            println(sensor.toString())
            println(sensor.predict(listOf(1.0, 1.0)))
            println(sensor.predict(listOf(0.0, 0.0)))
            println(sensor.predict(listOf(1.0, 0.0)))
            println(sensor.predict(listOf(0.0, 1.0)))
        }
        fun testNetwork() {
            val il = InputLayer(4)
            val hl1 = HiddenLayer(1, 1, ::LeakyReLU)
            val ol = OutputLayer(2, 5, ::tanh)
            val network = Network(listOf(il, hl1, ol))

            network.train(
                listOf(
                    listOf(0.0, 0.0, 1.0, 0.0, 0.0),
                    listOf(0.0, 0.0, 0.0, 1.0, 0.0),
                    listOf(0.0, 0.0, 0.0, 1.0, 0.0),
                    listOf(0.0, 0.0, 1.0, 0.0, 0.0),
                    listOf(0.0, 1.0, 0.0, 0.0, 0.0),
                    listOf(1.0, 0.0, 0.0, 0.0, 0.0)
                ),
                listOf(
                    listOf(1.0, 0.0, 0.0, 1.0),
                    listOf(1.0, 0.0, 1.0, 1.0),
                    listOf(1.0, 1.0, 0.0, 1.0),
                    listOf(1.0, 1.0, 0.0, 0.0),
                    listOf(1.0, 0.0, 0.0, 0.0),
                    listOf(0.0, 0.0, 0.0, 0.0)
                ),
                0.1,
                100
            )
//            network.getGradient(listOf(1.0), listOf(0.0, 0.0, 0.0, 1.0))
//            network.gradientCheck(listOf(1.0), listOf(0.0, 0.0, 0.0, 1.0))
            println(network.predict(listOf(0.0, 0.0, 0.0, 1.0)))
            println(network.predict(listOf(0.0, 0.0, 1.0, 0.0)))
            println(network.predict(listOf(0.0, 0.0, 1.0, 1.0)))
            println(network.predict(listOf(1.0, 1.0, 0.0, 0.0)))
            println(network.predict(listOf(0.0, 0.0, 0.0, 0.0)))
        }

        @JvmStatic
        fun main(args: Array<String>) {
//            testSensor()
            testNetwork()
        }
    }
}
