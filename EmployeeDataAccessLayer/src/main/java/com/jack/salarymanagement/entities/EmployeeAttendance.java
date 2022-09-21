package com.jack.salarymanagement.entities;

import java.io.Serializable;

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
 * Entity Class - EmployeeAttendance
 * Stores Employee Attendance Details
 * 
 * Attributes - id,employeeid,paidleaves,unpaidleaves 
 */
@SuppressWarnings("serial")
@Entity
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Table(name = "employee_attendance")
public class EmployeeAttendance implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "employeeid", unique = true)
	private Integer employeeid;
	@Column(name = "paidleaves")
	private int paidleaves;
	@Column(name = "unpaidleaves")
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
