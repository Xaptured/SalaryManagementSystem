package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.repository.EmployeeAttendanceRepo;
import com.jack.salarymanagement.utilities.EmployeeAttendanceDataAccessUtilities;

@Service
public class EmployeeAttendanceServie implements EmployeeAttendanceDataAccessUtilities {

	@Autowired
	private EmployeeAttendanceRepo repo;
	
	@Override
	public EmployeeAttendance savEmployeeAttendance(EmployeeAttendance eAttendance) {
		return repo.save(eAttendance);
	}

	@Override
	public EmployeeAttendance fetchEmployeeAttendance(Integer employeeid) {
		return repo.findByEmployeeid(employeeid);
	}

	@Override
	public void deleteEmployeeAttendance(Integer employeeid) {
		repo.deleteByEmployeeid(employeeid);
	}

	@Override
	public EmployeeAttendance updateEmployeeAttendance(EmployeeAttendance eAttendance) {
		return repo.save(eAttendance);
	}

}
