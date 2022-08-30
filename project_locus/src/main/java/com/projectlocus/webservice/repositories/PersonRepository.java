package com.projectlocus.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectlocus.webservice.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
