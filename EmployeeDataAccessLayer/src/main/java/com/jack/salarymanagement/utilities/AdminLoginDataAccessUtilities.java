package com.jack.salarymanagement.utilities;

import com.jack.salarymanagement.entities.AdminLogin;

public interface AdminLoginDataAccessUtilities extends DataAccessUtilities {

	AdminLogin saveAdmin(AdminLogin adminLogin);
	AdminLogin fetchAdmin(String username);
	void deleteAdmin(String username);
	AdminLogin updateAdmin(AdminLogin adminLogin);
}
