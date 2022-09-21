package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeDetails;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeDetailsDataAccessUtilities
 */
public interface EmployeeDetailsDataAccessUtilities extends DataAccessUtilities {

	void saveEmployeeDetails(EmployeeDetails employeeDetails) throws Exception;
	EmployeeDetails fetchEmployeeDetails(Integer employeeid) throws Exception;
	void deleteEmployeeDetails(Integer employeeid);
	EmployeeDetails updatEmployeeDetails(EmployeeDetails employeeDetails);
}
