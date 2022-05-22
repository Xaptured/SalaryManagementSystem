package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.entities.AdminLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.repository.AdminLoginRepo;
import com.jack.salarymanagement.utilities.AdminLoginDataAccessUtilities;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class AdminLoginService implements AdminLoginDataAccessUtilities {

	@Autowired
	private AdminLoginRepo repo;
	@Autowired
	private ReturnMessage returnMessage;
	
	@Override
	public AdminLogin saveAdmin(AdminLogin adminLogin) {
		return repo.save(adminLogin);
	}

	@Override
	public AdminLogin fetchAdmin(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void deleteAdmin(String username) {
		repo.deleteByUsername(username);
	}

	@Override
	public AdminLogin updateAdmin(AdminLogin adminLogin) {
		return repo.save(adminLogin);
	}
	
	// Move to BusinessLayer
	@SuppressWarnings("deprecation")
	public ReturnMessage isValidAdminWithMessage(AdminLogin adminLogin)
	{
		AdminLogin detailsFromDB = fetchAdmin(adminLogin.getUsername());
		if(!StringUtils.isEmpty(detailsFromDB))
		{
			boolean isValidAdmin = isValidAdmin(adminLogin,detailsFromDB);
			if(isValidAdmin)
			{
				returnMessage.setValid(isValidAdmin);
				returnMessage.setMessage(StringConstants.VALID_ADMIN);
				return returnMessage;
			}
			else
			{
				returnMessage.setValid(isValidAdmin);
				returnMessage.setMessage(StringConstants.INVALID_ADMIN);
				return returnMessage;
			}
		}
		else
		{
			returnMessage.setValid(false);
			returnMessage.setMessage(StringConstants.ADMIN_NOT_FOUND);
			return returnMessage;
		}
	}
	
	// Move to BusinessLayer
	private boolean isValidAdmin(AdminLogin adminLogin,AdminLogin adminLoginFromDB)
	{
		if(adminLoginFromDB.getPassword().equals(adminLogin.getPassword())&&
				adminLoginFromDB.getSectretkey().equals(adminLogin.getSectretkey()))
		{
			return true;
		}
		return false;
	}

}
