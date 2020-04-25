package net.javaci.customerCrud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {

	@JsonIgnore
	private Long id;
	
	@ApiModelProperty(position = 1, required = true,  value = "Customer name")
	private String name;
	
	@ApiModelProperty(position = 2, required = true,  value = "Customer middle name")
	private String middleName;
	
	@ApiModelProperty(position = 3, required = true,  value = "Customer last name")
	private String lastName;
	
	@ApiModelProperty(position = 4, required = true,  value = "Customer address")
	private String address;
	
	@ApiModelProperty(position = 5, required = true,  value = "Customer birth date")
	private LocalDate birthDate;
	
}
