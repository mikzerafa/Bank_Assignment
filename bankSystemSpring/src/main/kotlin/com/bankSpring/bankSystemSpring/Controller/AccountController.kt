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
import com.bankSpring.bankSystemSpring.Response.ResponseService


@RestController
class AccountController
{
    @Autowired
    lateinit var bankRepository: BankRepository
    var backupManager = BackupManager()
    val bankService = BankService()
    val responseService = ResponseService()
    var response = Response()

    @GetMapping("/api/account/test")
    fun test(): String
    {
        return "connection okay"
    }

    @GetMapping("/api/account/test2")
    fun test2(): Response
    {
        response = responseService.addAction(response,"testing connection")
        response = responseService.addProcess(response,"n/a")
        response = responseService.addResult(response, "okay")

        return response
    }

    @PostMapping("/api/account/load")
    fun loadAccounts() : Response
    {
        response = responseService.addAction(response, "Loading")
        response = responseService.addProcess(response, "loading accounts into database")

        try {
            for(acc in backupManager.loadAccounts())
            {
                response = responseService.addProcess(response, "saving account")
                bankRepository.save(acc)

            }
        } catch(ex: Exception)
        {
            response = responseService.addProcess(response, "caught exception")
            response = responseService.addResult(response, ex.message!!)
        }


        response = responseService.addResult(response,"accounts have been loaded!")

        return response
    }

    @PostMapping("/api/account/create")
    fun createAccount(@RequestParam(name="beneficiaryName") beneficiaryName: String,
                      @RequestParam(name="pinNumber") pinNumber:String) : Response
    {
        response = responseService.addAction(response, "Creating Account")
        response = responseService.addProcess(response, "validating Pin")

        if(bankService.validNewPin(pinNumber))
        {
            response = responseService.addProcess(response, "creating account")
            val newAcc = bankService.createAccount(beneficiaryName,pinNumber)

            response = responseService.addProcess(response, "saving account")
            bankRepository.save(newAcc)

            response = responseService.addProcess(response, "backing up Account")
            backupManager.backupAccount(newAcc)

            response = responseService.addResult(response, "account added successfully")
        }
        else
        {
            response = responseService.addResult(response,"pin must only contain digits 0-9 and be 4 digits long")
        }


        return response
    }

    @GetMapping("/api/account/getAccountNumber")
    fun getAccountNumber(@RequestParam(name="beneficiaryName") beneficiaryName: String,
                         @RequestParam(name="pinNumber")pinNumber: String) : Response
    {
        response = responseService.addAction(response, "getting Account number")
        response = responseService.addProcess(response, "searching for account")
        response = responseService.addResult(response,  "account not found or invalid pin")
        for( acc in bankRepository.findAll())
        {
            if(acc.beneficiaryName == beneficiaryName && acc.testPin(pinNumber))
            {
                response = responseService.addResult(response,  acc.accountNumber.toString())
            }
        }

        return response
    }



    @PostMapping("/api/account/deposit")
    fun deposit(@RequestParam (name="accountNumber")accountNumber: String,
                @RequestParam(name="amount") amount: Double) : Response
    {
        response = responseService.addAction(response, "depositing into account")
        response = responseService.addResult(response, "deposit unsuccessful")
        response = responseService.addProcess(response, "checking if account exists")

        if(bankRepository.existsById(accountNumber))
        {
            response = responseService.addProcess(response, "depositing amoount: " + amount)
            val depositFloatAccount = bankService.deposit(amount, findAccount(accountNumber))


            response = responseService.addProcess(response, "updating databases")
            bankRepository.save(depositFloatAccount)
            backupManager.modBackUp(accountNumber, depositFloatAccount)

            response = responseService.addResult(response, "deposit successful")// no validation on deposits
        }

        return response
    }

    @PostMapping("/api/account/withdraw")
    fun withdraw(@RequestParam(name = "accountNumber") accountNumber: String,
                 @RequestParam(name="amount") amount: Double,
                 @RequestParam(name= "pinNumber") pinNumber: String) : Response
    {
        response = responseService.addAction(response,"Withdrawing from account")
        response = responseService.addResult(response, "Withdraw unsuccessful")

        response = responseService.addProcess(response, "checking if account exists")
        if(bankRepository.existsById(accountNumber))
        {
            response = responseService.addProcess(response, "testing pin number")
            if(findAccount(accountNumber).testPin(pinNumber))
            {
                response = responseService.addProcess(response, "withdrawing from account amount: "+ amount)
                val withdrawFloatAcc = bankService.withdraw(amount, findAccount(accountNumber), pinNumber)

                response = responseService.addProcess(response, "updating databases")
                bankRepository.save(withdrawFloatAcc)
                backupManager.modBackUp(accountNumber, withdrawFloatAcc)

               response = responseService.addResult(response, "withdraw successful")
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
        response = responseService.addAction(response, "Transfer")
        response = responseService.addResult(response, "transfer unsuccessful")

        if(bankRepository.existsById(fromAccountNumber) && bankRepository.existsById(toAccountNumber))
        {
            if(findAccount(fromAccountNumber).testPin((pinNumber)))
            {
                val withdrawResponse = withdraw(fromAccountNumber, amount, pinNumber)
                response = responseService.merge(response, withdrawResponse)
                val depositResponse = deposit(toAccountNumber, amount)
                response = responseService.merge(response, depositResponse)
                response = responseService.addResult(response, "transfer of amount: " + amount + "  successful")
            }
        }

        return response
    }

    @GetMapping("/api/account/data")
    fun data(): Response
    {
        var accountsStr = ""
        response = responseService.addAction(response, "getting account Data")

        if(bankRepository.findAll().count()>0)
        {
            for( acc in bankRepository.findAll())
            {
                response = responseService.addProcess(response, bankService.data(acc))
                accountsStr += (bankService.data(acc))
            }

            response = responseService.addResult(response, accountsStr)
        }
        else
        {
            response = responseService.addProcess(response, "no accounts found")
            response = responseService.addResult(response, "no accounts found in Database!")
        }

        return response
    }

    @GetMapping("/api/account/exists")
    fun accountExists(@RequestParam(name="AccountNumber") accountNumber: String) : Response
    {
        response = responseService.addAction(response, "checking if account exists")

        response = responseService.addResult(response, bankRepository.existsById(accountNumber).toString())
        return response
    }

    @GetMapping("/api/account/account")
    fun findAccount(@RequestParam(name="AccountNumber") accountNumber: String): Account
    {
        return bankRepository.findByIdOrNull(accountNumber)!!
    }

}
