package com.example.config;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRoute extends RouteBuilder {

	public void readCsvData(String fileName) {
		CsvDataFormat csv = new CsvDataFormat();

		csv.setSkipHeaderRecord(true);

		from("file://src/main/resources?fileName=test.csv&noop=true&delay=15m")
				.unmarshal(csv)
				.convertBodyTo(List.class)
				.process(new Processor() {
					@Override
					public void process(Exchange msg) throws Exception {
						List<List<String>> data = (List<List<String>>) msg.getIn().getBody();
						for (List<String> line : data) {
							System.out.println("Original:" + line);
						}
					}
				}).marshal(csv).to("file://src/main/resources?fileName=out.csv")
				.log("done.").end();
	}

	@Override
	public void configure() throws Exception {

		List<Object> result = new ArrayList<>();

		CsvDataFormat csv = new CsvDataFormat();

		csv.setSkipHeaderRecord(true);

		from("file://src/main/resources?fileName=test.csv&noop=true&delay=15m")
				.unmarshal(csv)
				.convertBodyTo(List.class)
				.process(new Processor() {
					@Override
					public void process(Exchange msg) throws Exception {
						ArrayList<String> list = new ArrayList<String>();
						list.add("one");
						list.add("two");
						list.add("three");
						msg.getIn().setBody(list, ArrayList.class);
					}
				}).marshal(csv).to("file://src/main/resources?fileName=out.csv")
				.log("done.").end();
	}
}
