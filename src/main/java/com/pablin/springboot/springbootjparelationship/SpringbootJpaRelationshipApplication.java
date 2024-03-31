package com.pablin.springboot.springbootjparelationship;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.pablin.springboot.springbootjparelationship.entitites.Adress;
import com.pablin.springboot.springbootjparelationship.entitites.Client;
import com.pablin.springboot.springbootjparelationship.entitites.Invoice;
import com.pablin.springboot.springbootjparelationship.repositories.ClientRespository;
import com.pablin.springboot.springbootjparelationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRespository clientRespository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOne();
		// manyToOneFindByIdClient();
		// oneToMany();
		// oneToManyFind();
		// removeAdres();
		removeAdressFindById();
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("Pablo", "Santillana");
		clientRespository.save(client);
		Invoice invoice = new Invoice("Compras de Consolas", 2000L);
		invoice.setClient(client);
		var res = invoiceRepository.save(invoice);
		System.out.println(res);
		// Optional<Client> optionalcliente = clientRespository.findById(2L);
		// optionalcliente.ifPresent(p -> {
		// Invoice invoice = new Invoice("Compras de Consolas", 2000L);
		// invoice.setClient(p);
		// var res = invoiceRepository.save(invoice);
		// System.out.println(res);
		// });
	}

	@Transactional
	public void removeAdres() {
		Client client = new Client("Juana", "La Iguana");
		Adress adress1 = new Adress("La condesa", 456);
		Adress adress2 = new Adress("Reforma", 6565);
		client.getAddreses().add(adress1);
		client.getAddreses().add(adress2);
		var res = clientRespository.save(client);
		System.out.println(res);

		Optional<Client> optionalClient = clientRespository.findById(1L);
		optionalClient.ifPresent(c -> {
			c.getAddreses().remove(adress1);
			var mp = clientRespository.save(c);
			System.out.println(mp);
		});
	}

	@Transactional
	public void manyToOneFindByIdClient() {
		Optional<Client> clientOp = clientRespository.findById(2L);
		if (clientOp.isPresent()) {
			Client client = clientOp.orElseThrow();
			System.out.println(client);
			Invoice invoice = new Invoice("Compra de consolas", 500L);
			invoice.setClient(client);
			var res = invoiceRepository.save(invoice);
			System.out.println(res);
		}
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Juana", "La Iguana");
		Adress adress1 = new Adress("La condesa", 456);
		Adress adress2 = new Adress("Reforma", 6565);
		client.getAddreses().add(adress1);
		client.getAddreses().add(adress2);
		var res = clientRespository.save(client);
		System.out.println(res);
	}

	@Transactional
	public void removeAdressFindById() {
		Optional<Client> optionalcliente = clientRespository.findOne(1L);
		optionalcliente.ifPresent(p -> {
			Adress adress1 = new Adress("Tlaxcala", 4854);
			Adress adress2 = new Adress("Nose", 65);
			p.setAddreses(Arrays.asList(adress1, adress2));
			var res = clientRespository.save(p);
			System.out.println(res);

			Optional<Client> optionalClient = clientRespository.findOne(1L);
			optionalClient.ifPresent(c -> {
				Adress adress3 = c.getAddreses().get(1);
				c.getAddreses().remove(adress3);
				var mp = clientRespository.save(c);
				System.out.println(mp);
			});
		});

	}

	@Transactional
	public void oneToManyFind() {
		Optional<Client> optionalcliente = clientRespository.findById(1L);
		optionalcliente.ifPresent(p -> {
			Adress adress1 = new Adress("Tlaxcala", 4854);
			Adress adress2 = new Adress("Nose", 65);
			p.setAddreses(Arrays.asList(adress1, adress2));
			var res = clientRespository.save(p);
			System.out.println(res);
		});
	}
}
