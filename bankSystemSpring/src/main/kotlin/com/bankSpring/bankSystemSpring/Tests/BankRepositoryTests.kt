package com.bankSpring.bankSystemSpring.Tests

import com.bankSpring.bankSystemSpring.Model.BankRepository
import com.bankSpring.bankSystemSpring.Service.BankService
import org.junit.Assert
import org.junit.Test

class BankRepositoryTests
{
    val beneficiaryName = "mikhail"
    val pinNumber = "1234"
    val badPinNumber = "2333"
    val bankService =  BankService()

    @Test
    fun createAccountTest() : Boolean
    {
        val newAccount = bankService.createAccount(beneficiaryName, pinNumber)

        return  Assert.assertEquals(newAccount.beneficiaryName, beneficiaryName).toString() != "" &&
                Assert.assertEquals(newAccount.pinNumber, pinNumber).toString() != ""
    }

    @Test
    fun depositTest() : Boolean
    {
        val amount = 1.3
        val account = bankService.createAccount(beneficiaryName, pinNumber)
        val originalFunds = account.funds
        val depositedAccount = bankService.deposit(amount, account)

        println("account funds: " + account.funds)
        println("depositied Account funds: " + depositedAccount.funds)
        return Assert.assertEquals(depositedAccount.funds, originalFunds + amount, 0.1).toString() != ""
    }

    @Test
    fun withdrawTest() : Boolean
    {
        val originalAmount = 5.4
        val amount = 1.3
        var account = bankService.createAccount(beneficiaryName, pinNumber)
        account = bankService.deposit(originalAmount, account)
        val originalFunds = account.funds

        val withdrawAccount = bankService.withdraw(amount, account, pinNumber)

        return Assert.assertEquals(withdrawAccount.funds, originalFunds-amount, 0.0).toString() != ""
    }

    @Test
    fun dataTest() : Boolean
    {
        val acc = bankService.createAccount(beneficiaryName, pinNumber)
        val data = bankService.data(acc)

        return Assert.assertEquals(acc.accountNumber +", " + acc.beneficiaryName +", " + acc.funds, data).toString() != ""
    }
}