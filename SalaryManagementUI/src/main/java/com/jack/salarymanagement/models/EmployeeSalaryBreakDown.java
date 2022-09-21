package com.jack.salarymanagement.models;

/**
 * @author JACK
 *
 * Model Class - EmployeeSalaryBreakDown
 * Stores Employee Salary Breakdown Details
 * 
 * Attributes - id,designation,basicsalary,increment,rent,conveyance,medical,specialpay,bonus,wfh
 */
public class EmployeeSalaryBreakDown {

	private int id;
	private String designation;
	private double basicSalary;
	private double increment;
	private double rent;
	private double conveyance;
	private double medical;
	private double specialPay;
	private double bonus;
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
