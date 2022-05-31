package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.entities.EmployeeAdminAccess;
import com.jack.salarymanagement.entities.EmployeeAttendance;
import com.jack.salarymanagement.entities.EmployeeDetails;
import com.jack.salarymanagement.entities.EmployeeLogin;
import com.jack.salarymanagement.repository.EmployeeLoginRepo;
import com.jack.salarymanagement.utilities.EmployeeAdminAccessDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeAttendanceDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeDetailsDataAccessUtilities;
import com.jack.salarymanagement.utilities.EmployeeLoginDataAccessUtilities;

@Service
public class EmployeeLoginService implements EmployeeLoginDataAccessUtilities {

	@Autowired
	private EmployeeLoginRepo repo;
	@Autowired
	private EmployeeDetails eDetails;
	@Autowired
	private EmployeeAdminAccess eAdminAccess;
	@Autowired
	private EmployeeAttendance eAttendance;
	@Autowired
	private EmployeeDetailsDataAccessUtilities eDetailUtilities;
	@Autowired
	private EmployeeAdminAccessDataAccessUtilities eAdminUtilities;
	@Autowired
	private EmployeeAttendanceDataAccessUtilities eAttendanceUtilities;

	@Override
	public EmployeeLogin saveEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	@Override
	public EmployeeLogin fetchEmployee(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void deleteEmployee(String username) {
		repo.deleteByUsername(username);
	}

	@Override
	public EmployeeLogin updateEmployee(EmployeeLogin employeeLogin) {
		return repo.save(employeeLogin);
	}

	@Override
	public List<Integer> fetchEmployeeIds() {
		return repo.findAllIDs();
	}

	public void saveEmployeeSignupDetails(EmployeeLogin employeeLogin) {
		try {
			populateEmployeeDetails(employeeLogin);
			populateAdminAcess(employeeLogin);
			populateEmployeeAttendance(employeeLogin.getEmployeeid());

			saveEmployee(employeeLogin);
			eDetailUtilities.saveEmployeeDetails(eDetails);
			eAdminUtilities.saveAdminAccess(eAdminAccess);
			eAttendanceUtilities.savEmployeeAttendance(eAttendance);
		} catch (Exception e) {
			// log-message
		}
	}
	
	private void populateAdminAcess(EmployeeLogin employeeLogin)
	{
		eAdminAccess.setEmployeeid(employeeLogin.getEmployeeid());
	}

	private void populateEmployeeDetails(EmployeeLogin employeeLogin) {
		eDetails.setEmployeeid(employeeLogin.getEmployeeid());
		eDetails.setDoj(getCurrentDate());
		eDetails.setExperience(0);
	}

	private void populateEmployeeAttendance(Integer employeeid) {
		eAttendance.setEmployeeid(employeeid);
		eAttendance.setPaidleaves(22);
		eAttendance.setUnpaidleaves(0);
	}

	private Date getCurrentDate() {
		Date date = Date.valueOf(LocalDate.now());
		return date;
	}

}
