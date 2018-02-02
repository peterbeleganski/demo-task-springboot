package com.example.controllers;

import com.example.entity.CsvParsedFile;
import com.example.exceptions.ResourceNotFoundException;
import com.example.services.ReadCsvData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class FindFileController {
    @Autowired
    private ReadCsvData readCsvData;

    @GetMapping("/find-csv/{fileName}")
    public ResponseEntity getFileData(@PathVariable String fileName)  {
        CsvParsedFile file = this.readCsvData.readCsvData(fileName);

        return ResponseEntity.ok(file);
    }
}
