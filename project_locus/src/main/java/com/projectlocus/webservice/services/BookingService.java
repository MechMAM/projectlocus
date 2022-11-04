package com.projectlocus.webservice.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projectlocus.webservice.entities.Booking;
import com.projectlocus.webservice.repositories.BookingRepository;
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

	public Booking insert(Booking obj) throws Exception {
		try {
			obj.setMoment(Instant.now());
			checkAvailability(obj);
			return repository.save(obj);
		} catch (Exception e) {
			throw new Exception("Não foi possível inserir a reserva");
		}
	}

	private void checkAvailability(Booking obj) throws Exception {
		try {
			if (obj.getStartDate().compareTo(obj.getEndDate()) > 0 || obj.getStartDate().equals(null)
					|| obj.getEndDate().equals(null)) {
				throw new Exception("Data de início deve ser maior que a data fim e as datas devem ser válidas");
			}
			boolean available = true;			
			for (Booking spaceBooking : obj.getSpace().getSpaceBookings()) {
				if (spaceBooking.getStartDate().compareTo(obj.getEndDate()) < 0
						&& spaceBooking.getEndDate().compareTo(obj.getStartDate()) > 0) {
					available = false;
				}		
			}			
			if (!available) {
				throw new Exception("Não há disponibilidade para a data selecionada");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
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
