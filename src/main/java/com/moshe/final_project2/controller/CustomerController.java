package com.moshe.final_project2.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.service.CustomerService;
import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	RestTemplate restTemplate;
	
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
		return customerservice.findAllPurchasedCouponsByType(custmerId,copnType);
	}
	
	@GetMapping("/allCustomerCouponsByPrice/{cstmrid}/{macprc}")
	public Collection<Coupon> findAllPurchasedCouponsByPrice (@PathVariable("cstmrid") long custmrId, @PathVariable("macprc") double maxPrce){
		return customerservice.findAllPurchasedCouponsByPrice(custmrId,maxPrce);
	}
	
	@GetMapping("/customerDetailes/{id}")
	public Customer showCustomerDetails(@PathVariable("id") Long cstmer) {
		return customerservice.showCustomerDetails(cstmer);
	}

	
}
