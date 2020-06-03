package com.shoppingcart.util;

public interface DiscountCalculationStrategy {

	double calculateDiscount(double purchaseAmount, double discount);
}
