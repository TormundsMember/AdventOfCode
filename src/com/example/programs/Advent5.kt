package com.example.programs

import java.security.MessageDigest
import kotlin.experimental.and

/**
 * Created by Dominik Gudic on 26.06.2017.
 */
object Advent5 {


    @JvmStatic
    fun main(args: Array<String>) {
        val hashFunction = MessageDigest.getInstance("MD5")
        val input = "wtnhxymk"
        val maxTries = 8
        var foundParts = 0
        val acceptedChars = mutableListOf<Char>()
        acceptedChars.add('0')
        acceptedChars.add('1')
        acceptedChars.add('2')
        acceptedChars.add('3')
        acceptedChars.add('4')
        acceptedChars.add('5')
        acceptedChars.add('6')
        acceptedChars.add('7')
        val password = HashMap<Char, Char>()
        var i = 0
        while (i < Int.MAX_VALUE && foundParts < maxTries) {
            hashFunction.update((input + i).toByteArray())
            val digest = hashFunction.digest()
//            println("$i. iteration: $digest")

            val digestedString = toHexString(digest)
            if (digestedString.take(5).all { it == '0' }) {
                if (!password.containsKey(digestedString[5]) && acceptedChars.contains(digestedString[5])) {
                    println("${digestedString[5]}, ${digestedString[6]}")
                    password.put(digestedString[5], digestedString[6])
                    foundParts++
                }
            }
            ++i
        }
        print("password is \"")
        for (key in password.keys.toSortedSet()) {
            print(password[key])
        }
        println("\"")
    }


    fun toHexString(bytes: ByteArray): String {
        val hexString = StringBuilder()

        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }

        return hexString.toString()
    }
}