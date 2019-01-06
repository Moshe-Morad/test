package com.moshe.final_project2.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Income;

import ExceptionController.CustomException;

public interface CompanyService {
	
	
	Coupon addNewCoupon(Coupon newCoupon, long id) throws CustomException;
	Coupon deleteCoupon( long compnyId, Coupon deleteCoupon)  throws CustomException;
	Coupon upDateCoupon(Coupon upDateCoupon, long companyId);
	Coupon findCouponById(long findCouponById);
	Company showCompanyDetails(long comp) throws CustomException;
	Company showCompanyDetailsHomePage(Long comp);
	Collection<Coupon> companyCoupons(long companId);
	Collection<Coupon> findCouponByType(CouponType findCouponByType);
	Collection<Coupon> findCouponByPriceLimit(long companyId, double price);
	Collection<Coupon> findCouponByEndDate(long companyId, String endDate) throws ParseException;
	Collection<Coupon> findAllCoupons();
	long login(String userName, String password, ClientType clientType) throws CustomException;
//	String findIncomeByCompany(String companyName);
	

	
	
		

}
