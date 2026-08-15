package com.manav.securebanking.model;

public class Account {

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

    private String name;
    private String accountType;
}
