package com.springboot.springorderapp.services;

import com.springboot.springorderapp.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(long id);
    Product updateProduct(Product product, long id);
    void deleteProduct(long id);
}
