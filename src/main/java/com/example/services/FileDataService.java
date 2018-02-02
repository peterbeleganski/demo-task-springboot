package com.example.services;

import com.example.entity.CsvParsedFile;
import com.example.repository.CsvFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDataService {

    @Autowired
    private CsvFilesRepository repository;

    public void saveCsvToDatabase(CsvParsedFile file) {
        this.repository.save(file);
    }
}
