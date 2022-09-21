package com.jack.salarymanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;

/**
 * @author JACK
 *
 * Client Interface - calls Business Layer Admin APIs
 */
@FeignClient(name = "admin-business-service",url = "http://localhost:8081/admin")
public interface AdminClient {

	/**
	 * Do Validate Admin Process
	 * 
	 * @param aLogin
	 * @return ReturnMessage
	 */
	@PostMapping("/dologin")
	ReturnMessage doValidateAdmin(@RequestBody AdminLogin aLogin);
	
	/**
	 * Do Set Designation Process
	 * 
	 * @param eAccess
	 * @return ReturnMessage
	 */
	@PostMapping("/dosetdesignation")
	ReturnMessage doSetDesignation(@RequestBody EmployeeAdminAccess eAccess);
	
	/**
	 * Do Calculate salary Process
	 * 
	 * @param employeeid
	 * @return ReturnMessage
	 */
	@PostMapping("/docalculatesalary")
	ReturnMessage doCalculateSalary(@RequestBody Integer employeeid);
}
