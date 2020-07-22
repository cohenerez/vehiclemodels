package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.CityDTO;

import java.util.List;

public interface CityService {
	
	public List<CityDTO> findByStateId(Integer id);


}
