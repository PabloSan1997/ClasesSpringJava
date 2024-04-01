package com.pablo.spirngboot.springbootcrudjpa.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.spirngboot.springbootcrudjpa.entities.Product;
import com.pablo.spirngboot.springbootcrudjpa.services.ProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/prodcuts")
public class ProductCotnroller {
    
    @Autowired
    private ProductService productService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> list(){
        return productService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            Product res = product.orElseThrow();
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public  ResponseEntity<?> postProduct(@RequestBody Product entity) {
        var product = productService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public  ResponseEntity<?> postProduct(@PathVariable Long id ,@RequestBody Product entity) {
        entity.setId(id);
        var product = productService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    
    @RequestMapping(path = "/{id}" , method=RequestMethod.DELETE)
    public  ResponseEntity<?> requestMethodName(@PathVariable Long id) {
       productService.delete(id);
       return ResponseEntity.noContent().build(); 
    }
    
}
