package com.erez.thymeleaf.vehiclemodelsshope.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="order")
public class Order {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="orderNumber")
	Integer orderNumber;
	
	@Column(name="orderDate")
	Date orderDate;
	
	@Column(name="requiredDate")
	Date requiredDate;
	
	@Column(name="status")
	String status;
	
	@Column(name="comments")
	String comments;
	
	@ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name ="id" )
	Customer customer;

}
