package com.moshe.final_project2.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.CouponType;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.entity.Income;
import com.moshe.final_project2.entity.IncomeType;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.repository.CouponRepository;
import com.moshe.final_project2.repository.CustomerRepository;

import ExceptionController.CustomException;
@Synchronize(value = { "sync" })
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	RestTemplate restTemplate;

	
	LocalDateTime todayDate = LocalDateTime.now();
	Income newIncome = new Income();
	IncomeType newDescription;
	
	@Override
	public CustomerServiceImpl login(String userName, String password, ClientType clientType) {
		Customer loginUserName = customerRepository.findByCustomerName(userName);
		if (loginUserName.getCustomerName().equals(userName) && loginUserName.getCustomerPassword().equals(password) && clientType.equals(ClientType.CUSTOMER))
			return this;
		
		else return null;
	}
	
	
	@Override
	public Coupon purchaseCoupon(long couponId, long customerId) throws CustomException {
//		Coupon purchaseCouponId = couponRepository.findById(couponId);
//		Customer customerPurchaseId = customerRepository.findById(customerId);
//		Set<Coupon> coupons = customerPurchaseId.getCoupons();
//		coupons.add(purchaseCouponId);
//		couponRepository.save(purchaseCouponId);
//		return purchaseCouponId;

		Coupon purchaseCouponId = couponRepository.findById(couponId);
		Customer customerPurchaseId = customerRepository.findById(customerId);
		
			if(purchaseCouponId != null) {
				if(purchaseCouponId.getAmount() == 0) {
					throw new CustomException("This Coupon Is Out Of Stock");
				}
				if (CouponExpired(purchaseCouponId.getEndDate())) {
					throw new CustomException("This Coupon Has Expired");
				}
				if (customerPurchaseId.getCoupons().contains(purchaseCouponId)) {
					throw new CustomException("You already bought this coupon, You can't buy the same coupon twice  ");
				}
			}
			
			Income income = new Income(customerPurchaseId.getCustomerName(), 
					todayDate, purchaseCouponId.getPrice(), newDescription.CUSTOMER_PURCHASE);
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity<Income> entity = new HttpEntity<Income>(income,headers);

		      restTemplate.exchange(
		         "http://localhost:8888/rest/api/income/storeIncome", HttpMethod.POST, entity, String.class).getBody();
				
				Set<Coupon> coupons = customerPurchaseId.getCoupons();
				coupons.add(purchaseCouponId);
				purchaseCouponId.setAmount(purchaseCouponId.getAmount()-1);
				couponRepository.save(purchaseCouponId);
				customerPurchaseId.setCoupons(coupons);
				customerRepository.save(customerPurchaseId);
				couponRepository.save(purchaseCouponId);
				
				
				return purchaseCouponId;
			
			

	}
	
	@Override
	public Collection<Coupon> findAllPurchasedCoupons(long customerId) {
//		Customer purchaseCustomerId = customerRepository.findById(customerId);
// 		return purchaseCustomerId.getCoupons();
 		List<Coupon> purchaseCoupons = couponRepository.findAll();
 		return purchaseCoupons;
		
		
	}
	
	@Override
	public Collection<Coupon> findAllPurchasedCouponsByType(long custId, CouponType type) {
		Collection<Coupon> listOfPurchasedCouponsByType = customerRepository.findAllPurchasedCouponsByType(custId, type);
		return listOfPurchasedCouponsByType;
	}
	
	@Override
	public Collection<Coupon> findAllPurchasedCouponsByPrice(long custId, double maximumPrice) {
		Collection<Coupon> listOfPurchasedCouponsByPrice = customerRepository.findAllPurchasedCouponsByPrice(custId, maximumPrice);
		return listOfPurchasedCouponsByPrice;
	}
	
	

	private boolean CouponExpired(LocalDate localDate) {
		Date now = Calendar.getInstance().getTime();
		return (convertToDateViaInstant(localDate)).before(now);
	}	
	
	public Date convertToDateViaInstant(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}
	
	@Override
	public Customer showCustomerDetails(Long cust) {
		Customer cstmer = customerRepository.findById(cust).get();
		cstmer.setCustomerPassword("********");
		return cstmer;
	}

	
	
	
	
	
	
}
