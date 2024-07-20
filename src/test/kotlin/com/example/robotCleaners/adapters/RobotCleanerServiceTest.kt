package com.example.robotCleaners.adapters

import com.example.robotCleaners.application.RobotCleanerController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RobotCleanerServiceTest {

    private val controller = RobotCleanerController()
    private val service = RobotCleanerService(controller)

    @Test
    fun `should process instructions and generate correct output`() {
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent()

        val robots = service.processInstructions(input)
        val output = service.generateOutput(robots)

        val expectedOutput = """
            Final positions and facing of robot cleaners:
            1 3 N
            5 1 E
            
        """.trimIndent()

        println("Actual output:\n$output")
        println("Expected output:\n$expectedOutput")

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should handle empty input correctly`() {
        val input = "5 5"
        val robots = service.processInstructions(input)

        assertEquals(0, robots.size)
    }

    @Test
    fun `should throw exception for missing commands`() {
        val input = """
            5 5
            1 2 N
        """.trimIndent()

        val exception = assertThrows<IllegalArgumentException> {
            service.processInstructions(input)
        }
        assert(exception.message?.contains("Missing commands for robot at line 1") == true)
    }

    @Test
    fun `should throw exception for invalid position info`() {
        val input = """
            5 5
            1 2
            LMLMLMLMM
        """.trimIndent()

        val exception = assertThrows<IllegalArgumentException> {
            service.processInstructions(input)
        }
        assert(exception.message?.contains("Invalid position info format at line 1") == true)
    }

    @Test
    fun `should throw exception for invalid direction`() {
        val input = """
            5 5
            1 2 X
            LMLMLMLMM
        """.trimIndent()

        val exception = assertThrows<IllegalArgumentException> {
            service.processInstructions(input)
        }
        assert(exception.message?.contains("Invalid direction") == true)
    }
}