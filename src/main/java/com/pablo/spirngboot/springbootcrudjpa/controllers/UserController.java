package com.pablo.spirngboot.springbootcrudjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.spirngboot.springbootcrudjpa.entities.User;
import com.pablo.spirngboot.springbootcrudjpa.services.UserService;

import jakarta.validation.Valid;

@CrossOrigin(originPatterns  = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> list(){
        return service.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        if(result.hasFieldErrors()){
            return vlidation(result);
        }
        user.setAdmin(false);
        return create(user, result);
    }

    @PostMapping
    private ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasFieldErrors()){
            return vlidation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

     private ResponseEntity<?> vlidation(BindingResult result) {
        Map<String, String> errors = new HashMap<String, String>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });
        ;
        return ResponseEntity.badRequest().body(errors);
    }
}
