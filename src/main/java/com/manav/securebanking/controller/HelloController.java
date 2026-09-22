package com.manav.securebanking.controller;

import com.manav.securebanking.dto.*;
import com.manav.securebanking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.manav.securebanking.dto.TransferRequest;

import java.util.List;

@RestController


public class HelloController {

    public HelloController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/api/v1/hello")
    public String hello() {
        return "Login Feature";
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountCreateRequest request){

        String response = accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping("/api/accounts")
    public List<AccountResponse> getAllAccount(){
        return accountService.getAllAccounts();
    }

    @GetMapping("/api/accounts/{id}")
    public AccountResponse getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PutMapping("/api/accounts/{id}")
    public AccountResponse updateAccount(@PathVariable Long id , @RequestBody AccountUpdateRequest request){
        return accountService.updateAccount(id , request);
    }

    @PatchMapping("/api/accounts/{id}")
    public AccountResponse patchAccount(@PathVariable Long id, @RequestBody AccountPatchRequest request ) {
        return accountService.patchAccount(id, request);
    }

    @DeleteMapping("/api/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/accounts/{id}/deposit")

    public ResponseEntity<AccountResponse> deposit(
            @PathVariable Long id,
            @Valid @RequestBody DepositRequest request){

        return ResponseEntity.ok(
                accountService.deposit(id, request)
        );
    }

    @PostMapping("/api/accounts/transfer")
    public ResponseEntity<String> transfer(
            @Valid @RequestBody TransferRequest request){

        return ResponseEntity.ok(
                accountService.transfer(request)
        );
    }







    private AccountService accountService;





}