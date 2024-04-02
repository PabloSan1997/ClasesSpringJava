package com.pablo.spirngboot.springbootcrudjpa.respoistories;

import org.springframework.data.repository.CrudRepository;

import com.pablo.spirngboot.springbootcrudjpa.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    boolean existsByName(String name);
}
