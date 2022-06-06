package com.jack.salarymanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jack.salarymanagement.entities.EmployeeLogin;
import com.jack.salarymanagement.services.EmployeeLoginService;

@Controller
public class DataAccessController {

	@Autowired
	private EmployeeLoginService eLoginService;
	
	@GetMapping("/index")
	public String showHomePage()
	{
		return "index";
	}
	
	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "signup";
	}
	
	@PostMapping("/submitsignup")
	public String signupOnSubmit(@ModelAttribute EmployeeLogin eLogin)
	{
		eLogin.setEmployeeid(1);
		eLoginService.saveEmployeeSignupDetails(eLogin);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String showLoginPageEmployee()
	{
		return "loginemployee";
	}
}
