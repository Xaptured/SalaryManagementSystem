package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;
import com.jack.salarymanagement.repository.SalaryBreakDownRepo;
import com.jack.salarymanagement.utilities.EmployeeSalaryDataAccessUtilities;

@Service
public class SalaryBreakDownService implements EmployeeSalaryDataAccessUtilities {

	@Autowired
	private SalaryBreakDownRepo repo;

	@Override
	public EmployeeSalaryBreakDown saveSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown) {
		return repo.save(salaryBreakDown);
	}

	@Override
	public EmployeeSalaryBreakDown fetchSalaryBreakDown(String designation) {
		return repo.findByDesignation(designation);
	}

	@Override
	public void deleteSalaryBreakDown(String designation) {
		repo.deleteByDesignation(designation);
	}

	@Override
	public EmployeeSalaryBreakDown updateSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown) {
		return repo.save(salaryBreakDown);
	}

}
