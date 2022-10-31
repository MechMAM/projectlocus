package com.projectlocus.webservice.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_space")
public class Space implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String propertyName;
	private String spaceName;
	private int capacity;
	private double area;
//	private Set<Integer> differentialsList = new HashSet<>();
//	private Set<Integer> purposesList = new HashSet<>();
//	private Set<Integer> acessibilityResources = new HashSet<>();
//	private Set<Integer> includedServices = new HashSet<>();
//	private Set<Integer> additionalServices = new HashSet<>();
	private int cleaningTime;
	private double cleaningPrice;
	private double hourlyReservationPrice;
	private int discountPercentage;
	private String spaceDescription;
	private String surroundingsAdvantages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "space")
	private List<Booking> spaceBookings = new ArrayList<>();
	
	public Space() {
	}
	
	public Space(Long id, String propertyName, String spaceName, int capacity, double area, int cleaningTime, double cleaningPrice, double hourlyReservationPrice, int discountPercentage, String spaceDescription, String surroundingsAdvantages) {
		super();
		this.id = id;
		this.propertyName = propertyName;
		this.spaceName = spaceName;
		this.capacity = capacity;
		this.area = area;
		this.cleaningTime = cleaningTime;
		this.cleaningPrice = cleaningPrice;
		this.hourlyReservationPrice = hourlyReservationPrice;
		this.discountPercentage = discountPercentage;
		this.spaceDescription = spaceDescription;
		this.surroundingsAdvantages = surroundingsAdvantages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

//	public Set<Integer> getDifferentialsList() {
//		return differentialsList;
//	}
//
//	public void setDifferentialsList(Set<Integer> differentialsList) {
//		this.differentialsList = differentialsList;
//	}
//
//	public Set<Integer> getPurposesList() {
//		return purposesList;
//	}
//
//	public void setPurposesList(Set<Integer> purposesList) {
//		this.purposesList = purposesList;
//	}
//
//	public Set<Integer> getAcessibilityResources() {
//		return acessibilityResources;
//	}
//
//	public void setAcessibilityResources(Set<Integer> acessibilityResources) {
//		this.acessibilityResources = acessibilityResources;
//	}
//
//	public Set<Integer> getIncludedServices() {
//		return includedServices;
//	}
//
//	public void setIncludedServices(Set<Integer> includedServices) {
//		this.includedServices = includedServices;
//	}
//
//	public Set<Integer> getAdditionalServices() {
//		return additionalServices;
//	}
//
//	public void setAdditionalServices(Set<Integer> additionalServices) {
//		this.additionalServices = additionalServices;
//	}

	public int getCleaningTime() {
		return cleaningTime;
	}

	public void setCleaningTime(int cleaningTime) {
		this.cleaningTime = cleaningTime;
	}

	public double getCleaningPrice() {
		return cleaningPrice;
	}

	public void setCleaningPrice(double cleaningPrice) {
		this.cleaningPrice = cleaningPrice;
	}

	public double getHourlyReservationPrice() {
		return hourlyReservationPrice;
	}

	public void setHourlyReservationPrice(double hourlyReservationPrice) {
		this.hourlyReservationPrice = hourlyReservationPrice;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getSpaceDescription() {
		return spaceDescription;
	}

	public void setSpaceDescription(String spaceDescription) {
		this.spaceDescription = spaceDescription;
	}

	public String getSurroundingsAdvantages() {
		return surroundingsAdvantages;
	}

	public void setSurroundingsAdvantages(String surroundingsAdvantages) {
		this.surroundingsAdvantages = surroundingsAdvantages;
	}
	
	public List<Booking> getSpaceBookings() {
		return spaceBookings;
	}

	public void setSpaceBookings(List<Booking> spaceBookings) {
		this.spaceBookings = spaceBookings;
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
		Space other = (Space) obj;
		return id == other.id;
	}
	
	
	
	
	
	
	
	

}
