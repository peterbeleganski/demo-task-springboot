package com.example.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "files")
public class CsvParsedFile {

    @Id
    private String id;

    private String fileName;

    private List<CsvPerson> data;

    public CsvParsedFile() {
    }

    public CsvParsedFile(String fileName, List<CsvPerson> data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<CsvPerson> getData() {
        return data;
    }

    public void setData(List<CsvPerson> data) {
        this.data = data;
    }
}
