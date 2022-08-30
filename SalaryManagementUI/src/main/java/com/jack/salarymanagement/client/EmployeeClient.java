package com.jack.salarymanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;

@FeignClient(name = "employee-business-service", url = "http://localhost:8081/employee")
public interface EmployeeClient {

	@PostMapping("/dosignup")
	ReturnMessage doSignUp(@RequestBody EmployeeLogin eLogin);
	
	@PostMapping("/dologin")
	ReturnMessage doValidateEmployee(@RequestBody EmployeeLogin eLogin);
	
	@PostMapping("/dosubmitdetails")
	ReturnMessage doSubmitDetails(@RequestBody EmployeeDetails eDetails);
	
	@PostMapping("/doapplyleave")
	ReturnMessage doApplyLeave();
	
	@PostMapping("/getLoginDetails")
	EmployeeLogin getEmployeeLoginDetails(@RequestBody String username);
}
