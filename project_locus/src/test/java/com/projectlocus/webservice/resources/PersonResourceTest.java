package com.projectlocus.webservice.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projectlocus.webservice.entities.Person;
import com.projectlocus.webservice.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonResourceTest {
	
	@InjectMocks
	private PersonResource resource;
	
	@Mock
	private PersonService service;
			
	@Test
	@DisplayName("Teste do endpoint findAll")
	public void testFindAll() {
		
		Person p1 = new Person(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Person p2 = new Person(2L, "Alex Green", "alex@gmail.com", "977777777", "123456");
		List<Person> pList = new ArrayList<Person>();
		Collections.addAll(pList, p1,p2);
		
		Mockito.when(service.findAll()).thenReturn(pList);
		
		
		ResponseEntity<List<Person>> personsList = resource.findAll();
		Assertions.assertEquals(pList, personsList.getBody());
		Assertions.assertEquals(HttpStatus.OK.value(), personsList.getStatusCodeValue());
		
	}
	
	//Anotações para os testes @Test @DisplayName("Nome do teste") @Disabled 
	//Assertion.assertEquals @BeforeEach @AfterEach @BeforeAll @AfterAll
	//

}
