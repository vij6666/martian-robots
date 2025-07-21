package com.martianrobots

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.martianrobots.model.*

class ValidationTest {
    
    @Test
    fun `test invalid orientation symbol`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Orientation.fromSymbol('X')
        }
        assertEquals("Invalid orientation symbol: X", exception.message)
    }
    
    @Test
    fun `test invalid instruction symbol`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Instruction.fromSymbol('X')
        }
        assertEquals("Invalid instruction symbol: X", exception.message)
    }
    
    @Test
    fun `test invalid instruction string`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Instruction.parseInstructions("LRFX")
        }
        assertEquals("Invalid instruction symbol: X", exception.message)
    }
    
    @Test
    fun `test negative grid dimensions`() {
        val input = """
            -1 -1
            1 1 N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Grid dimensions must be between 0 and 50") ?: false)
    }
    
    @Test
    fun `test grid dimensions exceeding maximum`() {
        val input = """
            51 51
            1 1 N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Grid dimensions must be between 0 and 50") ?: false)
    }
    
    @Test
    fun `test robot position outside grid`() {
        val input = """
            5 5
            6 6 N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Robot position must be within grid boundaries") ?: false)
    }
    
    @Test
    fun `test empty input`() {
        val input = ""
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Input cannot be empty") ?: false)
    }
    
    @Test
    fun `test malformed input - missing grid dimensions`() {
        val input = """
            1 1 N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Invalid input format") ?: false)
    }
    
    @Test
    fun `test malformed input - missing robot position`() {
        val input = """
            5 5
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Invalid input format") ?: false)
    }
    
    @Test
    fun `test malformed input - missing instructions`() {
        val input = """
            5 5
            1 1 N
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val exception = assertThrows(IllegalArgumentException::class.java) {
            martianRobots.process(input)
        }
        assertTrue(exception.message?.contains("Invalid input format") ?: false)
    }
    
    @Test
    fun `test malformed input - invalid grid dimensions format`() {
        val input = """
            5 X
            1 1 N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        assertThrows(NumberFormatException::class.java) {
            martianRobots.process(input)
        }
    }
    
    @Test
    fun `test malformed input - invalid robot position format`() {
        val input = """
            5 5
            1 X N
            F
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        assertThrows(NumberFormatException::class.java) {
            martianRobots.process(input)
        }
    }
}