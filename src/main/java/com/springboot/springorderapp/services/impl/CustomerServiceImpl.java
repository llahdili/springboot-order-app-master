package com.springboot.springorderapp.services.impl;

import com.springboot.springorderapp.exception.ResourceNotFoundException;
import com.springboot.springorderapp.model.Customer;
import com.springboot.springorderapp.repositories.CustomerRepository;
import com.springboot.springorderapp.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, "id", String.valueOf(id)));
    }

    @Override
    public Customer updateCustomer(Customer customer, long id) {
        return customerRepository.findById(id)
                .map(cu -> {
                    cu.setFirstName(customer.getFirstName());
                    cu.setLastName(customer.getLastName());
                    return customerRepository.save(cu);
                })
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, "id", String.valueOf(id)));
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class, "id", String.valueOf(id)));
        customerRepository.deleteById(id);
    }
}
