package com.martianrobots.controller

import com.martianrobots.model.Grid
import com.martianrobots.model.Instruction
import com.martianrobots.model.Robot

/**
 * Controls the movement of robots on the grid.
 */
class RobotController(private val grid: Grid) {
    
    /**
     * Processes a list of instructions for a robot and returns the final state of the robot.
     */
    fun processInstructions(robot: Robot, instructions: List<Instruction>): Robot =
        instructions.fold(robot) { current, instruction ->
            if (current.isLost) current
            else when (instruction) {
                Instruction.LEFT    -> current.copy(orientation = current.orientation.turnLeft())
                Instruction.RIGHT   -> current.copy(orientation = current.orientation.turnRight())
                Instruction.FORWARD -> moveForward(current)
            }
        }

    /**
     * Moves the robot forward one step in its current orientation.
     * If the move takes the robot off the grid, and there's no scent at the current position,
     * the robot is marked as lost and a scent is added at its last valid position.
     */
    private fun moveForward(robot: Robot): Robot {
        val nextPosition = robot.position.move(robot.orientation)
        return when {
            grid.isValidPosition(nextPosition) ->
                robot.copy(position = nextPosition)
            grid.hasScent(robot.position) ->
                robot
            else -> {
                grid.addScent(robot.position)
                robot.copy(isLost = true)
            }
        }
    }

}