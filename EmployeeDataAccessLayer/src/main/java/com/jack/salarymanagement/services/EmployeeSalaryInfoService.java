package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeSalary;
import com.jack.salarymanagement.repository.EmployeeSalaryRepo;
import com.jack.salarymanagement.utilities.EmployeeSalaryInfoDataAccessUtilities;

@Service
public class EmployeeSalaryInfoService implements EmployeeSalaryInfoDataAccessUtilities {

	@Autowired
	private EmployeeSalaryRepo repo;
	
	@Override
	public EmployeeSalary savEmployeeSalary(EmployeeSalary employeeSalary) {
		return repo.save(employeeSalary);
	}

	@Override
	public EmployeeSalary fetchEmployeeSalary(Integer employeeid) {
		return repo.findByEmployeeid(employeeid);
	}

	@Override
	public void deleteEmployeeSalary(Integer employeeid) {
		repo.deleteByEmployeeid(employeeid);
	}

	@Override
	public EmployeeSalary updateEmployeeSalary(EmployeeSalary employeeSalary) {
		return repo.save(employeeSalary);
	}

}
