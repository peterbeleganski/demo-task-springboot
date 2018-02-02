package com.example.controllers;

import com.example.entity.CsvParsedFile;
import com.example.services.ReadCsvData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FindFileController {
    @Autowired
    private ReadCsvData readCsvData;

    @GetMapping("/find-csv/{fileName}")
    public ResponseEntity getFileData(@PathVariable String fileName) {
        CsvParsedFile file = this.readCsvData.readCsvData(fileName);

        Map<String, String> error = new HashMap<>();
        error.put("error", "Given file does not exist on the filesystem!");

        if(file == null) {
            return ResponseEntity.ok().body(error);
        } else {
            return ResponseEntity.ok().body(file);
        }

    }
}
