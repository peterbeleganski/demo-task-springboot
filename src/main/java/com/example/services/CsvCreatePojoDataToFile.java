package com.example.services;

import com.example.entity.Person;
import com.example.util.CustomFunctions;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CsvCreatePojoDataToFile {

    public String createFileOnSystem(long countRows) {

        CustomFunctions functions = new CustomFunctions();
        String fileName = functions.encryptString();
        CamelContext context = new DefaultCamelContext();
        CsvDataFormat csv = new CsvDataFormat();
        csv.setSkipHeaderRecord(true);

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file://src/main/resources?fileName=test.csv&noop=true&delay=15m")
                            .unmarshal(csv)
                            .convertBodyTo(String.class)
                            .process(msg -> {
                                ArrayList<ArrayList<String>> list = getArrayListData(countRows);
                                msg.getIn().setBody(list);
                            }).marshal(csv).to("file://src/main/resources/output?fileName="+fileName)
                            .log("done.").end();
                }
            });
            context.start();
            Thread.sleep(2000);
            context.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return fileName;
        }
    }

    private ArrayList<ArrayList<String>> getArrayListData(long countRows) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        Person pepi = new Person("pepi", "bel", "main street", 19, "pld");

        for(int i=0;i < countRows;i++) {

            ArrayList<String> data = new ArrayList<>();

            data.add(pepi.getFirstName());
            data.add(pepi.getLastName());
            data.add(pepi.getStreetAddress());
            data.add(pepi.getAge().toString());
            data.add(pepi.getBornTown());

            list.add(data);
        }
        return list;
    }
}