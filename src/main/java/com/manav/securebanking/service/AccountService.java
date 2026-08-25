package com.manav.securebanking.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.manav.securebanking.model.Account;
import com.manav.securebanking.repository.AccountRepository;
import org.springframework.web.server.ResponseStatusException;


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

    public List<Account> getAllAccounts (){

        return accountRepository.findAll();

    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not Found"));
    }

    public Account updateAccount(Long id , Account account){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));

        existingAccount.setName(account.getName());
        existingAccount.setAccountType(account.getAccountType());

        return accountRepository.save(existingAccount);

    }

    public Account patchAccount(Long id, Account account) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not Found"));

        if (account.getName() != null) {
            existingAccount.setName(account.getName());
        }

        if (account.getAccountType() != null) {
            existingAccount.setAccountType(account.getAccountType());
        }

        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long id){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found"));

        accountRepository.delete(existingAccount);
    }





}
