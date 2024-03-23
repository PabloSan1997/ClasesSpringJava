package com.pablo.aop.springbootaop.service;

public interface GreetingService {
    String sayHello(String person, String phrase);
    String sayHelloError(String person, String phrase);
}
