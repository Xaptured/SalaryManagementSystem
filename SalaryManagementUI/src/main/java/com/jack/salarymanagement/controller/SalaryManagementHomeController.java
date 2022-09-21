package com.jack.salarymanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author JACK
 *
 * Controller class - Handles the Home Activities
 */
@Controller
public class SalaryManagementHomeController {

	private static final Logger logger = LoggerFactory.getLogger(SalaryManagementHomeController.class);

	/**
	 * API call to get index page
	 * 
	 * @return index page
	 */
	@GetMapping("/")
	public String showHomePage()
	{
		logger.info("-----Salary Management System Started-----");
		return "index";
	}
}
