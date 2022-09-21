package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jack.salarymanagement.client.AdminClient;
import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Service class - AttendanceService
 * Process Attendance for Employee and also manages Paid and Unpaid Leaves 
 */
@Service
public class AttendanceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceService.class);
	
	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private AdminClient aClient;
	private EmployeeAttendance eAttendance;
	private EmployeeDetails eDetails;
	
	/**
	 * Process Apply Leave
	 * 
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage applyLeave()
	{
		Integer employeeid = null;
		
		//Fetch current Logged In User's EmployeeId 
		if(EmployeeService.getGlobalEmployeeid()!=0)
		{
			employeeid = EmployeeService.getGlobalEmployeeid();
		}
		else
		{
			employeeid = ValidateService.getGlobalEmployeeid();
		}
		
		// get EmployeeAttendance Details
		eAttendance = aClient.getEmployeeAttendance(employeeid);
		int updatedLeaves;
		
		try {
			if(!StringUtils.isEmpty(eAttendance))
			{
				//Paid Leaves
				if(eAttendance.getPaidleaves()>0)
				{
					updatedLeaves = eAttendance.getPaidleaves();
					updatedLeaves = updatedLeaves-1;
					eAttendance.setPaidleaves(updatedLeaves);
					
					EmployeeAttendance employeeAttendance = aClient.saveEmployeeAttendance(eAttendance);
					
					if(!StringUtils.isEmpty(employeeAttendance))
					{
						returnMessage.setValid(true);
						returnMessage.setMessage(StringConstants.PAID_LEAVES_SAVED);
					}
					else
					{
						returnMessage.setValid(false);
						returnMessage.setMessage(StringConstants.PAID_LEAVES_NOT_SAVED);
					}
					
				}
				//Unpaid Leaves
				else 
				{
					updatedLeaves = eAttendance.getUnpaidleaves();
					updatedLeaves = updatedLeaves+1;
					eAttendance.setUnpaidleaves(updatedLeaves);
					
					EmployeeAttendance employeeAttendance = aClient.saveEmployeeAttendance(eAttendance);
					
					if(!StringUtils.isEmpty(employeeAttendance))
					{
						returnMessage.setValid(true);
						returnMessage.setMessage(StringConstants.UNPAID_LEAVES_SAVED);
					}
					else
					{
						returnMessage.setValid(false);
						returnMessage.setMessage(StringConstants.UNPAID_LEAVES_NOT_SAVED);
					}
					
				}
			}
			else {
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);
			}
		} catch (Exception e) {
			LOGGER.error(">>>>>Exception Occurred in applyLeave<<<<<", e);
		}
		return returnMessage;
	}
	
	/**
	 * Update Unpaid Leaves
	 * 
	 * @param employeeAttendance
	 */
	public void updateUnpaidLeaves(EmployeeAttendance employeeAttendance)
	{
		employeeAttendance.setUnpaidleaves(0);
		aClient.saveEmployeeAttendance(employeeAttendance);
	}
	
	/**
	 * Update Paid Leaves
	 * 
	 * @param employeeAttendance
	 */
	@SuppressWarnings("deprecation")
	public void updatePaidLeaves(EmployeeAttendance employeeAttendance)
	{
		eDetails = aClient.getEmployeeDetails(employeeAttendance.getEmployeeid());

		if(!StringUtils.isEmpty(eDetails))
		{
			int experience = calculateExperience(eDetails.getDoj());
			if(experience != eDetails.getExperience())
			{
				employeeAttendance.setPaidleaves(22);
				eDetails.setExperience(experience);
				aClient.saveEmployeeAttendance(employeeAttendance);
				aClient.saveEmployeeDetails(eDetails);
			}
		}		
	}
	
	/**
	 * Calculate Experience
	 * 
	 * @param date
	 * @return experienceInYears
	 */
	private int calculateExperience(Date date)
	{
		LocalDate designationDate = date.toLocalDate();
		LocalDate todayDate = getTodayDate();
		return findDifference(designationDate,todayDate);
	}
	
	/**
	 * Get Today Date
	 * 
	 * @return date
	 */
	private LocalDate getTodayDate() {
		return LocalDate.now();
	}
	
	/**
	 * Difference between start date and end date
	 * 
	 * @param start_date
	 * @param end_date
	 * @return experienceInYears
	 */
	private int findDifference(LocalDate start_date,LocalDate end_date)
	{
		 Period diff= Period.between(start_date,end_date);
		 int experienceInYears = diff.getYears();
		 return experienceInYears;
	}
}
