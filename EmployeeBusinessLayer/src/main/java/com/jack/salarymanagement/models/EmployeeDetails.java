package com.jack.salarymanagement.models;

import java.sql.Date;

import org.springframework.stereotype.Component;

/**
 * @author JACK
 *
 * Model Class - EmployeeDetails
 * Stores and Autowire Employee Details
 * 
 * Attributes - id,employeeid,name,mail,phno,location,pan,bankacc,doj,experience 
 */
@Component
public class EmployeeDetails {

	private int id;
	private Integer employeeid;
	private String name;
	private String mail;
	private String phno;
	private String location;
	private String pan;
	private String bankacc;
	private Date doj;
	private int experience;

	public EmployeeDetails() {
		super();
	}

	public EmployeeDetails(Integer employeeid, String name, String mail, String phno, String location, String pan,
			String bankacc, Date doj, int experience) {
		super();
		this.employeeid = employeeid;
		this.name = name;
		this.mail = mail;
		this.phno = phno;
		this.location = location;
		this.pan = pan;
		this.bankacc = bankacc;
		this.doj = doj;
		this.experience = experience;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBankacc() {
		return bankacc;
	}

	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "EmployeeDetails [id=" + id + ", employeeid=" + employeeid + ", name=" + name + ", mail=" + mail
				+ ", phno=" + phno + ", location=" + location + ", pan=" + pan + ", bankacc=" + bankacc + ", doj=" + doj
				+ ", experience=" + experience + "]";
	}
}
