package com.BankRiver.bankSystem.Model

import java.util.*
import javax.persistence.*

@Entity
@Table(name= "Account")
data class Account
(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val accountNumber: String,
    val beneficiaryName: String,
    @Column(length = 4)
    val pinNumber: String,
    var funds :Double
)



open class AccountServiceImplementation(private val account: Account) : AccountService
{



}