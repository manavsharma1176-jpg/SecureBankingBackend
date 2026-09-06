package com.manav.securebanking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerCreateRequest {

    @NotBlank
    @Size(min  = 2, max = 50)
    private String name;

    public void setName(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }
}
