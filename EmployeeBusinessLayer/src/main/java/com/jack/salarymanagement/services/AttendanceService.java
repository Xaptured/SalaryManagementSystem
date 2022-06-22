package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.client.AdminClient;
import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class AttendanceService {

	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private AdminClient aClient;
	
	private EmployeeAttendance eAttendance;
	private EmployeeDetails eDetails;
	
	@SuppressWarnings("deprecation")
	public ReturnMessage applyLeave()
	{
		Integer employeeid = null;
		if(EmployeeService.getGlobalEmployeeid()!=0)
		{
			employeeid = EmployeeService.getGlobalEmployeeid();
		}
		else
		{
			employeeid = ValidateService.getGlobalEmployeeid();
		}
		eAttendance = aClient.getEmployeeAttendance(employeeid);
		int updatedLeaves;
		
		try {
			if(!StringUtils.isEmpty(eAttendance))
			{
				if(eAttendance.getPaidleaves()>0)
				{
					updatedLeaves = eAttendance.getPaidleaves();
					updatedLeaves = updatedLeaves-1;
					eAttendance.setPaidleaves(updatedLeaves);
					
					aClient.saveEmployeeAttendance(eAttendance);
					
					returnMessage.setValid(true);
					returnMessage.setMessage(StringConstants.SUCCESSFUL_PAID_LEAVES);
				}
				else 
				{
					updatedLeaves = eAttendance.getUnpaidleaves();
					updatedLeaves = updatedLeaves+1;
					eAttendance.setUnpaidleaves(updatedLeaves);
					
					aClient.saveEmployeeAttendance(eAttendance);
					
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
		employeeAttendance.setUnpaidleaves(0);
		aClient.saveEmployeeAttendance(employeeAttendance);
	}
	
	public void updatePaidLeaves(EmployeeAttendance employeeAttendance)
	{
		eDetails = aClient.getEmployeeDetails(employeeAttendance.getEmployeeid());
		int experience = calculateExperience(eDetails.getDoj());
		if(experience != eDetails.getExperience())
		{
			employeeAttendance.setPaidleaves(22);
			eDetails.setExperience(experience);
			aClient.saveEmployeeAttendance(employeeAttendance);
			aClient.saveEmployeeDetails(eDetails);
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
