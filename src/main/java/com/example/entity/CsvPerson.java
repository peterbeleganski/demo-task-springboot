package com.example.entity;


public class CsvPerson {
    private String firstName;
    private String lastName;
    private String streetAddress;
    private Integer age;
    private String bornTown;

    public CsvPerson() {
    }

    public CsvPerson(String firstName, String lastName, String streetAddress, Integer age, String bornTown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.age = age;
        this.bornTown = bornTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBornTown() {
        return bornTown;
    }

    public void setBornTown(String bornTown) {
        this.bornTown = bornTown;
    }
}
