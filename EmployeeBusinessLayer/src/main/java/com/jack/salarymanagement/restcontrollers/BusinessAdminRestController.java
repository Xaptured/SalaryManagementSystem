package com.jack.salarymanagement.restcontrollers;

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
import com.jack.salarymanagement.services.ValidateService;

@RestController
@RequestMapping("/admin")
public class BusinessAdminRestController {

	@Autowired
	private ValidateService vService;
	@Autowired
	private AdminService aService;
	@Autowired
	private CalculateSalaryService cService;
	
	@PostMapping("/dologin")
	public ReturnMessage doValidateAdmin(@RequestBody AdminLogin aLogin)
	{
		return vService.isValidAdminWithMessage(aLogin);
	}
	
	@PostMapping("/dosetdesignation")
	public ReturnMessage doSetDesignation(@RequestBody EmployeeAdminAccess eAccess)
	{
		return aService.saveAdminAccessDetails(eAccess);
	}
	
	@PostMapping("/docalculatesalary")
	public ReturnMessage doCalculateSalary(@RequestBody Integer employeeid)
	{
		return cService.calculateSalary(employeeid);
	}
}
