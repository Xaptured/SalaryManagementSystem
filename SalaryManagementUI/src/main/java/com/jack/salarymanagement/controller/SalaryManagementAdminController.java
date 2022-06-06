package com.jack.salarymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.utilities.StringConstants;

@Controller
public class SalaryManagementAdminController {

	static List<String> designationList = null;
	static Integer employeeid = 0;
	
	static {
		designationList = new ArrayList<>();
		designationList.add(StringConstants.CHOOSE_DESIGNATION);
		designationList.add(StringConstants.PROGRAMMER_ANALYST_TRAINEE);
		designationList.add(StringConstants.PROGGRAMMER_ANALYST);
		designationList.add(StringConstants.ASSOSCIATE);
		designationList.add(StringConstants.SR_ASSOSCIATE);
		designationList.add(StringConstants.MANAGER);
		designationList.add(StringConstants.SR_MANAGER);
		designationList.add(StringConstants.BUSINESS_LEAD);
	}
	
	@GetMapping("/loginadmin")
	public String showLoginPage()
	{
		return "login_admin";
	}
	
	@PostMapping("/dologinadmin")
	public String doLogin(@ModelAttribute AdminLogin aLogin)
	{
		System.out.println(aLogin);
		return "redirect:/adminhome";
	}
	
	@GetMapping("/adminhome")
	public String showAdminHome(Model model)
	{
		model.addAttribute("designationList",designationList);
		model.addAttribute("employeeid", employeeid);
		return "adminhome";
	}
	
	@PostMapping("/dodesignation")
	public String doDesignation(@ModelAttribute EmployeeAdminAccess eAccess)
	{
		System.out.println(eAccess);
		return "redirect:/adminhome";
	}
	
	@PostMapping("/docalculatesalary")
	public String doCalculateSalary(@RequestParam Integer employeeid)
	{
		System.out.println(employeeid);
		return "redirect:/adminhome";
	}
}
