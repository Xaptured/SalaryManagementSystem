package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeSalary;
import com.jack.salarymanagement.repository.EmployeeSalaryRepo;
import com.jack.salarymanagement.utilities.EmployeeSalaryInfoDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - EmployeeSalaryInfoService
 * Non JavaDoc Methods are unused
 */
@Service
public class EmployeeSalaryInfoService implements EmployeeSalaryInfoDataAccessUtilities {

	@Autowired
	private EmployeeSalaryRepo repo;
	
	/**
	 * Save Employee Salary Details
	 * 
	 * @param employeeSalary
	 * @return eSalary
	 * @throws Exception
	 */
	@Override
	public EmployeeSalary savEmployeeSalary(EmployeeSalary employeeSalary) throws Exception{
		EmployeeSalary eSalary = null;
		try {
			eSalary = repo.save(employeeSalary);
		}
		catch (Exception e) {
			throw new Exception();
		}
		return eSalary;
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
