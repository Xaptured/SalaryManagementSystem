package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;

/**
 * @author JACK
 *
 * Utility Interface - EmployeeAdminAccessDataAccessUtilities
 */
public interface EmployeeAdminAccessDataAccessUtilities extends DataAccessUtilities {

	EmployeeAdminAccess saveAdminAccess(EmployeeAdminAccess eAccess) throws Exception;
	EmployeeAdminAccess fetchAdminAccess(Integer employeeid) throws Exception;
	void deleteAdminAccess(Integer employeeid);
	EmployeeAdminAccess updatAdminAccess(EmployeeAdminAccess eAccess);
}
