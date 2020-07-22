package com.erez.thymeleaf.vehiclemodelsshope.service;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Address;

import java.util.List;


public interface AddressService {
	
	public List<Address> getAddresses();

	public void saveAddress(Address theAddress);

	public Address getAddress(Integer theId);

	public void deleteAddress(Integer theId);

}
