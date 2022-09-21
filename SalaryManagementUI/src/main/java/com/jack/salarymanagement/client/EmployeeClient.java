package com.jack.salarymanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;

/**
 * @author JACK
 *
 * Client Interface - calls Business Layer Employee APIs
 */
@FeignClient(name = "employee-business-service", url = "http://localhost:8081/employee")
public interface EmployeeClient {

	/**
	 * Do SignUp Process
	 * 
	 * @param eLogin
	 * @return ReturnMessage
	 */
	@PostMapping("/dosignup")
	ReturnMessage doSignUp(@RequestBody EmployeeLogin eLogin);
	
	/**
	 * Do Validate Employee Process
	 * 
	 * @param eLogin
	 * @return ReturnMessage
	 */
//	@PostMapping("/dologin")
//	ReturnMessage doValidateEmployee(@RequestBody EmployeeLogin eLogin);
	
	/**
	 * Do Submit Details Process
	 * 
	 * @param eDetails
	 * @return ReturnMessage
	 */
	@PostMapping("/dosubmitdetails")
	ReturnMessage doSubmitDetails(@RequestBody EmployeeDetails eDetails);
	
	/**
	 * Do Apply Leave Process
	 * 
	 * @return ReturnMessage
	 */
	@PostMapping("/doapplyleave")
	ReturnMessage doApplyLeave();
	
	/**
	 * Get Employee Login Details
	 * 
	 * @param username
	 * @return EmployeeLogin
	 */
	@PostMapping("/getLoginDetails")
	EmployeeLogin getEmployeeLoginDetails(@RequestBody String username);
}
