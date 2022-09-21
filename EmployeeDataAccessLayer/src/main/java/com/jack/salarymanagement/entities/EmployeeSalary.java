package com.jack.salarymanagement.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JACK
 *
 * Entity Class - EmployeeSalary
 * Stores Employee Salary Details
 * 
 * Attributes - id,employeeid,dateodcredit,salarycredit
 */
@Entity
@Table(name = "employee_salary")
public class EmployeeSalary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "employeeid")
	private Integer employeeid;
	@Column(name = "dateofcredit")
	private Date dateOfCredit;
	@Column(name = "salarycredit")
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
