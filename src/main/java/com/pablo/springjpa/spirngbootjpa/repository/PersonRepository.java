package com.pablo.springjpa.spirngbootjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pablo.springjpa.spirngbootjpa.entities.Person;
import com.pablo.springjpa.spirngbootjpa.modelsDto.PersonDto;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("select lower(concat(p.name,' ',p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select upper(p.name||' '||p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select p.name||' '||p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select count(distinct(p.programingLanguage)) from Person p")
    Long findAllLenguagesProgramingDistictCount();

    @Query("select distinct(p.programingLanguage) from Person p")
    List<String> findAllLenguagesProgramingDistict();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllDistinctNames();

    @Query("select new com.pablo.springjpa.spirngbootjpa.modelsDto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonalizedPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllPersonalizedPerson();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select concat(p.name,' ',p.lastname) from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p, p.programingLanguage from Person p")
    List<Object[]> findAllPersonMixDataList();

    @Query("select p.name, p.lastname, p.programingLanguage from Person p where p.id = ?1")
    Optional<Object> obtenerPersonDataFull(Long id);

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
