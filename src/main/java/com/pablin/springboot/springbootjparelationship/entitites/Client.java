package com.pablin.springboot.springbootjparelationship.entitites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbl_clients_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_direcciones"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "id_direcciones" }))
    private List<Adress> addreses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Invoice> invoices;

    public Client() {
        addreses = new ArrayList<Adress>();
    }

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
        this.addreses = new ArrayList<Adress>();
        this.invoices = new ArrayList<Invoice>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Adress> getAddreses() {
        return addreses;
    }

    public void setAddreses(List<Adress> addreses) {
        this.addreses = addreses;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return " {id=" + id + ", name=" + name + ", lastname=" + lastname + ", addreses=" + addreses + ", invoices="
                + invoices + "}";
    }

}
