package com.projectlocus.webservice.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonTest {

	@Test
	public void UserTest() {
		Person person = new Person(1L,"Miguel","05945366950","miguel@gmail.com","123456");
		assertEquals("Miguel", person.getName());
//		assertTrue(person.toString().contains("Miguel"));
	}
	
	

}
