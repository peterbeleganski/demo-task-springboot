package com.example.controllers;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import com.example.services.CsvCreatePojoDataToFile;
import com.example.services.ReadCsvDataAndPersistDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {


    @Autowired
    private CsvCreatePojoDataToFile csvCreatePojoDataToFile;

    @Autowired
    private ReadCsvDataAndPersistDatabase readCsvDataAndPersistDatabase;

    @RequestMapping("/")
    public List<Person> getAllPeople() {

        return null;
    }

    @GetMapping("/create-csv/{countRows}")
    public ResponseEntity getFileName(@PathVariable long countRows) {

        String fileName = this.csvCreatePojoDataToFile.createFileOnSystem(countRows);
		return ResponseEntity.ok(fileName);
	}


	@GetMapping("/find-csv/{fileName}")
	public Map<String, String> getDatabaseStructure(@PathVariable String fileName) {

        this.readCsvDataAndPersistDatabase.readCsvData(fileName);

        return null;
    }

}
