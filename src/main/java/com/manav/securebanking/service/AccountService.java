package com.manav.securebanking.service;


import com.manav.securebanking.dto.*;
import com.manav.securebanking.model.Customer;
import com.manav.securebanking.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.manav.securebanking.model.Account;
import com.manav.securebanking.repository.AccountRepository;
import org.springframework.web.server.ResponseStatusException;
import com.manav.securebanking.dto.TransferRequest;


import com.manav.securebanking.exception.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.List;



@Service
public class AccountService {




    public String createAccount(AccountCreateRequest request){

        Account account = new Account();

        account.setName(request.getName());
        account.setAccountType(request.getAccountType());
        account.setBalance(BigDecimal.ZERO);

        Customer customer = customerRepository.findById(request.getCustomerId())
                        .orElseThrow(()->
                                new AccountNotFoundException("Customer Not Found"));

        account.setCustomer(customer);
        accountRepository.save(account);

        return "Account created for " + account.getName();

    }

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository ,
                          CustomerRepository customerRepository){

        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
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

    public AccountResponse updateAccount(Long id , AccountUpdateRequest request){
        Account existingAccount = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account Not Found"));

        existingAccount.setName(request.getName());
        existingAccount.setAccountType(request.getAccountType());

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

    public AccountResponse deposit(Long id , DepositRequest request){

        Account account = accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account Not Found"));

        account.setBalance(
                account.getBalance().add(request.getAmount())
        );

        Account updatedAccount = accountRepository.save(account);

        return AccountResponse.fromAccount(updatedAccount);

    }

    @Transactional
    public String transfer(TransferRequest request){

        Account sender = accountRepository.findById(request.getSenderAccountId())
                .orElseThrow(()->
                        new AccountNotFoundException("Sender Account Not Found"));

        if (sender.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        Account receiver = accountRepository.findById(request.getReceiverAccountId())
                .orElseThrow(()->
                        new AccountNotFoundException("Receiver Account Not Found"));

        sender.setBalance(
                sender.getBalance().subtract(request.getAmount())
        );

        receiver.setBalance(
                receiver.getBalance().add(request.getAmount())
        );

        accountRepository.save(sender);
        accountRepository.save(receiver);

        return "Transfer successful";



    }



}
