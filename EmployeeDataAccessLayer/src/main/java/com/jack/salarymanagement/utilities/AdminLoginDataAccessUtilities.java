package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.AdminLogin;

/**
 * @author JACK
 *
 * Utility Interface - AdminLoginDataAccessUtilities
 */
public interface AdminLoginDataAccessUtilities extends DataAccessUtilities {

	AdminLogin saveAdmin(AdminLogin adminLogin);
	AdminLogin fetchAdmin(String username) throws Exception;
	void deleteAdmin(String username);
	AdminLogin updateAdmin(AdminLogin adminLogin);
}
