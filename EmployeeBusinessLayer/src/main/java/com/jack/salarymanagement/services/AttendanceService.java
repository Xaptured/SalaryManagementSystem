package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;

@Service
public class AttendanceService {

	private EmployeeAttendance eAttendance;
	private EmployeeDetails eDetails;
	
	public void applyLeave(Integer employeeid)
	{
		//Call DB layer
		eAttendance = new EmployeeAttendance();
		int updatedLeaves;
		
		if(eAttendance.getPaidleaves()>0)
		{
			updatedLeaves = eAttendance.getPaidleaves();
			updatedLeaves = updatedLeaves-1;
			eAttendance.setPaidleaves(updatedLeaves);
			
			//Call DB layer to save eAttendance
			//...
		}
		else 
		{
			updatedLeaves = eAttendance.getUnpaidleaves();
			updatedLeaves = updatedLeaves+1;
			eAttendance.setUnpaidleaves(updatedLeaves);
			
			//Call DB layer to save eAttendance
			//...
		}
	}
	
	public void updateUnpaidLeaves(EmployeeAttendance employeeAttendance)
	{
		// Update EmployeeAttendance Table when salary generates
		employeeAttendance.setUnpaidleaves(0);
		// Call DB layer to save employeeAttendance
		//...
	}
	
	public void updatePaidLeaves(EmployeeAttendance employeeAttendance)
	{
		// Call DB layer
		eDetails = new EmployeeDetails();
		int experience = calculateExperience(eDetails.getDoj());
		if(experience != eDetails.getExperience())
		{
			// Update EmployeeAttendance Table when salary generates
			employeeAttendance.setPaidleaves(22);
			eDetails.setExperience(experience);
			// Call DB layer to save employeeAttendance and eDetails
			//...
		}
	}
	
	private int calculateExperience(Date date)
	{
		LocalDate designationDate = date.toLocalDate();
		LocalDate todayDate = getTodayDate();
		return findDifference(designationDate,todayDate);
	}
	
	private LocalDate getTodayDate() {
		return LocalDate.now();
	}
	
	private int findDifference(LocalDate start_date,LocalDate end_date)
	{
		 Period diff= Period.between(start_date,end_date);
		 int experienceInYears = diff.getYears();
		 return experienceInYears;
	}
}
