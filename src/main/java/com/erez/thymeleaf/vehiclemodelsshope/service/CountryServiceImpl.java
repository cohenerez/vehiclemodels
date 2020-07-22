package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.dao.CountryRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {


	private CountryRepository countryRepository;


	@Autowired
	public CountryServiceImpl(@Qualifier("countryRepository") CountryRepository theCountryRepository) {

		countryRepository = theCountryRepository;
	}

	@Override
	public Iterable<Country> findAll() {
		return countryRepository.findAll();
	}

	@Override
	public Country find(int id) {
		return countryRepository.findById(id).get();
	}

}
