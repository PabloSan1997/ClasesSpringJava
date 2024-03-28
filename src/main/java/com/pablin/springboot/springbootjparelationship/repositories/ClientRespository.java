package com.pablin.springboot.springbootjparelationship.repositories;

import org.hibernate.mapping.List;
import org.springframework.data.repository.CrudRepository;

import com.pablin.springboot.springbootjparelationship.entitites.Client;

public interface ClientRespository extends CrudRepository<Client, Long>{

}
