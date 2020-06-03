package com.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.dataaccesslayer.entity.Bill;
import com.shoppingcart.dataaccesslayer.entity.Customer;
import com.shoppingcart.util.CustomerCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillServiceTest {

	@Autowired
	private BillService billService;
	
	
	@Test
	public void testCreateBill() {
		
		//create customer
		Customer cust = new Customer();
		cust.setName("Bob");
		cust.setCustomerCategory(CustomerCategory.Regular);
		//create a new bill
		Bill o1 = billService.createBill(new Bill(0,15000,0,0,cust));
		Bill o2 = billService.getBillById(o1.getId());
		
		assertThat(o1.getId()).isEqualTo(o2.getId());
	}

	
	@Test
	public void testVerifyRegularCustomerBill() {
		
		//create customer
		Customer cust_John = new Customer(0,"John",CustomerCategory.Regular);
		Customer cust_Alex = new Customer(0,"Alex",CustomerCategory.Regular);
		Customer cust_Nick = new Customer(0,"Nick",CustomerCategory.Regular);
		
		//create a new bill
		Bill input5000 = billService.createBill(new Bill(0,5000,0,0,cust_John));
		Bill input10000 = billService.createBill(new Bill(0,10000,0,0,cust_Alex));
		Bill input15000 = billService.createBill(new Bill(0,15000,0,0,cust_Nick));
		
		assertThat(input5000.getBillAmount()).isEqualTo(5000);
		assertThat(input10000.getBillAmount()).isEqualTo(9500);
		assertThat(input15000.getBillAmount()).isEqualTo(13500);
	}
	
	
	@Test
	public void testVerifyPrimiumCustomerBill() {
		
		//create customer
		Customer cust_Sam = new Customer(0,"Sam",CustomerCategory.Premium);
		Customer cust_Mark = new Customer(0,"Mark",CustomerCategory.Premium);
		Customer cust_Brian = new Customer(0,"Brian",CustomerCategory.Premium);
		Customer cust_Raj = new Customer(0,"Raj",CustomerCategory.Premium);
		
		//create a new bill
		Bill input5000 = billService.createBill(new Bill(0,4000,0,0,cust_Sam));
		Bill input10000 = billService.createBill(new Bill(0,8000,0,0,cust_Mark));
		Bill input15000 = billService.createBill(new Bill(0,12000,0,0,cust_Brian));
		Bill input20000 = billService.createBill(new Bill(0,20000,0,0,cust_Raj));
		
		assertThat(input5000.getBillAmount()).isEqualTo(3600);
		assertThat(input10000.getBillAmount()).isEqualTo(7000);
		assertThat(input15000.getBillAmount()).isEqualTo(10200);
		assertThat(input20000.getBillAmount()).isEqualTo(15800);
	}


	

}
