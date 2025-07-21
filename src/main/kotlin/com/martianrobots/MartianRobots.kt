package com.martianrobots

import com.martianrobots.controller.RobotController
import com.martianrobots.model.*
import java.io.File

class MartianRobots {
    private data class ValidatedInput(
        val grid: Grid,
        val robotsWithInstructions: List<Pair<Robot, List<Instruction>>>
    )

    /**
     * Processes the input and returns the output.
     */
    fun process(input: String): String =
        validateInput(input).let { (grid, robotsWithInstructions) ->
            val controller = RobotController(grid)
            robotsWithInstructions
                .map { (robot, instructions) -> controller.processInstructions(robot, instructions) }
                .joinToString("\n")
        }

    /**
     * Validates the input and returns a ValidatedInput object.
     */
    private fun validateInput(input: String): ValidatedInput {
        val lines = input.trim().lines().filter { it.isNotBlank() }
        require(lines.isNotEmpty()) { "Input cannot be empty" }
        require(lines.size >= 3) { "Invalid input format: missing grid dimensions, robot position, or instructions" }

        val (width, height) = lines[0].split(" ").map { it.toInt() }
            .also { require(it.size == 2) { "Grid dimensions must be two integers separated by a space" } }

        require(width in 0..50 && height in 0..50) { "Grid dimensions must be between 0 and 50" }
        val grid = Grid(width, height)

        val robotsWithInstructions = lines
            .drop(1)
            .chunked(2)
            .map { (positionLine, instructionsLine) ->
                val (x, y, orientationSymbol) = positionLine.split(" ")
                    .also { require(it.size == 3) { "Robot position must be two integers and an orientation symbol" } }
                val robotX = x.toInt()
                val robotY = y.toInt()
                require(robotX in 0..width && robotY in 0..height) { "Robot position must be within grid boundaries" }
                val robot = Robot.create(robotX, robotY, orientationSymbol.single())
                val instructions = Instruction.parseInstructions(instructionsLine)
                robot to instructions
            }

        return ValidatedInput(grid, robotsWithInstructions)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = if (args.isNotEmpty()) File(args[0]).readText()
            else generateSequence(::readLine).joinToString("\n")
            println(MartianRobots().process(input))
        }
    }
}