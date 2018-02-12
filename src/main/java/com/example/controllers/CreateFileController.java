package com.example.controllers;

import com.example.exceptions.CsvNotCreatedException;
import com.example.services.CsvCreatePojoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CreateFileController {

    @Autowired
    private CsvCreatePojoData csvCreatePojoData;

    @GetMapping("/create-csv/{countRows}")
    public ResponseEntity getFileName(@PathVariable long countRows) throws CsvNotCreatedException {

        String fileName = this.csvCreatePojoData.createFileOnSystem(countRows);

        Map<String, String> resultSet = new HashMap<>();

        resultSet.put("fileName", fileName);

        return ResponseEntity.ok().body(resultSet);
    }
}
