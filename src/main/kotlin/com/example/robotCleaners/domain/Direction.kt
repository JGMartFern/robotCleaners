package com.example.robotCleaners.domain

enum class Direction {
    N, E, S, W;

    fun left(): Direction {
        return when (this) {
            N -> W
            W -> S
            S -> E
            E -> N
        }
    }

    fun right(): Direction {
        return when (this) {
            N -> E
            E -> S
            S -> W
            W -> N
        }
    }
}