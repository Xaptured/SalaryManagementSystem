package com.jack.salarymanagement.models;

import java.sql.Date;

import org.springframework.stereotype.Component;

/**
 * @author JACK
 *
 * Model Class - EmployeeSalary
 * Stores and Autowire Employee Salary Details
 * 
 * Attributes - id,employeeid,dateodcredit,salarycredit
 */
@Component
public class EmployeeSalary {

	private int id;
	private Integer employeeid;
	private Date dateOfCredit;
	private double salarycredit;

	public EmployeeSalary() {
		super();
	}

	public EmployeeSalary(Integer employeeid, Date dateOfCredit, double salarycredit) {
		super();
		this.employeeid = employeeid;
		this.dateOfCredit = dateOfCredit;
		this.salarycredit = salarycredit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	public Date getDateOfCredit() {
		return dateOfCredit;
	}

	public void setDateOfCredit(Date dateOfCredit) {
		this.dateOfCredit = dateOfCredit;
	}

	public double getSalarycredit() {
		return salarycredit;
	}

	public void setSalarycredit(double salarycredit) {
		this.salarycredit = salarycredit;
	}

	@Override
	public String toString() {
		return "EmployeeSalary [id=" + id + ", employeeid=" + employeeid + ", dateOfCredit=" + dateOfCredit
				+ ", salarycredit=" + salarycredit + "]";
	}
}
