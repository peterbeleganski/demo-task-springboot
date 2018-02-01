package com.example.services;

import com.example.entity.CsvParsedFile;
import com.example.entity.CsvPerson;
import com.example.entity.Person;
import com.example.repository.CsvFilesRepository;
import com.example.repository.PersonRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReadCsvDataAndPersistDatabase {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private CsvFilesRepository csvFilesRepository;

    public CsvParsedFile readCsvData(String fileName) {


        if (checkIfFileExists(fileName)) return null;

        CamelContext context = new DefaultCamelContext();

        CsvDataFormat csv = new CsvDataFormat();

        final CsvParsedFile[] file = {null};

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file://src/main/resources/output?fileName="+fileName +".csv&noop=true&delay=15m")
                            .unmarshal(csv)
                            .convertBodyTo(List.class)
                            .process(msg -> {
                                List<CsvPerson> rowsData = getCsvPeople(msg);
                                file[0] = new CsvParsedFile(fileName, rowsData);
                                csvFilesRepository.save(file[0]);
                            }).end();
                }
            });

            context.start();
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file[0];
    }


    private boolean checkIfFileExists(String fileName) {
        File f = new File("/src/main/resources/output/" + fileName + ".csv");
        if(!f.exists() && f.isDirectory()) {
            return true;
        }
        return false;
    }

    private List<CsvPerson> getCsvPeople(Exchange msg) {
        List<List<String>> data = (List<List<String>>) msg.getIn().getBody();
        List<CsvPerson> rowsData = new ArrayList<>();
        for (List<String> line : data) {

            rowsData
                    .add(new CsvPerson(line.get(0),
                            line.get(1),
                            line.get(2),
                            Integer.parseInt(line.get(3)),
                            line.get(4)));
        }
        return rowsData;
    }
}
