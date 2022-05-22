package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.repository.EmployeeDetailsRepo;
import com.jack.salarymanagement.utilities.EmployeeDetailsDataAccessUtilities;

@Service
public class EmployeeDetailsServie implements EmployeeDetailsDataAccessUtilities {

	@Autowired
	private EmployeeDetailsRepo repo;
	
	@Override
	public EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails) {
		return repo.save(employeeDetails);
	}

	@Override
	public EmployeeDetails fetchEmployeeDetails(Integer employeeid) {
		return repo.findByEmployeeid(employeeid);
	}

	@Override
	public void deleteEmployeeDetails(Integer employeeid) {
		repo.deleteByEmployeeid(employeeid);
	}

	@Override
	public EmployeeDetails updatEmployeeDetails(EmployeeDetails employeeDetails) {
		return repo.save(employeeDetails);
	}

}
