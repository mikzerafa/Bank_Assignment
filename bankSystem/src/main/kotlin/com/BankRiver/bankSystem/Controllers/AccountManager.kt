package com.BankRiver.bankSystem.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam

import com.BankRiver.bankSystem.Repositories.AccountRep;
import org.springframework.beans.factory.annotation.Autowired

@RestController
class AccountManager : AccountService{




    @GetMapping("/api/Account/AddAccount")
    fun addAccount()
    {

    }

    override fun withdraw(amount: Double, EnteredPin: String) : Boolean {

        val ok = testPin(EnteredPin)

        if(ok)
        {
            account.funds -= amount
        }

        return ok
    }

    override fun deposit(amount: Double) : Boolean
    {
        account.funds+= amount
        return true // No validation
    }

    override fun testPin(enteredPin: String): Boolean {
        return enteredPin == account.pinNumber
    }

    override fun getAccountNumber(): String {
        return account.accountNumber
    }

    override fun getBeneficiaryName(): String {
        return account.beneficiaryName
    }

    override fun getPinNumber(): String {
        return account.pinNumber
    }

    override fun getFunds(): Double {
        return account.funds
    }
}