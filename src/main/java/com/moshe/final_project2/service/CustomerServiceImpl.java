package com.moshe.final_project2.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.repository.CouponRepository;
import com.moshe.final_project2.repository.CustomerRepository;

import ExceptionController.CustomException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Coupon purchaseCoupon(long couponId, long customerId) throws CustomException {
		Coupon purchaseCouponId = couponRepository.findById(couponId);
		Customer customerPurchaseId = customerRepository.findById(customerId);
		Set<Coupon> coupons = customerPurchaseId.getCoupons();
		coupons.add(purchaseCouponId);
		couponRepository.save(purchaseCouponId);
		return purchaseCouponId;
	}
		
	
	@Override
	public Collection<Coupon> findAllPurchasedCoupons(long customerId) {
		Customer purchaseCustomerId = customerRepository.findById(customerId);
 		return purchaseCustomerId.getCoupons();
//		List<Coupon> purchaseCoupons = couponRepository.findAll();
 		
//		return purchaseCoupons;
		
		
	}
	@Override
	public Collection<Coupon> findAllPurchasedCouponsByType(CouponType couponType, long customerID) {
		Customer purchaseCustomerType = customerRepository.findById(customerID);
		List<Coupon> purchaseCouponType = (List<Coupon>) couponRepository.findByType(couponType);
		return purchaseCustomerType.getCoupons();
	}
	@Override
	public Collection<Coupon> findAllPurchasedCouponsByPrice() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
	
}
