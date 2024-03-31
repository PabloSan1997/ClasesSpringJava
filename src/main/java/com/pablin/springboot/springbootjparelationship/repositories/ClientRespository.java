package com.pablin.springboot.springbootjparelationship.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pablin.springboot.springbootjparelationship.entitites.Client;

public interface ClientRespository extends CrudRepository<Client, Long>{
    
    @Query("select c from Client c join fetch c.addreses where c.id=?1")
    Optional<Client> findOne(Long id);
}
