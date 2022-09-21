package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeSalary;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeSalaryInfoDataAccessUtilities
 */
public interface EmployeeSalaryInfoDataAccessUtilities extends DataAccessUtilities{

	EmployeeSalary savEmployeeSalary(EmployeeSalary employeeSalary) throws Exception;
	EmployeeSalary fetchEmployeeSalary(Integer employeeid);
	void deleteEmployeeSalary(Integer employeeid);
	EmployeeSalary updateEmployeeSalary(EmployeeSalary employeeSalary);
}
