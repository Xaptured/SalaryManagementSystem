package com.jack.salarymanagement.restcontrollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


/**
 * @author JACK
 * 
 * Rest Controller class - Handles the Admin Activities
 */
@RestController
@RequestMapping("/admin")
public class DataAccessAdminRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessAdminRestController.class);
	
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
	
	/**
	 * API call to Fetch Admin Login Details from DataBase using username
	 * 
	 * @param username
	 * @return adminLogin
	 */
	@GetMapping("/logindetails/{username}")
	public AdminLogin fetchAdminLoginDetails(@PathVariable String username)
	{
		AdminLogin adminLogin = null;
		try {
			adminLogin = aLoginService.fetchAdmin(username);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in fetchAdminLoginDetails<<<<<",e);
		}
		return adminLogin;
	}
	
	/**
	 * API call to Fetch Admin Access from DataBase using employeeid 
	 * 
	 * @param employeeid
	 * @return eAdminAccess
	 */
	@GetMapping("/adminaccess/{employeeid}")
	public EmployeeAdminAccess fetchAdminAccess(@PathVariable Integer employeeid)
	{
		EmployeeAdminAccess eAdminAccess = null;
		try {
			eAdminAccess = eAdminAccessService.fetchAdminAccess(employeeid);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in fetchAdminAccess<<<<<",e);
		}
		return eAdminAccess;
	}
	
	/**
	 * API call to Fetch Salary Break Down from DataBase using designation
	 * 
	 * @param designation
	 * @return eSalaryBreakDown
	 */
	@GetMapping("/salarybreakdown/{designation}")
	public EmployeeSalaryBreakDown fetchSalaryBreakDown(@PathVariable String designation)
	{
		EmployeeSalaryBreakDown eSalaryBreakDown = null;
		try {
			eSalaryBreakDown = sBreakDownService.fetchSalaryBreakDown(designation);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in fetchSalaryBreakDown<<<<<",e);
		}
		return eSalaryBreakDown;
	}
	
	/**
	 * API call to Fetch Employee Attendance from DataBase using employeeid
	 * 
	 * @param employeeid
	 * @return eAttendance
	 */
	@GetMapping("/attendance/{employeeid}")
	public EmployeeAttendance fetchEmployeeAttendance(@PathVariable Integer employeeid)
	{
		EmployeeAttendance eAttendance = null;
		try {
			eAttendance = eAttendanceServie.fetchEmployeeAttendance(employeeid);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in fetchEmployeeAttendance<<<<<",e);
		}
		return eAttendance;
	}
	
	/**
	 * API call to Fetch Employee Details from DataBase using employeeid
	 * 
	 * @param employeeid
	 * @return eDetails
	 */
	@GetMapping("/details/{employeeid}")
	public EmployeeDetails fetchEmployeeDetails(@PathVariable Integer employeeid)
	{
		EmployeeDetails eDetails = null;
		try {
			eDetails = eDetailsServie.fetchEmployeeDetails(employeeid);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in fetchEmployeeDetails<<<<<",e);
		}
		return eDetails;
	}
	
	
	/**
	 * API call to Save Admin Access to DataBase using eAdminAccess
	 * 
	 * @param eAdminAccess
	 * @return employeeAdminAccess
	 */
	@PostMapping("/adminaccess")
	public EmployeeAdminAccess saveAdminAccess(@RequestBody EmployeeAdminAccess eAdminAccess)
	{
		EmployeeAdminAccess employeeAdminAccess = null;
		try {
			employeeAdminAccess = eAdminAccessService.saveAdminAccess(eAdminAccess);
		}
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveAdminAccess<<<<<",e);
		}	
		return employeeAdminAccess;
	}
	
	/**
	 * API call to Save Salary Information to DataBase using eSalary
	 * 
	 * @param eSalary
	 * @return employeeSalary
	 */
	@PostMapping("/salary")
	public EmployeeSalary saveSalaryInfo(@RequestBody EmployeeSalary eSalary)
	{
		EmployeeSalary employeeSalary = null;
		try {
			employeeSalary = eSalaryInfoService.savEmployeeSalary(eSalary);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveSalaryInfo<<<<<",e);
		}
		return employeeSalary;
	}
	
	/**
	 * API call to Save Employee Attendance to DataBase using eAttendance
	 * 
	 * @param eAttendance
	 * @return employeeAttendance
	 */
	@PostMapping("/attendance")
	public EmployeeAttendance saveEmployeeAttendance(@RequestBody EmployeeAttendance eAttendance)
	{
		EmployeeAttendance employeeAttendance = null;
		try {
			employeeAttendance = eAttendanceServie.savEmployeeAttendance(eAttendance);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveEmployeeAttendance<<<<<",e);
		}
		return employeeAttendance;
	}
	
	/**
	 * API call to Save Employee Details to DataBase using eDetails
	 * 
	 * @param eDetails
	 */
	@PostMapping("/details")
	public void saveEmployeeDetails(@RequestBody EmployeeDetails eDetails)
	{
		try {
			eDetailsServie.saveEmployeeDetails(eDetails);
		}
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveEmployeeDetails<<<<<",e);
		}		
	}
}
