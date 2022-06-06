package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class ValidateService {

	@Autowired
	private ReturnMessage returnMessage;

	@SuppressWarnings("deprecation")
	public ReturnMessage isValidAdminWithMessage(AdminLogin adminLogin) {
		try {
			// Should call DB layer
			AdminLogin detailsFromDB = new AdminLogin();

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
			// log-message
		}
		return returnMessage;
	}

	private boolean isValidAdmin(AdminLogin adminLogin, AdminLogin adminLoginFromDB) {
		if (adminLoginFromDB.getPassword().equals(adminLogin.getPassword())
				&& adminLoginFromDB.getSectretkey().equals(adminLogin.getSectretkey())) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public ReturnMessage isValidEmployeeWithMessage(EmployeeLogin employeeLogin) {
		try {
			// Should call DB layer
			EmployeeLogin detailsFromDB = new EmployeeLogin();

			if (!StringUtils.isEmpty(detailsFromDB)) {
				boolean isValidEmployee = isValidEmployee(employeeLogin, detailsFromDB);
				if (isValidEmployee) {
					returnMessage.setValid(isValidEmployee);
					returnMessage.setMessage(StringConstants.VALID_EMPLOYEE);
				} else {
					returnMessage.setValid(isValidEmployee);
					returnMessage.setMessage(StringConstants.INVALID_EMPLOYEE);
				}
			} else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.EMPLOYEE_NOT_FOUND);
			}
		} catch (Exception e) {
			// log-message
		}
		return returnMessage;
	}

	private boolean isValidEmployee(EmployeeLogin employeeLogin, EmployeeLogin employeeLoginFromDB) {
		if (employeeLoginFromDB.getPassword().equals(employeeLogin.getPassword())) {
			return true;
		}
		return false;
	}

	public boolean validateEmployeeDetails(EmployeeDetails employeeDetails) {
		// Should validate according to criteria
		return true;
	}

}
