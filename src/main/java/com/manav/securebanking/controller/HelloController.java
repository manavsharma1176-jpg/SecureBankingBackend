package com.manav.securebanking.controller;

import com.manav.securebanking.model.Account;
import com.manav.securebanking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Login Feature";
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account account){

        String response = accountService.createAccount(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping("/api/accounts")
    public List<Account> getAllAccount(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/api/accounts/{id}")
    public Account getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PutMapping("/api/accounts/{id}")
    public Account updateAccount(@PathVariable Long id , @RequestBody Account account){
        return accountService.updateAccount(id , account);
    }

    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }





    private AccountService accountService;

    public HelloController(AccountService accountService){
        this.accountService = accountService;
    }



}