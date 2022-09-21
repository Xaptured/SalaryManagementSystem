package com.jack.salarymanagement.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author JACK
 *
 * Entity Class - EmployeeAdminAccess
 * Stores Employee Designation Details set by the Admin
 * 
 * Attributes - id,employeeid,dod,designation 
 */
@SuppressWarnings("serial")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "employee_admin_access")
public class EmployeeAdminAccess implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "employeeid", unique = true)
	private Integer employeeid;
	@Column(name = "dod")
	private Date dod;
	@Column(name = "designation")
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
