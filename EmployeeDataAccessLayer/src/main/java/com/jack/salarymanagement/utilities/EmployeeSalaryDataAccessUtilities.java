package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeSalaryDataAccessUtilities
 */
public interface EmployeeSalaryDataAccessUtilities extends DataAccessUtilities{

	EmployeeSalaryBreakDown saveSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown);

	EmployeeSalaryBreakDown fetchSalaryBreakDown(String designation) throws Exception;

	void deleteSalaryBreakDown(String designation);

	EmployeeSalaryBreakDown updateSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown);
}
