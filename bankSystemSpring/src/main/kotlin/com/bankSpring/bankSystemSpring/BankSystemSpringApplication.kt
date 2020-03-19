package com.bankSpring.bankSystemSpring

import com.bankSpring.bankSystemSpring.Tests.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class BankSystemSpringApplication

fun main(args: Array<String>) {

	val runningTests: Boolean = true
	val testManager = TestManager()

	runApplication<BankSystemSpringApplication>(*args)


	if(runningTests)
	{
		testManager.runTests()
	}
}

