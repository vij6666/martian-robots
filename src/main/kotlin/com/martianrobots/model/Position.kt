package com.martianrobots.model

/**
 * Represents a position on the grid with x and y coordinates.
 */
data class Position(val x: Int, val y: Int) {
    /**
     * Creates a new position by moving one step in the specified direction.
     */
    fun move(orientation: Orientation): Position = when (orientation) {
        Orientation.NORTH -> Position(x, y + 1)
        Orientation.EAST -> Position(x + 1, y)
        Orientation.SOUTH -> Position(x, y - 1)
        Orientation.WEST -> Position(x - 1, y)
    }

    override fun toString(): String = "$x $y"
}
