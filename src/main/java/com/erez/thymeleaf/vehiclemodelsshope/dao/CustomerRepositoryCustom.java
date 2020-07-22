package com.erez.thymeleaf.vehiclemodelsshope.dao;


import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;

import java.util.List;
import java.util.Set;


public interface CustomerRepositoryCustom  {

	 List<Customer> findCustomerByName(Set<String> customerName);
			
}
