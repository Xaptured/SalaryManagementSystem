package com.jack.salarymanagement.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jack.salarymanagement.entities.AdminLogin;
import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.entities.EmployeeSalary;
import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;
import com.jack.salarymanagement.services.AdminLoginService;
import com.jack.salarymanagement.services.EmployeeAdminAccessService;
import com.jack.salarymanagement.services.EmployeeAttendanceServie;
import com.jack.salarymanagement.services.EmployeeDetailsServie;
import com.jack.salarymanagement.services.EmployeeSalaryInfoService;
import com.jack.salarymanagement.services.SalaryBreakDownService;

@RestController
@RequestMapping("/admin")
public class DataAccessAdminRestController {

	@Autowired
	private AdminLoginService aLoginService;
	@Autowired
	private EmployeeAdminAccessService eAdminAccessService;
	@Autowired
	private SalaryBreakDownService sBreakDownService;
	@Autowired
	private EmployeeAttendanceServie eAttendanceServie;
	@Autowired
	private EmployeeDetailsServie eDetailsServie;
	@Autowired
	private EmployeeSalaryInfoService eSalaryInfoService;
	
	@GetMapping("/logindetails/{username}")
	public AdminLogin fetchAdminLoginDetails(@PathVariable String username)
	{
		return aLoginService.fetchAdmin(username);
	}
	
	@GetMapping("/adminaccess/{employeeid}")
	public EmployeeAdminAccess fetchAdminAccess(@PathVariable Integer employeeid)
	{
		return eAdminAccessService.fetchAdminAccess(employeeid);
	}
	
	@GetMapping("/salarybreakdown/{designation}")
	public EmployeeSalaryBreakDown fetchSalaryBreakDown(@PathVariable String designation)
	{
		return sBreakDownService.fetchSalaryBreakDown(designation);
	}
	
	@GetMapping("/attendance/{employeeid}")
	public EmployeeAttendance fetchEmployeeAttendance(@PathVariable Integer employeeid)
	{
		return eAttendanceServie.fetchEmployeeAttendance(employeeid);
	}
	
	@GetMapping("/details/{employeeid}")
	public EmployeeDetails fetchEmployeeDetails(@PathVariable Integer employeeid)
	{
		return eDetailsServie.fetchEmployeeDetails(employeeid);
	}
	
	@PostMapping("/adminaccess")
	public void saveAdminAccess(@RequestBody EmployeeAdminAccess eAdminAccess)
	{
		eAdminAccessService.saveAdminAccess(eAdminAccess);
	}
	
	@PostMapping("/salary")
	public void saveSalaryInfo(@RequestBody EmployeeSalary eSalary)
	{
		eSalaryInfoService.savEmployeeSalary(eSalary);
	}
	
	@PostMapping("/attendance")
	public void saveEmployeeAttendance(@RequestBody EmployeeAttendance eAttendance)
	{
		eAttendanceServie.savEmployeeAttendance(eAttendance);
	}
	
	@PostMapping("/details")
	public void saveEmployeeDetails(@RequestBody EmployeeDetails eDetails)
	{
		eDetailsServie.saveEmployeeDetails(eDetails);
	}
}
