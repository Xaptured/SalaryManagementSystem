package com.jack.salarymanagement.models;

/**
 * @author JACK
 *
 * Model Class - AdminLogin
 * Stores admin login details
 * 
 * Attributes - id,username,password,role 
 */
public class AdminLogin{

	private int id;
	private String username;
	private String password;
	private String role;

	public AdminLogin() {
		super();
	}

	public AdminLogin(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminLogin [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
}
