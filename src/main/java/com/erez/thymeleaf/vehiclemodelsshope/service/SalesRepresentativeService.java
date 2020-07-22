package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.SalesRepresentative;

import java.util.List;

public  interface SalesRepresentativeService {

	    public List<SalesRepresentative> getSalesRepresentatives();

		public void saveSalesRepresentative(SalesRepresentative theSalesRepresentative);

		public SalesRepresentative getSalesRepresentative(Integer theId);

		public void deleteSalesRepresentative(Integer theId);
	
}
