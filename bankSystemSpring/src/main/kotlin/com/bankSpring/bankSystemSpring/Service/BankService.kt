package com.bankSpring.bankSystemSpring.Service

import com.bankSpring.bankSystemSpring.Model.Account

class BankService {

    fun validNewPin(enteredPinNumber: String) : Boolean {
        var isValidPin = false

        if (enteredPinNumber.length == 4)
        {
            if(enteredPinNumber.toIntOrNull() != null)
            {
                isValidPin = true
            }
        }

        return isValidPin
    }
    fun createAccount(beneficiaryName: String, pinNumber: String) : Account
    {
        return Account(beneficiaryName, pinNumber)
    }


    fun deposit(amount: Double, account: Account) : Account
    {
        account.deposit(amount)
        return account
    }

    fun withdraw(amount: Double, account: Account, pin: String) : Account
    {
        account.withdraw(amount, pin)
        return account
    }

    fun data(account: Account) : String
    {
        var output = ""

        output += account.accountNumber + ", "
        output += account.beneficiaryName +", "
        output += account.funds

        return output
    }
}