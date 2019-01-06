package com.moshe.final_project2.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Income;
import com.moshe.final_project2.service.CompanyService;
import com.moshe.final_project2.service.CompanyServiceImpl;
import com.moshe.final_project2.service.CouponClientService;

import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api/company.html")
public class CompanyController {
	
	@Autowired
	CompanyService companyservice;
	
	
//	@PostMapping("/Login")
//	public Company companyLogin(@RequestBody Company loginCompany)throws CustomException {
//		return companyservice.login(loginCompany);
//	}
	
	@PostMapping("/newCoupon/{compId}")
	public Coupon addNewCoupon(@Valid @RequestBody Coupon newCoupon, @PathVariable("compId") long id) throws CustomException {
		return companyservice.addNewCoupon(newCoupon,id);
	}
	
	@GetMapping("/coupon/{id}")
	public Coupon findCouponById(@PathVariable("id") long couponId) throws CustomException {
		return companyservice.findCouponById(couponId);
	}
	
	@DeleteMapping("/deletecoupon/{compId}/{id}")
	public ResponseEntity<Coupon> deleteCoupon(@Valid @PathVariable("compId") long compyId, @PathVariable("id") Coupon couponId) throws CustomException {
		companyservice.deleteCoupon(compyId,couponId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updateCoupon/{id}")
	public Coupon couponToUpDate(@RequestBody Coupon couponDetails, @PathVariable("id") long compId) throws CustomException {
		return companyservice.upDateCoupon(couponDetails,compId);
	}
	
	@GetMapping("/comanyDetailes/{id}")
	public Company showCompanyDetails(@PathVariable("id") long compny) throws CustomException {
		return companyservice.showCompanyDetails(compny);
	}
	
	@GetMapping("/comanyDetailesHomePage/{id}")
	public Company showCompanyDetailsHomePage(@PathVariable("id") Long compny) throws CustomException {
		return companyservice.showCompanyDetailsHomePage(compny);
	}
	
	@GetMapping("/companyCoupons/{id}")
	public Collection<Coupon> companyCoupons(@PathVariable("id") long company) {
		return companyservice.companyCoupons(company);
	}
	
	@GetMapping("/coupons")
	public Collection<Coupon> getAllCoupons(){
		return companyservice.findAllCoupons();
	}
	
	@GetMapping("/typeCoupons/{couponType}")
	public Collection<Coupon> findCouponByType(@PathVariable("couponType")CouponType typeOfCoupons){
		return companyservice.findCouponByType(typeOfCoupons);
	}
	@GetMapping("/typeCoupons")
	public void findCouponByType(){
	}
	
	@GetMapping("/couponPrice/{companyId}/{limitPrice}")
	public Collection<Coupon> findCouponByPriceLimit(@PathVariable("companyId") long companyid,
			@PathVariable("limitPrice") long limitPrice){
		return companyservice.findCouponByPriceLimit(companyid, limitPrice);
	}
	@GetMapping("/couponEndDate/{companyId}/{endDate}")
	public Collection<Coupon> findCouponByEndDate(@PathVariable("companyId")long companyId,@PathVariable("endDate") String endDate) throws ParseException{
		return companyservice.findCouponByEndDate(companyId, endDate);
	}
	
	
//	@GetMapping("/companyincome/{compName}")
//	public String findIncomeByCompany(@PathVariable("compName") String companyName){
//		return companyservice.findIncomeByCompany(companyName);
//	}

	
	
}
