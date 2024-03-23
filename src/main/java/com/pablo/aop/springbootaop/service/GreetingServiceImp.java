package com.pablo.aop.springbootaop.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImp implements GreetingService{

    @Override
    public String sayHello(String person, String phrase) {
        String greeting = phrase + " "+person;
        return greeting;
    }

    @Override
    public String sayHelloError(String person, String phrase) {
        throw new RuntimeException("Algun error");
    }
    
}
