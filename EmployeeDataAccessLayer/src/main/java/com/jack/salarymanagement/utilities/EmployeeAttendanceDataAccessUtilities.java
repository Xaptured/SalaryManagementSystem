package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeAttendance;

public interface EmployeeAttendanceDataAccessUtilities extends DataAccessUtilities {

	EmployeeAttendance savEmployeeAttendance(EmployeeAttendance eAttendance);
	EmployeeAttendance fetchEmployeeAttendance(Integer employeeid);
	void deleteEmployeeAttendance(Integer employeeid);
	EmployeeAttendance updateEmployeeAttendance(EmployeeAttendance eAttendance);
}
