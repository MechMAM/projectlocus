package com.projectlocus.webservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectlocus.webservice.entities.Space;
import com.projectlocus.webservice.repositories.SpaceRepository;

@Service
public class SpaceService {
	
	@Autowired
	private SpaceRepository repository;
	
	public List<Space> findAll(){
		return repository.findAll();
	}

}
