package com.shoppingcart.rest.controller.product.beans;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.shoppingcart.util.CustomerCategory;
public class CustomerInfo {


	@NotNull
	private String name;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CustomerCategory customerCategory;

	@NotNull
	@DecimalMin(value = "0.1")
	private double purchaseAmount;

	
	
	public CustomerInfo() {
		super();
	}

	public CustomerInfo(String name, CustomerCategory customerCategory, double purchaseAmount) {
		super();
		this.name = name;
		this.customerCategory = customerCategory;
		this.purchaseAmount = purchaseAmount;
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

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	
	

}
