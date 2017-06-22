package com.example.programs

import com.example.Util.loadFileIntoList

/**
 * Created by Dominik Gudic on 20.06.2017.
 */

object Advent4 {

    val characters = mutableListOf<Char>()
    init {
        (0..26).mapTo(characters) { ('a'.toInt() + it).toChar() }
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        partOne()
        partTwo()
    }

    private fun partTwo() {
        val codes = loadFileIntoList("advent4.txt")
        var finalTally = 0
        codes.forEach { code ->
            val lastIndex = code.lastIndexOf('-')
            val letters = code.subSequence(0, lastIndex).replace(Regex("-"), "")
            val sectorId = code.subSequence(lastIndex + 1, code.length).replace(Regex("\\[.+]"), "").toInt()
            val checkSum = code.subSequence(code.indexOf('[') + 1, code.length - 1)

            val lettrs = letters.map { increment(it, sectorId) }
                    .map { it.toString() }
                    .reduce { a, b -> a + b }

            if (lettrs.contains("north", ignoreCase = true))
                println(sectorId)
        }

    }


    fun partOne() {
        val codes = loadFileIntoList("advent4.txt")
        var finalTally = 0
        codes.forEach { code ->
            val lastIndex = code.lastIndexOf('-')
            val letters = code.subSequence(0, lastIndex).replace(Regex("-"), "")
            val sectorId = code.subSequence(lastIndex + 1, code.length).replace(Regex("\\[.+]"), "").toInt()
            val checkSum = code.subSequence(code.indexOf('[') + 1, code.length - 1)
            println(code)
            println("the sectorshift is ${sectorId % 26}")
            val lettrs = letters.map { it.toString() }
                    .fold(HashMap<String, Int>(), {
                        map, str ->
                        map.addToMapOrIncrement(str)
                    })
                    .map { it.toPair() }
                    .fold(HashMap<Int, MutableList<String>>(), {
                        map, (first, second) ->
                        val list: MutableList<String>
                        if (map.containsKey(second)) {
                            list = map.getValue(second)
                        } else {
                            list = mutableListOf()
                            map.put(second, list)
                        }
                        list.add(first)
                        list.sort()
                        map
                    })
                    .flatMap { it.value.sortedDescending() }
                    .fold("", { acc, s -> s + acc })
                    .subSequence(0, 5)
                    .toString()

            println("$lettrs, $checkSum")
            if (lettrs == checkSum) {
                finalTally += sectorId
            }
            println()
        }

        println("The final tally is $finalTally")
    }

    private fun increment(it: Char, shift: Int): Char {

        var c = characters.indexOf(it)
        c += shift
        c %= 26
        return characters[c]
    }


    fun HashMap<String, Int>.addToMapOrIncrement(key: String): HashMap<String, Int> {
        if (containsKey(key)) {
            val value = getValue(key) + 1
            remove(key)
            put(key, value)
        } else {
            put(key, 1)
        }

        return this
    }
}