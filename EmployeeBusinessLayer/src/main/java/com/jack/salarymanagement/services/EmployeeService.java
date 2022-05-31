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
	
	private EmployeeDetails detailsFromDB;
	
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

				// Should call DB layer
				//saveEmployee(employeeLogin);

				returnMessage.setValid(true);
				returnMessage.setMessage(StringConstants.SAVED_TO_DB);
			} else {
				// Should call DB layer -- "Should not update table"

				//updateEmployee(employeeLogin);

				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.UPDATED_TO_DB);
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
			detailsFromDB = new EmployeeDetails();
			if (!StringUtils.isEmpty(detailsFromDB)) 
			{
				if(validateService.validateEmployeeDetails(employeeDetails))
				{
					populateEmployeeDetailDB(employeeDetails);
					
					// Call DB layer to save detailsFromDB
					//...
				}
				else
				{
					//Should return message invalid details
				}
			}
			else
			{
				// Should return message no record found
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		// Change the return object
		return null;
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
