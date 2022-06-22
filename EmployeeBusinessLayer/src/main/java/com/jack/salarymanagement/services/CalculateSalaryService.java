package com.jack.salarymanagement.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.client.AdminClient;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.models.EmployeeAttendance;
import com.jack.salarymanagement.models.EmployeeSalary;
import com.jack.salarymanagement.models.EmployeeSalaryBreakDown;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Service
public class CalculateSalaryService {

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

	public ReturnMessage calculateSalary(Integer employeeid) 
	{
		try {
			eAdminAccess = aClient.getAdminAccessById(employeeid);
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
			
			if(eAttendance.getUnpaidleaves()>0)
			{
				double deduction = (salary/30)*eAttendance.getUnpaidleaves();
				salary = salary - deduction;
			}
			
			populateEmployeeSalary(employeeid);
			
			aClient.saveSalaryInfo(eSalary);
			
			attendanceService.updateUnpaidLeaves(eAttendance);
			attendanceService.updatePaidLeaves(eAttendance);
			
			returnMessage.setValid(true);
			returnMessage.setMessage(StringConstants.SALARY_CALCULATED);

		} catch (Exception e) {
			returnMessage.setValid(false);
			returnMessage.setMessage(StringConstants.SALARY_NOT_CALCULATED);
			//log-message
		}
		return returnMessage;
	}
	
	private void populateEmployeeSalary(Integer employeeid)
	{
		Date todayDate = Date.valueOf(LocalDate.now());
		
		eSalary.setEmployeeid(employeeid);
		eSalary.setDateOfCredit(todayDate);
		eSalary.setSalarycredit(salary);
	}
	
	private void salaryIncrement(int experience,EmployeeSalaryBreakDown eBreakDown)
	{
		for(int i=0;i<experience;i++)
		{
			salary = salary + ((salary*eBreakDown.getIncrement())%100);
		}
	}

	private void calculateExperienceDesignation(Date date) {
		LocalDate designationDate = date.toLocalDate();
		LocalDate todayDate = getTodayDate();
		findDifference(designationDate,todayDate);
	}

	private LocalDate getTodayDate() {
		return LocalDate.now();
	}

	private void findDifference(LocalDate start_date,LocalDate end_date)
	{
		 Period diff= Period.between(start_date,end_date);
		 experienceInYears = diff.getYears();
	}
}
