package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 28.06.2017.
 */
object Advent12 {

    @JvmStatic
    fun main(args: Array<String>) {
        val instructions = Util.loadFileIntoList("advent12.txt")
        var i = 0
        val map = mutableMapOf(Pair("a", 1))
        while (i < instructions.size) {
            println("$i: ${instructions[i]}")

            val parts = instructions[i].split(' ')
            when (parts[0]) {
                "cpy" -> {
                    copyToMap(map, parts)
                    ++i
                }
                "inc" -> {
                    increaseInMap(map, parts)
                    ++i
                }
                "dec" -> {
                    decreaseInMap(map, parts)
                    ++i
                }
                "jnz" -> i += jumpMap(map, parts)
                else -> throw IllegalArgumentException("Illegal instruction \"${parts[0]}\" found")
            }
            println(map)
            println()

        }

        println(map)
    }

    private fun jumpMap(map: MutableMap<String, Int>, parts: List<String>): Int {
        if (Regex("""\d+""").matches(parts[1])) {
            if (parts[1].toInt() != 0)
                return parts[2].toInt()
        } else {
            if (map.containsKey(parts[1]))
                if(map.getValue(parts[1]) != 0)
                    return parts[2].toInt()
        }
        return 1
    }

    private fun decreaseInMap(map: MutableMap<String, Int>, parts: List<String>) {
        val value = map.getValue(parts[1])
        map.put(parts[1], value - 1)
    }

    private fun increaseInMap(map: MutableMap<String, Int>, parts: List<String>) {
        val value = map.getValue(parts[1])
        map.put(parts[1], value + 1)
    }

    private fun copyToMap(map: MutableMap<String, Int>, parts: List<String>) {
        if (Regex("""\d+""").matches(parts[1])) {
            map.put(parts[2], parts[1].toInt())
        } else {
            map.put(parts[2], map.getValue(parts[1]))
        }
    }
}