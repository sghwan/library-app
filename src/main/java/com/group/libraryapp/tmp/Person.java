package com.group.libraryapp.tmp;

import javax.persistence.*;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Person() {

    }

    public Person(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
