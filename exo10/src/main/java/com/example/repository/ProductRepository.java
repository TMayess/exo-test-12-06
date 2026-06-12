package com.example.repository;

import com.example.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findByKeyword(String keyword);
    List<Product> findByMaxPrice(double maxPrice);
    List<Product> findByCategory(String category);
}