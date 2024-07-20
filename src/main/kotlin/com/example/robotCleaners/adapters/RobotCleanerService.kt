package com.example.robotCleaners.adapters

import com.example.robotCleaners.application.RobotCleanerController
import com.example.robotCleaners.domain.Direction
import com.example.robotCleaners.domain.Position
import com.example.robotCleaners.domain.RobotCleaner

class RobotCleanerService(private val controller: RobotCleanerController) {

    fun processInstructions(input: String): List<RobotCleaner> {
        val lines = input.lines()

        if (lines.isEmpty()) {
            throw IllegalArgumentException("Instructions are empty")
        }

        val gridSize = lines[0].split(" ").map { it.toInt() }
        val limitX = gridSize[0]
        val limitY = gridSize[1]
        val robots = mutableListOf<RobotCleaner>()

        var i = 1
        while (i < lines.size) {

            if (i >= lines.size) {
                throw IllegalArgumentException("Incomplete instructions")
            }

            val positionInfo = lines.getOrNull(i)?.split(" ")

            if (positionInfo == null || positionInfo.size < 3) {
                throw IllegalArgumentException("Invalid position info format at line $i")
            }

            val initialPosition = Position(
                positionInfo[0].toIntOrNull() ?: throw IllegalArgumentException("Invalid x coordinate"),
                positionInfo[1].toIntOrNull() ?: throw IllegalArgumentException("Invalid y coordinate")
            )
            val initialDirection = Direction.entries.find { it.name == positionInfo[2] }
                ?: throw IllegalArgumentException("Invalid direction")

            val robot = RobotCleaner(initialPosition, initialDirection)

            val commands = lines.getOrNull(i + 1)
                ?: throw IllegalArgumentException("Missing commands for robot at line $i")

            try {
                controller.executeCommands(robot, commands, limitX, limitY)
            } catch (e: IllegalArgumentException) {
                println("Could not execute commands for robot ${robots.size+1}: ${e.message}")
            }
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
        }
    }
}