package com.bankSpring.bankSystemSpring.Controller

import com.bankSpring.bankSystemSpring.Backup.BackupManager
import com.bankSpring.bankSystemSpring.Service.BankService

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.RequestParam


import com.bankSpring.bankSystemSpring.Model.BankRepository
import com.bankSpring.bankSystemSpring.Model.Account



@RestController
class AccountController
{
    @Autowired
    lateinit var bankRepository: BankRepository
    var backupManager = BackupManager()
    val bankService = BankService()

    @GetMapping("/api/account/test")
    fun test(): String
    {
        return "connection okay"
    }

    @PostMapping("/api/account/load")
    fun loadAccounts() : String
    {

        for(acc in backupManager.loadAccounts())
        {
            bankRepository.save(acc)
        }

        return "accounts have been loaded!"
    }

    @PostMapping("/api/account/create")
    fun createAccount(@RequestParam(name="beneficiaryName") beneficiaryName: String, @RequestParam(name="pinNumber") pinNumber:String) : String
    {
        var output = "pin must only contain digits 0-9 and be 4 digits long"
        if(bankService.validNewPin(pinNumber))
        {
            val newAcc = bankService.createAccount(beneficiaryName,pinNumber)
            bankRepository.save(newAcc)
            //backupManager.backupAccounts(accountService)
            backupManager.backupAccount(newAcc)

            output = "account added"
        }

        return output
    }

    @GetMapping("/api/account/getAccountNumber")
    fun getAccountNumber(@RequestParam(name="beneficiaryName") beneficiaryName: String, @RequestParam(name="pinNumber")pinNumber: String) :String
    {
        var output = "account not found or invalid pin"
        for( acc in bankRepository.findAll())
        {
            if(acc.beneficiaryName == beneficiaryName && acc.testPin(pinNumber))
            {
                output = acc.accountNumber.toString()
            }
        }

        return output
    }



    @PostMapping("/api/account/deposit")
    fun deposit(@RequestParam (name="accountNumber")accountNumber: String, @RequestParam(name="amount") amount: Double) : String
    {
        var success = "deposit unsuccessful"

        if(accountExists(accountNumber))
        {

            val depositFloatAccount = bankService.deposit(amount, findAccount(accountNumber))


            //bank.deleteById(accountNumber)
            bankRepository.save(depositFloatAccount)
            backupManager.modBackUp(accountNumber, depositFloatAccount)

            success = "deposit successful" // no validation on deposits
        }

        return success
    }

    @PostMapping("/api/account/withdraw")
    fun withdraw(@RequestParam(name = "accountNumber") accountNumber: String, @RequestParam(name="amount") amount: Double, @RequestParam(name= "pinNumber") pinNumber: String) : String
    {
        var success = "Withdraw unsuccessful"


        if(accountExists(accountNumber))
        {
            if(findAccount(accountNumber).testPin(pinNumber))
            {
                val withdrawFloatAcc = bankService.withdraw(amount, findAccount(accountNumber), pinNumber)

                bankRepository.save(withdrawFloatAcc)
                backupManager.modBackUp(accountNumber, withdrawFloatAcc)
                success = "withdraw successful"
            }
        }


        return success
    }

    @PostMapping("api/account/transfer")
    fun transfer(@RequestParam(name = "fromAccount") fromAccountNumber: String, @RequestParam(name="toAccount") toAccountNumber: String,@RequestParam(name="amount") amount: Double, @RequestParam(name= "pinNumber") pinNumber: String) : String
    {
        var success = "transfer unsuccessful"

        if(accountExists(fromAccountNumber) && accountExists(toAccountNumber))
        {
            if(withdraw(fromAccountNumber, amount, pinNumber).contains(" successful")) {
                deposit(toAccountNumber, amount)
                success = "transfer of amount: " + amount + "  successful"
            }
        }

        return success
    }

    @GetMapping("/api/account/data")
    fun data(): String
    {
        var output = ""
        for( acc in bankRepository.findAll())
        {
            output += bankService.data(acc) + "\n"
        }
        return output
    }

    @GetMapping("/api/account/exists")
    fun accountExists(@RequestParam(name="AccountNumber") accountNumber: String) : Boolean
    {
        return bankRepository.existsById(accountNumber)
    }

    @GetMapping("/api/account/account")
    fun findAccount(@RequestParam(name="AccountNumber") accountNumber: String): Account
    {
        return bankRepository.findByIdOrNull(accountNumber)!!
    }

}
