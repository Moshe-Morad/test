package com.moshe.final_project2.service;

import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Customer;

import ExceptionController.CustomException;

public interface AdminService {
	
	Company addNewCompany(Company addNewCompany) throws CustomException;
	Company deleteCompany(Long company) throws CustomException;
	Company updateCompanyDetailes(Company updateCompany) throws CustomException;
	Company findCompanyById(long companyId);
	Collection<Company> findAllCompanies();
	Customer addNewCustomer(Customer addNewCustomer) throws CustomException;
	Customer deleteCustomer(long CustomerId) throws CustomException;
	Customer updateCustomerDetailes(Customer updateCustomer);
	Customer findCustomerById(long id);
	Collection<Customer> findAllCustomers();
	boolean login (String userName, String password);
	
}
