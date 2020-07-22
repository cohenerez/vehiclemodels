package com.erez.thymeleaf.vehiclemodelsshope.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/*
employeeNumber int PK
lastName varchar(50)
firstName varchar(50)
extension varchar(10)
email varchar(100)
officeCode int
reportsTo int
jobTitle varchar(50)
 */

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "office_id")
public class SalesRepresentative implements Serializable {

	
	

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="employeeNumber")
	private Integer id;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="extension")
	private String extension;
	
	@Column(name="email")
	private String email;
	
	
		
	@Column(name="jobTitle")
	private String jobTitle;



	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "reportsTo")
	SalesRepresentative manager ;
	
	@ManyToOne( cascade= { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST , CascadeType.REFRESH} )
    @JoinColumn(name ="officeCode" )
	private Office office;

	
	@OneToMany(mappedBy="salesRepresentative",fetch = FetchType.LAZY
			,cascade= { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST , CascadeType.REFRESH})
	@JsonIgnore
	private List<Customer> customers ;
	

	public 	void addCustomer(Customer theCustomer) {
		if (customers== null) {
		
			List<Customer> customers = new ArrayList<Customer>();
		}
		customers.add(theCustomer);
		theCustomer.setSalesRepresentative(this);
	}




}





