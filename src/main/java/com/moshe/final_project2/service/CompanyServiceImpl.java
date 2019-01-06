package com.moshe.final_project2.service;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.management.Query;

import org.apache.catalina.mapper.Mapper;
import org.apache.commons.logging.Log;
import org.hibernate.annotations.Synchronize;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.SetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CouponRepository couponRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	RestTemplate restTemplate;

	
	
//	@Scheduled(fixedDelay=5000)
	
	
	LocalDateTime todayDate = LocalDateTime.now();
	Income newIncome = new Income();
	IncomeType newDescription;
	
	
	@Override
	public long login(String userName, String password, ClientType clientType) throws CustomException {
		Company companyLogin = companyRepository.findByCompName(userName);
		if (companyRepository.existsBycompName(userName) ){
			System.out.println("login succsesful, welcome " + companyLogin.getId());
			return  companyLogin.getId();
		}
		else {
			throw new CustomException("your user name or password isn't correct");
		}
		
	}

	@Override
	public Coupon addNewCoupon(Coupon newCouponAdded, long companyId) throws CustomException {
		Company compId = companyRepository.findById(companyId);
		Coupon newCoupon = couponRepository.findByTitle(newCouponAdded.getTitle());
		if (couponRepository.existsByTitle(newCouponAdded.getTitle())) {
			throw new CustomException("this Coupon is allradey exsisting");
		}
		Income income = new Income(compId.getCompName(), 
				todayDate, 100,newDescription.COMPANY_NEW_COUPON);
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Income> entity = new HttpEntity<Income>(income,headers);

	      restTemplate.exchange(
	         "http://localhost:8888/rest/api/income/storeIncome", HttpMethod.POST, entity, String.class).getBody();

		Set<Coupon> companyCoupon = compId.getCoupons();
		companyCoupon.add(newCouponAdded);
		return couponRepository.save(newCouponAdded);

	}

	@Override
	public Coupon deleteCoupon(long compnyId,Coupon deleteCoupon) throws CustomException {
		Company compId = companyRepository.findById(compnyId);
		Coupon removeCoupon = findCouponById(deleteCoupon.getId());
		Collection<Customer> allCustomers = customerRepository.findAll();
		for (Customer customer : allCustomers) {
		
		if (removeCoupon == null) {
			throw new CustomException("There is no Coupon");
		}
		Collection<Coupon> companyCouponRemove = compId.getCoupons();
		Collection<Coupon> customerCoupons = customer.getCoupons();
		customerCoupons.removeAll(customerCoupons);
		companyCouponRemove.removeAll(companyCouponRemove);
		couponRepository.delete(deleteCoupon);
		}
		return removeCoupon;
	}

	@Override
	public Coupon upDateCoupon(Coupon couponToUpDate, long compId) {
		Company comp = companyRepository.findById(compId);
		Coupon couponFromDb = couponRepository.findById(couponToUpDate.getId()).get();
		
		Income income = new Income(comp.getCompName(), 
				todayDate, 10, newDescription.COMPANY_UPDATE_COUPON);
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Income> entity = new HttpEntity<Income>(income,headers);

	      restTemplate.exchange(
	         "http://localhost:8888/rest/api/income/storeIncome", HttpMethod.POST, entity, String.class).getBody();
		
		couponFromDb.setEndDate(couponToUpDate.getEndDate());
		couponFromDb.setPrice(couponToUpDate.getPrice());
		couponToUpDate = couponRepository.save(couponFromDb);
		
		return couponToUpDate;
	}
	
	@Override
	public Company showCompanyDetails(long comp) {
		Company compny = companyRepository.findById(comp);
		compny.setPassword("******");
		return compny;
	}
	
	@Override
	public Company showCompanyDetailsHomePage(Long comp) {
		Company compny = companyRepository.findById(comp).get();
		compny.setPassword("******");
		return compny;
	}

	@Override
	public Coupon findCouponById(long findCouponById) {
		Coupon couponId = couponRepository.findById(findCouponById);
		return couponId;
	}
	
	@Transactional
	@Override
	public Collection<Coupon> companyCoupons(long companId) {
		Company companyId = companyRepository.findById(companId);
//		Income companyIncome = new Income();
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(companyId.getCompName());
	    
	   restTemplate.exchange("http://localhost:8888/rest/api/income/viewIncomeByCompanyName/"+ entity, 
	    		HttpMethod.GET, entity, String.class).getBody();
		   System.out.println();
		   

		Collection<Coupon> companyCoupons = companyId.getCoupons();
		return companyCoupons;
	}

	@Override
	public Collection<Coupon> findAllCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		return coupons;
	}

	@Override
	public Collection<Coupon> findCouponByType(CouponType findCouponByType) {
		Collection<Coupon> couponType = couponRepository.findByType(findCouponByType);
		return couponType;
	}
	
	@Override
	public Collection<Coupon> findCouponByPriceLimit(long companyId, double price) {
		Company companId = companyRepository.findById(companyId);
		Collection<Coupon> couponLimitPrice = companId.getCoupons();
		return couponLimitPrice.stream().filter(coupon -> coupon.getPrice() <= price)
                .collect(Collectors.toCollection(LinkedList::new));
	}


	
	@Override
	public Collection<Coupon> findCouponByEndDate(long companyId, String endDate) {
		Company companId = companyRepository.findById(companyId);
		LocalDate endDateFormat =  LocalDate.parse(endDate);
		Collection<Coupon> companyCoupons = couponRepository.findByendDate(endDateFormat);
		return companyCoupons;
	}
	
//	@Override
//	public CompanyServiceImpl login(String userName, String password, ClientType clientType) {
//		Company loginUserName = companyRepository.findByCompName(userName);
//		if (loginUserName.equals(userName) && loginUserName.getPassword().equals(password) && clientType.equals(ClientType.COMPANY))
//			return this;
//		
//		else return null;
//	}
	
//	@Async
//	public String findIncomeByCompany(String companyName) {
//		Company company = companyRepository.findByCompName(companyName);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//
//		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8888/rest/api/income/viewIncomeByCompanyName/" + company);
//		        
//
//		HttpEntity<?> entity = new HttpEntity<>(headers);
//
//		HttpEntity<String> response = restTemplate.exchange(
//		        builder.toUriString(), 
//		        HttpMethod.GET, 
//		        entity, 
//		        String.class);
//		return response.toString();
//	}





}
