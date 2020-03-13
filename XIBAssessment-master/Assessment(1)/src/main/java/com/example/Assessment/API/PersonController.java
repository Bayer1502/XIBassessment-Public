package com.example.Assessment.API;

import com.example.Assessment.model.Person;
import com.example.Assessment.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/vi/person")
@RestController
public class PersonController {
    private final PersonService PersonService;
    private String id;

    @Autowired
    public PersonController(PersonService PersonService) {
        this.PersonService = PersonService;
    }
    //used to tell string that this method is used to post
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        PersonService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return PersonService. getAllpeople();
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return PersonService.getPersonById(id)
                .orElse(null);
    }
    //to delete a person
    @DeleteMapping(path = ("{id}"))
    public void deletePersonById(@PathVariable("id") UUID id) {
        PersonService.deletePerson(id);
    }
    //once person is deleted update
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Person personToUpdate) {
        PersonService.updatePerson(id, personToUpdate);
    }
}
