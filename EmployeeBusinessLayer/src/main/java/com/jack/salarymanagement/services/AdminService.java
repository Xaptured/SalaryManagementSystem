package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class AdminService {

	@Autowired
	private ReturnMessage returnMessage;
	
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
				
				returnMessage.setValid(true);
				returnMessage.setMessage(StringConstants.SAVED_TO_DB);
			}
			else 
			{
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);
			}
		}
		catch (Exception e) {
			//log-message
		}
		return returnMessage;
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
