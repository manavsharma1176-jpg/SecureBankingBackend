package com.manav.securebanking.controller;

import com.manav.securebanking.model.Account;
import com.manav.securebanking.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/accounts")
    public List<Account> getAllAccount(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/api/accounts/{id}")
    public Account getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }




    private AccountService accountService;

    public HelloController(AccountService accountService){
        this.accountService = accountService;
    }



}