package com.example.Assessment.dao;

import com.example.Assessment.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class PersonDAO {
    public abstract List<Person> insertPerson(UUID id, Person person);


    public List<Person> insertPerson(Person person) {
        UUID id= UUID.randomUUID();
        return insertPerson(id, person);
    }
    public abstract List<Person> selectedAllPeople();

    public abstract Optional<Person>selectedPersonId(UUID id);

    public abstract int deletePersonById(UUID id);

    public abstract int updatePersonid(UUID id, Person person);
}
