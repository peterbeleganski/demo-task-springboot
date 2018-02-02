package com.example;

import com.example.entity.CsvParsedFile;
import com.example.entity.CsvPerson;
import com.example.entity.Person;
import com.example.repository.CsvFilesRepository;
import com.example.repository.PersonRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoTaskApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private CsvFilesRepository filesRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoTaskApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		seedInitialDataForPesonAndFile();
	}

	private void seedInitialDataForPesonAndFile() {
		Person pepi = new Person(
				"Peter",
				"Beleganski",
				"Main 1 Street",
				19,
				"Plovdiv"
		);

		List<CsvPerson> pepps = new ArrayList<>();
		CsvPerson csvPerson = new CsvPerson("firstName", "lastName", "main 1 steeet", 19,"plovdiv");
		pepps.add(csvPerson);

		CsvParsedFile file = new CsvParsedFile("mytest",pepps);
		this.filesRepository.deleteAll();
		this.filesRepository.save(file);

		List<Person> people = Arrays.asList(pepi);
		this.repository.deleteAll();
		this.repository.save(people);
	}
}
