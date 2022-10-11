package com.springboot.springorderapp.repositories;

import com.springboot.springorderapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
