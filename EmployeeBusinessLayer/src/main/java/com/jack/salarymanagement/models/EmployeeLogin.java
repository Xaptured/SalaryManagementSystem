package com.jack.salarymanagement.models;

import org.springframework.stereotype.Component;

/**
 * @author JACK
 *
 * Model Class - EmployeeLogin
 * Stores and Autowire Employee Login Details
 * 
 * Attributes - id,username,password,employeeid,role
 */
@Component
public class EmployeeLogin {

	private int id;
	private String username;
	private String password;
	private Integer employeeid;
	private String role;//Security-1.0
	
	public EmployeeLogin() {
		super();
	}

	public EmployeeLogin(String username, String password, int employeeid, String role) {
		super();
		this.username = username;
		this.password = password;
		this.employeeid = employeeid;
		this.role = role;
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}

	@Override
	public String toString() {
		return "EmployeeLogin [id=" + id + ", username=" + username + ", password=" + password + ", employeeid="
				+ employeeid + ", role=" + role + "]";
	}
}
