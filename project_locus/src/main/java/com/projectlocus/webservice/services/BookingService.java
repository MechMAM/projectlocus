package com.projectlocus.webservice.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projectlocus.webservice.entities.Booking;
import com.projectlocus.webservice.repositories.BookingRepository;
import com.projectlocus.webservice.services.exceptions.BookingDateException;
import com.projectlocus.webservice.services.exceptions.DatabaseException;
import com.projectlocus.webservice.services.exceptions.ResourceNotFoundException;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repository;

	public List<Booking> findAll() {
		return repository.findAll();
	}

	public Booking findById(Long id) {
		Optional<Booking> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Booking insert(Booking obj) {
		try {
			obj.setMoment(Instant.now());
			checkDates(obj);
			checkAvailability(obj);
			evaluateBookingPrice(obj);
			return repository.save(obj);
		} catch (BookingDateException e) {
			throw new BookingDateException("Não foi possível inserir a reserva: " + e.getMessage());
		}
	}

	public void evaluateBookingPrice(Booking obj) {
		double bookingDuration = ChronoUnit.HOURS.between(obj.getStartDate(), obj.getEndDate());
		double bookingPrice = obj.getSpace().getHourlyReservationPrice() * bookingDuration
				+ obj.getSpace().getCleaningPrice();
		obj.setBookingPrice(bookingPrice);
	}

	public void checkDates(Booking obj) {
		try {
			if (obj.getStartDate().compareTo(obj.getEndDate()) > 0) {
				throw new BookingDateException("Data de início deve ser menor que data fim");
			}
		} catch (NullPointerException e) {
			throw new BookingDateException("Datas não devem ser nulas");
		}
	}

	public void checkAvailability(Booking obj) {
		for (Booking spaceBooking : obj.getSpace().getSpaceBookings()) {
			int cleaningTime = spaceBooking.getSpace().getCleaningTime();
			if ((obj.getEndDate().compareTo(spaceBooking.getEndDate().plus(cleaningTime, ChronoUnit.MINUTES)) <= 0
					&& obj.getEndDate().compareTo(spaceBooking.getStartDate()) >= 0)
					|| (obj.getStartDate()
							.compareTo(spaceBooking.getEndDate().plus(cleaningTime, ChronoUnit.MINUTES)) <= 0
							&& obj.getStartDate().compareTo(spaceBooking.getStartDate()) >= 0)) {
				throw new BookingDateException("Não há disponibilidade para a data selecionada");
			}
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Booking update(Long id, Booking obj) {
		try {
			Booking entity = repository.getReferenceById(id); // getOne está deprecated, mas é utilizado pois não vai
																// direto ao banco e é mais eficiente
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Booking entity, Booking obj) {
		entity.setStartDate(obj.getStartDate());
		entity.setEndDate(obj.getEndDate());
		entity.setClient(obj.getClient());
		entity.setMoment(obj.getMoment());
		entity.setSpace(obj.getSpace());
	}

}
