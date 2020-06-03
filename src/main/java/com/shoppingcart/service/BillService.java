package com.shoppingcart.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.dataaccesslayer.entity.Bill;
import com.shoppingcart.dataaccesslayer.repository.BillRepository;
import com.shoppingcart.rest.controller.CustomException;
import com.shoppingcart.util.CustomerCategory;
import com.shoppingcart.util.PremiumDiscountCalculation;
import com.shoppingcart.util.RegularDiscountCalculation;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepo;



	final Logger logger = LogManager.getLogger(getClass());

	
	@Autowired
	private RegularDiscountCalculation regularDiscountCalculation;
	@Autowired
	private PremiumDiscountCalculation premiumDiscountCalculation;





	private double computeBillValue(CustomerCategory customerCategory, double purchaseAmount) {
		logger.debug("customerCategory : " + customerCategory + "  purchaseAmount = " + purchaseAmount);
		double saleValue = 0;
		double total_discount= 0;
		if (customerCategory.equals(CustomerCategory.Regular)) {
			total_discount = regularDiscountCalculation.calculateDiscount(purchaseAmount, 0);
			logger.info("Discount got by customer = " + total_discount);
			saleValue = purchaseAmount - total_discount;

		} else if (customerCategory.equals(CustomerCategory.Premium)) {
			total_discount = premiumDiscountCalculation.calculateDiscount(purchaseAmount, 0);
			logger.info("Discount got by customer = " + total_discount);
			saleValue = purchaseAmount - total_discount;

		} 
		return saleValue;
	}
	
	

	public Bill createBill(Bill bill) {
		logger.info("Input recieved to create Bill for Customer = " + bill.getCustomer().getName() + ",Purchase amount= " + bill.getPurchaseAmount());
		Bill saleValue = bill ;
		double billAmount =  computeBillValue(bill.getCustomer().getCustomerCategory(), bill.getPurchaseAmount());
		saleValue.setBillAmount(billAmount);
		Bill finalbill = billRepo.save(saleValue);
		logger.info("Bill amount = " + finalbill.getBillAmount());
		return finalbill;

	}

	public void deleteBill(Long id) {
		verifyBillExists(id);
		billRepo.delete(id);
	}

	public Iterable<Bill> getAllBills() {
		Iterable<Bill> bill = billRepo.findAll();
		logger.info("returning all products");
		return bill;
	}

	public Bill getBillById(Long id) {
		verifyBillExists(id);
		return billRepo.findOne(id);
	}





	/*public Bill updateBill(BillUpdateInfo billUpdateInfo, Long billId) {

		logger.info("Request recieved for update of  : " + billId);
		if (null == billUpdateInfo) {
			throw new CustomException("There is no information to be updated for id " + billId);
		}
		verifyBillExists(billId);

		logger.info("Processing products to be added");
		if (null != billUpdateInfo.getProductsToBeAdded()) {
			List<ProductInfoForBill> prodToBeAdded = billUpdateInfo.getProductsToBeAdded();
			Iterator<ProductInfoForBill> prodToBeAddedIter = prodToBeAdded.iterator();
			while (prodToBeAddedIter.hasNext()) {
				ProductInfoForBill pInfo = prodToBeAddedIter.next();
				logger.debug("Product to be added : " + pInfo);
				addProductToBill(billId, pInfo.getBarCodeId(), pInfo.getQuantity());
			}
		}

		logger.info("Processing products to be removed");
		if (null != billUpdateInfo.getProductsToBeRemoved()) {
			List<ProductInfoForBill> prodToBeRemoved = billUpdateInfo.getProductsToBeRemoved();
			Iterator<ProductInfoForBill> prodToBeRemovedIter = prodToBeRemoved.iterator();
			while (prodToBeRemovedIter.hasNext()) {
				ProductInfoForBill pInfo = prodToBeRemovedIter.next();
				logger.info("Product to be removed : " + pInfo);
				removeProductFromBill(billId, pInfo.getBarCodeId());
			}
		}

		Bill bill = billRepo.findOne(billId);
		bill.setBillStatus(billUpdateInfo.getStatus());
		logger.info("Computing total for the bill");
		computeTotalValues(bill);
		return bill;
	}*/

	private void verifyBillExists(Long id) {
		Bill bill = billRepo.findOne(id);
		if (bill == null) {
			throw new CustomException("Bill with id " + id + " not found");
		}
		logger.info(" Bill exists with an id = " + id);
	}

	

}
