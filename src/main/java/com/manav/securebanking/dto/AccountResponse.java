package com.manav.securebanking.dto;

import com.manav.securebanking.model.Account;

public class AccountResponse {


    private Long id;
    private String name;
    private String accountType;

    // DTO used for account API responses

    public Long getId(){
       return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public static AccountResponse fromAccount(Account account){


        AccountResponse response = new AccountResponse();

        response.setId(account.getId());
        response.setName(account.getName());
        response.setAccountType(account.getAccountType());

        return response;

    }

}




