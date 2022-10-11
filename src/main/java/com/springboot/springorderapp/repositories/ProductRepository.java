package com.springboot.springorderapp.repositories;

import com.springboot.springorderapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
