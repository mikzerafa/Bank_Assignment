package com.bankSpring.bankSystemSpring.Tests

class TestManager {

    fun runTests()
    {
        val bankTests = BankRepositoryTests()

        resultPrint("Create Account", bankTests.createAccountTest())
        resultPrint("Deposit", bankTests.depositTest())
        resultPrint("Withdraw", bankTests.withdrawTest() )
        resultPrint("Data", bankTests.dataTest())
    }

    private fun resultPrint(testName: String, result: Boolean)
    {
        print(testName + " test: ")
        if(result)
        {
            println("OKAY")
        }
        else
        {
            println("FAIL")
        }
    }

}