package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Order;
import com.erez.thymeleaf.vehiclemodelsshope.exception.NoRecordFoundException;
import com.erez.thymeleaf.vehiclemodelsshope.exception.RemoteServiceNotAvailableException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;


public interface OrderService {
	
	 public List<Order> getCustomers();

	 public void saveCustomer(Order theCustomer);

	 @Retryable(value = { RemoteServiceNotAvailableException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	 public Order getOrder(Integer theId);

	 public boolean deleteOrder(Integer theId) throws NoRecordFoundException;
	
	 public List<Order> searchOrderById(Integer orderId);
	
	 public Page<Order> getAllOrdersPaginated(Pageable pageable);
	
	 public Page<Order> searchOrderByNumber(Integer orderId, Pageable pageable );
	 
	
	 
	 @Recover
	public String getBackendResponseFallback(RuntimeException e);

}
