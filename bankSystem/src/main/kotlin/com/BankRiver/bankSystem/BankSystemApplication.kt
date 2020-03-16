package com.BankRiver.bankSystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import com.BankRiver.bankSystem.Model.Bank


@SpringBootApplication
class BankSystemApplication

fun main(args: Array<String>) {
	runApplication<BankSystemApplication>(*args)
	val bank = Bank()
	//Initialize bank


}
