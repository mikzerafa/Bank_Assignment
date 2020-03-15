package com.bankSpring.bankSystemSpring.Tests

import com.bankSpring.bankSystemSpring.Service.Bank
import com.bankSpring.bankSystemSpring.Model.Account
import org.junit.Assert
import org.junit.Test

class BankTests
{
    var beneficiaryName = "mikhail"
    var pinNumber = "1234"

    @Test
    fun createAccountTest() : Boolean
    {
        val newAccount = Bank.createAccount(beneficiaryName, pinNumber)

        return  Assert.assertEquals(newAccount.beneficiaryName, beneficiaryName).toString() != "" &&
                Assert.assertEquals(newAccount.pin, pinNumber).toString() != ""
    }

    @Test
    fun depositTest() : Boolean
    {
        val amount = 1.3
        val account = Bank.createAccount(beneficiaryName, pinNumber)
        val originalFunds = account.funds
        val depositedAccount = Bank.deposit(amount, account)

        println("account funds: " + account.funds)
        println("depositied Account funds: " + depositedAccount.funds)
        return Assert.assertEquals(depositedAccount.funds, originalFunds + amount, 0.1).toString() != ""
    }

    @Test
    fun withdrawTest() : Boolean
    {
        val originalAmount = 5.4
        val amount = 1.3
        var account = Bank.createAccount(beneficiaryName, pinNumber)
        account = Bank.deposit(originalAmount, account)
        val originalFunds = account.funds

        val withdrawAccount = Bank.withdraw(amount, account, pinNumber)

        return Assert.assertEquals(withdrawAccount.funds, originalFunds-amount, 0.0).toString() != ""
    }

    @Test
    fun dataTest() : Boolean
    {
        val acc = Bank.createAccount(beneficiaryName, pinNumber)
        val data = Bank.data(acc)

        return Assert.assertEquals(acc.accountNumber +", " + acc.beneficiaryName +", " + acc.funds, data).toString() != ""
    }
}