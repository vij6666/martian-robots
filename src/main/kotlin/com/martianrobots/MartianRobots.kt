package com.martianrobots

import com.martianrobots.controller.RobotController
import com.martianrobots.model.*
import java.io.File

/**
 * Main application class for the Martian Robots challenge.
 */
class MartianRobots {

    /**
     * Data class to hold validated input data.
     */
    private data class ValidatedInput(
        val grid: Grid,
        val robotsWithInstructions: List<Pair<Robot, List<Instruction>>>
    )

    /**
     * Processes the input and returns the output.
     */
    fun process(input: String): String {
        // Validate input and get validated data
        val validatedData = validateInput(input)
        val grid = validatedData.grid
        val robotsWithInstructions = validatedData.robotsWithInstructions

        val controller = RobotController(grid)

        // Process each robot
        val results = mutableListOf<Robot>()

        for ((robot, instructions) in robotsWithInstructions) {
            // Process the instructions
            val finalRobot = controller.processInstructions(robot, instructions)
            results.add(finalRobot)
        }

        // Generate the output
        return results.joinToString("\n") { it.toString() }
    }

    /**
     * Validates the input and returns a ValidatedInput object.
     */
    private fun validateInput(input: String): ValidatedInput {
        if (input.trim().isEmpty()) {
            throw IllegalArgumentException("Input cannot be empty")
        }

        val lines = input.trim().lines()

        if (lines.isEmpty()) {
            throw IllegalArgumentException("Input cannot be empty")
        }

        // Parse the grid dimensions
        if (lines.size < 3) {
            throw IllegalArgumentException("Invalid input format: missing grid dimensions, robot position, or instructions")
        }

        val parts = lines[0].split(" ")
        if (parts.size != 2) {
            throw IllegalArgumentException("Invalid input format: grid dimensions must be two integers separated by a space")
        }

        val width = parts[0].toInt()
        val height = parts[1].toInt()

        // Validate grid dimensions
        if (width < 0 || height < 0) {
            throw IllegalArgumentException("Grid dimensions must be positive")
        }

        if (width > 50 || height > 50) {
            throw IllegalArgumentException("Grid dimensions cannot exceed 50")
        }

        val grid = Grid(width, height)

        // Process each robot
        val robotsWithInstructions = mutableListOf<Pair<Robot, List<Instruction>>>()
        var lineIndex = 1

        while (lineIndex < lines.size) {
            // Check if we have enough lines for a robot
            if (lineIndex + 1 >= lines.size) {
                throw IllegalArgumentException("Invalid input format: missing robot position or instructions")
            }

            // Parse the robot position and orientation
            val positionLine = lines[lineIndex++]
            val positionParts = positionLine.split(" ")

            if (positionParts.size != 3) {
                throw IllegalArgumentException("Invalid input format: robot position must be two integers and an orientation symbol separated by spaces")
            }

            val x = positionParts[0].toInt()
            val y = positionParts[1].toInt()
            val orientationSymbol = positionParts[2].single()

            // Validate robot position
            if (x < 0 || y < 0 || x > width || y > height) {
                throw IllegalArgumentException("Robot position must be within grid boundaries")
            }

            val robot = Robot.create(x, y, orientationSymbol)

            // Parse the instructions
            val instructionsLine = lines[lineIndex++]
            val instructions = Instruction.parseInstructions(instructionsLine)

            robotsWithInstructions.add(robot to instructions)
        }

        return ValidatedInput(grid, robotsWithInstructions)
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