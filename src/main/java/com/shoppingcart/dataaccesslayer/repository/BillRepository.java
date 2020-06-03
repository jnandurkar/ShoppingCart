package com.shoppingcart.dataaccesslayer.repository;

import org.springframework.data.repository.CrudRepository;

import com.shoppingcart.dataaccesslayer.entity.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {

}
