package com.jack.salarymanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.client.AdminClient;
//import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Service class - ValidateService
 * Validate Admin Details
 */
@Service
public class ValidateService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateService.class);
	
	@Autowired
	private ReturnMessage returnMessage;
//	@Autowired
//	private EmployeeClient eClient;
	@Autowired
	private AdminClient aClient;
	
	private static Integer globalEmployeeid=0;
	
	public static Integer getGlobalEmployeeid() {
		return globalEmployeeid;
	}

	/**
	 * Checks for the Valid Admin
	 * 
	 * @param adminLogin
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage isValidAdminWithMessage(AdminLogin adminLogin) {
		try {
			LOGGER.info("-----Validate Admin-----");
			AdminLogin detailsFromDB = aClient.getAdminLoginDetailsByUsername(adminLogin.getUsername());

			if (!StringUtils.isEmpty(detailsFromDB)) {
				boolean isValidAdmin = isValidAdmin(adminLogin, detailsFromDB);
				if (isValidAdmin) {
					returnMessage.setValid(isValidAdmin);
					returnMessage.setMessage(StringConstants.VALID_ADMIN);
				} else {
					returnMessage.setValid(isValidAdmin);
					returnMessage.setMessage(StringConstants.INVALID_ADMIN);
				}
			} else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.ADMIN_NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error(">>>>>Exception occurred in isValidAdminWithMessage<<<<<",e);
		}
		return returnMessage;
	}

	/**
	 * Validate Admin
	 * 
	 * @param adminLogin
	 * @param adminLoginFromDB
	 * @return boolean
	 */
	private boolean isValidAdmin(AdminLogin adminLogin, AdminLogin adminLoginFromDB) {
		if (adminLoginFromDB.getPassword().equals(adminLogin.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * @param employeeLogin
	 * @return
	 */
//	@SuppressWarnings("deprecation")
//	public ReturnMessage isValidEmployeeWithMessage(EmployeeLogin employeeLogin) {
//		try {
//			EmployeeLogin detailsFromDB = eClient.getEmployeeLoginByUserName(employeeLogin.getUsername());
//
//			if (!StringUtils.isEmpty(detailsFromDB)) {
//				boolean isValidEmployee = isValidEmployee(employeeLogin, detailsFromDB);
//				if (isValidEmployee) {
//					globalEmployeeid = detailsFromDB.getEmployeeid();
//					returnMessage.setValid(isValidEmployee);
//					returnMessage.setMessage(StringConstants.VALID_EMPLOYEE);
//				} else {
//					returnMessage.setValid(isValidEmployee);
//					returnMessage.setMessage(StringConstants.INVALID_EMPLOYEE);
//				}
//			} else {
//				returnMessage.setValid(false);
//				returnMessage.setMessage(StringConstants.EMPLOYEE_NOT_FOUND);
//			}
//		} catch (Exception e) {
//			// log-message
//		}
//		return returnMessage;
//	}

	/**
	 * @param employeeLogin
	 * @param employeeLoginFromDB
	 * @return
	 */
//	private boolean isValidEmployee(EmployeeLogin employeeLogin, EmployeeLogin employeeLoginFromDB) {
//		if (employeeLoginFromDB.getPassword().equals(employeeLogin.getPassword())) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * Validate Employee Details
	 * 
	 * @param employeeDetails
	 * @return boolean
	 */
	public boolean validateEmployeeDetails(EmployeeDetails employeeDetails) {
		// Should validate according to criteria
		return true;
	}

}
