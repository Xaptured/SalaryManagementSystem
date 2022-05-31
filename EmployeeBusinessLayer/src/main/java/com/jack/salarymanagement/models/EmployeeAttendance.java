package com.jack.salarymanagement.models;

public class EmployeeAttendance {

	private int id;
	private Integer employeeid;
	private int paidleaves;
	private int unpaidleaves;

	public EmployeeAttendance() {
		super();
	}

	public EmployeeAttendance(Integer employeeid, int paidleaves, int unpaidleaves) {
		super();
		this.employeeid = employeeid;
		this.paidleaves = paidleaves;
		this.unpaidleaves = unpaidleaves;
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

	public int getPaidleaves() {
		return paidleaves;
	}

	public void setPaidleaves(int paidleaves) {
		this.paidleaves = paidleaves;
	}

	public int getUnpaidleaves() {
		return unpaidleaves;
	}

	public void setUnpaidleaves(int unpaidleaves) {
		this.unpaidleaves = unpaidleaves;
	}

	@Override
	public String toString() {
		return "EmployeeAttendance [id=" + id + ", employeeid=" + employeeid + ", paidleaves=" + paidleaves
				+ ", unpaidleaves=" + unpaidleaves + "]";
	}

}
