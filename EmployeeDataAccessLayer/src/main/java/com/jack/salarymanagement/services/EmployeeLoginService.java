package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.entities.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.repository.EmployeeLoginRepo;
import com.jack.salarymanagement.utilities.EmployeeAdminAccessDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeAttendanceDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeDetailsDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeLoginDataAccessUtilities;
import com.jack.salarymanagement.utilities.GenerateEmployeeID;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class EmployeeLoginService implements EmployeeLoginDataAccessUtilities {

	@Autowired
	private EmployeeLoginRepo repo;
	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private GenerateEmployeeID generateID;
	@Autowired
	private EmployeeDetails eDetails;
	@Autowired
	private EmployeeAdminAccess eAdminAccess;
	@Autowired
	private EmployeeAttendance eAttendance;
	@Autowired
	private EmployeeDetailsDataAccessUtilities eAccessUtilities;
	@Autowired
	private EmployeeAdminAccessDataAccessUtilities eAdminUtilities;
	@Autowired
	private EmployeeAttendanceDataAccessUtilities eAttendanceUtilities;

	@Override
	public EmployeeLogin saveEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	@Override
	public EmployeeLogin fetchEmployee(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void deleteEmployee(String username) {
		repo.deleteByUsername(username);
	}

	@Override
	public EmployeeLogin updateEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	@Override
	public List<Integer> fetchEmployeeIds() {
		return repo.findAllIDs();
	}
	
	public void saveEmployeeSignupDetails(EmployeeLogin employeeLogin)
	{
		try 
		{
			eDetails.setEmployeeid(employeeLogin.getEmployeeid());
			eAdminAccess.setEmployeeid(employeeLogin.getEmployeeid());
			eAdminAccess.setDoj(getCurrentDate());
			populateEmployeeAttendance(employeeLogin.getEmployeeid()); 
			
			saveEmployee(employeeLogin);
			eAccessUtilities.saveEmployeeDetails(eDetails);
			eAdminUtilities.saveAdminAccess(eAdminAccess);
			eAttendanceUtilities.savEmployeeAttendance(eAttendance);		
		} 
		catch (Exception e) 
		{
			// log-message
		}
	}
	
	private void populateEmployeeAttendance(Integer employeeid)
	{
		eAttendance.setEmployeeid(employeeid);
		eAttendance.setPaidleaves(22);
		eAttendance.setUnpaidleaves(0);
	}

	private Date getCurrentDate()
	{
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}
	
	// Move to BusinessLayer
	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeLoginDetails(EmployeeLogin employeeLogin) {
		try 
		{
			EmployeeLogin detailsFromDB = fetchEmployee(employeeLogin.getUsername());
			if (StringUtils.isEmpty(detailsFromDB)) 
			{
				List<Integer> idList = fetchEmployeeIds();
				Set<Integer> employeeSet = new HashSet<>(idList);
				
				generateID.setEmployeeIdSet(employeeSet);
				Integer employeeID = generateID.GenerateID();
				employeeLogin.setEmployeeid(employeeID);
				
				saveEmployee(employeeLogin);

				returnMessage.setValid(true);
				returnMessage.setMessage(StringConstants.SAVED_TO_DB);
			} 
			else 
			{
				updateEmployee(employeeLogin);

				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.UPDATED_TO_DB);
			}
		} 
		catch (Exception e)
		{
			// log-message
		}
		return returnMessage;
	}

	// Move to BusinessLayer
	@SuppressWarnings("deprecation")
	public ReturnMessage isValidEmployeeWithMessage(EmployeeLogin employeeLogin)
	{
		try 
		{
			EmployeeLogin detailsFromDB = fetchEmployee(employeeLogin.getUsername());
			if (!StringUtils.isEmpty(detailsFromDB))
			{
				boolean isValidEmployee = isValidEmployee(employeeLogin, detailsFromDB);
				if (isValidEmployee) 
				{
					returnMessage.setValid(isValidEmployee);
					returnMessage.setMessage(StringConstants.VALID_EMPLOYEE);
				}
				else 
				{
					returnMessage.setValid(isValidEmployee);
					returnMessage.setMessage(StringConstants.INVALID_EMPLOYEE);
				}
			} 
			else
			{
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.EMPLOYEE_NOT_FOUND);
			}
		} 
		catch (Exception e) {
			// log-message
		}
		return returnMessage;
	}

	// Move to BusinessLayer
	private boolean isValidEmployee(EmployeeLogin employeeLogin, EmployeeLogin employeeLoginFromDB) {
		if (employeeLoginFromDB.getPassword().equals(employeeLogin.getPassword())) {
			return true;
		}
		return false;
	}

}
