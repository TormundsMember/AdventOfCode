package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 21.06.2017.
 */
object Advent8 {

    private val colCount = 7
    private val rowCount = 3

    /**
     * x = colCount
     *
     * y = rowCount
     */
    val screen: Array<Array<Boolean>>

    init {
        screen = Array(com.example.programs.Advent8.colCount) { (kotlin.Array(com.example.programs.Advent8.rowCount) { false }) }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        partOne()
    }

    private fun partOne() {
        val instructions = Util.loadFileIntoList("advent8.txt")

        instructions
                .map { it.split(" ") }
                .forEach {
                    println("$it\n")
                    when (it[0]) {
                        "rect" -> {
                            val size = it[1].split("x")
                            fillRect(size[0].toInt(), size[1].toInt())
                        }
                        "rotate" -> getRotation(it[1], it[2].split("=")[1].toInt(), it[4].toInt())
                    }
                }
        println("and now we're done\n")
        printScreen()
    }

    private fun printScreen() {
        for (x in 0..rowCount - 1) {
            for (y in 0..colCount - 1) {
                val screen1 = screen
                val b = screen1[y][x]
                print("${if (b) '#' else '.'}")
            }
            println()
        }
        println()
    }

    private fun getRotation(direction: String, target: Int, amount: Int) {

        val action: (Int) -> Unit = when (direction) {
            "row" -> {
                { _ -> shiftRight(screen, target) }
            }
            else -> {
                { _ -> shiftDown(screen, target) }
            }
        }
        (0..amount).forEach(action)
        printScreen()
    }

    private fun fillRect(width: Int, height: Int) {
        for (i in 0..width - 1) {
            for (j in 0..height - 1) {
                screen[i][j] = true
            }
        }
        printScreen()
    }


    /**
     * shifts the column down by one
     */
    fun shiftDown(screen: Array<Array<Boolean>>, col: Int) {
        if (col !in 0..colCount - 1)
            throw IllegalArgumentException()
        val lastValue = screen[col][rowCount - 1]
        for (i in rowCount - 1 downTo 1) {
            screen[col][i] = screen[col][i - 1]
        }
        screen[col][0] = lastValue
    }

    /**
     * shift the column to the right by one
     */
    fun shiftRight(screen: Array<Array<Boolean>>, row: Int) {
        if (row !in 0..colCount - 1)
            throw IllegalArgumentException()

        val lastValue = screen[0][row]
        for (i in 1..colCount - 1) {
            screen[i][row] = screen[i - 1][row]
        }
        screen[colCount - 1][row] = lastValue
    }


}