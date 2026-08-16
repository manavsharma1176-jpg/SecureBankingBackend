package com.manav.securebanking.service;


import org.springframework.stereotype.Service;
import com.manav.securebanking.model.Account;

@Service
public class AccountService {

    public String createAccount(Account account){

        return "Account created for " + account.getName();

    }
}
