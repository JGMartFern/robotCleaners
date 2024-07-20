package com.example.robotCleaners.application

import com.example.robotCleaners.domain.Direction
import com.example.robotCleaners.domain.Position
import com.example.robotCleaners.domain.RobotCleaner
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotCleanerControllerTest {

    private val robotCleanerController = RobotCleanerController()
    private val limitX = 5
    private val limitY = 5
    private val robotCleaner = RobotCleaner(Position(1, 2), Direction.N)

    @Test
    fun `should move following commands`() {

        val commands = "LMLMLMLMM"
        val endPosition = Position(1, 3)
        val endDirection = Direction.N

        robotCleanerController.executeCommands(robotCleaner, commands, limitX, limitY)

        assertEquals(endPosition, robotCleaner.position)
        assertEquals(endDirection, robotCleaner.direction)

        }

    @Test
    fun `should throw exception for invalid command format`() {

        val invalidCommands = "INVALID_COMMAND"

        assertThrows(IllegalArgumentException::class.java) {
            robotCleanerController.executeCommands(robotCleaner, invalidCommands, limitX, limitY)
        }
    }
}