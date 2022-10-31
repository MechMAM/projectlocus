package com.projectlocus.webservice.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Space> findById(@PathVariable Long id){
		Space obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Space> insert(@RequestBody Space obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Space> update(@PathVariable Long id, @RequestBody Space obj){
		obj = service.update(id,obj);
		return ResponseEntity.ok().body(obj);
	}

}
