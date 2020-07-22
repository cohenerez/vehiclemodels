package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// add a method to sort by last name
		public List<Customer> findAllByOrderByCustomerNameAsc();
		
		// search by name
		public List<Customer> findByCustomerNameContainsOrContactLastNameContainsAllIgnoreCase(String customerName, String contactLastName);
		
			
}
