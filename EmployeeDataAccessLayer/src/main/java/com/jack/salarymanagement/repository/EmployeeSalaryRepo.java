package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeSalary;

/**
 * @author JACK
 *
 * Repository Interface - EmployeeSalaryRepo
 */
@Repository
public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, Integer> {

	EmployeeSalary findByEmployeeid(Integer employeeid);
	void deleteByEmployeeid(Integer employeeid);
}
