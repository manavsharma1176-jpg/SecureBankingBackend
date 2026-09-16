package com.manav.securebanking.dto;

import java.util.List;

public class CustomerResponse {

    private Long id;
    private String name;
    private List<AccountResponse> accounts;

    public CustomerResponse(Long id , String name , List<AccountResponse> accounts){

        this.id = id;
        this.name = name;
        this.accounts = accounts;

    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public List<AccountResponse> getAccounts(){
        return accounts;
    }

}
