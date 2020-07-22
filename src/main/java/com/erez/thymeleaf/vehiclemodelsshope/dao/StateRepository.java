package com.erez.thymeleaf.vehiclemodelsshope.dao;

import com.erez.thymeleaf.vehiclemodelsshope.entity.State;
import com.erez.thymeleaf.vehiclemodelsshope.entity.StateDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
	
	@Query("select new com.erez.thymeleaf.vehiclemodelsshope.entity.StateDTO(id, name) from State where country.id = :id")
	public List<StateDTO> findByCountryId(@Param("id") Integer id);

}
