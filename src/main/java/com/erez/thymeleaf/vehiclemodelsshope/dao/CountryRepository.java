package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("countryRepository")
public interface CountryRepository extends CrudRepository<Country, Integer> {

}