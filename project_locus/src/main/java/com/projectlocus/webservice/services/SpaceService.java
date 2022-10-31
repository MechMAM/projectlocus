package com.projectlocus.webservice.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projectlocus.webservice.entities.Space;
import com.projectlocus.webservice.repositories.SpaceRepository;
import com.projectlocus.webservice.services.exceptions.DatabaseException;
import com.projectlocus.webservice.services.exceptions.ResourceNotFoundException;

@Service
public class SpaceService {
	
	@Autowired
	private SpaceRepository repository;
	
	public List<Space> findAll(){
		return repository.findAll();
	}
	
	public Space findById(Long id) {
		Optional<Space> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Space insert(Space obj) {
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
	
	public Space update(Long id, Space obj) {
		try {
			Space entity = repository.getReferenceById(id); //getOne está deprecated, mas é utilizado pois não vai direto ao banco e é mais eficiente
			updateData(entity, obj);
			return repository.save(entity);			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Space entity, Space obj) {		
		entity.setPropertyName(obj.getPropertyName());
		entity.setSpaceName(obj.getSpaceName());
		entity.setCapacity(obj.getCapacity());
		entity.setArea(obj.getArea());
		entity.setCleaningTime(obj.getCleaningTime());
		entity.setCleaningPrice(obj.getCleaningPrice());
		entity.setHourlyReservationPrice(obj.getHourlyReservationPrice());
		entity.setDiscountPercentage(obj.getDiscountPercentage());
		entity.setSpaceDescription(obj.getSpaceDescription());
		entity.setSurroundingsAdvantages(obj.getSurroundingsAdvantages());
	}

}
