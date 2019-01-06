package com.moshe.final_project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moshe.final_project2.entity.ClientType;

import com.moshe.final_project2.service.LoginServiceImpl;

import ExceptionController.CustomException;

@RestController
@RequestMapping("/rest/api")
public class LoginController {
	
	@Autowired
	LoginServiceImpl loginService;
	
	@GetMapping("/login/{userName}/{password}/{clientType}")
	public long Login(@PathVariable("userName") String userName,@PathVariable("password") String password,
			@PathVariable("clientType") ClientType clientType)throws CustomException {
		long comp =  loginService.login(userName, password, clientType);
		return comp;
	}

	

}
