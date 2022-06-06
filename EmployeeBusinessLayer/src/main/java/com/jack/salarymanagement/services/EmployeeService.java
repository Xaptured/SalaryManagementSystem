package com.jack.salarymanagement.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
	private AttendanceService aService;
	
	private EmployeeDetails detailsFromDB;
	private Integer globalEmployeeId;
	
	@SuppressWarnings("deprecation")
	public ReturnMessage saveEmployeeLoginDetails(EmployeeLogin employeeLogin) {
		try {
			// Should call DB layer
			EmployeeLogin detailsFromDB = new EmployeeLogin();
			
			if (StringUtils.isEmpty(detailsFromDB)) {
				// Should call DB layer - fetchEmployeeIds()
				List<Integer> idList = new ArrayList<>();
				Set<Integer> employeeSet = new HashSet<>(idList);

				generateID.setEmployeeIdSet(employeeSet);
				Integer employeeID = generateID.GenerateID();
				employeeLogin.setEmployeeid(employeeID);
				globalEmployeeId = employeeID;
				aService.setGlobalEmployeeId(globalEmployeeId);
				
				// Should call DB layer
				//saveEmployee(employeeLogin);

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
			//Should call DB layer
			EmployeeDetails detailsFromDB = new EmployeeDetails();
			if (!StringUtils.isEmpty(detailsFromDB)) 
			{
				if(validateService.validateEmployeeDetails(employeeDetails))
				{
					populateEmployeeDetailDB(employeeDetails);
					
					// Call DB layer to save detailsFromDB
					//...
					
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
