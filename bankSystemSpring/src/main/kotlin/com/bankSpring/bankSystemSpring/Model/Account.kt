package com.bankSpring.bankSystemSpring.Model

import javax.persistence.*

@Entity
@Table(name= "Account")
data class Account( val beneficiaryName: String, val pinNumber: String)
{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "serial")
        val accountNumber: String? = null
        var funds :Double = 0.0

        fun deposit(amount: Double)
        {
                funds += amount
        }

        fun withdraw(amount: Double, enteredPin: String) : Boolean
        {
                var success = false
                if(testPin(enteredPin))
                {
                        funds -= amount
                        success = true
                }

                return success
        }

        fun testPin(enteredPin: String):Boolean
        {
                return enteredPin == pinNumber
        }

}


