package com.shoppingcart.util;

import org.springframework.stereotype.Component;

@Component
public class RegularDiscountCalculation implements DiscountCalculationStrategy{

	@Override
	public double calculateDiscount(double purchaseAmount, double discount) {
		// TODO Auto-generated method stub
		double total_discount= discount;
		
		if(purchaseAmount<= 5000){
			return total_discount;
		}else if(purchaseAmount> 5000 && purchaseAmount <= 10000){
			if(purchaseAmount == 10000){
			total_discount = total_discount + (5000 * 0.10);
			}else{
				total_discount = total_discount + ((purchaseAmount - 5000) * 0.10);
			}
			return total_discount;
		}else if(purchaseAmount> 10000){
			total_discount = total_discount + ((purchaseAmount - 10000) * 0.20);
			return total_discount = calculateDiscount(10000, total_discount);
		}
		return 0;
	}
	
	
}
