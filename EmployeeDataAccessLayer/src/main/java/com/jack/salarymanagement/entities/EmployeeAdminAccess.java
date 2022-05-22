package com.jack.salarymanagement.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_admin_access")
public class EmployeeAdminAccess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "employeeid", unique = true)
	private Integer employeeid;
	@Column(name = "doj")
	private Date doj;
	@Column(name = "designation")
	private String designation;

	public EmployeeAdminAccess() {
		super();
	}

	public EmployeeAdminAccess(Integer employeeid, Date doj, String designation) {
		super();
		this.employeeid = employeeid;
		this.doj = doj;
		this.designation = designation;
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

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "EmployeeAdminAccess [id=" + id + ", employeeid=" + employeeid + ", doj=" + doj + ", designation="
				+ designation + "]";
	}

}
