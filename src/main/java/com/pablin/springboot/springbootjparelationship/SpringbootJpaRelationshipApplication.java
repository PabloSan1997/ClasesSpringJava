package com.pablin.springboot.springbootjparelationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		manyToOneFindByIdClient();
	}

	public void manyToOne(){
		Client client = new Client("Pablo", "Santillana");
		clientRespository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		var res = invoiceRepository.save(invoice);
		System.out.println(res);
	}
	public void manyToOneFindByIdClient(){
		Optional<Client> clientOp = clientRespository.findById(1L);
		if(clientOp.isPresent()){
			Client client = clientOp.orElseThrow();
			System.out.println(client);
			Invoice invoice =  new Invoice("Compra de consolas", 500L);
			invoice.setClient(client);
			var res = invoiceRepository.save(invoice);
			System.out.println(res);
		}
	}
}
