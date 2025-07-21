package com.martianrobots.model

/**
 * Represents a robot on the Martian surface.
 */
data class Robot(
    val position: Position,
    val orientation: Orientation,
    val isLost: Boolean = false
) {
    /**
     * Returns a string representation of the robot's position and orientation.
     */
    override fun toString(): String = "$position $orientation${if (isLost) " LOST" else ""}"
    
    /**
     * Creates a new robot with the given position and orientation.
     */
    companion object {
        fun create(x: Int, y: Int, orientationSymbol: Char): Robot {
            val orientation = Orientation.fromSymbol(orientationSymbol)
            return Robot(Position(x, y), orientation)
        }
    }
}