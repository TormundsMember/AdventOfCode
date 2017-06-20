package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 19.06.2017.
 */
object Advent1 {

    @JvmStatic fun main(args: Array<String>) {
        val directions = Util.loadFile("advent1.txt").split(", ")
        var y = 0
        var x = 0
        var rotation = Direction.North
        val steps = HashMap<Direction, Pair<Int, Int>>().withDefault { Pair(0, 0) }
        steps[Direction.North] = Pair(0, 1)
        steps[Direction.East] = Pair(1, 0)
        steps[Direction.West] = Pair(-1, 0)
        steps[Direction.South] = Pair(0, -1)
        val locationsVisited = HashMap<String, Int>().withDefault { 0 }
        locationsVisited["$y|$x"] = 1
        var valueFound = false
        for (direction in directions) {
            rotation = when (direction[0]) {
                'L' -> rotation.toLeft()
                else -> rotation.toRight()
            }
            val stepsToTake = direction.subSequence(1, direction.length).toString().toInt()

            val movement = steps[rotation]!!

            for (i in 1..stepsToTake) {
                x += movement.first
                y += movement.second
                if(locationsVisited.containsKey("$y|$x")) {
                    println("${Math.abs(x) + Math.abs(y)}")
                    valueFound = true
                    break
                }
                locationsVisited["$y|$x"] = 1
            }
            if(valueFound)
                break
        }
    }


    internal enum class Direction {
        North, South, East, West;


        fun toLeft(): Direction {
            when (this) {
                North -> return West
                West -> return South
                South -> return East
                else -> return North
            }
        }

        fun toRight(): Direction {
            when (this) {
                North -> return East
                East -> return South
                South -> return West
                else -> return North
            }
        }

        fun getStringValue(): String {
            when (this) {
                North -> return "↑"
                South -> return "↓"
                East -> return "→"
                else -> return "←"
            }
        }
    }
}
