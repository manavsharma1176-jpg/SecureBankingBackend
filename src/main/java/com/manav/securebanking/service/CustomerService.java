package com.manav.securebanking.service;


import com.manav.securebanking.dto.CustomerCreateRequest;
import com.manav.securebanking.model.Customer;
import com.manav.securebanking.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.manav.securebanking.dto.AccountResponse;
import com.manav.securebanking.dto.CustomerResponse;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    // Constructor-based dependency injection
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerCreateRequest request){

        // Create and save customer
        Customer customer = new Customer();

        customer.setName(request.getName());

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getName(),
                List.of()

        );
    }

    public List<CustomerResponse> getAllCustomers(){

        return customerRepository.findAll()
                .stream()
                .map(customer->new CustomerResponse(
                        customer.getId(),
                        customer.getName(),
                        customer.getAccounts()
                                .stream()
                                .map(AccountResponse::fromAccount)
                                .toList()
                ))
                .toList();
    }

    public CustomerResponse getCustomerById(Long id){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer Not Found"
                ));

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getAccounts()
                        .stream()
                        .map(AccountResponse::fromAccount)
                        .toList()

        );

    }

    public CustomerResponse updateCustomer(Long id , CustomerCreateRequest request){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Customer Not Found"
                ));

        customer.setName(request.getName());

        Customer updatedCustomer = customerRepository.save(customer);

        return new CustomerResponse(
                updatedCustomer.getId(),
                updatedCustomer.getName(),
                updatedCustomer.getAccounts()
                        .stream()
                        .map(AccountResponse::fromAccount)
                        .toList()

        );
    }

    public void deleteCustomer(Long id){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer Not Found"

                        ));

        customerRepository.delete(customer);
    }
    }


