package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.dao.OfficeRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Office;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class OfficeServiceImpl implements OfficeService {
	
	
		@Autowired
		private OfficeRepository officeRepository;
		
		@Override
		@Transactional
		public List<Office> getOffices() {
			return officeRepository.findAll();
		}

		@Override
		@Transactional
		public void saveOffice(Office theOffice) {

			officeRepository.save(theOffice);
		}

		@Override
		@Transactional
		public Office getOffice(int theId) {
			
			Optional<Office> result = officeRepository.findById(theId);
			Office theOffice =null;
			if(result.isPresent()) {
				theOffice = result.get();
			}
			
			else {
				throw new NoRecordFoundException("No office fund with office id  :" + theId);
			}
			return theOffice;
		}

		@Override
		@Transactional
		public void deleteOffice(int theId) {
			
			officeRepository.deleteById(theId);
		}

}
