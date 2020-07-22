package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
