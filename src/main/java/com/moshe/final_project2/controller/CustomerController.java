package com.moshe.final_project2.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.service.CustomerService;

import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@PatchMapping("/customerCoupon/{cpnid}/{cstrid}")
	@ResponseBody
	public Coupon customerPurchaseCoupon(@PathVariable("cpnid") long cpnid, @PathVariable("cstrid") long customid) throws CustomException{
		return customerservice.purchaseCoupon(cpnid, customid);
	}
	
	@GetMapping("/allCoustomerCoupon/{cstmerId}")
	public Collection<Coupon> allCustomerPurchaseCoupons(@PathVariable("cstmerId") long customerId) {
		return customerservice.findAllPurchasedCoupons(customerId);
	}
	
	@GetMapping("/allCustomerCouponByType/{cstmrid}/{cpnType}")
	public Collection<Coupon> findAllPurchasedCouponsByType(@PathVariable("cstmrid") long custmerId, @PathVariable("cpnType") CouponType copnType){
		return customerservice.findAllPurchasedCouponsByType(copnType,custmerId);
	}

	
}
