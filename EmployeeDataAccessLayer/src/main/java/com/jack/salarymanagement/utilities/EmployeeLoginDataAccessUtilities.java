package com.jack.salarymanagement.utilities;

import java.util.List;

import com.jack.salarymanagement.entities.EmployeeLogin;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeLoginDataAccessUtilities
 */
public interface EmployeeLoginDataAccessUtilities extends DataAccessUtilities{

	EmployeeLogin saveEmployee(EmployeeLogin employeeLogin);
	EmployeeLogin fetchEmployee(String username) throws Exception;
	void deleteEmployee(String username);
	EmployeeLogin updateEmployee(EmployeeLogin employeeLogin);
	List<Integer> fetchEmployeeIds() throws Exception;
}
