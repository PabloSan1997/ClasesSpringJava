package com.pablin.springboot.springbootjparelationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pablin.springboot.springbootjparelationship.entitites.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
    
}
