package com.jack.salarymanagement.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.services.AttendanceService;
import com.jack.salarymanagement.services.EmployeeService;
//import com.jack.salarymanagement.services.ValidateService;

/**
 * @author JACK
 * 
 * Rest Controller class - Handles the Employee Activities
 */
@RestController
@RequestMapping("/employee")
public class BusinessEmployeeRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessEmployeeRestController.class);
	
	@Autowired
	private EmployeeService eService;
//	@Autowired
//	private ValidateService vService;
	@Autowired
	private AttendanceService aService;
	
	/**
	 * API call to do SignUp Process
	 * 
	 * @param eLogin
	 * @return ReturnMessage
	 */
	@PostMapping("/dosignup")
	public ReturnMessage doSignUp(@RequestBody EmployeeLogin eLogin)
	{
		LOGGER.info("-----Employee SignUp Process : Do Signup Start-----");
		return eService.saveEmployeeLoginDetails(eLogin);
	}
	
//	@PostMapping("/dologin")
//	public ReturnMessage doValidateEmployee(@RequestBody EmployeeLogin eLogin)
//	{
//		return vService.isValidEmployeeWithMessage(eLogin);
//	}
	
	/**
	 * API call to do Submit Details Process
	 * 
	 * @param eDetails
	 * @return ReturnMessage
	 */
	@PostMapping("/dosubmitdetails")
	public ReturnMessage doSubmitDetails(@RequestBody EmployeeDetails eDetails)
	{
		LOGGER.info("-----Submit Details Process : Do Submit Details Start-----");
		return eService.saveEmployeeDetails(eDetails);
	}
	
	/**
	 * API call to do Apply Leave Process
	 * 
	 * @return ReturnMessage
	 */
	@PostMapping("/doapplyleave")
	public ReturnMessage doApplyLeave()
	{
		LOGGER.info("-----Apply Leave Process : Do Apply Leave Start-----");
		return aService.applyLeave();
	}
	
	/**
	 * API call to Get Employee Login Details
	 * 
	 * @param username
	 * @return EmployeeLogin
	 */
	@PostMapping("/getLoginDetails")
	EmployeeLogin getEmployeeLoginDetails(@RequestBody String username)
	{
		return eService.getEmployeeLoginDetails(username);
	}
}
