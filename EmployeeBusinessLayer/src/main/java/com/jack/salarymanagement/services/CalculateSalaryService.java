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
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeSalary;
import com.jack.salarymanagement.models.EmployeeSalaryBreakDown;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Service class - CalculateSalaryService
 * Calculate Salary according to the business logic 
 */
@Service
public class CalculateSalaryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CalculateSalaryService.class);

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private EmployeeSalary eSalary;
	@Autowired
	private ReturnMessage returnMessage;
	@Autowired
	private AdminClient aClient;
	
	private EmployeeAdminAccess eAdminAccess;
	private EmployeeSalaryBreakDown eSalaryBreakDown;
	private EmployeeAttendance eAttendance;
	private double salary;
	private int experienceInYears;

	/**
	 * Calculate Salary
	 * 
	 * @param employeeid
	 * @return returnMessage
	 */
	@SuppressWarnings("deprecation")
	public ReturnMessage calculateSalary(Integer employeeid) 
	{
		try {
			LOGGER.info("-----Calculate Salary Start-----");
			
			eAdminAccess = aClient.getAdminAccessById(employeeid);

			if(!StringUtils.isEmpty(eAdminAccess))
			{
				eSalaryBreakDown = aClient.getSalaryBreakDown(eAdminAccess.getDesignation());
				eAttendance = aClient.getEmployeeAttendance(employeeid);
				
				calculateExperienceDesignation(eAdminAccess.getDod());
				
				salary = eSalaryBreakDown.getBasicSalary() + eSalaryBreakDown.getRent() + eSalaryBreakDown.getConveyance()
						+ eSalaryBreakDown.getMedical() + eSalaryBreakDown.getSpecialPay() + eSalaryBreakDown.getBonus()
						+ eSalaryBreakDown.getWfh();

				if (StringConstants.PROGRAMMER_ANALYST_TRAINEE.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.PROGRAMMER_ANALYST.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.ASSOSCIATE.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.SR_ASSOSCIATE.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.MANAGER.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.SR_MANAGER.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				else if (StringConstants.BUSINESS_LEAD.equalsIgnoreCase(eAdminAccess.getDesignation()))
				{
					salaryIncrement(experienceInYears,eSalaryBreakDown);
				}
				
				if(!StringUtils.isEmpty(eAttendance))
				{
					if(eAttendance.getUnpaidleaves()>0)
					{
						double deduction = (salary/30)*eAttendance.getUnpaidleaves();
						salary = salary - deduction;
					}
					
					populateEmployeeSalary(employeeid);
					
					EmployeeSalary employeeSalary = aClient.saveSalaryInfo(eSalary);
					
					if(!StringUtils.isEmpty(employeeSalary))
					{
						attendanceService.updateUnpaidLeaves(eAttendance);
						attendanceService.updatePaidLeaves(eAttendance);
						
						returnMessage.setValid(true);
						returnMessage.setMessage(StringConstants.SALARY_SAVED);
					}
					else
					{
						returnMessage.setValid(false);
						returnMessage.setMessage(StringConstants.SALARY_NOT_SAVED);			
					}

				}
				else
				{
					returnMessage.setValid(false);
					returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);	
				}
			}
			else
			{
				returnMessage.setValid(false);
				returnMessage.setMessage(StringConstants.DETAILS_NOT_FOUND);	
			}
			
		} catch (Exception e) {
			returnMessage.setValid(false);
			returnMessage.setMessage(StringConstants.SALARY_NOT_CALCULATED);
			LOGGER.error(">>>>>Exception Occurred in calculateSalary<<<<<",e);
		}
		return returnMessage;
	}
	
	/**
	 * Populate Employee Salary Details
	 * 
	 * @param employeeid
	 */
	private void populateEmployeeSalary(Integer employeeid)
	{
		Date todayDate = Date.valueOf(LocalDate.now());
		
		eSalary.setEmployeeid(employeeid);
		eSalary.setDateOfCredit(todayDate);
		eSalary.setSalarycredit(salary);
	}
	
	/**
	 * Calculate Salary Increment
	 * 
	 * @param experience
	 * @param eBreakDown
	 */
	private void salaryIncrement(int experience,EmployeeSalaryBreakDown eBreakDown)
	{
		for(int i=0;i<experience;i++)
		{
			salary = salary + ((salary*eBreakDown.getIncrement())%100);
		}
	}

	/**
	 * Calculate Experience
	 * 
	 * @param date
	 */
	private void calculateExperienceDesignation(Date date) {
		LocalDate designationDate = date.toLocalDate();
		LocalDate todayDate = getTodayDate();
		findDifference(designationDate,todayDate);
	}

	/**
	 * Get today's date
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
	 */
	private void findDifference(LocalDate start_date,LocalDate end_date)
	{
		 Period diff= Period.between(start_date,end_date);
		 experienceInYears = diff.getYears();
	}
}
