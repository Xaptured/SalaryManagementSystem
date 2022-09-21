package com.jack.salarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeLogin;

/**
 * @author JACK
 *
 * Repository Interface - EmployeeLoginRepo
 */
@Repository
public interface EmployeeLoginRepo extends JpaRepository<EmployeeLogin, Integer> {

	EmployeeLogin findByUsername(String username);

	void deleteByUsername(String username);

	@Query(value = "select employeeid from employee_login", nativeQuery = true)
	List<Integer> findAllIDs();
}
