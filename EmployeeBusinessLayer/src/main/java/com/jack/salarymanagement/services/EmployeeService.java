package com.jack.salarymanagement.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.GenerateEmployeeID;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Service class - EmployeeService
 * Process the activities related to Employee
 */
@Service
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private GenerateEmployeeID generateID;
	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private ValidateService validateService;
	@Autowired
	private EmployeeClient eClient;
	
	private static Integer globalEmployeeid=0;
	
	private EmployeeDetails detailsFromDB;
	
	public static Integer getGlobalEmployeeid() {
		return globalEmployeeid;
	}

	/**
	 * Save Employee Login Details during SignUp Process
	 * 
	 * @param employeeLogin
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeLoginDetails(EmployeeLogin employeeLogin) {
		try {
			LOGGER.info("-----Save Employee Login Details-----");
			EmployeeLogin detailsFromDB = eClient.getEmployeeLoginByUserName(employeeLogin.getUsername());
			
			if (StringUtils.isEmpty(detailsFromDB)) {
				List<Integer> idList = eClient.getEmployeeIds();
				Set<Integer> employeeSet = new HashSet<>(idList);
				generateID.setEmployeeIdSet(employeeSet);
				Integer employeeID = generateID.GenerateID(true);
				employeeLogin.setEmployeeid(employeeID);
				globalEmployeeid = employeeID;
				
				eClient.saveEmployeeSignUp(employeeLogin);

				returnMessage.setValid(true);
				returnMessage.setMessage(StringConstants.SAVED_TO_DB);
			} 
			else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.NOT_SAVED_TO_DB);
			}
		} catch (Exception e) {
			LOGGER.error(">>>>>Exception Occurred in calculateSalary saveEmployeeLoginDetails<<<<<",e);
		}
		return returnMessage;
	}
	
	/**
	 * Save Employee details
	 * 
	 * @param employeeDetails
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeDetails(EmployeeDetails employeeDetails)
	{
		try {
			LOGGER.info("-----Save Employee Details-----");
			detailsFromDB = eClient.getEmployeeDetailsById(EmployeeService.globalEmployeeid);
			System.out.println(detailsFromDB);
			if (!StringUtils.isEmpty(detailsFromDB)) 
			{
				if(validateService.validateEmployeeDetails(employeeDetails))
				{
					populateEmployeeDetailDB(employeeDetails);
					System.out.println(detailsFromDB);
					eClient.saveEmployeeDetails(detailsFromDB);
					
					returnMessage.setValid(true);
					returnMessage.setMessage(StringConstants.SAVED_TO_DB);
				}
				else
				{
					returnMessage.setValid(false);
					returnMessage.setMessage(StringConstants.INVALID_DETAILS);
				}
			}
			else
			{
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);
			}
		} 
		catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in saveEmployeeDetails<<<<<<",e);
		}
		return returnMessage;
	}
	
	/**
	 * Populate Employee Details
	 * 
	 * @param employeeDetails
	 */
	private void populateEmployeeDetailDB(EmployeeDetails employeeDetails)
	{
		detailsFromDB.setName(employeeDetails.getName());
		detailsFromDB.setMail(employeeDetails.getMail());
		detailsFromDB.setPhno(employeeDetails.getPhno());
		detailsFromDB.setLocation(employeeDetails.getLocation());
		detailsFromDB.setPan(employeeDetails.getPan());
		detailsFromDB.setBankacc(employeeDetails.getBankacc());
	}
	
	/**
	 * Get Employee Login Details
	 * 
	 * @param username
	 * @return detailsFromDB
	 */
	public EmployeeLogin getEmployeeLoginDetails(String username)
	{
		EmployeeLogin detailsFromDB = null;
		try 
		{			
			detailsFromDB = eClient.getEmployeeLoginByUserName(username);
		} 
		catch (Exception e) 
		{
			LOGGER.error(">>>>>Exception occurred in getEmployeeLoginDetails<<<<<<",e);
		}
		return detailsFromDB;
	}
}
