package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;

@Repository
public interface SalaryBreakDownRepo extends JpaRepository<EmployeeSalaryBreakDown, Integer> {

	EmployeeSalaryBreakDown findByDesignation(String designation);
	void deleteByDesignation(String designation);
}
