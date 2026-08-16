package com.manav.securebanking.controller;

import com.manav.securebanking.model.Account;
import com.manav.securebanking.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController


public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Login Feature";
    }

    @PostMapping("/api/accounts")
    public String createAccount(@RequestBody Account account){
       return accountService.createAccount(account);

    }

    private AccountService accountService;

    public HelloController(AccountService accountService){
        this.accountService = accountService;
    }
}