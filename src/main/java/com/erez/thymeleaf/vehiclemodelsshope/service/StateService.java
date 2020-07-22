package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.StateDTO;

import java.util.List;

public interface StateService {
	
	public List<StateDTO> findByCountryId(Integer id);

}
