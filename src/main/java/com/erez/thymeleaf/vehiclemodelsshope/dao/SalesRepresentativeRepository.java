package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.SalesRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SalesRepresentativeRepository extends JpaRepository<SalesRepresentative, Integer> {

	// add a method to sort by last name
			public List<SalesRepresentative> findAllByOrderByLastNameAsc();
			
			// search by name
			public List<SalesRepresentative> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstName, String lastName);
	
}
