package com.pablo.factura.springfactura.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
// @JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    private List<Item> items;

    @PostConstruct
    public void agregar(){
        String nombre = client.getName();
        client.setName(nombre.concat(" Pepe "));
    }

    @PreDestroy
    public void destroy(){
        System.out.println("\n\n\n\nDestrullendo el componente bean\n\n\n\n\n");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        // int total = 0;
        // for (Item item : items) {
        // total += item.getImporte();
        // }
        int total = items.stream()
                .map(item -> item.getImporte())
                .reduce(0, (sum, importe) -> sum + importe);
        return total;
    }

}
