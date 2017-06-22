package com.example.programs

import com.example.Util
import com.example.programs.Advent4.addToMapOrIncrement

/**
 * Created by Dominik Gudic on 21.06.2017.
 */
object Advent6 {

    @JvmStatic
    fun main(args: Array<String>) {
        partOne()
    }

    private fun partOne() {

        val loadFileIntoList = Util.loadFileIntoList("advent6.txt")
        val strings = HashMap<Int, String>()
        loadFileIntoList.forEach { string ->
            (0..string.length - 1).forEach { i ->
                if (!strings.containsKey(i)) {
                    strings.put(i, "")
                }
                strings.put(i, strings[i] + string[i])
            }
        }
        val characters = HashMap<Char, Int>().withDefault { 0 }
        var finalMessage = ""
        for ((_, value) in strings) {
            characters.clear()
            finalMessage += value.fold(characters, { map, c ->
                var amount = 0
                if (map.containsKey(c))
                    amount = map.getValue(c)
                map.put(c, amount + 1)
                map
            }).map { Pair(it.value, it.key) }
                    .sortedBy { it.first }
                    .first()
                    .second
        }
        println("The final message is $finalMessage")

    }

}