package com.example.repository;

import com.example.entity.CsvParsedFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CsvFilesRepository extends MongoRepository<CsvParsedFile,String> {

}
