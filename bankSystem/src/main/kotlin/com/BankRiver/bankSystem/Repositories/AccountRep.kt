package com.BankRiver.bankSystem.Repositories

import com.BankRiver.bankSystem.Model.Account
import org.springframework.data.repository.CrudRepository


public interface AccountRep : CrudRepository<Account, String>
{

}