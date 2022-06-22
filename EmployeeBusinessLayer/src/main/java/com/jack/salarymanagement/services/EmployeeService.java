package com.jack.salarymanagement.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.GenerateEmployeeID;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class EmployeeService {

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

	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeLoginDetails(EmployeeLogin employeeLogin) {
		try {
			EmployeeLogin detailsFromDB = eClient.getEmployeeLoginByUserName(employeeLogin.getUsername());
			
			if (StringUtils.isEmpty(detailsFromDB)) {
				List<Integer> idList = eClient.getEmployeeIds();
				Set<Integer> employeeSet = new HashSet<>(idList);
				System.out.println(employeeSet);
				generateID.setEmployeeIdSet(employeeSet);
				Integer employeeID = generateID.GenerateID(true);
				System.out.println(employeeID);
				employeeLogin.setEmployeeid(employeeID);
				globalEmployeeid = employeeID;
				
				eClient.saveEmployeeSignUp(employeeLogin);

				returnMessage.setValid(true);
				returnMessage.setMessage(StringConstants.SAVED_TO_DB);
			} else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.NOT_SAVED_TO_DB);
			}
		} catch (Exception e) {
			// log-message
		}
		return returnMessage;
	}
	
	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeDetails(EmployeeDetails employeeDetails)
	{
		try {
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
			//log-message
		}
		return returnMessage;
	}
	
	private void populateEmployeeDetailDB(EmployeeDetails employeeDetails)
	{
		detailsFromDB.setName(employeeDetails.getName());
		detailsFromDB.setMail(employeeDetails.getMail());
		detailsFromDB.setPhno(employeeDetails.getPhno());
		detailsFromDB.setLocation(employeeDetails.getLocation());
		detailsFromDB.setPan(employeeDetails.getPan());
		detailsFromDB.setBankacc(employeeDetails.getBankacc());
	}

}
