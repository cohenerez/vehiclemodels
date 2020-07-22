package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.dao.CityRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl  implements CityService{

	
	
	private CityRepository cityRepository;
	
	
	@Autowired
	public CityServiceImpl(CityRepository theCityRepository) {
		
		cityRepository = theCityRepository;
	}


	@Override
	public List<CityDTO> findByStateId(Integer id) {
		return cityRepository.findByStateId(id);
	}



	
}
