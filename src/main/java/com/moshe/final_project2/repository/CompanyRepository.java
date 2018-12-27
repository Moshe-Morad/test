package com.moshe.final_project2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.entity.Customer;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findById(long companyId);
	Optional<Company> findByCompName(String companyName);
	
	


}
