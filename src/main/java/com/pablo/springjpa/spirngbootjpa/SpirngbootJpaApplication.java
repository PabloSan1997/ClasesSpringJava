package com.pablo.springjpa.spirngbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.pablo.springjpa.spirngbootjpa.entities.Person;
import com.pablo.springjpa.spirngbootjpa.repository.PersonRepository;

@SpringBootApplication
public class SpirngbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpirngbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// lista();
		// oneFindxd();
		create();
	}


	@Transactional
	public void create(){

		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String lastname = scanner.next();
		String programingLanguage = scanner.next();
		scanner.close();
		Person person = new Person(null, name, lastname, programingLanguage);
		var newPerson = repository.save(person);
		System.out.println(newPerson);
	}

	@Transactional(readOnly = true)
	private void lista(){
		// List<Person> persons = (List<Person>) repository.findAll(); 

		List<Person> persons = (List<Person>) repository.budcarByProgramingLanguage("Java"); 
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> persons2 = repository.obtenerPersonByProgramingLanguage("Java"); 
		persons2.stream().forEach(person -> System.out.println(person[0]+" "+person[1]));
	}

	@Transactional(readOnly = true)
	private void oneFindxd(){
		// Person persona =  null;
		// Optional<Person> optional = repository.findById(1L);
		// if (optional.isPresent()) {
		// 	persona = optional.get();
		// }
		// System.out.println(persona);	
		repository.findById(2L).ifPresent(System.out::println);	
		repository.leerPorNombre("Juan").ifPresent(System.out::println);
		List<Person> pornombre = (List<Person>) repository.leerPorParecidoNombre("En");	
		System.out.println(pornombre);
	}
}
