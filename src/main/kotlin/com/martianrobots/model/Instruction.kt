package com.martianrobots.model

/**
 * Represents the possible instructions for a robot.
 */
enum class Instruction(val symbol: Char) {
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F');
    
    companion object {
        /**
         * Parses a character into an Instruction.
         */
        fun fromSymbol(symbol: Char): Instruction {
            return entries.find { it.symbol == symbol }
                ?: throw IllegalArgumentException("Invalid instruction symbol: $symbol")
        }
        
        /**
         * Parses a string into a list of Instructions.
         */
        fun parseInstructions(instructionsStr: String): List<Instruction> {
            return instructionsStr.map { fromSymbol(it) }
        }
    }
}