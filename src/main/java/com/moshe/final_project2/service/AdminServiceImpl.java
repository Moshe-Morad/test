package com.moshe.final_project2.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.repository.CustomerRepository;

import ExceptionController.CustomException;



@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private CustomerRepository customerRpository;
	@Autowired
	private CompanyRepository companyRpository;

	
	
	@Override
	public Company addNewCompany(Company addNewCompany) throws CustomException {
		Optional<Company> newCompany = companyRpository.findByCompName(addNewCompany.getCompName());
		if(newCompany.isPresent()) {
			throw new CustomException("there are company in this name");
		}else {
		return companyRpository.save(addNewCompany);
		}
	}
	@Override
	public Company deleteCompany(Long companyId) throws CustomException {
		Company removeCompany = findCompanyById(companyId);
		if(removeCompany == null) {
			throw new CustomException();
		}
		companyRpository.deleteById(companyId);
		return removeCompany;
	}
	@Override
	public Company updateCompanyDetailes(Company companyToUpDate) throws CustomException {
		Company companyFromDb = companyRpository.findById(companyToUpDate.getId()).get();
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
		Company CompanyId = companyRpository.findById(id);
		return CompanyId;
	}
	@Override
	public Collection<Company> findAllCompanies() {
		List<Company> companies = companyRpository.findAll();
		return companies;
	}
	@Override
	public Customer addNewCustomer(Customer addNewCustomer) throws CustomException {
		Optional<Customer> newCustomer = customerRpository.findByCustomerName(addNewCustomer.getCustomerName());
		if(newCustomer.isPresent()) {
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
		return customerId;
	}
	@Override
	public Collection<Customer> findAllCustomers() {
		List<Customer> customers = customerRpository.findAll();
		return customers;
	}
	@Override
	public boolean login(String userName, String password) {
		if(userName == "Admin" &&
		password == "123456789") {
			return true;
		}else {
			return false;
		}
	}
	
	
	

	
}
