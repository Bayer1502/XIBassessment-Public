package com.example.Assessment.service;

import com.example.Assessment.dao.PersonDAO;
import com.example.Assessment.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDAO personDAO;
    @Autowired
    public PersonService(@Qualifier(value = "postgres") PersonDAO personDAO) {
        this.personDAO= personDAO;
    }
    public List<Person> addPerson(Person person) {
        List<Person> people = personDAO.insertPerson(person);
        return people;
    }
    public List<Person> getAllpeople() {
        return personDAO.selectedAllPeople();
    }
    public Optional<Person> getPersonById(UUID id) {
        return personDAO.selectedPersonId(id);
    }
    //to delete a person
    public  int deletePerson(UUID id) {
        return personDAO.deletePersonById(id);
    }
    //to update a person
    public int updatePerson(UUID id, Person newPerson) {
        return personDAO.updatePersonid(id, newPerson);
    }
}
