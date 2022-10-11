package com.springboot.springorderapp.services.impl;

import com.springboot.springorderapp.exception.ResourceNotFoundException;
import com.springboot.springorderapp.model.Product;
import com.springboot.springorderapp.repositories.ProductRepository;
import com.springboot.springorderapp.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, "id", String.valueOf(id)));
    }

    @Override
    public Product updateProduct(Product product, long id) {
        return productRepository.findById(id)
                .map(prod -> {
                    prod.setName(product.getName());
                    prod.setPrice(product.getPrice());
                    return productRepository.save(prod);
                })
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, "id", String.valueOf(id)));
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, "id", String.valueOf(id)));
        productRepository.deleteById(id);
    }
}
