package com.moshe.final_project2.Thread;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.repository.CouponRepository;
import com.moshe.final_project2.repository.CustomerRepository;
import com.moshe.final_project2.service.CompanyServiceImpl;
import com.moshe.final_project2.service.CustomerServiceImpl;

import ExceptionController.CustomException;

@Service
public class DailyCouponExpirationTask implements Runnable {
	private boolean quit = false;
	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CompanyRepository companyRepository;


	
	public DailyCouponExpirationTask() {
		quit = true;
	}
	
	@Scheduled(cron = "* * 5 * * *")
	@Override
	public void run() {
		List<Coupon> coupons = couponRepository.findAll();
		List<Company> companies = companyRepository.findAll();
		List<Customer> customers = customerRepository.findAll();
		for (Company company : companies) {
			for (Customer customer : customers) {
				for (Coupon coupon:coupons) {
					LocalDate nowDate =  LocalDate.now(); 
					if (coupon.getEndDate().isEqual(nowDate) || coupon.getEndDate().equals(nowDate)) {
							Collection<Coupon> companyCoupons = company.getCoupons();
							Collection<Coupon> customerCoupons = customer.getCoupons();
							Collection<Coupon> allCoupons = coupons;
							allCoupons.removeAll(allCoupons);
							companyCoupons.removeAll(companyCoupons);
							customerCoupons.removeAll(customerCoupons);
							couponRepository.delete(coupon);
					}
				}
				
			}
		}
		try {
			Thread.sleep((long) 8.64e+7);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	
//	@Scheduled(cron = "* * * * * *")
//	@Override
//	public void run() {
//
//			List<Coupon> coupons = couponRepository.findAll();
//			List<Company> companies = companyRepository.findAll();
//			for (Company company : companies) {
//			List<Customer> customers = customerRepository.findAll();
//			for (Customer customer : customers) {
//				for (Coupon coupon:coupons) {
//					LocalDate nowDate =  LocalDate.now(); 
//					if ( (coupon.getEndDate().isEqual(nowDate))) {
////						couponRepository.delete(coupon);
//							try {
//								companyServiceImpl.deleteCoupon(coupon, customer.getId(), company.getId());
//								
//							} catch (CustomException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							
//					}else {
//						
//					}
////					couponRepository.findById(coupon.getId() + 1);
//				}
//				}
//				}
//		
//	}
	

	public boolean isQuit() {
		quit = true;
		return quit;
	}


}
