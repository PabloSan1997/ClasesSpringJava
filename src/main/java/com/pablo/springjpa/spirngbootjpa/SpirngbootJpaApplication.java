package com.pablo.springjpa.spirngbootjpa;

import java.util.Arrays;
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
		lista();
		// oneFindxd();
		// create();
		//  update();
		// perzonalizedQueries();
		// perzonalizedQueriesPart2();
		// perzonilizedQueriesDisntinct();
		// perzonilizedQueriesDisntinctUpperAndLowerCase();
		// personilizedBetween();
		// queriesFunctionAggregation();
		// subQueries();
		// whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn(){
		System.out.println("==============Lista de personas con where in==========");
		List<Long> ids = Arrays.asList(1L, 3L, 5L);
		List<Person> registrers = repository.getPersonsByIds(ids);
		registrers.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries(){
		System.out.println("================consulta por el nombre mas corto y su largo==========");
		List<Object[]> registrers = repository.getShorterName();
		registrers.forEach(p->{
			System.out.println("Nombre: "+p[0]+" Largo "+p[1]);
		});
	} 

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		Long count = repository.totalPerson();
		Long max = repository.maxId();
		Long min = repository.minId();

		System.out.println("=============contar personas=================");
		System.out.println(count);

		System.out.println("=============Id maximo=================");
		System.out.println(max);

		System.out.println("=============Id minimo=================");
		System.out.println(min);

		System.out.println("============Largo de de los nombres=================");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Nombre: " + name + " Largo: " + length);
		});

		System.out.println("============El nombre mas corto=================");
		Integer minLength = repository.getMinLengthName();
		System.out.println(minLength);

		System.out.println("============Max min avg count suma=================");
		Object[] resultado = (Object[]) repository.getResumeAggregarionFunction();
		System.out.println("Min " + resultado[0] + " Max " + resultado[1] + " Suma " + resultado[2] + " Avg "
				+ resultado[3] + " Count " + resultado[4]);
	}

	@Transactional(readOnly = true)
	public void personilizedBetween() {
		System.out.println("=============Entre dos ids=================");
		List<Person> persons = repository.findAllBetweenId();
		persons.forEach(System.out::println);

		System.out.println("=============Entre dos letras=================");
		List<Person> persons2 = repository.findAllBetweenWord("J", "q");
		persons2.forEach(System.out::println);

		System.out.println("=============Entre dos letras sin Query=================");
		List<Person> persons3 = repository.findByNameBetween("J", "Q");
		persons3.forEach(System.out::println);

		System.out.println("=============Entre dos ids ordenado=================");
		List<Person> persons4 = repository.findAllBetweenIdOrder();
		persons4.forEach(System.out::println);

		System.out.println("=============Entre dos nombres ordenado=================");
		List<Person> persons5 = repository.findAllBetweenWordOrder("j", "q");
		persons5.forEach(System.out::println);

		System.out.println("=============Entre dos id ordenado por id sin query=================");
		List<Person> persons6 = repository.findByIdBetweenOrderByNameDesc(2L, 4L);
		persons6.forEach(System.out::println);

		System.out.println("============Todos los datos ordenados desc por nombre=================");
		List<Person> persons7 = repository.getAllOrdered();
		persons7.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void perzonilizedQueriesDisntinctUpperAndLowerCase() {
		System.out.println("=============Consulta nombres y apellidso=================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("\n===================Lower case======================");
		List<String> names2 = repository.findAllFullNameConcatLower();
		names2.forEach(System.out::println);

		System.out.println("\n========Upper case===========");
		List<String> names3 = repository.findAllFullNameConcatUpper();
		names3.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void perzonilizedQueriesDisntinct() {
		System.out.println("========Consultas con nombres de personas===========");
		List<String> nombres = repository.findAllNames();
		nombres.forEach(System.out::println);

		System.out.println("\n========Consultas con nombres distinck===========");
		List<String> nombres2 = repository.findAllDistinctNames();
		nombres2.forEach(System.out::println);

		System.out.println("\n========Consultas con leguajes de programacion distinck===========");
		List<String> prog = repository.findAllLenguagesProgramingDistict();
		prog.forEach(System.out::println);

		System.out.println("\n========Consultas con leguajes de programacion distinckCount===========");
		Long count = repository.findAllLenguagesProgramingDistictCount();
		System.out.println(count);
	}

	@Transactional(readOnly = true)
	public void perzonalizedQueriesPart2() {
		System.out.println("==========Consulata mixta enlistar================");
		List<Object[]> personRegs = repository.findAllPersonMixDataList();
		personRegs.forEach(reg -> {
			System.out.println("ProgaminLengage: " + reg[1] + ", Persona: " + reg[0]);
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
	public void perzonalizedQueries() {
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
			var message = "Nombre: " + personC[0] + " Lastname: " + personC[1] + " Programing language: " + personC[2];
			System.out.println(message);
		}, () -> {
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
		List<Person> persons = (List<Person>) repository.findAll();
		persons.stream().forEach(person -> System.out.println(person));

		List<Person> persons3 = (List<Person>) repository.budcarByProgramingLanguage("Java");
		persons3.stream().forEach(person -> System.out.println(person));

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
