package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;

public interface EmployeeAdminAccessDataAccessUtilities extends DataAccessUtilities {

	EmployeeAdminAccess saveAdminAccess(EmployeeAdminAccess eAccess);
	EmployeeAdminAccess fetchAdminAccess(Integer employeeid);
	void deleteAdminAccess(Integer employeeid);
	EmployeeAdminAccess updatAdminAccess(EmployeeAdminAccess eAccess);
}
