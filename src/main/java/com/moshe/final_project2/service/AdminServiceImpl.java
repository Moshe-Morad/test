package com.moshe.final_project2.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.entity.Income;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.repository.CouponRepository;
import com.moshe.final_project2.repository.CustomerRepository;

import ExceptionController.CustomException;



@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CustomerRepository customerRpository;
	@Autowired
	private CompanyRepository companyRpository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private RestTemplate restTemplate;

	
	
	@Override
	public Company addNewCompany(Company addNewCompany) throws CustomException {
		if(companyRpository.existsBycompName(addNewCompany.getCompName())) {
			throw new CustomException("There is already a company with this name, choose a different name");
		}
		return companyRpository.save(addNewCompany);
//		Optional<Company> newCompany = companyRpository.findByCompName(addNewCompany.getCompName());
//		if(newCompany.isPresent()) {
//			throw new CustomException("There is already a company with this name, choose a different name");
//		}else {
//		return companyRpository.save(addNewCompany);
//		}
	}
	@Override
	public Company deleteCompany(Company companyId) throws CustomException {
		Set<Coupon> companyCoupons = companyId.getCoupons();
		Collection<Customer> allCustomers = findAllCustomers();
		for (Coupon coupons : companyCoupons) {
			for (Customer customer : allCustomers) {
				Collection<Coupon> purchasedcustomerCoupons = customer.getCoupons();
				for (Coupon purchasedCoupon : purchasedcustomerCoupons) {
					purchasedcustomerCoupons.remove(purchasedCoupon);
				}
				customerRpository.save(customer);
			}
		}
		Company removeCompany = findCompanyById(companyId.getId());
		if(removeCompany == null) {	
			throw new CustomException("there is no company with this ID");
		}
		companyRpository.deleteById(companyId.getId());
		return removeCompany;

	}
	@Override
	public Company updateCompanyDetailes(Company companyToUpDate) throws CustomException {
		Company companyFromDb = companyRpository.findById(companyToUpDate.getId());
//			if(companyFromDb.getCompName() != companyToUpDate.getCompName()) {
//			throw new CustomException("can't change name");
//		}
		companyFromDb.setPassword(companyToUpDate.getPassword());
		companyFromDb.setEmail(companyToUpDate.getEmail());
		companyToUpDate = companyRpository.save(companyFromDb);
		
		return companyToUpDate;
	}
	

	@Override
	public Company findCompanyById(long id) {
		Company compnyId = companyRpository.findById(id);
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(compnyId.getCompName());
	    
	   restTemplate.exchange("http://localhost:8888/rest/api/income/viewIncomeByCompanyName/"+ entity, 
	    		HttpMethod.GET, entity, String.class).getBody();
		   System.out.println(entity.getClass());  
		return compnyId;
	}
	@Override
	public Collection<Company> findAllCompanies() {
		List<Company> companies = companyRpository.findAll();
		return companies;
	}
	@Override
	public Customer addNewCustomer(Customer addNewCustomer) throws CustomException {
		if(customerRpository.existsByCustomerName(addNewCustomer.getCustomerName())) {
//			Customer newCustomer = customerRpository.findByCustomerName(addNewCustomer.getCustomerName());
			throw new CustomException("this Customer is allradey register");
		}else {
			return customerRpository.save(addNewCustomer);
		}
		
	}
	@Override
	public Customer deleteCustomer(long CustomerId) throws CustomException {
		Customer removeCustomer = findCustomerById(CustomerId);
		if(removeCustomer == null) {
			throw new CustomException();
		}
		customerRpository.deleteById(CustomerId);
		return removeCustomer;
	}
	@Override
	public Customer updateCustomerDetailes(Customer customerToUpDate) {
		Customer customerFromDb = customerRpository.findById(customerToUpDate.getId());
		customerFromDb.setCustomerPassword(customerToUpDate.getCustomerPassword());
		customerFromDb.setCustomerEmail(customerToUpDate.getCustomerEmail());
		customerToUpDate = customerRpository.save(customerFromDb);
		return customerToUpDate;
	}
	@Override
	public Customer findCustomerById(long custoId) {
		Customer customerId = customerRpository.findById(custoId);
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(customerId.getCustomerName());
	    
	   restTemplate.exchange("http://localhost:8888/rest/api/income/viewIncomeByCompanyName/"+ entity, 
	    		HttpMethod.GET, entity, String.class).getBody();
		   System.out.println(entity.getClass());
		return customerId;
	}
	@Override
	public Collection<Customer> findAllCustomers()throws CustomException {
		List<Customer> customers = customerRpository.findAll();
		if(customers == null) 
			throw new CustomException();
		
		return customers;
	}
	
	@Override
	public AdminServiceImpl login(String userName, String password, ClientType clientType) {
		
		if ((userName.equals("admin")) && (password.equals("1234") && clientType.equals(ClientType.ADMIN)))
			return this;
		
			else return null;
		
		
		
//		if(userName == "Admin" &&
//		password == "123456789") {
//			return true;
//		}else {
//			return false;
//		}
	}
	@Override
	public String viewAllIncome() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
	   restTemplate.exchange("http://localhost:8888/rest/api/income/viewAllIncomes", 
	    		HttpMethod.GET, entity, String.class).getBody();
		   System.out.println(entity.getHeaders().toString());
		return entity.toString();
	}

	
	
	

	
}
