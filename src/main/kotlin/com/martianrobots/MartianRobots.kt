package com.martianrobots

import com.martianrobots.controller.RobotController
import com.martianrobots.model.*
import java.io.File

/**
 * Main application class for the Martian Robots challenge.
 */
class MartianRobots {
    
    /**
     * Processes the input and returns the output.
     */
    fun process(input: String): String {
        val lines = input.trim().lines()
        
        // Parse the grid dimensions
        val (width, height) = lines[0].split(" ").map { it.toInt() }
        val grid = Grid(width, height)
        val controller = RobotController(grid)
        
        // Process each robot
        val results = mutableListOf<Robot>()
        var lineIndex = 1
        
        while (lineIndex < lines.size) {
            // Parse the robot position and orientation
            val positionLine = lines[lineIndex++]
            val parts = positionLine.split(" ")
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientationSymbol = parts[2].single()
            val robot = Robot.create(x, y, orientationSymbol)
            
            // Parse the instructions
            val instructionsLine = lines[lineIndex++]
            val instructions = Instruction.parseInstructions(instructionsLine)
            
            // Process the instructions
            val finalRobot = controller.processInstructions(robot, instructions)
            results.add(finalRobot)
        }
        
        // Generate the output
        return results.joinToString("\n") { it.toString() }
    }
    
    companion object {
        /**
         * Main entry point for the application.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val input = if (args.isNotEmpty()) {
                File(args[0]).readText()
            } else {
                // Read from standard input
                generateSequence(::readLine).joinToString("\n")
            }
            
            val output = MartianRobots().process(input)
            println(output)
        }
    }
}