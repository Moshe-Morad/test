package com.moshe.final_project2.service;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;

import ExceptionController.CustomException;

public interface CompanyService {
	
	Coupon addNewCoupon(Coupon newCoupon, long id) throws CustomException;
	Coupon deleteCoupon(Coupon deleteCoupon) throws CustomException;
	Coupon upDateCoupon(Coupon upDateCoupon);
	Coupon findCouponById(long findCouponById);
	Collection<Coupon> findCouponByType(CouponType findCouponByType);
	Collection<Coupon> findAllCoupons();
	
	
		

}
