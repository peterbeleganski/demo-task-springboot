package com.example.services;

import com.example.repository.PersonRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReadCsvDataAndPersistDatabase {

    @Autowired
    private PersonRepository repository;

    public Object readCsvData(String fileName) {

        CamelContext context = new DefaultCamelContext();

        CsvDataFormat csv = new CsvDataFormat();

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file://src/main/resources/output?fileName=b5a5550e28a73d1748344668194c1838c2868e63.csv&noop=true&delay=15m")
                            .unmarshal(csv)
                            .convertBodyTo(List.class)
                            .process(msg -> {
                                List<List<String>> data = (List<List<String>>) msg.getIn().getBody();
                                for (List<String> line : data) {
                                    System.out.println(line);
                                }
                            }).marshal(csv).to("file://src/main/resources?fileName=out.csv")
                            .log("done.").end();
                }
            });

            context.start();
            Thread.sleep(2000);

            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
