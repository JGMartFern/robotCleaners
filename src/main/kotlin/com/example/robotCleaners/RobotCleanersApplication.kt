package com.example.robotCleaners

import com.example.robotCleaners.adapters.RobotCleanerService
import java.io.File

fun main() {
    val input = File("src/main/resources/static/sampleInstructions.txt").readText()

    val service = RobotCleanerService()

    val robots = service.processInstructions(input)
    val output = service.generateOutput(robots)

    println(output)
}