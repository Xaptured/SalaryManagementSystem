package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.repository.EmployeeAdminAccessRepo;
import com.jack.salarymanagement.utilities.EmployeeAdminAccessDataAccessUtilities;

/**
 * @author JACK
 *
 * Service class - EmployeeAdminAccessService
 * Non JavaDoc Methods are unused
 */
@Service
public class EmployeeAdminAccessService implements EmployeeAdminAccessDataAccessUtilities {

	@Autowired
	private EmployeeAdminAccessRepo repo;
	
	/**
	 * Save Admin Access Details
	 * 
	 * @param eAccess
	 * @return employeeAdminAccess
	 * @throws Exception
	 */
	@Override
	public EmployeeAdminAccess saveAdminAccess(EmployeeAdminAccess eAccess) throws Exception {
		EmployeeAdminAccess employeeAdminAccess = null;
		try {
			employeeAdminAccess = repo.save(eAccess);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return employeeAdminAccess;
	}

	/**
	 * Fetch Admin Access Details
	 * 
	 * @param employeeid
	 * @return eAdminAccess
	 * @throws Exception
	 */
	@Override
	public EmployeeAdminAccess fetchAdminAccess(Integer employeeid) throws Exception {
		
		EmployeeAdminAccess eAdminAccess = null;
		try {
			eAdminAccess = repo.findByEmployeeid(employeeid);
		} 
		catch (Exception e) {
			throw new Exception();
		}
		return eAdminAccess;
	}

	@Override
	public void deleteAdminAccess(Integer employeeid) {
		repo.deleteByEmployeeid(employeeid);
	}

	@Override
	public EmployeeAdminAccess updatAdminAccess(EmployeeAdminAccess eAccess) {
		return repo.save(eAccess);
	}

}
