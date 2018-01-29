package com.example.controllers;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {


    @Autowired
    private PersonRepository repository;

    @RequestMapping("/")
    public List<Person> getAllPeople() {
        List<Person> people = this.repository.findAll();

        return people;
    }

}
