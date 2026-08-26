package com.manav.securebanking.dto;

public class AccountPatchRequest {

    public String name;

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String accountType;

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(){
        this.accountType = accountType;
    }

}
