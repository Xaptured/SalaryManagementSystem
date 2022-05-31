package com.jack.salarymanagement.models;

import java.sql.Date;

public class EmployeeAdminAccess {

	private int id;
	private Integer employeeid;
	private Date dod;
	private String designation;

	public EmployeeAdminAccess() {
		super();
	}

	public EmployeeAdminAccess(Integer employeeid, Date dod, String designation) {
		super();
		this.employeeid = employeeid;
		this.dod = dod;
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

	public Date getDod() {
		return dod;
	}

	public void setDod(Date dod) {
		this.dod = dod;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "EmployeeAdminAccess [id=" + id + ", employeeid=" + employeeid + ", dod=" + dod + ", designation="
				+ designation + "]";
	}

}
