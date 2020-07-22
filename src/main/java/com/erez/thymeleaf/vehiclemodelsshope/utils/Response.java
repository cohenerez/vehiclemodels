package com.erez.thymeleaf.vehiclemodelsshope.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {

	@SerializedName("customerId")
	@Expose
	private Integer customerId;

	@SerializedName("customerName")
	@Expose
	private String customerName;

	@SerializedName("status")
	@Expose
	private String status;


}
