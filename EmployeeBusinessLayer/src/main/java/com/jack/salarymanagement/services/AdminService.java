package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.client.AdminClient;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Service class - AdminService
 * Process Admin Related Services
 */
@Service
public class AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private AdminClient aClient;
	
	private EmployeeAdminAccess detailsFromDB;
	
	/**
	 * Send Admin Access Details to DataBase Layer
	 * 
	 * @param eAdminAccess
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage saveAdminAccessDetails(EmployeeAdminAccess eAdminAccess)
	{
		try {
			LOGGER.info("-----Save Admin Access Details Start -----");
			
			// get Admin Access Details from DataBase
			detailsFromDB = aClient.getAdminAccessById(eAdminAccess.getEmployeeid());
			
			if(!StringUtils.isEmpty(detailsFromDB))
			{
				// Populate Admin Access Details
				populateAdminAccess(eAdminAccess);
				
				// Save Admin Access Details
				EmployeeAdminAccess eAccess = aClient.saveAdminAccess(detailsFromDB);
				
				if(!StringUtils.isEmpty(eAccess))
				{
					returnMessage.setValid(true);
					returnMessage.setMessage(StringConstants.SAVED_TO_DB);
				}
				else
				{
					returnMessage.setValid(false);
					returnMessage.setMessage(StringConstants.SAVED_TO_DB_FAILED);
				}
				
			}
			else 
			{
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);
			}
		}
		catch (Exception e) {
			LOGGER.error(">>>>>Exception Occurred in saveAdminAccessDetails<<<<<", e);
		}
		return returnMessage;
	}
	
	/**
	 * Populate Admin Access with DOD and Desgination
	 * 
	 * @param employeeAdminAccess
	 */
	private void populateAdminAccess(EmployeeAdminAccess employeeAdminAccess)
	{
		detailsFromDB.setDod(getCurrentDate());
		detailsFromDB.setDesignation(employeeAdminAccess.getDesignation());
	}
	
	/**
	 * Get Current Date
	 * 
	 * @return
	 */
	private Date getCurrentDate()
	{
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}
}
