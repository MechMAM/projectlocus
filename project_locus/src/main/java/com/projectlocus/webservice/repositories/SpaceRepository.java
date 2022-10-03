package com.projectlocus.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectlocus.webservice.entities.Space;

public interface SpaceRepository extends JpaRepository<Space, Long>{

}
