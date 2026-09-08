package com.manav.securebanking.service;


import com.manav.securebanking.dto.CustomerCreateRequest;
import com.manav.securebanking.model.Customer;
import com.manav.securebanking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerCreateRequest request){

        Customer customer = new Customer();

        customer.setName(request.getName());

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){

        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Customer Not Found"));

    }

    public Customer updateCustomer(Long id , CustomerCreateRequest request){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        customer.setName(request.getName());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        customerRepository.delete(customer);
    }
    }


