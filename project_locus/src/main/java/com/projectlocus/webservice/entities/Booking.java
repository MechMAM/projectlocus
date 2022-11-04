package com.projectlocus.webservice.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_booking")
public class Booking implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Instant moment;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Instant startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
	private Instant endDate;	
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Person client;
	
	@ManyToOne
	@JoinColumn(name = "space_id")
	private Space space;
	
	public Booking() {
	}
	
	public Booking(Long id, Instant startDate, Instant endDate, Person client, Space space) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.client = client;
		this.space = space;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Instant getStartDate() {
		return startDate;
	}

	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}

	public Instant getEndDate() {
		return endDate;
	}

	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}

	public Person getClient() {
		return client;
	}

	public void setClient(Person client) {
		this.client = client;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
	
	

}
