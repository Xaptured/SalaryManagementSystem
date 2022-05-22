package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;

public interface EmployeeSalaryDataAccessUtilities extends DataAccessUtilities{

	EmployeeSalaryBreakDown saveSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown);

	EmployeeSalaryBreakDown fetchSalaryBreakDown(String designation);

	void deleteSalaryBreakDown(String designation);

	EmployeeSalaryBreakDown updateSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown);
}
