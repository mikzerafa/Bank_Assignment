package com.BankRiver.bankSystem.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam

import com.BankRiver.bankSystem.Model.*;

@RestController
class BankManager
{
    @GetMapping("/api/Bank/newBank")
    fun newBank() : Bank
    {
        val bank = Bank()
        return bank;
    }

    @GetMapping("/api/Bank/CreateAccount")
    fun createAccount()
    {
        //BankServiceImplementation()
    }

}