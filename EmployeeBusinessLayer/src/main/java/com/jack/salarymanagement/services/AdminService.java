package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;

@Service
public class AdminService {

	private EmployeeAdminAccess detailsFromDB;
	
	@SuppressWarnings("deprecation")
	public ReturnMessage saveAdminAccessDetails(EmployeeAdminAccess eAdminAccess)
	{
		try {
			// Should call DB layer
			detailsFromDB = new EmployeeAdminAccess();
			
			if(!StringUtils.isEmpty(detailsFromDB))
			{
				populateAdminAccess(eAdminAccess);
				
				// Call DB layer to save detailsFromDB
				//...
			}
			else 
			{
				// Should return message details doesn't exist
			}
		}
		catch (Exception e) {
			
		}
		// Should change to return message
		return null;
	}
	
	private void populateAdminAccess(EmployeeAdminAccess employeeAdminAccess)
	{
		detailsFromDB.setDod(getCurrentDate());
		detailsFromDB.setDesignation(employeeAdminAccess.getDesignation());
	}
	
	private Date getCurrentDate()
	{
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}
}
