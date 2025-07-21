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
    fun processInstructions(robot: Robot, instructions: List<Instruction>): Robot {
        if (robot.isLost) return robot

        return instructions
            .fold(robot) { instructedRobot, instruction ->
                if (instructedRobot.isLost) instructedRobot
                else when (instruction) {
                    Instruction.LEFT    -> instructedRobot.copy(orientation = instructedRobot.orientation.turnLeft())
                    Instruction.RIGHT   -> instructedRobot.copy(orientation = instructedRobot.orientation.turnRight())
                    Instruction.FORWARD -> moveForward(instructedRobot)
                }
            }
    }

    /**
     * Moves the robot forward one step in its current orientation.
     * If the move takes the robot off the grid, and there's no scent at the current position,
     * the robot is marked as lost and a scent is added at its last valid position.
     */
    private fun moveForward(robot: Robot): Robot {
        val newPosition = robot.position.move(robot.orientation)
        
        // Check if the new position is valid
        if (!grid.isValidPosition(newPosition)) {
            // Check if there's a scent at the current position
            if (grid.hasScent(robot.position)) {
                // If there's a scent, the robot stays in place
                return robot
            }
            
            // Add a scent at the current position
            grid.addScent(robot.position)
            
            // Mark the robot as lost
            return robot.copy(isLost = true)
        }
        
        // Move the robot to the new position
        return robot.copy(position = newPosition)
    }
}