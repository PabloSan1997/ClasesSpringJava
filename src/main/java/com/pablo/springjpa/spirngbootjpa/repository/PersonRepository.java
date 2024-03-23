package com.pablo.springjpa.spirngbootjpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.pablo.springjpa.spirngbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
