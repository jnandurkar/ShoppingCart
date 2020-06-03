package com.shoppingcart.dataaccesslayer.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.shoppingcart.util.BillStatus;

@Entity
@Table(name = "BILLS")

public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private double purchaseAmount;

	private double billAmount;
	
	private int discount;

	

	@OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	private Customer customer;

	public Bill() {
		super();
	}

	public Bill(long id, double purchaseAmount, double billAmount, int discount, Customer customer) {
		super();
		this.id = id;
		this.purchaseAmount = purchaseAmount;
		this.billAmount = billAmount;
		this.discount = discount;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
