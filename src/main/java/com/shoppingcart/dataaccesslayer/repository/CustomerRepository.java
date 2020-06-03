package com.shoppingcart.dataaccesslayer.repository;



import org.springframework.data.repository.CrudRepository;

import com.shoppingcart.dataaccesslayer.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {



}
