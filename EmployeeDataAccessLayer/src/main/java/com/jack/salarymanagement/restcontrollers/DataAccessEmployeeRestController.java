package com.jack.salarymanagement.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.entities.EmployeeLogin;
import com.jack.salarymanagement.services.EmployeeAttendanceServie;
import com.jack.salarymanagement.services.EmployeeDetailsServie;
import com.jack.salarymanagement.services.EmployeeLoginService;

@RestController
@RequestMapping("/employee")
public class DataAccessEmployeeRestController {

	@Autowired
	private EmployeeLoginService eLoginService;
	@Autowired
	private EmployeeDetailsServie eDetailsServie;
	@Autowired
	private EmployeeAttendanceServie eAttendanceServie;
	
	@GetMapping("/logindetails/{username}")
	public EmployeeLogin fetchEmployeeLoginDetails(@PathVariable String username)
	{
		return eLoginService.fetchEmployee(username);
	}
	
	@GetMapping("/employeeids")
	public List<Integer> fetchEmployeeIDs()
	{
		return eLoginService.fetchEmployeeIds();
	}
	
	@GetMapping("/details/{employeeid}")
	public EmployeeDetails fetchEmployeeDetails(@PathVariable Integer employeeid)
	{
		return eDetailsServie.fetchEmployeeDetails(employeeid);
	}
	
	@GetMapping("/attendance/{employeeid}")
	public EmployeeAttendance fetchEmployeeAttendance(@PathVariable Integer employeeid)
	{
		return eAttendanceServie.fetchEmployeeAttendance(employeeid);
	}
	
	@PostMapping("/signupdetails")
	public void saveEmployeeSignUp(@RequestBody EmployeeLogin employeeLogin)
	{
		eLoginService.saveEmployeeSignupDetails(employeeLogin);
	}
	
	@PostMapping("/details")
	public void saveEmployeeDetails(@RequestBody EmployeeDetails eDetails)
	{
		System.out.println(eDetails);
		eDetailsServie.saveEmployeeDetails(eDetails);
	}
	
	@PostMapping("/attendance")
	public void saveEmployeeAttendance(@RequestBody EmployeeAttendance eAttendance)
	{
		eAttendanceServie.savEmployeeAttendance(eAttendance);
	}
}
