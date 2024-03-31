package com.pablin.springboot.springbootjparelationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pablin.springboot.springbootjparelationship.entitites.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long>{

}
