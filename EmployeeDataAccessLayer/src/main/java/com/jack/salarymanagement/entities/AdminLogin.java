package com.jack.salarymanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JACK
 *
 * Entity Class - AdminLogin
 * Stores admin login details
 * 
 * Attributes - id,username,password,role 
 */
@Entity
@Table(name = "admin_login")
public class AdminLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username",unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;//Security-1.0

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
