package com.manav.securebanking.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.util.List;


@Entity
public class Customer {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2 , max = 50 , message = "Customer name must be between 2 and 50 characters")
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


    // A customer can have multiple bank accounts
    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
    private List<Account> accounts;

    public void setAccount(List<Account> accounts){
        this.accounts = accounts;
    }

    public List<Account> getAccounts(){
        return accounts;
    }



}
