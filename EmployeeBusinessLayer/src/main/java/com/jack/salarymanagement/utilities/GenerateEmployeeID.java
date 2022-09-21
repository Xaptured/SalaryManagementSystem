package com.jack.salarymanagement.utilities;

import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * @author JACK
 *
 * Ultility class - GenerateEmployeeID
 * Generation of Employee ID
 */
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

	/**
	 * Generate random employee id
	 * 
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int GenerateID(boolean condition) {
		Random randomObj = new Random();
		while (condition) {
			randomNumber = randomObj.nextInt(1000);
			if(StringUtils.isEmpty(employeeIdSet))
			{
				condition = false;
			}
			else if (!employeeIdSet.contains(randomNumber)) {
				condition = false;
			}
		}
		return randomNumber;
	}

}
