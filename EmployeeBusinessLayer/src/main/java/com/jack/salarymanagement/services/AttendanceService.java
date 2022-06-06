package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class AttendanceService {

	@Autowired
	private ReturnMessage returnMessage;
	@SuppressWarnings("unused")
	private Integer globalEmployeeId;
	
	private EmployeeAttendance eAttendance;
	private EmployeeDetails eDetails;
	
	public void setGlobalEmployeeId(Integer globalEmployeeId)
	{
		this.globalEmployeeId = globalEmployeeId;
	}
	
	@SuppressWarnings("deprecation")
	public ReturnMessage applyLeave()
	{
		//Call DB layer
		eAttendance = new EmployeeAttendance();
		int updatedLeaves;
		
		try {
			if(!StringUtils.isEmpty(eAttendance))
			{
				if(eAttendance.getPaidleaves()>0)
				{
					updatedLeaves = eAttendance.getPaidleaves();
					updatedLeaves = updatedLeaves-1;
					eAttendance.setPaidleaves(updatedLeaves);
					
					//Call DB layer to save eAttendance
					//...
					
					returnMessage.setValid(true);
					returnMessage.setMessage(StringConstants.SUCCESSFUL_PAID_LEAVES);
				}
				else 
				{
					updatedLeaves = eAttendance.getUnpaidleaves();
					updatedLeaves = updatedLeaves+1;
					eAttendance.setUnpaidleaves(updatedLeaves);
					
					//Call DB layer to save eAttendance
					//...
					
					returnMessage.setValid(true);
					returnMessage.setMessage(StringConstants.SUCCESSFUL_UNPAID_LEAVES);
				}
			}
			else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);
			}
		} catch (Exception e) {
			// log-message
		}
		return returnMessage;
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
