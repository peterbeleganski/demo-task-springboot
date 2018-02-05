package com.example.services;

import com.example.entity.CsvParsedFile;
import com.example.entity.CsvPerson;
import com.example.exceptions.ResourceNotFoundException;
import com.example.util.CustomFunctions;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static com.example.util.CustomFunctions.getCsvPeople;
import static com.example.util.CustomFunctions.startContext;
import static com.example.util.FileConstants.LOG_MESSAGE_DONE;
import static com.example.util.FileConstants.OUTPUT_FILES_URL;


@Service
public class ReadCsvData {

    @Autowired
    private FileDataService fileDataService;

    public CsvParsedFile readCsvData(String fileName) {

        CamelContext context = new DefaultCamelContext();
        CsvDataFormat csv = new CsvDataFormat();

        final CsvParsedFile[] file = {null};

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(OUTPUT_FILES_URL +fileName +".csv&noop=true")
                            .unmarshal(csv)
                            .convertBodyTo(List.class)
                            .process(msg -> parseDataToService(msg, file, fileName))
                            .log(LOG_MESSAGE_DONE)
                            .end();
                }
            });

        startContext(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkFile(fileName, file[0]);

        return file[0];
    }
    public static boolean checkIfFileExists(String fileName) {
        File f = new File("/src/main/resources/output/" + fileName + ".csv");
        return !f.exists() && f.isDirectory();
    }

    public void parseDataToService(Exchange msg, CsvParsedFile[] file, String fileName) {
        List<CsvPerson> rowsData = getCsvPeople(msg);
        file[0] = new CsvParsedFile(fileName, rowsData);
        fileDataService.saveCsvToDatabase(file[0]);
    }

    public void checkFile(String fileName, CsvParsedFile file) {
        if(file == null) {
            throw new ResourceNotFoundException(fileName, "Not found file with given name!");
        }
    }
}
