package com.shoppingcart.dataaccesslayer.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.shoppingcart.util.CustomerCategory;

@Entity
@Table(name = "CUSTOMER_MASTER")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customer_id;
	
	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CustomerCategory customerCategory;




	
	public Customer() {
		super();
	}


	public Customer(long customer_id, String name, CustomerCategory customerCategory) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.customerCategory = customerCategory;
	}


	public long getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CustomerCategory getCustomerCategory() {
		return customerCategory;
	}


	public void setCustomerCategory(CustomerCategory customerCategory) {
		this.customerCategory = customerCategory;
	}


	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	

	
}
