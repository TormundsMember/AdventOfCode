package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 26.06.2017.
 */
object Advent9 {


    @JvmStatic
    fun main(args: Array<String>) {
        val debug = false

        val input: String
        if (debug) {
            input = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"
        } else {
            input = Util.loadFile("advent9.txt")
        }
        val finalLength : Long = decompress(input)
        println("$finalLength")
    }

    private fun decompress(input: String): Long {
        var i = 0
        var finalLength = 0L
        while (i < input.length) {
            if (input[i] == '(') {
                val marker = getMarkerForInput(input.substring(i))
                i += marker.strLen
                finalLength += decompress(input.substring(i, i + marker.requestedBuffer)) * marker.repeatCount
                i += marker.requestedBuffer
            } else {
                ++finalLength
                ++i
            }
        }
        return finalLength
    }

    private fun getMarkerForInput(input: String): Marker {
        val closingBracket = input.indexOf(char = ')', startIndex = 0)
        val map = input.substring(1, closingBracket).split("x").map { it.toInt() }
        return map.toMarker()
    }


    data class Marker(val requestedBuffer: Int, val repeatCount: Int, val strLen: Int = "(${requestedBuffer}x$repeatCount)".length) {

        override fun toString(): String {
            return "Marker[$requestedBuffer, $repeatCount]"
        }


    }

    fun List<Int>.toMarker(): Marker = Marker(requestedBuffer = this[0], repeatCount = this[1])


}