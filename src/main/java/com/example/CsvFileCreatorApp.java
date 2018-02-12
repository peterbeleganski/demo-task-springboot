package com.example;

import com.example.repository.CsvFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvFileCreatorApp implements CommandLineRunner {

	@Autowired
	private CsvFilesRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CsvFileCreatorApp.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.deleteAll();
	}
}
