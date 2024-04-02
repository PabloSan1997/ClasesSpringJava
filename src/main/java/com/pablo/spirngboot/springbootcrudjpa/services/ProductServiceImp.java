package com.pablo.spirngboot.springbootcrudjpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.spirngboot.springbootcrudjpa.entities.Product;
import com.pablo.spirngboot.springbootcrudjpa.respoistories.ProductRepository;



@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> delete(Long id) {
        Optional<Product> opproduct = productRepository.findById(id);
        opproduct.ifPresent(p -> {
            productRepository.deleteById(p.getId());
        });
        return opproduct;
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> opproduct = productRepository.findById(id);
        if(opproduct.isPresent()){
            Product prod = opproduct.orElseThrow();
            prod.setDescription(product.getDescription());
            prod.setName(product.getName());;
            prod.setPrice(product.getPrice());
            return Optional.of(productRepository.save(prod));
        }
        return opproduct;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

}
