package com.moshe.final_project2.controller;



import java.util.Collection;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.client.ResourceAccessException;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Customer;
import com.moshe.final_project2.repository.CompanyRepository;
import com.moshe.final_project2.service.AdminService;
import com.moshe.final_project2.service.AdminServiceImpl;

import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/companies")
	public Collection<Company> findAllCompanies(){
		return adminService.findAllCompanies();
	}

	@PostMapping("/newCompany")
	public Company addNewCompany(@Valid @RequestBody Company addNewCompany) throws CustomException {
		return adminService.addNewCompany(addNewCompany);
	}

	@GetMapping("/company/{id}")
	public Company findCompanyById(@PathVariable("id") long companyId) throws CustomException {
		return adminService.findCompanyById(companyId);
	}

	@DeleteMapping("/deletecompany/{id}")
	public ResponseEntity<Company> deleteCompany(@Valid @PathVariable("id") Long companyId) throws CustomException {
		adminService.deleteCompany(companyId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/updateCompany")
	public Company companyToUpDate(@RequestBody Company companyDetails) throws CustomException {
		return adminService.updateCompanyDetailes(companyDetails);
	}
	
	@PostMapping("/newCustomer")
	public Customer addNewCustomer(@Valid @RequestBody Customer addNewCustomer) throws CustomException {
		return adminService.addNewCustomer(addNewCustomer);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<Customer> deleteCustomer(@Valid @PathVariable("id") Long customerId) throws CustomException {
		adminService.deleteCustomer(customerId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/customer/{id}")
	public Customer findCustomerById(@PathVariable("id") long customerID) throws CustomException {
		return adminService.findCustomerById(customerID);
	}
	
	@GetMapping("/customers")
	public Collection<Customer> findAllCustomers(){
		return adminService.findAllCustomers();
	}
	
	@PutMapping("/updateCustomer")
	public Customer customerToUpDate(@RequestBody Customer customerDetails) throws CustomException {
		return adminService.updateCustomerDetailes(customerDetails);
	}


}
