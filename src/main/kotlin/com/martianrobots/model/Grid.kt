package com.martianrobots.model

/**
 * Represents the rectangular grid of Mars.
 */
class Grid(val width: Int, val height: Int) {
    private val scents: MutableSet<Position> = mutableSetOf()
    
    /**
     * Checks if the given position is within the grid boundaries.
     */
    fun isValidPosition(position: Position): Boolean {
        return position.x in 0..width && position.y in 0..height
    }
    
    /**
     * Adds a scent at the given position.
     */
    fun addScent(position: Position) {
        scents.add(position)
    }
    
    /**
     * Checks if there is a scent at the given position.
     */
    fun hasScent(position: Position): Boolean {
        return position in scents
    }
}