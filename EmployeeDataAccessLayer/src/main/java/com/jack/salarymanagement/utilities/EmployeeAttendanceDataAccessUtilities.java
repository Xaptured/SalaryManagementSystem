package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeAttendance;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeAttendanceDataAccessUtilities
 */
public interface EmployeeAttendanceDataAccessUtilities extends DataAccessUtilities {

	EmployeeAttendance savEmployeeAttendance(EmployeeAttendance eAttendance) throws Exception;
	EmployeeAttendance fetchEmployeeAttendance(Integer employeeid) throws Exception;
	void deleteEmployeeAttendance(Integer employeeid);
	EmployeeAttendance updateEmployeeAttendance(EmployeeAttendance eAttendance);
}
