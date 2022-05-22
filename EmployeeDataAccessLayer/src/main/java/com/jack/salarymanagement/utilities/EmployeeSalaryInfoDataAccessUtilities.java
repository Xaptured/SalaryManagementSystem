package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeSalary;

public interface EmployeeSalaryInfoDataAccessUtilities extends DataAccessUtilities{

	EmployeeSalary savEmployeeSalary(EmployeeSalary employeeSalary);
	EmployeeSalary fetchEmployeeSalary(Integer employeeid);
	void deleteEmployeeSalary(Integer employeeid);
	EmployeeSalary updateEmployeeSalary(EmployeeSalary employeeSalary);
}
