package com.jack.salarymanagement.restcontrollers;

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
import com.jack.salarymanagement.services.ValidateService;

@RestController
@RequestMapping("/employee")
public class BusinessEmployeeRestController {

	@Autowired
	private EmployeeService eService;
	@Autowired
	private ValidateService vService;
	@Autowired
	private AttendanceService aService;
	
	@PostMapping("/dosignup")
	public ReturnMessage doSignUp(@RequestBody EmployeeLogin eLogin)
	{
		System.out.println(eLogin);
		return eService.saveEmployeeLoginDetails(eLogin);
	}
	
	@PostMapping("/dologin")
	public ReturnMessage doValidateEmployee(@RequestBody EmployeeLogin eLogin)
	{
		return vService.isValidEmployeeWithMessage(eLogin);
	}
	
	@PostMapping("/dosubmitdetails")
	public ReturnMessage doSubmitDetails(@RequestBody EmployeeDetails eDetails)
	{
		System.out.println(eDetails);
		return eService.saveEmployeeDetails(eDetails);
	}
	
	@PostMapping("/doapplyleave")
	public ReturnMessage doApplyLeave()
	{
		return aService.applyLeave();
	}
	
	@PostMapping("/getLoginDetails")
	EmployeeLogin getEmployeeLoginDetails(@RequestBody String username)
	{
		return eService.getEmployeeLoginDetails(username);
	}
}
