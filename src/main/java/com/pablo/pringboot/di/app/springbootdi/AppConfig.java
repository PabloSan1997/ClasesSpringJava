package com.pablo.pringboot.di.app.springbootdi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.pablo.pringboot.di.app.springbootdi.repositories.ProductRepository;
import com.pablo.pringboot.di.app.springbootdi.repositories.ProductRepositoryJson;



@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    
}
