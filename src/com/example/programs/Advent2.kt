package com.example.programs

import com.example.Util
import java.util.HashMap


/**
 * Created by Dominik Gudic on 20.06.2017.
 */
object Advent2 {


    @JvmStatic fun main(args: Array<String>) {
        val instructions = Util.loadFileIntoList("advent2.txt")
        val keypad: KeyPad = AdvancedKeyPad()
        keypad.moveToInitialSpot()
        var finalNumber = ""
        for (instruction in instructions) {
            for (move in instruction) {
                when (move) {
                    'U' -> keypad.moveUp()
                    'D' -> keypad.moveDown()
                    'L' -> keypad.moveLeft()
                    'R' -> keypad.moveRight()
                }
            }
            finalNumber += keypad.getNumber()
        }
        println("the final number is $finalNumber")
    }

    abstract class KeyPad {
        internal var x = 1
        internal var y = 1

        abstract fun moveToInitialSpot()

        abstract fun moveUp()
        abstract fun moveDown()
        abstract fun moveLeft()
        abstract fun moveRight()

        abstract fun getNumber(): String
    }

    class SimpleKeyPad : KeyPad() {

        override fun moveToInitialSpot() {
            x = 1
            y = 1
        }

        override fun moveLeft() {
            x = Math.max(0, x - 1)
        }

        override fun moveRight() {
            x = Math.min(2, x + 1)
        }

        override fun moveUp() {
            y = Math.max(0, y - 1)
        }

        override fun moveDown() {
            y = Math.min(2, y + 1)
        }

        override fun getNumber(): String {
            return (y * 3 + x + 1).toString()
        }
    }

    class AdvancedKeyPad : KeyPad() {
        val numbers = initArray()


        fun initArray(): Array<Array<String>> {
            val arrayOfArrays = Array(5) { Array(5) { "" } }

            arrayOfArrays[0][2] = "1"

            arrayOfArrays[1][1] = "2"
            arrayOfArrays[1][2] = "3"
            arrayOfArrays[1][3] = "4"

            arrayOfArrays[2][0] = "5"           //   1
            arrayOfArrays[2][1] = "6"           //  234
            arrayOfArrays[2][2] = "7"           // 56789
            arrayOfArrays[2][3] = "8"           //  ABC
            arrayOfArrays[2][4] = "9"           //   D

            arrayOfArrays[3][1] = "A"
            arrayOfArrays[3][2] = "B"
            arrayOfArrays[3][3] = "C"

            arrayOfArrays[4][2] = "D"

            return arrayOfArrays
        }

        override fun moveToInitialSpot() {
            x = 0
            y = 2
        }

        override fun moveUp() {
            val oldY = y
            y = Math.max(0, y - 1)
            if (getNumber() == "")
                y = oldY
        }

        override fun moveDown() {
            val oldY = y
            y = Math.min(4, y + 1)
            if (getNumber() == "")
                y = oldY
        }

        override fun moveLeft() {
            val oldX = x
            x = Math.max(0, x - 1)
            if (getNumber() == "")
                x = oldX
        }

        override fun moveRight() {
            val oldX = x
            x = Math.min(4, x + 1)
            if (getNumber() == "")
                x = oldX
        }

        override fun getNumber(): String {
            return numbers[y][x]
        }


    }
}
