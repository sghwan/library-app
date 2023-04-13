package com.group.libraryapp.tmp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Person> people = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addPerson(String name) {
        people.add(new Person(this, name));
    }

    public void addPerson(Person person) {
        person.setTeam(this);
        people.add(person);
    }
}
