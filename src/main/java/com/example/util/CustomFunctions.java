package com.example.util;

import com.example.entity.CsvPerson;
import com.example.entity.Person;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class CustomFunctions {

    String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        return sb.toString();
    }

    public static boolean checkIfFileExists(String fileName) {
        File f = new File("/src/main/resources/output/" + fileName + ".csv");
        if(!f.exists() && f.isDirectory()) {
            return true;
        }
        return false;
    }

    public void startContext(CamelContext context) throws Exception {
        context.start();
        Thread.sleep(1100);
        context.stop();
    }

    public static List<CsvPerson> getCsvPeople(Exchange msg) {
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

    public String encryptString() {
        String value = generateRandomWord(10);
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(value.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1 + ".csv";
    }

    public void ensureFileStartExists() {
        try {
            File file = new File("C:/Users/petar.beleganski/Desktop/demo-task-springboot/src/main/resources/output/test.csv");
            boolean fvar = file.createNewFile();
            if (fvar){
                System.out.println("File has been created successfully");
            }
            else{
                System.out.println("File already present at the specified location");
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getArrayListData(long countRows) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        Person personData = new Person("pepi", "bel", "main street", 19, "pld");

        for(int i=0;i < countRows;i++) {

            ArrayList<String> data = new ArrayList<>();

            data.add(personData.getFirstName());
            data.add(personData.getLastName());
            data.add(personData.getStreetAddress());
            data.add(personData.getAge().toString());
            data.add(personData.getBornTown());

            list.add(data);
        }
        return list;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
