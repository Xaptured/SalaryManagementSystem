package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeDetails;

public interface EmployeeDetailsDataAccessUtilities extends DataAccessUtilities {

	EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);
	EmployeeDetails fetchEmployeeDetails(Integer employeeid);
	void deleteEmployeeDetails(Integer employeeid);
	EmployeeDetails updatEmployeeDetails(EmployeeDetails employeeDetails);
}
