package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.AdminLogin;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLogin, Integer> {

	AdminLogin findByUsername(String username);
	void deleteByUsername(String userName);
}
