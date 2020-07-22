package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import com.erez.thymeleaf.vehiclemodelsshope.exception.RemoteServiceNotAvailableException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;


public interface CustomerService {
	
	 public List<Customer> getCustomers();

	 public void saveCustomer(Customer theCustomer);

	 @Retryable(value = { RemoteServiceNotAvailableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	 public Customer getCustomer(Integer theId);

	 public boolean deleteCustomer(Integer theId) throws NoRecordFoundException;
	
	 public List<Customer> searchCustomerByName(String theName);
	
	 public Page<Customer> getAllCustomersPaginated(Pageable pageable);
	
	 public Page<Customer> searchCustomerByName(String theName, Pageable pageable );
	 
	
	 
	 @Recover
	public String getBackendResponseFallback(RuntimeException e);

}
