package com.pablo.spirngboot.springbootcrudjpa.services;

import java.util.List;
import java.util.Optional;

import com.pablo.spirngboot.springbootcrudjpa.entities.Product;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> delete(Long id);
}
