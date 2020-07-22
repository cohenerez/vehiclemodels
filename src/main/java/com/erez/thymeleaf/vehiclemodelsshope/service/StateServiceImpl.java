package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.dao.StateRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.StateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {
	
	
	
	
	private StateRepository stateRepository;
	
	
	@Autowired
	public StateServiceImpl(StateRepository theStateRepository) {
	
		stateRepository = theStateRepository;
	}


	@Override
	public List<StateDTO> findByCountryId(Integer id) {
		return stateRepository.findByCountryId(id);
	}


}
