package com.example;

import com.example.config.MyRoute;
import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoTaskApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private MyRoute route;

	public static void main(String[] args) {
		SpringApplication.run(DemoTaskApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Person pepi = new Person(
				"Peter",
				"Beleganski",
				"Main 1 Street",
				19,
				"Plovdiv"
		);

		List<Person> people = Arrays.asList(pepi);

		this.repository.deleteAll();

		this.repository.save(people);
		this.route.configure();
		//System.out.println("Configuring ROUTE:\n");

		// System.out.println("People seed added successfully!");


	}
}
