package com.manav.securebanking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountCreateRequest {

    @NotBlank
    @Size(min = 2 , max = 50)
    private String name;

    @NotBlank
    private String accountType;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public String getAccountType(){
        return accountType;
    }
}
