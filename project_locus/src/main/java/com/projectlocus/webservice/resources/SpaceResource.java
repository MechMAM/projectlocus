package com.projectlocus.webservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectlocus.webservice.entities.Space;
import com.projectlocus.webservice.services.SpaceService;

@RestController
@RequestMapping(value = "/spaces")
public class SpaceResource {
	
 	@Autowired
	private SpaceService service;
	
	@GetMapping
	public ResponseEntity<List<Space>> findAll(){
		List<Space> spaces = service.findAll();
		return ResponseEntity.ok().body(spaces);
	}

}
