package com.projectlocus.webservice.services;

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
	
	public List<Booking> findAll(){
		return repository.findAll();
	}
	
	public Booking findById(Long id) {
		Optional<Booking> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Booking insert(Booking obj) {
		return repository.save(obj);
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
			Booking entity = repository.getReferenceById(id); //getOne está deprecated, mas é utilizado pois não vai direto ao banco e é mais eficiente
			updateData(entity, obj);
			return repository.save(entity);			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Booking entity, Booking obj) {
		entity.setDate(obj.getDate());
		entity.setClient(obj.getClient());
		entity.setMoment(obj.getMoment());
		entity.setSpace(obj.getSpace());
	}

}
