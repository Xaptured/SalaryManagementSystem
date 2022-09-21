package com.jack.salarymanagement.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.services.AdminService;
import com.jack.salarymanagement.services.CalculateSalaryService;
//import com.jack.salarymanagement.services.ValidateService;
import com.jack.salarymanagement.services.ValidateService;

/**
 * @author JACK
 * 
 * Rest Controller class - Handles the Admin Activities
 */
@RestController
@RequestMapping("/admin")
public class BusinessAdminRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAdminRestController.class);
	
	@Autowired
	private ValidateService vService;
	@Autowired
	private AdminService aService;
	@Autowired
	private CalculateSalaryService cService;
	
	/**
	 * API call to do Validate Admin Process
	 * 
	 * @param aLogin
	 * @return ReturnMessage
	 */
	@PostMapping("/dologin")
	public ReturnMessage doValidateAdmin(@RequestBody AdminLogin aLogin)
	{
		LOGGER.info("-----Admin Login Process : Validate Admin Start-----");
		return vService.isValidAdminWithMessage(aLogin);
	}
	
	/**
	 * API call to do Set Designation Process
	 * 
	 * @param eAccess
	 * @return ReturnMessage
	 */
	@PostMapping("/dosetdesignation")
	public ReturnMessage doSetDesignation(@RequestBody EmployeeAdminAccess eAccess)
	{
		LOGGER.info("-----Set Designation Process : Set Designation Start-----");
		return aService.saveAdminAccessDetails(eAccess);
	}
	
	/**
	 * API call to do Calculate salary Process
	 * 
	 * @param employeeid
	 * @return ReturnMessage
	 */
	@PostMapping("/docalculatesalary")
	public ReturnMessage doCalculateSalary(@RequestBody Integer employeeid)
	{
		LOGGER.info("-----Calculate Salary Process : Calculate Salary Start-----");
		return cService.calculateSalary(employeeid);
	}
}
