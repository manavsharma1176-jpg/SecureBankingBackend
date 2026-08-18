package com.manav.securebanking.service;


import org.springframework.stereotype.Service;
import com.manav.securebanking.model.Account;
import com.manav.securebanking.repository.AccountRepository;
import java.util.List;

@Service
public class AccountService {

    public String createAccount(Account account){
        accountRepository.save(account);

        return "Account created for " + account.getName();

    }

    private AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){

        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts (){

        return accountRepository.findAll();

    }
}
