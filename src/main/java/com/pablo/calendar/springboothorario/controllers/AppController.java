package com.pablo.calendar.springboothorario.controllers;



import java.util.Date;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {

    @RequestMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request){
        var data = new HashMap();
        data.put("title", "Bienvenidos al sistema de atiencion");
        data.put("time", new Date());
        data.put("message", request.getAttribute("message"));
        return ResponseEntity.status(200).body(data);
    }
}
