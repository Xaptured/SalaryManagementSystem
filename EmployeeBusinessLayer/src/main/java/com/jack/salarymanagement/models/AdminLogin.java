package com.jack.salarymanagement.models;

public class AdminLogin {

	private int id;
	private String username;
	private String password;
	private String sectretkey;

	public AdminLogin() {
		super();
	}

	public AdminLogin(String username, String password, String sectretkey) {
		super();
		this.username = username;
		this.password = password;
		this.sectretkey = sectretkey;
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

	public String getSectretkey() {
		return sectretkey;
	}

	public void setSectretkey(String sectretkey) {
		this.sectretkey = sectretkey;
	}

	@Override
	public String toString() {
		return "AdminLogin [username=" + username + ", password=" + password + ", sectretkey=" + sectretkey + "]";
	}

}
