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
 * Entity Class - EmployeeSalaryBreakDown
 * Stores Employee Salary Breakdown Details
 * 
 * Attributes - id,designation,basicsalary,increment,rent,conveyance,medical,specialpay,bonus,wfh
 */
@Entity
@Table(name = "salary_breakdown")
public class EmployeeSalaryBreakDown {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "designation")
	private String designation;
	@Column(name = "basic")
	private double basicSalary;
	@Column(name = "increment")
	private double increment;
	@Column(name = "rent")
	private double rent;
	@Column(name = "conveyance")
	private double conveyance;
	@Column(name = "medical")
	private double medical;
	@Column(name = "specialpay")
	private double specialPay;
	@Column(name = "bonus")
	private double bonus;
	@Column(name = "work_from_home")
	private double wfh;

	public EmployeeSalaryBreakDown() {
	}

	public EmployeeSalaryBreakDown(String designation, double basicSalary, double increment, double rent,
			double conveyance, double medical, double specialPay, double bonus, double wfh) {
		this.designation = designation;
		this.basicSalary = basicSalary;
		this.increment = increment;
		this.rent = rent;
		this.conveyance = conveyance;
		this.medical = medical;
		this.specialPay = specialPay;
		this.bonus = bonus;
		this.wfh = wfh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getIncrement() {
		return increment;
	}

	public void setIncrement(double increment) {
		this.increment = increment;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public double getConveyance() {
		return conveyance;
	}

	public void setConveyance(double conveyance) {
		this.conveyance = conveyance;
	}

	public double getMedical() {
		return medical;
	}

	public void setMedical(double medical) {
		this.medical = medical;
	}

	public double getSpecialPay() {
		return specialPay;
	}

	public void setSpecialPay(double specialPay) {
		this.specialPay = specialPay;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getWfh() {
		return wfh;
	}

	public void setWfh(double wfh) {
		this.wfh = wfh;
	}

	@Override
	public String toString() {
		return "EmployeeSalaryBreakDown [id=" + id + ", designation=" + designation + ", basicSalary=" + basicSalary
				+ ", increment=" + increment + ", rent=" + rent + ", conveyance=" + conveyance + ", medical=" + medical
				+ ", specialPay=" + specialPay + ", bonus=" + bonus + ", wfh=" + wfh + "]";
	}

}
