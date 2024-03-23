package com.pablo.factura.springfactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.pablo.factura.springfactura.models.Item;
import com.pablo.factura.springfactura.models.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "utf-8")
public class AppConfig {

    @Bean
    List<Item> itemsInvoice() {
        Product p1 = new Product("Camara Sony", 500);
        Product p2 = new Product("Bicicleta", 256);
        List<Item> items = Arrays.asList(
                new Item(p1, 2),
                new Item(p2, 5));
        return items;
    }

    @Primary
    @Bean
    List<Item> itemsInvoiceOficina() {
        Product p1 = new Product("Monitor asus 24 pulgadas", 700);
        Product p2 = new Product("Notebook", 800);
        Product p3 = new Product("Impresora", 8200);
        Product p4 = new Product("Xbox series x", 8);
        List<Item> items = Arrays.asList(
                new Item(p1, 2),
                new Item(p2, 5),
                new Item(p3, 5),
                new Item(p4, 7));
        return items;
    }
}
