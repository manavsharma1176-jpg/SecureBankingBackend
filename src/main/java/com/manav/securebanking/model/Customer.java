package com.manav.securebanking.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public void setId(Long Id){
        this.id = id;
    }

    public Long Id(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String name(){
        return name;
    }


    // One customer can have multiple accounts
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    public void setAccount(List<Account> account){
        this.accounts = accounts;
    }

    public List<Account> accounts(){
        return accounts;
    }



}
