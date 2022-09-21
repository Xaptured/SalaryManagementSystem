package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.AdminLogin;
import com.jack.salarymanagement.repository.AdminLoginRepo;
import com.jack.salarymanagement.utilities.AdminLoginDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - AdminLoginService
 * Non JavaDoc Methods are unused
 */
@Service
public class AdminLoginService implements AdminLoginDataAccessUtilities {

	@Autowired
	private AdminLoginRepo repo;

	@Override
	public AdminLogin saveAdmin(AdminLogin adminLogin) {
		return repo.save(adminLogin);
	}

	/**
	 * Fetch Admin Details
	 * 
	 * @param username
	 * @return adminLogin
	 * @throws Exception
	 */
	@Override
	public AdminLogin fetchAdmin(String username) throws Exception {
		AdminLogin adminLogin = null;
		try {
			adminLogin = repo.findByUsername(username);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return adminLogin;
	}

	@Override
	public void deleteAdmin(String username) {
		repo.deleteByUsername(username);
	}

	@Override
	public AdminLogin updateAdmin(AdminLogin adminLogin) {
		return repo.save(adminLogin);
	}

}
