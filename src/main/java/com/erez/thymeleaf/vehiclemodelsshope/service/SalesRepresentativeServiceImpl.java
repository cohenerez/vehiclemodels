package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.dao.SalesRepresentativeRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.SalesRepresentative;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SalesRepresentativeServiceImpl implements  SalesRepresentativeService {
	
	
	

		@Autowired
		private SalesRepresentativeRepository salesRepresentativeRepository;
		
		@Override
		public List<SalesRepresentative> getSalesRepresentatives() {
			
			return salesRepresentativeRepository.findAll();
		}

		@Override
		public void saveSalesRepresentative(SalesRepresentative theSalesRepresentative) {
			salesRepresentativeRepository.save(theSalesRepresentative);

		}

		@Override
		public SalesRepresentative getSalesRepresentative(Integer theSalesRepresentativeId) {
			Optional<SalesRepresentative> result = salesRepresentativeRepository.findById(theSalesRepresentativeId);
			SalesRepresentative theSalesRepresentative = null;
			if(result.isPresent()) {
				theSalesRepresentative = result.get();
			}
			
			else {
				 throw new NoRecordFoundException("No fund SalesRepresentative whth id " + theSalesRepresentativeId); 
			}
			return theSalesRepresentative;
			
		}

		@Override
		public void deleteSalesRepresentative(Integer theSalesRepresentativeId) {
			salesRepresentativeRepository.deleteById(theSalesRepresentativeId);

		}

}
