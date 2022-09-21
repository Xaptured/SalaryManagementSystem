package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.entities.EmployeeLogin;
import com.jack.salarymanagement.repository.EmployeeLoginRepo;
import com.jack.salarymanagement.utilities.EmployeeAdminAccessDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeAttendanceDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeDetailsDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeLoginDataAccessUtilities;
import com.jack.salarymanagement.utilities.SpringBeanApplicationContext;

/**
 * @author JACK
 *
 * Service class - EmployeeLoginService
 * Non JavaDoc Methods are unused
 */
@Service
public class EmployeeLoginService implements EmployeeLoginDataAccessUtilities{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLoginService.class);
	
	@Autowired
	private EmployeeLoginRepo repo;
	@Autowired
	private EmployeeDetailsDataAccessUtilities eDetailUtilities;
	@Autowired
	private EmployeeAdminAccessDataAccessUtilities eAdminUtilities;
	@Autowired
	private EmployeeAttendanceDataAccessUtilities eAttendanceUtilities;
	@SuppressWarnings("unused")
	@Autowired
	private ApplicationContext context;
	
	private EmployeeDetails eDetails;
	private EmployeeAdminAccess eAdminAccess;
	private EmployeeAttendance eAttendance;
	
	@Override
	public EmployeeLogin saveEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	/**
	 * Fetch Employee Details
	 * 
	 * @param username
	 * @return eLogin
	 * @throws Exception
	 */
	@Override
	public EmployeeLogin fetchEmployee(String username) throws Exception {
		EmployeeLogin eLogin = null;
		try {
			eLogin = repo.findByUsername(username);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return eLogin;
	}

	@Override
	public void deleteEmployee(String username) {
		repo.deleteByUsername(username);
	}

	@Override
	public EmployeeLogin updateEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	/**
	 * Fetch Employee Ids
	 * 
	 * @return idList
	 * @throws Exception
	 */
	@Override
	public List<Integer> fetchEmployeeIds() throws Exception {
		List<Integer> idList = null;
		try {
			idList = repo.findAllIDs();
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return idList;
	}

	/**
	 * Save Employee SignUp details
	 * 
	 * @param employeeLogin
	 * @throws Exception 
	 */
	public void saveEmployeeSignupDetails(EmployeeLogin employeeLogin) throws Exception {
		try {
			LOGGER.info("-----Save Employee SignUp details-----");
			eDetails = 
					(EmployeeDetails)SpringBeanApplicationContext.getBean("employeeDetails");
			eAdminAccess = 
					(EmployeeAdminAccess)SpringBeanApplicationContext.getBean("employeeAdminAccess");
			eAttendance = 
					(EmployeeAttendance)SpringBeanApplicationContext.getBean("employeeAttendance");
			
			populateEmployeeDetails(employeeLogin);
			populateAdminAcess(employeeLogin);
			populateEmployeeAttendance(employeeLogin.getEmployeeid());

			saveEmployee(employeeLogin);
			eDetailUtilities.saveEmployeeDetails(eDetails);
			eAdminUtilities.saveAdminAccess(eAdminAccess);
			eAttendanceUtilities.savEmployeeAttendance(eAttendance);
		} 
		catch (Exception e) {
			throw new Exception();
		}
	}
	
	/**
	 * Populate Admin Access Details
	 * 
	 * @param employeeLogin
	 */
	private void populateAdminAcess(EmployeeLogin employeeLogin)
	{
		eAdminAccess.setEmployeeid(employeeLogin.getEmployeeid());
	}

	/**
	 * Populate Employee Details
	 * 
	 * @param employeeLogin
	 */
	private void populateEmployeeDetails(EmployeeLogin employeeLogin) {
		eDetails.setEmployeeid(employeeLogin.getEmployeeid());
		eDetails.setDoj(getCurrentDate());
		eDetails.setExperience(0);
	}

	/**
	 * Populate Employee Attendance
	 * 
	 * @param employeeid
	 */
	private void populateEmployeeAttendance(Integer employeeid) {
		eAttendance.setEmployeeid(employeeid);
		eAttendance.setPaidleaves(22);
		eAttendance.setUnpaidleaves(0);
	}

	/**
	 * Get Current Date
	 * 
	 * @return date
	 */
	private Date getCurrentDate() {
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}

}
