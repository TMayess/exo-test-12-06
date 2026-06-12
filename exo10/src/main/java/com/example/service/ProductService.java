package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> search(String keyword) {
        return productRepository.findByKeyword(keyword);
    }

    public List<Product> searchByMaxPrice(double maxPrice) {
        return productRepository.findByMaxPrice(maxPrice);
    }

    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}