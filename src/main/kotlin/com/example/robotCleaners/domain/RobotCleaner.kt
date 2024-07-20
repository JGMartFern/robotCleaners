package com.example.robotCleaners.domain

data class RobotCleaner(var position: Position, var direction: Direction) {

    fun move(limitX: Int, limitY: Int) {
        val newPosition = when (direction) {
            Direction.N -> position.copy(y = position.y + 1)
            Direction.E -> position.copy(x = position.x + 1)
            Direction.S -> position.copy(y = position.y - 1)
            Direction.W -> position.copy(x = position.x - 1)
        }
        if (newPosition.x in 0..limitX && newPosition.y in 0..limitY) {
            position = newPosition
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.N -> Direction.W
            Direction.W -> Direction.S
            Direction.S -> Direction.E
            Direction.E -> Direction.N
        }
    }

    fun turnRight() {
        direction = when (direction) {
            Direction.N -> Direction.E
            Direction.E -> Direction.S
            Direction.S -> Direction.W
            Direction.W -> Direction.N
        }
    }
}