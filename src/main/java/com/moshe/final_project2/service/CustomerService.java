package com.moshe.final_project2.service;

import java.util.Collection;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Customer;

import ExceptionController.CustomException;

public interface CustomerService {
	
	Coupon purchaseCoupon(long couponId, long customerId) throws CustomException;
	Collection<Coupon> findAllPurchasedCoupons(long customerId);
	Collection<Coupon> findAllPurchasedCouponsByType(long customerID,CouponType cpnType);
	Collection<Coupon> findAllPurchasedCouponsByPrice(long custId, double maxPrice);
	CustomerServiceImpl login(String userName, String password, ClientType clientType);
	Customer showCustomerDetails(Long cust);




	
}
