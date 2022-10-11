package com.springboot.springorderapp.services;

import com.springboot.springorderapp.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);
    Customer updateCustomer(Customer customer, long id);
    void deleteCustomer(long id);
}
