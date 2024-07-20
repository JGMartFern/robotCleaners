package com.example.robotCleaners.application

import com.example.robotCleaners.domain.RobotCleaner

class RobotCleanerController {

    fun executeCommands(robotCleaner: RobotCleaner, commands: String, limitX: Int, limitY: Int) {

        commands.forEach { command ->
            when (command) {
                'L' -> robotCleaner.turnLeft()
                'R' -> robotCleaner.turnRight()
                'M' -> robotCleaner.move(limitX, limitY)
                else -> throw IllegalArgumentException("Invalid format in the provided instructions: $command")
            }
        }
    }
}