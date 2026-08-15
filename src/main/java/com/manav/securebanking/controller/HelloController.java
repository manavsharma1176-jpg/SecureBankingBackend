package com.manav.securebanking.controller;

import com.manav.securebanking.model.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController


public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Banking Backend is running";
    }

    @PostMapping("/api/accounts")
    public String createAccount(@RequestBody Account account){
       return "Account created for " + account.getName();

    }
}