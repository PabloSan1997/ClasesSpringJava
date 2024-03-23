package com.pablo.springbooterror.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.pablo.springbooterror.exceptions.UserNotFoundException;
import com.pablo.springbooterror.models.domain.User;
import com.pablo.springbooterror.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/app")
public class AppController {
    
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String index(){
        // int value = 100/0;
        int value = Integer.parseInt("fdsafsafesda1ds0");
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User show(@PathVariable Long id) {
        var user = service.findById(id);
        if (user==null){
            throw new UserNotFoundException("No se encontro usuario");
        }
        return user;
    }
    

}
