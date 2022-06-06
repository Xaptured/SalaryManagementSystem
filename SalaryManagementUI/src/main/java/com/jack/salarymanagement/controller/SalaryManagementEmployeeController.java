package com.jack.salarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;

@Controller
public class SalaryManagementEmployeeController {

	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "signup";
	}
	
	@PostMapping("/dosignup")
	public String doSignUp(@ModelAttribute EmployeeLogin eLogin)
	{
		System.out.println(eLogin);
		return "redirect:/employeehome";
	}
	
	@GetMapping("/login")
	public String showLoginPage()
	{
		return "login";
	}
	
	@PostMapping("/dologin")
	public String doLogin(@ModelAttribute EmployeeLogin eLogin)
	{
		System.out.println(eLogin);
		return "redirect:/employeehome";
	}
	
	@GetMapping("/details")
	public String showDetailsPage()
	{
		return "details";
	}
	
	@PostMapping("/dodetailssubmit")
	public String doDetailsSubmit(@ModelAttribute EmployeeDetails details)
	{
		System.out.println(details);
		return "redirect:/employeehome";
	}
	
	@GetMapping("/doapplyleave")
	public String doApplyLeave()
	{
		System.out.println("Do redirect");
		return "redirect:/employeehome";
	}
	
	@GetMapping("/employeehome")
	public String showEmployeeHome()
	{
		return "employeehome";
	}
}
