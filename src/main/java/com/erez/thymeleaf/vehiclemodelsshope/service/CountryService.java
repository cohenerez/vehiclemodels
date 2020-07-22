package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Country;

public interface CountryService {
	
	public Iterable<Country> findAll();

	public Country find(int id);

}
