package com.BankRiver.bankSystem.Model

import java.util.*
import javax.persistence.Entity
import kotlin.collections.ArrayList

@Entity
class Bank (){
    val accounts = ArrayList<Account>()
}

interface BankService
{
    fun createAccount(beneficiaryName: String, pinNumber: String) :Boolean
    fun accountExists(beneficiaryName: String, pinNumber: String): Boolean
    fun accountExists(accountNumber: String): Boolean

    fun findAccountNumber(beneficiaryName: String, pinNumber: String) : String
    fun findAccount(accountNumber: String) : Account
    fun transferFunds(amount: Double, senderAccountNumber: String, senderPinNumber: String, receiverAccountNumber: String) :Boolean
    fun deposit(amount: Double, accountNumber: String) : Boolean
    fun withdraw(amount: Double, accountNumber: String, pinNumber: String) : Boolean

    fun accountsAvailable() : Int
}

class BankServiceImplementation(private val bank : Bank) : BankService
{
    override fun createAccount(beneficiaryName: String, pinNumber: String): Boolean {
        val account = Account(UUID.randomUUID().toString(), beneficiaryName, pinNumber, 0.0)
        bank.accounts.add(account)
        return true //no validation
    }

    override fun accountExists(beneficiaryName: String, pinNumber: String) : Boolean {
        return findAccountNumber(beneficiaryName, pinNumber) != ""
    }

    override fun accountExists(accountNumber: String) : Boolean{
        var output = false
        for(acc in bank.accounts)
        {
            if(acc.accountNumber == accountNumber) {
                output = true
            }
        }
        return output
    }

    override fun findAccountNumber(beneficiaryName: String, pinNumber: String) : String {
        var output = ""

        for(acc in bank.accounts)
        {
            if(acc.beneficiaryName == beneficiaryName)
            {
                if(acc.pinNumber == pinNumber)
                {
                    output = acc.accountNumber
                }
            }
        }

        return output
    }

    override fun findAccount(accountNumber: String): Account {
        var accIndex = 0
        var accFound = 0

        for(acc in bank.accounts)
        {
            if(acc.accountNumber == accountNumber)
            {
                accFound = accIndex
            }
            accIndex++
        }

        return bank.accounts.get(accFound) // will return the first account if no accounts found
    }

    override fun transferFunds(amount: Double, senderAccountNumber: String, senderPinNumber: String, receiverAccountNumber: String): Boolean {
        val sender = findAccount(senderAccountNumber)
        val receiver = findAccount(receiverAccountNumber)

        AccountServiceImplementation(sender).withdraw(amount, senderPinNumber)
        return AccountServiceImplementation(receiver).deposit(amount)
    }

    override fun deposit(amount: Double, accountNumber: String): Boolean {
        val acc = findAccount(accountNumber)

        return AccountServiceImplementation(acc).deposit(amount)
    }

    override fun withdraw(amount: Double, accountNumber: String, pinNumber: String): Boolean {
        val acc = findAccount(accountNumber)

        return AccountServiceImplementation(acc).withdraw(amount, pinNumber)
    }

    override fun accountsAvailable(): Int {
        return bank.accounts.size
    }

}