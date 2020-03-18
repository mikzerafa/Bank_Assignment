package com.bankSpring.bankSystemSpring.Controller

import com.bankSpring.bankSystemSpring.Backup.BackupManager
import com.bankSpring.bankSystemSpring.Service.BankService
import com.bankSpring.bankSystemSpring.Response.Response

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
    var response = Response().newResponse()

    @GetMapping("/api/account/test")
    fun test(): String
    {
        return "connection okay"
    }

    @GetMapping("/api/account/test2")
    fun test2():Response
    {
        response = Response().addAction(response,"testing connection")
        response = Response().addProcess(response,"n/a")
        response = Response().addResult(response, "okay")

        return response
    }

    @PostMapping("/api/account/load")
    fun loadAccounts() : Response
    {
        response = Response().addAction(response, "Loading")
        response = Response().addProcess(response, "loading accounts into database")

        try {
            for(acc in backupManager.loadAccounts())
            {
                response = Response().addProcess(response, "saving account")
                bankRepository.save(acc)

            }
        } catch(ex: Exception)
        {
            response = Response().addProcess(response, "caught exception")
            response = Response().addResult(response, ex.message!!)
        }


        response = Response().addResult(response,"accounts have been loaded!")

        return response
    }

    @PostMapping("/api/account/create")
    fun createAccount(@RequestParam(name="beneficiaryName") beneficiaryName: String,
                      @RequestParam(name="pinNumber") pinNumber:String) : Response
    {
        response = Response().addAction(response, "Creating Account")
        response = Response().addProcess(response, "validating Pin")

        if(bankService.validNewPin(pinNumber))
        {
            response = Response().addProcess(response, "creating account")
            val newAcc = bankService.createAccount(beneficiaryName,pinNumber)

            response = Response().addProcess(response, "saving account")
            bankRepository.save(newAcc)

            response = Response().addProcess(response, "backing up Account")
            backupManager.backupAccount(newAcc)

            response = Response().addResult(response, "account added successfully")
        }
        else
        {
            response = Response().addResult(response,"pin must only contain digits 0-9 and be 4 digits long")
        }


        return response
    }

    @GetMapping("/api/account/getAccountNumber")
    fun getAccountNumber(@RequestParam(name="beneficiaryName") beneficiaryName: String,
                         @RequestParam(name="pinNumber")pinNumber: String) :Response
    {
        response = Response().addAction(response, "getting Account number")
        response = Response().addProcess(response, "searching for account")
        response = Response().addResult(response,  "account not found or invalid pin")
        for( acc in bankRepository.findAll())
        {
            if(acc.beneficiaryName == beneficiaryName && acc.testPin(pinNumber))
            {
                response = Response().addResult(response,  acc.accountNumber.toString())
            }
        }

        return response
    }



    @PostMapping("/api/account/deposit")
    fun deposit(@RequestParam (name="accountNumber")accountNumber: String,
                @RequestParam(name="amount") amount: Double) : Response
    {
        response = Response().addAction(response, "depositing into account")
        response = Response().addResult(response, "deposit unsuccessful")
        response = Response().addProcess(response, "checking if account exists")

        if(bankRepository.existsById(accountNumber))
        {
            response = Response().addProcess(response, "depositing amoount: " + amount)
            val depositFloatAccount = bankService.deposit(amount, findAccount(accountNumber))


            response = Response().addProcess(response, "updating databases")
            bankRepository.save(depositFloatAccount)
            backupManager.modBackUp(accountNumber, depositFloatAccount)

            response = Response().addResult(response, "deposit successful")// no validation on deposits
        }

        return response
    }

    @PostMapping("/api/account/withdraw")
    fun withdraw(@RequestParam(name = "accountNumber") accountNumber: String,
                 @RequestParam(name="amount") amount: Double,
                 @RequestParam(name= "pinNumber") pinNumber: String) : Response
    {
        response = Response().addAction(response,"Withdrawing from account")
        response = Response().addResult(response, "Withdraw unsuccessful")

        response = Response().addProcess(response, "checking if account exists")
        if(bankRepository.existsById(accountNumber))
        {
            response = Response().addProcess(response, "testing pin number")
            if(findAccount(accountNumber).testPin(pinNumber))
            {
                response = Response().addProcess(response, "withdrawing from account amount: "+ amount)
                val withdrawFloatAcc = bankService.withdraw(amount, findAccount(accountNumber), pinNumber)

                response = Response().addProcess(response, "updating databases")
                bankRepository.save(withdrawFloatAcc)
                backupManager.modBackUp(accountNumber, withdrawFloatAcc)

               response = Response().addResult(response, "withdraw successful")
            }
        }


        return response
    }

    @PostMapping("api/account/transfer")
    fun transfer(@RequestParam(name = "fromAccount") fromAccountNumber: String,
                 @RequestParam(name="toAccount") toAccountNumber: String,
                 @RequestParam(name="amount") amount: Double,
                 @RequestParam(name= "pinNumber") pinNumber: String) : Response
    {
        response = Response().addAction(response, "Transfer")
        response = Response().addResult(response, "transfer unsuccessful")

        if(bankRepository.existsById(fromAccountNumber) && bankRepository.existsById(toAccountNumber))
        {
            if(findAccount(fromAccountNumber).testPin((pinNumber)))
            {
                val withdrawResponse = withdraw(fromAccountNumber, amount, pinNumber)
                response = Response().merge(response, withdrawResponse)
                val depositResponse = deposit(toAccountNumber, amount)
                response = Response().merge(response, depositResponse)
                response = Response().addResult(response, "transfer of amount: " + amount + "  successful")
            }
        }

        return response
    }

    @GetMapping("/api/account/data")
    fun data(): Response
    {
        response = Response().addAction(response, "getting account Data")

        for( acc in bankRepository.findAll())
        {
            response = Response().addProcess(response, bankService.data(acc))
        }
        return response
    }

    @GetMapping("/api/account/exists")
    fun accountExists(@RequestParam(name="AccountNumber") accountNumber: String) : Response
    {
        response = Response().addAction(response, "checking if account exists")

        response = Response().addResult(response, bankRepository.existsById(accountNumber).toString())
        return response
    }

    @GetMapping("/api/account/account")
    fun findAccount(@RequestParam(name="AccountNumber") accountNumber: String): Account
    {
        return bankRepository.findByIdOrNull(accountNumber)!!
    }

}
