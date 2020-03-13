package com.example.Assessment.dao;

import com.example.Assessment.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository ("fakeDAO")
public class FakePersonDataAccessService extends PersonDAO {
    public static List<Person> DB = new ArrayList<>();
    @Override
    public List<Person> insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return DB;
    }

    @Override
    public List<Person> selectedAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectedPersonId(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectedPersonId(id);
        if (personMaybe.isEmpty()){
            DB.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonid(UUID id, Person update) {
        return selectedPersonId(id)
                .map(person -> {
                    int indexOfPersonToUpdate =DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
