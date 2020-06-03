package com.shoppingcart.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.dataaccesslayer.entity.Customer;
import com.shoppingcart.dataaccesslayer.repository.CustomerRepository;
import com.shoppingcart.rest.controller.product.beans.CustomerInfo;
@Service
public class CustomerDetailsService {

	final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private CustomerRepository custRepo;
	
	public Customer createProduct(CustomerInfo customerInfo) {
		logger.info("Input recieved to create product = " + customerInfo);
		Customer customer = new Customer();
		
		customer.setName(customerInfo.getName());
		customer.setCustomerCategory(customerInfo.getCustomerCategory());

		customer = custRepo.save(customer);
		logger.info("Customer details are added sucessfully");
		return customer;

	}
	
	public void deleteCustomer(Long id) {
		custRepo.delete(id);
	}
	
	public Iterable<Customer> getAllCustomer() {
		Iterable<Customer> customer = custRepo.findAll();
		logger.info("returning all customers");
		return customer;
	}

	public Customer getCustomerById(Long id) {
		return custRepo.findOne(id);
	}

	public Customer updateCustomer(CustomerInfo customerInfo, Long id) {
		
		Customer customer = custRepo.findOne(id);
		customer.setName(customerInfo.getName());
		customer.setCustomerCategory(customerInfo.getCustomerCategory());
		
		Customer updatedCustomer = custRepo.save(customer);
		logger.info("updated sucessfully ");
		return updatedCustomer;
	}
}
