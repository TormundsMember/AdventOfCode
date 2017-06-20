package com.example.programs

import com.example.Util
import com.example.Util.loadFileIntoList
import javafx.collections.transformation.SortedList

/**
 * Created by Dominik Gudic on 20.06.2017.
 */

object Advent4 {


    @JvmStatic
    fun main(args: Array<String>) {
        partOne()
    }


    fun partOne() {
        val codes = loadFileIntoList("advent4.txt")

        var sortedLetters = listOf<Char>().toSortedSet()
        val amountOf = HashMap<String, Int>().withDefault { 0 }

        for (code in codes) {
            val lastIndex = code.lastIndexOf('-')
            val letters = code.subSequence(0, lastIndex).replace(Regex("-"), "")
            val sectorId = code.subSequence(lastIndex, code.length).replace(Regex("\\[.+]"), "").toInt()
            val checkSum = code.subSequence(code.indexOf('['), code.length)
            sortedLetters = letters.toSortedSet()
            amountOf.clear()
            for (c in sortedLetters) {
                if (amountOf.containsKey(c.toString())) {
                    val i: Int = amountOf[c.toString()]!!
                    amountOf[c.toString()] = i + 1
                } else {
                    amountOf[c.toString()] = 1
                }
            }
            val keys = amountOf.keys

            for (key in keys) {

            }
        }
    }

    fun partTwo() {

    }
}