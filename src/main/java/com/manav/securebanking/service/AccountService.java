package com.manav.securebanking.service;


import com.manav.securebanking.dto.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.manav.securebanking.model.Account;
import com.manav.securebanking.repository.AccountRepository;
import org.springframework.web.server.ResponseStatusException;
import com.manav.securebanking.dto.AccountPatchRequest;


import com.manav.securebanking.exception.AccountNotFoundException;
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

    public List<AccountResponse> getAllAccounts (){

        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::fromAccount)
                .toList();

    }

    public AccountResponse getAccountById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not Found"));
        return AccountResponse.fromAccount(account);
    }

    public AccountResponse updateAccount(Long id , Account account){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));

        existingAccount.setName(account.getName());
        existingAccount.setAccountType(account.getAccountType());

        Account updatedAccount = accountRepository.save(existingAccount);


        return AccountResponse.fromAccount(updatedAccount);

    }

    public AccountResponse patchAccount(Long id, AccountPatchRequest request) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not Found"));

        if (request.getName() != null) {
            existingAccount.setName(request.getName());
        }

        if (request.getAccountType() != null) {
            existingAccount.setAccountType(request.getAccountType());
        }

        Account updatedAccount = accountRepository.save(existingAccount);

        return AccountResponse.fromAccount(updatedAccount);
    }

    public void deleteAccount(Long id){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found"));

        accountRepository.delete(existingAccount);
    }



}
