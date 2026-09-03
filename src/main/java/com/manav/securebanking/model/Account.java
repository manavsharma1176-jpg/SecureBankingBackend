package com.manav.securebanking.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Account {

    @NotBlank(message = "Name is Required")
    @Size(min = 2 , max = 50 , message = "Name must be between 2 and 50 characters")

    private String name;

    @NotBlank(message = "Account name is Required")

    private String accountType;


    public String getName(){
        return name;


    }

    public void setName(String name){
        this.name = name;
    }

    public String getAccountType(){
        return accountType;
    }

    public Long getId() {
        return id;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Customer customer;

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Customer getCustomer(){
        return customer;
    }
}
