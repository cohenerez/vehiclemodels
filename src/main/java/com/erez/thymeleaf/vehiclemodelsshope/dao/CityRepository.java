package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.City;
import com.erez.thymeleaf.vehiclemodelsshope.entity.CityDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
	
	@Query("select new com.erez.thymeleaf.vehiclemodelsshope.entity.CityDTO(id, name) from City where state.id = :id")
	public List<CityDTO> findByStateId(@Param("id") int id);

}
