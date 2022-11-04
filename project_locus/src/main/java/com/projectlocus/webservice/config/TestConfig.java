package com.projectlocus.webservice.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projectlocus.webservice.entities.Booking;
import com.projectlocus.webservice.entities.Person;
import com.projectlocus.webservice.entities.Space;
import com.projectlocus.webservice.repositories.BookingRepository;
import com.projectlocus.webservice.repositories.PersonRepository;
import com.projectlocus.webservice.repositories.SpaceRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Person p1 = new Person(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Person p2 = new Person(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		personRepository.saveAll(Arrays.asList(p1,p2));
		
		Space s1 = new Space(null,"Propriedade","Auditório 1",250,210.50,80,150.10,310.8,5,"Um auditório pequeno","Muitas coisas");
		
		Space s2 = new Space(null,"Hotel","Sala de Reunião",100,140.2,60,115.10,186.7,8,"Uma sala de reuniões","Arredores do hotel");
		
		spaceRepository.saveAll(Arrays.asList(s1,s2));
		
		Booking b1 = new Booking(null,Instant.parse("2023-01-20T08:30:00Z"),Instant.parse("2023-01-20T17:30:00Z"),p1,s1);
		b1.setMoment(Instant.now());
				
		Booking b2 = new Booking(null,Instant.parse("2023-03-25T08:30:00Z"),Instant.parse("2023-03-25T12:30:00Z"),p2,s2);
		b2.setMoment(Instant.now());
		
		
		bookingRepository.saveAll(Arrays.asList(b1,b2));
		
		
	}

}
