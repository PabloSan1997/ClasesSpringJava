package com.pablo.aop.springbootaop.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pablo.aop.springbootaop.service.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        var hello = greetingService.sayHello("Pablo", "Que onda");
        return ResponseEntity.status(200).body(Collections.singletonMap("greeting", hello));
    }


    @GetMapping("/greetingError")
    public ResponseEntity<?> greetingError() {
        var hello = greetingService.sayHelloError("Pablo", "Que onda");
        return ResponseEntity.status(200).body(Collections.singletonMap("greeting", hello));
    }

}
