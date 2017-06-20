package com.example.programs

import com.example.Util

/**
 * Created by Dominik Gudic on 20.06.2017.
 */
object Advent3 {


    @JvmStatic fun main(args: Array<String>) {
        partTwo()
    }

    private fun partOne() {
        val triangles = Util.loadFileIntoList("advent3.txt")
        var amountOfValidTriangles = 0
        for (triangle in triangles) {

            val a = triangle.subSequence(0, 5).toString().trim().toInt()
            val b = triangle.subSequence(6, 10).toString().trim().toInt()
            val c = triangle.subSequence(11, 15).toString().trim().toInt()

            if (a + b > c && a + c > b && b + c > a)
                amountOfValidTriangles++
        }
        println("Amount of valid triangles $amountOfValidTriangles")
    }

    private fun partTwo() {
        val triangles = Util.loadFileIntoList("advent3.txt")
        var amountOfValidTriangles = 0
        var counter = 0

        val first = Triplet(0, 0, 0)
        val second = Triplet(0, 0, 0)
        val third = Triplet(0, 0, 0)

        for (triangle in triangles) {

            val a = triangle.subSequence(0, 5).toString().trim().toInt()
            val b = triangle.subSequence(6, 10).toString().trim().toInt()
            val c = triangle.subSequence(11, 15).toString().trim().toInt()

            when (counter % 3) {
                0 -> {
                    first.a = a
                    second.a = b
                    third.a = c
                }
                1 -> {
                    first.b = a
                    second.b = b
                    third.b = c
                }
                else -> {
                    first.c = a
                    second.c = b
                    third.c = c

                    if (first.isViable())
                        amountOfValidTriangles++
                    if (second.isViable())
                        amountOfValidTriangles++
                    if (third.isViable())
                        amountOfValidTriangles++
                }
            }
            counter = (counter + 1) % 3
        }
        println("Amount of valid triangles $amountOfValidTriangles")
    }


    class Triplet(var a: Int, var b: Int, var c: Int) {
        fun isViable(): Boolean {
            return a + b > c && a + c > b && b + c > a
        }
    }
}
