package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Customer> findCustomerByName(Set<String> names) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
		Root<Customer> customer = query.from(Customer.class);
		Path<String> customerNamePath = customer.get("customerName");
		
		 List<Predicate> predicates = new ArrayList<>();
		
		 for (String name : names) {
	            predicates.add(cb.like(customerNamePath, name));
	  }
		 
		 query.select(customer).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		 
		 return entityManager.createQuery(query).getResultList();
	}

}
