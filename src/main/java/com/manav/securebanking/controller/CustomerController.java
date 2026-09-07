package com.manav.securebanking.controller;


import com.manav.securebanking.dto.CustomerCreateRequest;
import com.manav.securebanking.model.Customer;
import com.manav.securebanking.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    //customer
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody CustomerCreateRequest request){
        Customer customer = customerService.createCustomer(request);

        return ResponseEntity.ok(customer);
    }


}
