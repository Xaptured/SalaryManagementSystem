package com.jack.salarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalaryManagementHomeController {

	@GetMapping("/")
	public String showHomePage()
	{
		return "index";
	}
}
