package com.projectlocus.webservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectlocus.webservice.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
