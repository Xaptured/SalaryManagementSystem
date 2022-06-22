package com.jack.salarymanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;

@FeignClient(name = "admin-business-service",url = "http://localhost:8081/admin")
public interface AdminClient {

	@PostMapping("/dologin")
	ReturnMessage doValidateAdmin(@RequestBody AdminLogin aLogin);
	
	@PostMapping("/dosetdesignation")
	ReturnMessage doSetDesignation(@RequestBody EmployeeAdminAccess eAccess);
	
	@PostMapping("/docalculatesalary")
	ReturnMessage doCalculateSalary(@RequestBody Integer employeeid);
}
