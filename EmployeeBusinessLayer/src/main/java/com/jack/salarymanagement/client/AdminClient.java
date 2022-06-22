package com.jack.salarymanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeSalary;
import com.jack.salarymanagement.models.EmployeeSalaryBreakDown;

@FeignClient(name="admin-db-service",url="http://localhost:8080/admin")
public interface AdminClient {

	@GetMapping("/logindetails/{username}")
	AdminLogin getAdminLoginDetailsByUsername(@PathVariable String username);
	
	@GetMapping("/adminaccess/{employeeid}")
	EmployeeAdminAccess getAdminAccessById(@PathVariable Integer employeeid);
	
	@PostMapping("/adminaccess")
	void saveAdminAccess(@RequestBody EmployeeAdminAccess eAdminAccess);
	
	@GetMapping("/salarybreakdown/{designation}")
	EmployeeSalaryBreakDown getSalaryBreakDown(@PathVariable String designation);
	
	@GetMapping("/attendance/{employeeid}")
	EmployeeAttendance getEmployeeAttendance(@PathVariable Integer employeeid);
	
	@PostMapping("/salary")
	void saveSalaryInfo(@RequestBody EmployeeSalary eSalary);
	
	@PostMapping("/attendance")
	void saveEmployeeAttendance(@RequestBody EmployeeAttendance eAttendance);
	
	@GetMapping("/details/{employeeid}")
	EmployeeDetails getEmployeeDetails(@PathVariable Integer employeeid);
	
	@PostMapping("/details")
	void saveEmployeeDetails(@RequestBody EmployeeDetails eDetails);
}
