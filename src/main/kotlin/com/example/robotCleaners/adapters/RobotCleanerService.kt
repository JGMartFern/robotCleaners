package com.example.robotCleaners.adapters

import com.example.robotCleaners.domain.Direction
import com.example.robotCleaners.domain.Position
import com.example.robotCleaners.domain.RobotCleaner

class RobotCleanerService {

    fun processInstructions(input: String): List<RobotCleaner> {
        val lines = input.lines()
        val gridSize = lines[0].split(" ").map { it.toInt() }
        val limitX = gridSize[0]
        val limitY = gridSize[1]
        val robots = mutableListOf<RobotCleaner>()

        var i = 1
        while (i < lines.size) {
            if (i + 1 >= lines.size) {
                throw IllegalArgumentException("Missing commands for robot at line ${i + 1}")
            }

            val positionInfo = lines[i].split(" ")
            if (positionInfo.size < 3) {
                throw IllegalArgumentException("Invalid position info format at line ${i + 1}")
            }

            val initialPosition: Position
            val initialDirection: Direction

            try {
                initialPosition = Position(positionInfo[0].toInt(), positionInfo[1].toInt())
                initialDirection = Direction.valueOf(positionInfo[2])
            } catch (e: IllegalArgumentException) {
                if (positionInfo.size < 3) {
                    throw IllegalArgumentException("Invalid position info format at line ${i + 1}")
                } else {
                    throw IllegalArgumentException("Invalid direction at line ${i + 1}")
                }
            }

            val robot = RobotCleaner(initialPosition, initialDirection)
            val commands = lines[i + 1]
            robot.executeCommands(commands, limitX, limitY)
            robots.add(robot)
            i += 2
        }

        return robots
    }

    fun generateOutput(robots: List<RobotCleaner>): String {
        return buildString {
            appendLine("Final positions and facing of robot cleaners:")
            robots.forEach { robot ->
                appendLine("${robot.position.x} ${robot.position.y} ${robot.direction}")
            }
        }.trim()
    }
}
