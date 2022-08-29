package com.projectlocus.webservice.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectlocus.webservice.entities.Person;

@RestController
@RequestMapping(value = "/persons")
public class PersonResource {
	
	@GetMapping
	public ResponseEntity<Person> findAll(){
		Person p = new Person(1L, "sdfsdfsfs","dsfsdfsdfsd","sdfsdfsdf","sdfsdfsdf");
		return ResponseEntity.ok().body(p);
	}

}
