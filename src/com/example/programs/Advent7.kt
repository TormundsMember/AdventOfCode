package com.example.programs

import com.example.Util
import com.sun.org.apache.regexp.internal.RE

/**
 * Created by Dominik Gudic on 21.06.2017.
 */

object Advent7 {


    @JvmStatic
    fun main(args: Array<String>) {
//        partOne()
        partTwo()
    }

    private fun partOne() {
        val ipAddresses = Util.loadFileIntoList("advent7.txt")
        var amountOfValidIps = 0
        var foundValidIp: Boolean
        var foundInvalidIp: Boolean
        for (address in ipAddresses) {
            println(address)
            foundValidIp = false
            foundInvalidIp = false
            for (part in address.split(Regex("\\[\\w+]"))) {
                if (!foundValidIp && !foundInvalidIp)
                    for (i in 1..part.length - 3) {
                        if (part[i] == part[i + 1]) {
                            if (part[i - 1] != part[i]) {
                                if (part[i - 1] == part[i + 2]) {
                                    foundValidIp = true
                                    println("$part is valid!")
                                }
                            }
                        }
                    }
            }

            if (foundValidIp) {
                val parts = address.replace(Regex("^\\w+\\["), "").replace(Regex("]\\w+$"), "").replace(Regex("]\\w+\\["), ":").split(":")
                for (part in parts) {
                    if (!foundInvalidIp)
                        for (i in 1..part.length - 3) {
                            if (part[i] == part[i + 1]) {
                                if (part[i - 1] != part[i]) {
                                    if (part[i - 1] == part[i + 2]) {
                                        foundInvalidIp = true
                                        println("$part is valid!")
                                    }
                                }
                            }
                        }
                }
            }


            if (foundValidIp && !foundInvalidIp)
                amountOfValidIps++
            println()
        }
        println("There are $amountOfValidIps valid IPv7-Adresses in the list")
    }

    private fun partTwo() {
        val ipAddresses = Util.loadFileIntoList("advent7.txt")
        var amountOfValidIps = 0
        var foundValid: Boolean
        for (address in ipAddresses) {
            foundValid = false
            if (!foundValid) {
                val supernetSequences = address.split(Regex("\\[\\w+]"))
                val extendedAddress = "]$address["
                val hypernetSequences = extendedAddress.split(Regex("]\\w+\\["))
                if (!foundValid) {
                    for (sequence in supernetSequences) {
                        for (j in 1..sequence.length - 2) {
                            if (!foundValid) {
                                val s = sequence.subSequence(j - 1, j + 2).toString()
                                if (isValidABA(s)) {
                                    hypernetSequences
                                            .filter { it.contains(s.invert()) }
                                            .forEach { println("full adress:  $address, hypernet: $it, string: $s");foundValid = true }
                                }
                            }
                        }
                    }
                }
            }
            if (foundValid)
                amountOfValidIps++
        }

        println("I found $amountOfValidIps valid IPv7-Adresses")
    }

    private fun isValidABA(firstSub: CharSequence): Boolean = firstSub[0] == firstSub[2] && firstSub[0] != firstSub[1]

    private fun CharSequence.invert(): CharSequence {
        var sub = this.toString()
        sub += sub[1]
        sub = sub.subSequence(1, sub.length).toString()
        return sub
    }
}