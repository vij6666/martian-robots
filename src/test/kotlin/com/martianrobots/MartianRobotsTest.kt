package com.martianrobots

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MartianRobotsTest {
    
    @Test
    fun `test sample input`() {
        val input = """
            5 3
            1 1 E
            RFRFRFRF
            3 2 N
            FRRFLLFFRRFLL
            0 3 W
            LLFFFLFLFL
        """.trimIndent()
        
        val expected = """
            1 1 E
            3 3 N LOST
            2 3 S
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val actual = martianRobots.process(input)
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `test robot turning`() {
        val input = """
            5 5
            1 1 N
            LRLR
        """.trimIndent()
        
        val expected = "1 1 N"
        
        val martianRobots = MartianRobots()
        val actual = martianRobots.process(input)
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `test robot moving`() {
        val input = """
            5 5
            1 1 N
            FFF
        """.trimIndent()
        
        val expected = "1 4 N"
        
        val martianRobots = MartianRobots()
        val actual = martianRobots.process(input)
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `test robot falling off grid`() {
        val input = """
            5 5
            0 0 S
            F
        """.trimIndent()
        
        val expected = "0 0 S LOST"
        
        val martianRobots = MartianRobots()
        val actual = martianRobots.process(input)
        
        assertEquals(expected, actual)
    }
    
    @Test
    fun `test robot scent preventing fall`() {
        val input = """
            5 5
            0 0 S
            F
            0 0 S
            F
        """.trimIndent()
        
        val expected = """
            0 0 S LOST
            0 0 S
        """.trimIndent()
        
        val martianRobots = MartianRobots()
        val actual = martianRobots.process(input)
        
        assertEquals(expected, actual)
    }
}