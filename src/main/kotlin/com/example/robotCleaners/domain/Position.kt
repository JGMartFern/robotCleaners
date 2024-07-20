package com.example.robotCleaners.domain

data class Position(var x: Int, var y: Int) {
    fun isWithinBounds(limitX: Int, limitY: Int): Boolean {
        return x in 0..limitX && y in 0..limitY
    }

    fun move(direction: Direction) {
        when (direction) {
            Direction.N -> y++
            Direction.E -> x++
            Direction.S -> y--
            Direction.W -> x--
        }
    }
}