package com.manav.securebanking.controller;


import com.manav.securebanking.dto.AccountCreateRequest;
import com.manav.securebanking.dto.AccountPatchRequest;
import com.manav.securebanking.dto.AccountResponse;
import com.manav.securebanking.dto.AccountUpdateRequest;
import com.manav.securebanking.dto.DepositRequest;
import com.manav.securebanking.dto.TransferRequest;
import com.manav.securebanking.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/accounts")
public class AccountController {

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }



    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountCreateRequest request){

        String response = accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping
    public List<AccountResponse> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("{id}")
    public AccountResponse getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }

    @PutMapping("{id}")
    public AccountResponse updateAccount(@PathVariable Long id , @Valid @RequestBody AccountUpdateRequest request){
        return accountService.updateAccount(id , request);
    }

    @PatchMapping("{id}")
    public AccountResponse patchAccount(@PathVariable Long id, @Valid @RequestBody AccountPatchRequest request ) {
        return accountService.patchAccount(id, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/deposit")

    public ResponseEntity<AccountResponse> deposit(
            @PathVariable Long id,
            @Valid @RequestBody DepositRequest request){

        return ResponseEntity.ok(
                accountService.deposit(id, request)
        );
    }

    @PostMapping("transfer")
    public ResponseEntity<String> transfer(
            @Valid @RequestBody TransferRequest request){

        return ResponseEntity.ok(
                accountService.transfer(request)
        );
    }







    private final AccountService accountService;





}