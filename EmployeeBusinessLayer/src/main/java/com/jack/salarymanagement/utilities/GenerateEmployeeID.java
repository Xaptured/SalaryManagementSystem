package com.jack.salarymanagement.utilities;

import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

//Move to BusinessLayer
@Component
public class GenerateEmployeeID {

	private Set<Integer> employeeIdSet;
	private int randomNumber;

	public Set<Integer> getEmployeeIdSet() {
		return employeeIdSet;
	}

	public void setEmployeeIdSet(Set<Integer> employeeIdSet) {
		this.employeeIdSet = employeeIdSet;
	}

	public int GenerateID(boolean condition) {
		Random randomObj = new Random();
		while (condition) {
			randomNumber = randomObj.nextInt(1000);
			if (!employeeIdSet.contains(randomNumber)) {
				condition = false;
			}
		}
		return randomNumber;
	}

}
