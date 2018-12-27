package com.moshe.final_project2.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.service.CompanyService;

import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api/company")
public class CompanyController {
	
	@Autowired
	CompanyService companyservice;
	
	
	@PostMapping("/newCoupon/{compId}")
	public Coupon addNewCoupon(@Valid @RequestBody Coupon NewCoupon, @PathVariable("compId") long id) throws CustomException {
		return companyservice.addNewCoupon(NewCoupon,id);
	}
	
	@GetMapping("/coupon/{id}")
	public Coupon findCouponById(@PathVariable("id") long couponId) throws CustomException {
		return companyservice.findCouponById(couponId);
	}
	
	@DeleteMapping("/deletecoupon/{id}")
	public ResponseEntity<Coupon> deleteCoupon(@Valid @PathVariable("id") Coupon couponId) throws CustomException {
		companyservice.deleteCoupon(couponId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updateCoupon")
	public Coupon couponToUpDate(@RequestBody Coupon couponDetails) throws CustomException {
		return companyservice.upDateCoupon(couponDetails);
	}
	
	@GetMapping("/coupons")
	public Collection<Coupon> getAllCoupons(){
		return companyservice.findAllCoupons();
	}
	
	@GetMapping("/typeCoupons/{couponType}")
	public Collection<Coupon> findCouponByTyoe(@PathVariable("couponType")CouponType typeOfCoupons){
		return companyservice.findCouponByType(typeOfCoupons);
	}

}
