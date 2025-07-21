package com.martianrobots.model

/**
 * Represents the four cardinal directions a robot can face.
 */
enum class Orientation(val symbol: Char) {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');
    
    /**
     * Turns the orientation left (90 degrees counter-clockwise).
     */
    fun turnLeft(): Orientation = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }
    
    /**
     * Turns the orientation right (90 degrees clockwise).
     */
    fun turnRight(): Orientation = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }
    
    override fun toString(): String = symbol.toString()
    
    companion object {
        /**
         * Parses a character into an Orientation.
         */
        fun fromSymbol(symbol: Char): Orientation {
            return values().find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Invalid orientation symbol: $symbol")
        }
    }
}