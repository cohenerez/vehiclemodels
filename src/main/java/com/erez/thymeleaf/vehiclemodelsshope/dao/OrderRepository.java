package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Customer, Integer> {

	// add a method to sort by last name
		public List<Order> findAllByOrderByCustomerNameAsc();
		
		// search by name
		public List<Order> findByCustomerNameContainsOrContactLastNameContainsAllIgnoreCase(String customerName, String contactLastName);
		
			
}
