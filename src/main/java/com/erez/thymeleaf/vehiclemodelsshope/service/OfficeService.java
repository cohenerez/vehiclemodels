package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Office;

import java.util.List;

public interface OfficeService {
	
	public List<Office> getOffices();

	public void saveOffice(Office theOffice);

	public Office getOffice(int theId);

	public void deleteOffice(int theId);

}
