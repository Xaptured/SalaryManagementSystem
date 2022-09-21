package com.jack.salarymanagement.restcontrollers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @author JACK
 * 
 * Rest Controller class - Handles the Employee Activities
 */
@RestController
@RequestMapping("/employee")
public class DataAccessEmployeeRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataAccessEmployeeRestController.class);
	
	@Autowired
	private EmployeeLoginService eLoginService;
	@Autowired
	private EmployeeDetailsServie eDetailsServie;
	@Autowired
	private EmployeeAttendanceServie eAttendanceServie;
	
	/**
	 * API call to Fetch Employee Login Details from Database using username
	 * 
	 * @param username
	 * @return eLogin
	 */
	@GetMapping("/logindetails/{username}")
	public EmployeeLogin fetchEmployeeLoginDetails(@PathVariable String username)
	{
		EmployeeLogin eLogin = null;
		try {
			eLogin = eLoginService.fetchEmployee(username);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Error occurred in fetchEmployeeLoginDetails<<<<<",e);
		}
		return eLogin;
	}
	
	/**
	 * API call to Fetch All Employee Ids from Database
	 * 
	 * @return idList
	 */
	@GetMapping("/employeeids")
	public List<Integer> fetchEmployeeIDs()
	{
		List<Integer> idList = null;
		try {
			idList = eLoginService.fetchEmployeeIds();
		}
		catch (Exception e) {
			LOGGER.error(">>>>>Error occurred in fetchEmployeeLoginDetails<<<<<",e);
		}
		return idList;
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
	 * @param employeeLogin
	 */
	@PostMapping("/signupdetails")
	public void saveEmployeeSignUp(@RequestBody EmployeeLogin employeeLogin)
	{
		try {
			eLoginService.saveEmployeeSignupDetails(employeeLogin);
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveEmployeeSignUp<<<<<",e);
		}		
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
}
