package com.bankSpring.bankSystemSpring.Model

import javax.persistence.*

@Entity
@Table(name= "Account")
data class Account( val name: String, val pin: String)
{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "serial")
        val accountNumber: String? = null
        val beneficiaryName: String = name
        private val pinNumber: String = pin
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


