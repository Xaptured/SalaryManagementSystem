package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.repository.EmployeeDetailsRepo;
import com.jack.salarymanagement.utilities.EmployeeDetailsDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - EmployeeDetailsServie
 * Non JavaDoc Methods are unused
 */
@Service
public class EmployeeDetailsServie implements EmployeeDetailsDataAccessUtilities {

	@Autowired
	private EmployeeDetailsRepo repo;
	
	/**
	 * Save Employee Details 
	 * 
	 * @param employeeDetails
	 * @throws Exception
	 */
	@Override
	public void saveEmployeeDetails(EmployeeDetails employeeDetails) throws Exception {
		try {
			repo.save(employeeDetails);
		} 
		catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * Fetch Employee Details
	 * 
	 * @param employeeid
	 * @return eDetails
	 * @throws Exception
	 */
	@Override
	public EmployeeDetails fetchEmployeeDetails(Integer employeeid) throws Exception {
		EmployeeDetails eDetails = null;
		try {
			eDetails = repo.findByEmployeeid(employeeid);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return eDetails;
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
