package com.pablo.interserctor.springbootintersector.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/app")
public class FooController {
    
    @GetMapping("/foo")
    public Map<String, Object> foo() {
        Map<String, Object> myfoo =  new HashMap<String, Object>();
        myfoo.put("message", "Handle foo del controlador");
        return myfoo;
    }

    @GetMapping("/bar")
    public Map<String, Object> bar() {
        Map<String, Object> myfoo =  new HashMap<String, Object>();
        myfoo.put("message", "Handle bar del controlador");
        return myfoo;
    }
    
    @GetMapping("/baz")
    public Map<String, Object> baz() {
        Map<String, Object> myfoo =  new HashMap<String, Object>();
        myfoo.put("message", "Handle baz del controlador");
        return myfoo;
    }
}
