package com.jack.salarymanagement.models;

public class EmployeeLogin {

	private int id;
	private String username;
	private String password;
	private Integer employeeid;

	public EmployeeLogin() {
		super();
	}

	public EmployeeLogin(String username, String password, int employeeid) {
		super();
		this.username = username;
		this.password = password;
		this.employeeid = employeeid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	@Override
	public String toString() {
		return "EmployeeLogin [id=" + id + ", username=" + username + ", password=" + password + ", employeeid="
				+ employeeid + "]";
	}

}
