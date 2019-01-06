package com.moshe.final_project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.moshe.final_project2.entity.ClientType;
import com.moshe.final_project2.entity.Company;
import com.moshe.final_project2.repository.CompanyRepository;

import ExceptionController.CustomException;

@Service
public class LoginServiceImpl {
	
	@Autowired
	AdminServiceImpl adminService;
	@Autowired
	CompanyServiceImpl companyService;
	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	CustomerServiceImpl customerService;
	
	public long login(String userName, String password, ClientType clientType) throws CustomException {
		if(clientType.equals(ClientType.ADMIN)) {
			adminService.login(userName, password, clientType);
			System.out.println("admin loged in");
			
		}
		else if(clientType.equals(ClientType.COMPANY)) {
			Company companyLogin = companyRepository.findByCompName(userName);
			companyService.login(userName, password, clientType);
			System.out.println(companyLogin.getId());
			return companyLogin.getId();
		}
		else if(clientType.equals(ClientType.CUSTOMER)) {
			customerService.login(userName, password, clientType);
			System.out.println("customer loged in");
		}
		else {
			System.out.println("login didnt execute");
		}
		return 0;
	}





}
