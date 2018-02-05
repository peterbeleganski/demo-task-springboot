package com.example.services;

import com.example.exceptions.BadRequestException;
import com.example.util.CustomFunctions;
import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import static com.example.util.CustomFunctions.startContext;
import static com.example.util.FileConstants.INITIAL_URL;
import static com.example.util.FileConstants.LOG_MESSAGE_DONE;
import static com.example.util.FileConstants.OUTPUT_FILES_URL;

@Service
public class CsvCreatePojoData {

    public String createFileOnSystem(long countRows) {

        if(countRows <= 0) throw new BadRequestException(countRows, "Input number must be greater than 0!");

        CustomFunctions functions = new CustomFunctions();

        String fileName = functions.encryptString();
        CamelContext context = new DefaultCamelContext();
        CsvDataFormat csv = new CsvDataFormat();

        functions.ensureFileStartExists();

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from(INITIAL_URL)
                            .unmarshal(csv)
                            .convertBodyTo(String.class)
                            .process(getProcessor(functions, countRows))
                            .marshal(csv)
                            .to(OUTPUT_FILES_URL+fileName)
                            .log(LOG_MESSAGE_DONE)
                            .end();
                }
            });
            startContext(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public Processor getProcessor(CustomFunctions functions, long countRows) {
        return msg -> {
            ArrayList<ArrayList<String>> list = functions.getArrayListData(countRows);
            msg.getIn().setBody(list);
        };
    }
}
