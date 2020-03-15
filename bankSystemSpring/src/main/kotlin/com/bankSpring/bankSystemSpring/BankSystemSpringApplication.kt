package com.bankSpring.bankSystemSpring

import com.bankSpring.bankSystemSpring.Tests.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication



@SpringBootApplication
class BankSystemSpringApplication

fun main(args: Array<String>) {
	runApplication<BankSystemSpringApplication>(*args)

	val testRun = true
	val bankTests = BankTests()

	if(testRun)
	{
		if(bankTests.createAccountTest())		{
			println("create account test OKAY")
		} else {
			println("create account test FAIL")
		}

		if(bankTests.depositTest()) {
			println("deposit test OKAY")
		} else {
			println("depost test FAIL")
		}

		if( bankTests.withdrawTest() ) {
			println("withdraw test OKAY")
		} else {
			println("deposit test FAIL")
		}

		if(	bankTests.dataTest() ) {
			println("data test OKAY")
		} else {
			println("data test FAIL")
		}

	}


}

