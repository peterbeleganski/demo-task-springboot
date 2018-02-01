package com.example.controllers;

import com.example.entity.CsvParsedFile;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {


    @Autowired
    private CsvCreatePojoDataToFile csvCreatePojoDataToFile;

    @Autowired
    private ReadCsvDataAndPersistDatabase readCsvDataAndPersistDatabase;

    @GetMapping("/create-csv/{countRows}")
    public ResponseEntity getFileName(@PathVariable long countRows) {

        String fileName = this.csvCreatePojoDataToFile.createFileOnSystem(countRows);

        Map<String, String> resultSet = new HashMap<>();

        resultSet.put("fileName", fileName);

		return ResponseEntity.ok().body(resultSet);
	}


	@GetMapping("/find-csv/{fileName}")
	public ResponseEntity getDatabaseStructure(@PathVariable String fileName) {
        CsvParsedFile file = this.readCsvDataAndPersistDatabase.readCsvData(fileName);

        Map<String, String> error = new HashMap<>();
        error.put("error", "Given file does not exist on the filesystem!");

        if(file == null) {
            return ResponseEntity.ok().body(error);
        } else {
            return ResponseEntity.ok().body(file);
        }

    }

}
