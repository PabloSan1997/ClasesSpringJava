package com.pablo.pringboot.di.app.springbootdi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.pringboot.di.app.springbootdi.models.Product;
import com.pablo.pringboot.di.app.springbootdi.services.ProductService;



@RestController
@RequestMapping("/api")
public class SomeController {
    
    @Autowired
    private ProductService service;

    @RequestMapping(path = "/product", method = RequestMethod.GET)
    public List<Product> list() {
        return service.findAll();
    }
    
    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public Product show(@PathVariable Long id) {
        return service.findById(id);
    }
}
