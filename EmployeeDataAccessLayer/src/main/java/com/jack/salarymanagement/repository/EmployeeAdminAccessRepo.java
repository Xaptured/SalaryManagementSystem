package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;

@Repository
public interface EmployeeAdminAccessRepo extends JpaRepository<EmployeeAdminAccess, Integer> {

	EmployeeAdminAccess findByEmployeeid(Integer employeeid);
	void deleteByEmployeeid(Integer employeeid);
}
