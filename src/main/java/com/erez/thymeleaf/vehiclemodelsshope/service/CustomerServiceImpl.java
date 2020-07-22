package com.erez.thymeleaf.vehiclemodelsshope.service;


import com.erez.thymeleaf.vehiclemodelsshope.dao.CustomerRepository;
import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import com.erez.thymeleaf.vehiclemodelsshope.exception.RemoteServiceNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

	CustomerRepository customerRepository;
		
	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
		
	}

	private List<Customer> results = null;
	private int pageSize, currentPage, startItem;
	private List<Customer> list = null;


	@Override
	public void saveCustomer(Customer theCustomer) {

		customerRepository.save(theCustomer);
	}

	@Override
	public Customer getCustomer(Integer theId) {

		Optional <Customer> result = customerRepository.findById(theId);
		Customer theCustome = null;
		if(result.isPresent()) {
			theCustome = result.get();
		}
		else {
			throw new NoRecordFoundException("No customer fund with customer id  :" + theId);
		}

		return theCustome;

	}

	@Override
	public boolean deleteCustomer(Integer theId) {
		Optional <Customer> result = customerRepository.findById(theId);
		if(result.isPresent()) {
			customerRepository.deleteById(theId);
			return true;
		}

		else{
			throw new NoRecordFoundException("No customer fund with customer id  :" + theId);
		}
	}

	@Override
	public List<Customer> searchCustomerByName(String theName) {

		results = null;

		if (theName != null && (theName.trim().length() > 0)) {
			results = customerRepository. findByCustomerNameContainsOrContactLastNameContainsAllIgnoreCase(theName, theName);
		}
		else {

			results = customerRepository.findAllByOrderByCustomerNameAsc();
		}

		return results;
	}

	@Override
	public Page<Customer> getAllCustomersPaginated(Pageable pageable) {

		results = null;
		results = customerRepository.findAllByOrderByCustomerNameAsc();
		calculatePageSize(pageable);

		Page<Customer> custumerPage =  new PageImpl<Customer>(list, PageRequest.of(currentPage, pageSize), results.size());
		return custumerPage;

	}


	@Override
	public List<Customer> getCustomers() {
		results = null;
	    try {
		     results = customerRepository.findAllByOrderByCustomerNameAsc();
	    }
		finally {
			 if(results == null) {
				 throw new RemoteServiceNotAvailableException(" Spring-retry.. teyed 3 times");
				}
			if( results.size()== 0  ) {
				throw new NoRecordFoundException("No customer fund with customer id  :");
			}
		}
	   

		return results;
	}

	@Override
	public Page<Customer> searchCustomerByName(String theName, Pageable pageable) {

		results = null;
		if (theName != null && (theName.trim().length() > 0)) {
			results = customerRepository. findByCustomerNameContainsOrContactLastNameContainsAllIgnoreCase(theName, theName);

		}

		else {

			results = customerRepository.findAllByOrderByCustomerNameAsc();

		}
		calculatePageSize(pageable); 
		Page<Customer> custumerPage =  new PageImpl<Customer>(list, PageRequest.of(currentPage, pageSize), results.size());

		return custumerPage;
	}

	private void calculatePageSize(Pageable pageable) {

		pageSize = currentPage = pageSize =0;

		pageSize = pageable.getPageSize();

		currentPage = pageable.getPageNumber();

		startItem = currentPage * pageSize;


		if(results.size() < startItem) {
			list= Collections.emptyList();
		}
		else {

			int toIndex =Math.min(startItem + pageSize, results.size());
			list = results.subList(startItem, toIndex);
		}
	}

	@Override
	public String getBackendResponseFallback(RuntimeException e) {
		System.out.println("All retries completed, so Fallback method called!!!");
		return "All retries completed, so Fallback method called!!!";
	}

	
	

	
}
	

