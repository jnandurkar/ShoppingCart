package com.shoppingcart.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shoppingcart.dataaccesslayer.entity.Customer;
import com.shoppingcart.rest.controller.product.beans.CustomerInfo;
import com.shoppingcart.service.CustomerDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@Api(value = "shoppingcart",description="Manage cutomers")
public class CustomerController {

	final Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@ApiOperation(value = "Create a new Customer Details", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> created"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public ResponseEntity<Customer> createProduct(
			@ApiParam(value = "Data for the new customer", required = true) @Valid @RequestBody CustomerInfo customerInfo) {
		logger.info("Input recieved to create customer = " + customerInfo);
		Customer customer = customerDetailsService.createProduct(customerInfo);
		logger.info("Created customer with id = " + customer.getCustomer_id());

		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getCustomer_id())
				.toUri();
		logger.info("Setting header url with newly created customer= " + customer.getCustomer_id());
		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(customer, responseHeaders, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete existing Customer", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> deleted"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		customerDetailsService.deleteCustomer(id);
		return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
	}

	@ApiOperation(value = "View list of available customers", response = Iterable.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "Bad Credentials") })
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Customer>> getAllCustomers() {
		return new ResponseEntity<>(customerDetailsService.getAllCustomer(), HttpStatus.OK);
	}

	@ApiOperation(value = "View a specific customer", response = Customer.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved customer details"),
			@ApiResponse(code = 401, message = "Bad Credentials"),
			@ApiResponse(code = 404, message = "Customer does not exist") })
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(
			@ApiParam(value = "id of a particular customer", required = true) @PathVariable Long id) {
		return new ResponseEntity<>(customerDetailsService.getCustomerById(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Update existing Customer", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "id <id> updated"),
			@ApiResponse(code = 401, message = "Bad Credentials") })

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateProduct(@Valid @RequestBody CustomerInfo customerInfo, @PathVariable Long id) {
		Customer prod = customerDetailsService.updateCustomer(customerInfo, id);
		logger.info("updated product id = " + prod.getCustomer_id());
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}

}
