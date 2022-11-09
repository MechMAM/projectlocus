package com.projectlocus.webservice.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import com.projectlocus.webservice.services.exceptions.BookingDateException;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	private BookingService bookingService;

	@Mock
	private BookingRepository bookingRepository;

	private static Person person1 = new Person(1L, "Maria Brown", "maria@gmail.com", "988888888", "123456");
	private static Person person2 = new Person(2L, "Alex Green", "alex@gmail.com", "977777777", "123456");

	private static Space space1 = new Space(null, "Propriedade", "Auditório 1", 250, 210.50, 80, 150.10, 310.8, 5,
			"Um auditório pequeno", "Muitas coisas");
	private static Space space2 = new Space(null, "Hotel", "Sala de Reunião", 100, 140.2, 60, 115.10, 186.7, 8,
			"Uma sala de reuniões", "Arredores do hotel");

	private static Booking booking1 = new Booking(1L, Instant.parse("2023-01-20T08:30:00Z"),
			Instant.parse("2023-01-20T17:30:00Z"), person1, space1);
	private static Booking booking2 = new Booking(2L, Instant.parse("2023-03-25T08:30:00Z"),
			Instant.parse("2023-03-25T12:30:00Z"), person1, space2);
	private static Booking booking3 = new Booking(3L, Instant.parse("2023-04-10T08:30:00Z"),
			Instant.parse("2023-04-10T12:30:00Z"), person1, space1);
	private static Booking booking4 = new Booking(4L, Instant.parse("2022-12-25T08:30:00Z"),
			Instant.parse("2022-12-25T12:30:00Z"), person2, space2);

	private static Optional<Booking> booking5 = Optional.of(new Booking(5L, Instant.parse("2022-12-25T08:30:00Z"),
			Instant.parse("2022-12-25T12:30:00Z"), person2, space1));

	private static List<Booking> spaceList5 = new ArrayList<Booking>();

	@Test
	@BeforeAll
	static public void initTest() {

		booking1.setMoment(Instant.now());
		booking2.setMoment(Instant.now());
		booking3.setMoment(Instant.now());
		booking4.setMoment(Instant.now());

		List<Booking> spaceList1 = new ArrayList<Booking>();
		Collections.addAll(spaceList1, booking1, booking3);
		List<Booking> spaceList2 = new ArrayList<Booking>();
		Collections.addAll(spaceList2, booking2, booking4);
		List<Booking> spaceList3 = new ArrayList<Booking>();
		Collections.addAll(spaceList3, booking1, booking2, booking3);
		List<Booking> spaceList4 = new ArrayList<Booking>();
		spaceList4.add(booking4);

		Collections.addAll(spaceList5, booking1, booking2, booking3, booking4);

		space1.setSpaceBookings(spaceList1);
		space2.setSpaceBookings(spaceList2);

		person1.setPersonBookings(spaceList3);
		person2.setPersonBookings(spaceList4);
	}

	@Test
	@DisplayName("Teste de verificação de disponibilidade de datas")
	public void testCheckAvailability() {

		Booking bookingTest1 = new Booking(null, Instant.parse("2022-12-24T09:30:00Z"),
				Instant.parse("2022-12-25T10:30:00Z"), person1, space2);

		Booking bookingTest2 = new Booking(null, Instant.parse("2023-01-20T10:30:00Z"),
				Instant.parse("2023-01-20T17:30:00Z"), person1, space1);

		Booking bookingTest3 = new Booking(null, Instant.parse("2023-01-20T08:30:00Z"),
				Instant.parse("2023-01-20T12:30:00Z"), person1, space1);

		Booking bookingTest4 = new Booking(null, Instant.parse("2022-12-25T12:35:00Z"),
				Instant.parse("2022-12-25T17:30:00Z"), person1, space2);

		BookingDateException expected1 = new BookingDateException("Não há disponibilidade para a data selecionada");
		BookingDateException thrown1 = new BookingDateException("");
		BookingDateException thrown2 = new BookingDateException("");
		BookingDateException thrown3 = new BookingDateException("");
		BookingDateException thrown4 = new BookingDateException("");

		try {
			bookingService.checkAvailability(bookingTest1);
		} catch (BookingDateException e) {
			thrown1 = e;
		}

		try {
			bookingService.checkAvailability(bookingTest2);
		} catch (BookingDateException e) {
			thrown2 = e;
		}

		try {
			bookingService.checkAvailability(bookingTest3);
		} catch (BookingDateException e) {
			thrown3 = e;
		}

		try {
			bookingService.checkAvailability(bookingTest4);
		} catch (BookingDateException e) {
			thrown4 = e;
		}

		Assertions.assertEquals(expected1.getMessage(), thrown1.getMessage());
		Assertions.assertEquals(expected1.getMessage(), thrown2.getMessage());
		Assertions.assertEquals(expected1.getMessage(), thrown3.getMessage());
		Assertions.assertEquals(expected1.getMessage(), thrown4.getMessage());
	}

	@Test
	@DisplayName("Teste de reserva com datas nulas")
	public void testCheckDates() {
		Booking bookingTest1 = new Booking(null, null, null, person1, space2);

		Booking bookingTest2 = new Booking(null, Instant.parse("2022-12-26T08:30:00Z"),
				Instant.parse("2022-12-25T09:30:00Z"), person1, space2);

		BookingDateException expected1 = new BookingDateException("Datas não devem ser nulas");
		BookingDateException thrown1 = new BookingDateException("");

		try {
			bookingService.checkDates(bookingTest1);
		} catch (BookingDateException e) {
			thrown1 = e;
		}

		BookingDateException expected2 = new BookingDateException("Data de início deve ser menor que data fim");
		BookingDateException thrown2 = new BookingDateException("");

		try {
			bookingService.checkDates(bookingTest2);
		} catch (BookingDateException e) {
			thrown2 = e;
		}

		Assertions.assertEquals(expected1.getMessage(), thrown1.getMessage());
		Assertions.assertEquals(expected2.getMessage(), thrown2.getMessage());
	}

	@Test
	@DisplayName("Teste para verificar o cálculo do preço da reserva")
	public void evaluateBookingPriceTest() {
		Booking bookingTest1 = new Booking(null, Instant.parse("2022-11-20T09:30:00Z"),
				Instant.parse("2022-11-20T10:30:00Z"), person2, space2);
		double bookingDuration = ChronoUnit.HOURS.between(bookingTest1.getStartDate(), bookingTest1.getEndDate());
		double expectedPrice = space2.getHourlyReservationPrice() * bookingDuration + space2.getCleaningPrice();

		bookingService.evaluateBookingPrice(bookingTest1);

		Assertions.assertEquals(expectedPrice, bookingTest1.getBookingPrice());

	}

	@Test
	@DisplayName("Teste para inserir uma reserva no banco")
	public void insertTest() {

		Booking bookingTest1 = new Booking(null, Instant.parse("2022-11-20T09:30:00Z"),
				Instant.parse("2022-11-20T10:30:00Z"), person2, space2);
		Booking bookingTest2 = new Booking(null, null, null, person1, space2);
		Booking savedBooking = new Booking();

		BookingDateException expected1 = new BookingDateException(
				"Não foi possível inserir a reserva: Datas não devem ser nulas");
		BookingDateException thrown1 = new BookingDateException("");

		try {
			bookingService.insert(bookingTest2);
		} catch (BookingDateException e) {
			thrown1 = e;
		}

		Mockito.when(bookingRepository.save(bookingTest1)).thenReturn(bookingTest1);

		try {
			savedBooking = bookingService.insert(bookingTest1);
		} catch (BookingDateException e) {
			e.printStackTrace();
		}

		Assertions.assertEquals(bookingTest1, savedBooking);
		Assertions.assertEquals(expected1.getMessage(), thrown1.getMessage());
	}

	@Test
	@DisplayName("Teste do método findAll de reservas")
	public void findAllTest() {
		Mockito.when(bookingRepository.findAll()).thenReturn(spaceList5);

		Assertions.assertEquals(spaceList5, bookingService.findAll());

	}

	@Test
	@DisplayName("Teste do método findByID de reservas")
	public void findByIdTest() {
		Mockito.when(bookingRepository.findById(5L)).thenReturn(booking5);

		Assertions.assertEquals(booking5, bookingRepository.findById(5L));
	}
	

}
