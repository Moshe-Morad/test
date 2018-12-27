package com.moshe.final_project2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Coupon;
import com.moshe.final_project2.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerName(String customerName);
	Customer findById(long customerId);

	
	

}
