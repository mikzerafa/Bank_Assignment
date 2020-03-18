package com.bankSpring.bankSystemSpring.Model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BankRepository  : CrudRepository<Account, String>{

}