package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class Person {

    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private Integer age;
	private String bornTown;

	public Person() {
	}

	public Person(String firstName, String lastName, String streetAddress, Integer age, String bornTown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.age = age;
        this.bornTown = bornTown;
    }

	public String getBornTown() {
		return bornTown;
	}

	public void setBornTown(String bornTown) {
		this.bornTown = bornTown;
	}

	public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    @Override
    public String toString() {
        return this.getFirstName() + "," + this.getLastName() + "," + this.getStreetAddress() + "," +
                + this.getAge() + "," + this.getBornTown();
    }
}
