package com.manav.securebanking.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

   // private String name;
  //  private String accountType;

    @Id
    @GeneratedValue
    private Long id;
}
