package com.example.robotCleaners.domain

data class RobotCleaner(var position: Position, var direction: Direction) {

    fun executeCommands(commands: String, limitX: Int, limitY: Int) {
        for (command in commands) {
            when (command) {
                'L' -> turnLeft()
                'R' -> turnRight()
                'M' -> move(limitX, limitY)
                else -> throw IllegalArgumentException("Invalid command: $command")
            }
        }
    }

    private fun turnLeft() {
        direction = direction.left()
    }

    private fun turnRight() {
        direction = direction.right()
    }

    private fun move(limitX: Int, limitY: Int) {
        position.move(direction)
        if (!position.isWithinBounds(limitX, limitY)) {
            throw IllegalArgumentException("Position out of bounds: $position")
        }
    }
}