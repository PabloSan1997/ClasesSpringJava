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
import com.pablo.springjpa.spirngbootjpa.modelsDto.PersonDto;
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
		// create();
		// update();
		// perzonalizedQueries();
		perzonalizedQueriesPart2();
	}

	@Transactional(readOnly = true)
	public void perzonalizedQueriesPart2(){
		System.out.println("==========Consulata mixta enlistar================");
		List<Object[]> personRegs = repository.findAllPersonMixDataList();
		personRegs.forEach(reg -> {
			System.out.println("ProgaminLengage: "+reg[1] + ", Persona: "+reg[0]);
		});


		System.out.println("==========Consulata Perzonalizada clase Perzona================");
		List<Person> persons = repository.findAllPersonalizedPerson();
		persons.forEach(peron -> {
			System.out.println(peron);
		});

		System.out.println("==========Consulata Perzonalizada clase Perzona DTO================");
		List<PersonDto> personDto = repository.findAllPersonalizedPersonDto();
		personDto.forEach(peron -> {
			System.out.println(peron);
		});
	}

	@Transactional(readOnly = true)
	public void perzonalizedQueries(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escriba id");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("==========Consulata solo nombre por el id================");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("==========Consulata nombre completo por el id================");
		String fullname = repository.getFullNameById(id);
		System.out.println(fullname);

		System.out.println("==========Conculat campos perzonalizados por id");
		Optional<Object> personReq = repository.obtenerPersonDataFull(id);
		personReq.ifPresentOrElse(data -> {
			Object[] personC = (Object[]) data;
			var message = "Nombre: "+personC[0]+" Lastname: "+personC[1]+" Programing language: "+personC[2];
			System.out.println(message);
		}, ()->{
			System.out.println("No SE encontro datos");
		});
		
		
	}

	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String lastname = scanner.next();
		String programingLanguage = scanner.next();
		scanner.close();
		Person person = new Person(null, name, lastname, programingLanguage);
		var newPerson = repository.save(person);
		System.out.println(newPerson);
	}

	@Transactional
	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escriba id para eliminar");
		Long id = scanner.nextLong();
		// repository.deleteById(id);
		// System.out.println("Objeto eliminado");
		// scanner.close();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(
				person -> repository.delete(person),
				() -> System.out.println("No existe persona con ese id"));

		scanner.close();

	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escriba el id de la persona");
		Long id = scanner.nextLong();
		Optional<Person> optionalPerson = repository.findById(id);
		Person person = null;
		// optionalPerson.ifPresent(person->{
		// System.out.println(person);
		// System.out.println("Ingrese el lenguaje de programacion");
		// String programingLanguage = scanner.next();
		// person.setProgramingLanguage(programingLanguage);
		// var actualizado = repository.save(person);
		// System.out.println("Actualizado");
		// System.out.println(actualizado);
		// });
		if (optionalPerson.isPresent()) {
			person = optionalPerson.orElseThrow();
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion");
			String programingLanguage = scanner.next();
			person.setProgramingLanguage(programingLanguage);
			var actualizado = repository.save(person);
			System.out.println("Actualizado");
			System.out.println(actualizado);
		} else {
			System.out.println("No se encontro persona");
		}

		scanner.close();
	}

	@Transactional(readOnly = true)
	private void lista() {
		// List<Person> persons = (List<Person>) repository.findAll();

		List<Person> persons = (List<Person>) repository.budcarByProgramingLanguage("Java");
		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> persons2 = repository.obtenerPersonByProgramingLanguage("Java");
		persons2.stream().forEach(person -> System.out.println(person[0] + " " + person[1]));
	}

	@Transactional(readOnly = true)
	private void oneFindxd() {
		// Person persona = null;
		// Optional<Person> optional = repository.findById(1L);
		// if (optional.isPresent()) {
		// persona = optional.get();
		// }
		// System.out.println(persona);
		repository.findById(2L).ifPresent(System.out::println);
		repository.leerPorNombre("Juan").ifPresent(System.out::println);
		List<Person> pornombre = (List<Person>) repository.leerPorParecidoNombre("En");
		System.out.println(pornombre);
	}


}
