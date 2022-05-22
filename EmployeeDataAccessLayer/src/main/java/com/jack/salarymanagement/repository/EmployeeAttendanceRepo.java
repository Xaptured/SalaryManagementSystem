package com.jack.salarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jack.salarymanagement.entities.EmployeeAttendance;

@Repository
public interface EmployeeAttendanceRepo extends JpaRepository<EmployeeAttendance, Integer> {

	EmployeeAttendance findByEmployeeid(Integer employeeid);
	void deleteByEmployeeid(Integer employeeid);
}
