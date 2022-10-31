package com.projectlocus.webservice.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projectlocus.webservice.entities.Person;
import com.projectlocus.webservice.repositories.PersonRepository;
import com.projectlocus.webservice.services.exceptions.DatabaseException;
import com.projectlocus.webservice.services.exceptions.ResourceNotFoundException;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	public List<Person> findAll(){
		return repository.findAll();
	}
	
	public Person findById(Long id) {
		Optional<Person> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Person insert(Person obj) {
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
	
	public Person update(Long id, Person obj) {
		try {
			Person entity = repository.getReferenceById(id); //getOne está deprecated, mas é utilizado pois não vai direto ao banco e é mais eficiente
			updateData(entity, obj);
			return repository.save(entity);			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Person entity, Person obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPassword(obj.getPassword());
	}

}
