package com.group.libraryapp.tmp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);
}
