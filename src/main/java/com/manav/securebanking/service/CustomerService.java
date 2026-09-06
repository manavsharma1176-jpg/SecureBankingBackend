package com.manav.securebanking.service;


import com.manav.securebanking.dto.CustomerCreateRequest;
import com.manav.securebanking.model.Customer;
import com.manav.securebanking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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

}
