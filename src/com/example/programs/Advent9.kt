package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 26.06.2017.
 */
object Advent9 {


    @JvmStatic
    fun main(args: Array<String>) {
        val debug = true
        val input: String
        if (debug) {
            input = "X(8x2)(3x3)ABCY"
        } else {
            input = Util.loadFile("advent9.txt")
        }

        var finalLength = 0
        var i = 0
        while (i < input.length) {
            if (input[i] == '(') {
                var closingBracket = input.indexOf(')', i)
                val substring = input.substring(i + 1, closingBracket)
                val marker = substring.split("x").map { it.toInt() }
                closingBracket++
                val i1 = decompress(input.substring(closingBracket, closingBracket + marker[0]), marker) * marker[1]
                finalLength += i1
            } else {
                ++finalLength
            }
            ++i
        }

        println("$finalLength")
    }

    private fun decompress(input: String, marker: List<Int>): Int {
        if (input.contains('(')) {
            val closingBracket = input.indexOf(')')
            val newMarker = input.substring(1, closingBracket).split('x').map { it.toInt() }
            val input1 = input.substring(closingBracket + 1, closingBracket + 1 + newMarker [0])
            val decompressed = decompress(input1, newMarker)
            val i = decompressed * newMarker [1]
            println("decompressed: $decompressed, repetition: ${newMarker[1]}")
            return i
        }
        return input.filter { it != ' ' }.length
    }
}