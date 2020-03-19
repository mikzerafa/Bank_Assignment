package com.bankSpring.bankSystemSpring.Backup

import com.bankSpring.bankSystemSpring.Model.Account
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

class BackupManager2()
{
    var accountsBackupLocation: String = "src/main/kotlin/com/bankSpring/bankSystemSpring/Backup/accounts_backup.json"
    var accounts = ArrayList<Account>()
    val mapper = jacksonObjectMapper()
    var text: String = ""
    var accountsJson = ""

    fun loadAccounts(): ArrayList<Account>
    {
        var output =  ArrayList<Account>()

        if(fileHasText())
        {
            text = File(accountsBackupLocation).bufferedReader().readText()
            val acc: ArrayList<Account> = mapper.readValue(text)
            output = acc
        }

        return output
    }

    fun modBackUp(accNo: String, acc: Account)
    {
        deleteAccount(accNo)
        backupAccount(acc)
    }

    fun saveAccounts(acc: ArrayList<Account>)
    {
        accountsJson = mapper.writeValueAsString(acc)
        File(accountsBackupLocation).printWriter().println(accountsJson)
    }
    fun backupAccount(acc: Account)
    {
        println("backup called")
        accounts.clear()
        accounts = loadAccounts()
        accounts.add(acc)
        accountsJson = mapper.writeValueAsString(accounts)
        println("backup accoiunt")
        //File(accountsBackupLocation).printWriter().println("hello")// + accountsJson)
        //File(accountsBackupLocation).appendText("hello")
        File(accountsBackupLocation).writeText(accountsJson)
    }

    fun writeHello()
    {
        File(accountsBackupLocation).printWriter().println("hello")
    }

    private fun clearBackup()
    {
        File(accountsBackupLocation).printWriter().println("")
    }

    private fun deleteAccount(accNo: String)
    {
        accounts.clear()
        if(fileHasText())
        {
            accounts = loadAccounts()
        }

        clearBackup()

        for(a in accounts)
        {
            if(a.accountNumber == accNo)
            {
                accounts.remove(a)
            }
        }

        saveAccounts(accounts)
    }

    fun fileHasText() : Boolean
    {
        return File(accountsBackupLocation).bufferedReader().readText().isNotEmpty()
    }


}