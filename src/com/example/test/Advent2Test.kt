package com.example.test

import com.example.programs.Advent2
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by Dominik Gudic on 20.06.2017.
 */
class Advent2Test {

    val keyPad: Advent2.SimpleKeyPad = Advent2.SimpleKeyPad()

    fun Advent2.SimpleKeyPad.reset(){
        x = 1
        y = 1
    }

    @Before
    fun setUp() {
        keyPad.reset()
    }

    @Test
    fun shouldGetCorrectNumber() {
        keyPad.x = 0
        keyPad.y = 0
        assertEquals(keyPad.getNumber(), "1")
        keyPad.moveRight()
        assertEquals(keyPad.getNumber(), "2")
        keyPad.moveRight()
        assertEquals(keyPad.getNumber(), "3")

    }
}