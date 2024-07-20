package com.example.robotCleaners

import com.example.robotCleaners.adapters.RobotCleanerService
import com.example.robotCleaners.application.RobotCleanerController

fun main() {
	val controller = RobotCleanerController()
	val robotCleanerService = RobotCleanerService(controller)

	val fileName = "static/sampleInstructions.txt"
	val inputStream = object {}.javaClass.classLoader.getResourceAsStream(fileName)

	if (inputStream == null) {
		println("The file could not be found")
		return
	}

	val inputString = inputStream.bufferedReader().use { it.readText() }
	val robots = robotCleanerService.processInstructions(inputString)
	val output = robotCleanerService.generateOutput(robots)

	println(output)
}