package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeSalaryBreakDown;
import com.jack.salarymanagement.repository.SalaryBreakDownRepo;
import com.jack.salarymanagement.utilities.EmployeeSalaryDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - SalaryBreakDownService
 * Non JavaDoc Methods are unused
 */
@Service
public class SalaryBreakDownService implements EmployeeSalaryDataAccessUtilities {

	@Autowired
	private SalaryBreakDownRepo repo;

	@Override
	public EmployeeSalaryBreakDown saveSalaryBreakDown(EmployeeSalaryBreakDown salaryBreakDown) {
		return repo.save(salaryBreakDown);
	}

	/**
	 * Fetch Salary Breakdown Details
	 * 
	 * @param designation
	 * @return eSalaryBreakDown
	 * @throws Exception
	 */
	@Override
	public EmployeeSalaryBreakDown fetchSalaryBreakDown(String designation) throws Exception
	{
		EmployeeSalaryBreakDown eSalaryBreakDown = null;	
		try {
			eSalaryBreakDown = repo.findByDesignation(designation);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return eSalaryBreakDown;
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
