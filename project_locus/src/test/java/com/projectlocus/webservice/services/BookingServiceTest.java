package com.projectlocus.webservice.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projectlocus.webservice.entities.Booking;
import com.projectlocus.webservice.entities.Person;
import com.projectlocus.webservice.entities.Space;
import com.projectlocus.webservice.repositories.BookingRepository;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	private BookingService bookingService;

	@Mock
	private BookingRepository bookingRepository;

	private Person person1 = new Person(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");
	private Person person2 = new Person(2L, "Alex Green", "alex@gmail.com", "977777777", "123456");

	private Space space1 = new Space(null, "Propriedade", "Auditório 1", 250, 210.50, 80, 150.10, 310.8, 5,
			"Um auditório pequeno", "Muitas coisas");
	private Space space2 = new Space(null, "Hotel", "Sala de Reunião", 100, 140.2, 60, 115.10, 186.7, 8,
			"Uma sala de reuniões", "Arredores do hotel");

	private Booking booking1 = new Booking(1L, Instant.parse("2023-01-20T08:30:00Z"),
			Instant.parse("2023-01-20T17:30:00Z"), person1, space1);
	private Booking booking2 = new Booking(2L, Instant.parse("2023-03-25T08:30:00Z"),
			Instant.parse("2023-03-25T12:30:00Z"), person1, space2);
	private Booking booking3 = new Booking(3L, Instant.parse("2023-04-10T08:30:00Z"),
			Instant.parse("2023-04-10T12:30:00Z"), person1, space1);
	private Booking booking4 = new Booking(4L, Instant.parse("2022-12-25T08:30:00Z"),
			Instant.parse("2022-12-25T12:30:00Z"), person2, space2);

	@Test
	@BeforeAll
	public void initTest() {

		booking1.setMoment(Instant.now());
		booking2.setMoment(Instant.now());
		booking3.setMoment(Instant.now());
		booking4.setMoment(Instant.now());

		List<Booking> spaceList1 = new ArrayList<Booking>();
		Collections.addAll(spaceList1, booking1, booking3);
		List<Booking> spaceList2 = new ArrayList<Booking>();
		Collections.addAll(spaceList1, booking2, booking4);
		List<Booking> spaceList3 = new ArrayList<Booking>();
		Collections.addAll(spaceList1, booking1, booking2, booking3);
		List<Booking> spaceList4 = new ArrayList<Booking>();
		spaceList4.add(booking4);

		space1.setSpaceBookings(spaceList1);
		space2.setSpaceBookings(spaceList2);

		person1.setPersonBookings(spaceList3);
		person2.setPersonBookings(spaceList4);
	}

	@Test
	@DisplayName("Teste tentativa de reserva com término dentro de outra reserva")
	public void testCheckAvailability() {

		Booking bookingTest = new Booking(null, Instant.parse("2022-12-24T08:30:00Z"),
				Instant.parse("2022-12-25T09:30:00Z"), null, null);
		
		

//		Mockito.when(bookingRepository.save(booking4)).thenReturn(booking4);

//		Mockito.when(service.findAll()).thenReturn(pList);

//		ResponseEntity<List<Person>> personsList = resource.findAll();
//		Assertions.assertEquals(pList, personsList.getBody());
//		Assertions.assertEquals(HttpStatus.OK.value(), personsList.getStatusCodeValue());

	}

}
