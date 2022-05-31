package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.AdminLogin;
import com.jack.salarymanagement.repository.AdminLoginRepo;
import com.jack.salarymanagement.utilities.AdminLoginDataAccessUtilities;

@Service
public class AdminLoginService implements AdminLoginDataAccessUtilities {

	@Autowired
	private AdminLoginRepo repo;

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

}
