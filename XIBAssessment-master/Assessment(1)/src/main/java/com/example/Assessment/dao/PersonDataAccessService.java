package com.example.Assessment.dao;

import com.example.Assessment.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository ("postgres")
public class PersonDataAccessService implements PersonDAO {
    private final JDBCTemplate jdbcTemplate;
    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   @Override
    public List<Person> insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return DB;
    }
//GET /teams/ - Returns a list of teams in the database in JSON format
    @Override
    public List<Person> selectedAllPeople() {
        final string sql ="SELECT id,team from person";
        jdbcTemplate.query(sql, (resultset.1)-> {
            return new Person(resultSet.getString(columnLabel: "id"),
            resultset.getString(columnlable: "team")
            );
        });
        return new Person(id, team);
    }

    //GET /agents/ - Returns a list of all agents in the database in JSON format
    @Override
    public List<Person> selectedPeople() {
        final string sql ="SELECT id,agent from person";
        jdbcTemplate.query(sql, (resultset.1)-> {
            return new Person(resultSet.getString(columnLabel: "id"),
            resultset.getString(columnlable: "agent")
            );
        });
        return new Person(id, team);
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
