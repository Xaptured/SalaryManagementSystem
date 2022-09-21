package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.repository.EmployeeAttendanceRepo;
import com.jack.salarymanagement.utilities.EmployeeAttendanceDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - EmployeeAttendanceServie
 * Non JavaDoc Methods are unused
 */
@Service
public class EmployeeAttendanceServie implements EmployeeAttendanceDataAccessUtilities {

	@Autowired
	private EmployeeAttendanceRepo repo;
	
	/**
	 * Save Employee Attendance
	 * 
	 * @param eAttendance
	 * @return employeeAttendance
	 * @throws Exception
	 */
	@Override
	public EmployeeAttendance savEmployeeAttendance(EmployeeAttendance eAttendance) throws Exception {
		EmployeeAttendance employeeAttendance = null;
		try {
			employeeAttendance = repo.save(eAttendance);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return employeeAttendance;
	}

	
	/**
	 * Fetch Employee Attendance
	 * 
	 * @param employeeid
	 * @return eAttendance
	 * @throws Exception
	 */
	@Override
	public EmployeeAttendance fetchEmployeeAttendance(Integer employeeid) throws Exception {
		EmployeeAttendance eAttendance = null;
		try {
			eAttendance = repo.findByEmployeeid(employeeid);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return eAttendance;
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
