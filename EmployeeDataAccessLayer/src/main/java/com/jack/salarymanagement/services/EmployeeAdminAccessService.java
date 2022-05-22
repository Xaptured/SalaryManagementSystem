package com.jack.salarymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.repository.EmployeeAdminAccessRepo;
import com.jack.salarymanagement.utilities.EmployeeAdminAccessDataAccessUtilities;

@Service
public class EmployeeAdminAccessService implements EmployeeAdminAccessDataAccessUtilities {

	@Autowired
	private EmployeeAdminAccessRepo repo;
	
	@Override
	public EmployeeAdminAccess saveAdminAccess(EmployeeAdminAccess eAccess) {
		return repo.save(eAccess);
	}

	@Override
	public EmployeeAdminAccess fetchAdminAccess(Integer employeeid) {
		return repo.findByEmployeeid(employeeid);
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
