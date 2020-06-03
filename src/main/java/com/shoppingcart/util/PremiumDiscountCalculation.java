package com.shoppingcart.util;

import org.springframework.stereotype.Component;

@Component
public class PremiumDiscountCalculation implements DiscountCalculationStrategy{

	@Override
	public double calculateDiscount(double purchaseAmount, double discount) {
		// TODO Auto-generated method stub
		double total_discount= discount;
		
		if(purchaseAmount<= 4000){
					total_discount = total_discount + (purchaseAmount * 0.10);
				
				return total_discount;
		}else if(purchaseAmount> 4000 && purchaseAmount <= 8000){
			if(purchaseAmount == 8000){
			total_discount = total_discount + (4000 * 0.15);
			}else{
				total_discount = total_discount + ((purchaseAmount - 4000) * 0.15);
			}
			return total_discount = calculateDiscount(4000, total_discount);
		}else if(purchaseAmount> 8000 && purchaseAmount <= 12000){
			if(purchaseAmount == 12000){
			total_discount = total_discount + (4000 * 0.20);
			}else{
				total_discount = total_discount + ((purchaseAmount - 8000) * 0.20);
			}
			return total_discount = calculateDiscount(8000, total_discount);
		}else if(purchaseAmount> 12000){
			total_discount = total_discount + ((purchaseAmount - 12000) * 0.30);
			return total_discount = calculateDiscount(12000, total_discount);
		}
		return 0;
	}
	

}
