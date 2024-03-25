package com.pablo.springjpa.spirngbootjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pablo.springjpa.spirngbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByProgramingLanguage(String programingLanguage);

    @Query("select p from Person p where p.programingLanguage = ?1")
    List<Person> budcarByProgramingLanguage(String programingLanguage);

    @Query("select p.name, p.lastname from Person p where p.programingLanguage = ?1")
    List<Object[]> obtenerPersonByProgramingLanguage(String programingLanguage);

    @Query("select p from Person p where p.name = ?1")
    Optional<Person> leerPorNombre(String name);

    @Query("select p from Person p where p.name like ?1%")
    List<Person> leerPorParecidoNombre(String name);
}
