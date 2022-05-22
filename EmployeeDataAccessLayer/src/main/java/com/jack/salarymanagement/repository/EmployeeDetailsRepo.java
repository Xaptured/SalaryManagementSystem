package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer> {

	EmployeeDetails findByEmployeeid(Integer employeeid);
	void deleteByEmployeeid(Integer employeeid);
}
