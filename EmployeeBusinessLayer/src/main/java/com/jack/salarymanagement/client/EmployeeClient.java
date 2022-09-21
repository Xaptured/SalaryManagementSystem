package com.jack.salarymanagement.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;

/**
 * @author JACK
 *
 * Client Interface - calls DataBase Layer Employee APIs
 */
@FeignClient(name = "employee-db-service",url = "http://localhost:8080/employee")
public interface EmployeeClient {

	@GetMapping("/logindetails/{username}")
	EmployeeLogin getEmployeeLoginByUserName(@PathVariable String username);
	
	@GetMapping("/employeeids")
	List<Integer> getEmployeeIds();
	
	@PostMapping("/signupdetails")
	void saveEmployeeSignUp(@RequestBody EmployeeLogin employeeLogin);
	
	@GetMapping("/details/{employeeid}")
	EmployeeDetails getEmployeeDetailsById(@PathVariable Integer employeeid);
	
	@PostMapping("/details")
	void saveEmployeeDetails(@RequestBody EmployeeDetails eDetails);
	
	@GetMapping("/attendance/{employeeid}")
	EmployeeAttendance getEmployeeAttendanceById(@PathVariable Integer employeeid);
	
	@PostMapping("/attendance")
	void saveEmployeeAttendance(@RequestBody EmployeeAttendance eAttendance);
}
