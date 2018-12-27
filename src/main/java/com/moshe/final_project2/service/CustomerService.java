package com.moshe.final_project2.service;

import java.util.Collection;

import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;

import ExceptionController.CustomException;

public interface CustomerService {
	
	Coupon purchaseCoupon(long couponId, long customerId) throws CustomException;
	Collection<Coupon> findAllPurchasedCoupons(long customerId);
	Collection<Coupon> findAllPurchasedCouponsByType(CouponType cpnType, long customerID);
	Collection<Coupon> findAllPurchasedCouponsByPrice();
//	Coupon purchaseCoupon(long purchasedCoupon) throws CustomException;


	
}
