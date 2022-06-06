package com.jack.salarymanagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

@Controller
public class SalaryManagementEmployeeController {

	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "signup";
	}
	
	@PostMapping("/dosignup")
	public String doSignUp(@ModelAttribute EmployeeLogin eLogin,HttpSession session)
	{
		//send eLogin to BusinessLayer
		ReturnMessage returnMessage = new ReturnMessage();
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			returnPage = "redirect:/employeehome";
		}
		else
		{
			session.setAttribute("condition", returnMessage.isValid());
			session.setAttribute("message", StringConstants.ERROR_SIGNUP);
			returnPage = "redirect:/signup";
		}
		return returnPage;
	}
	
	@GetMapping("/login")
	public String showLoginPage()
	{
		return "login";
	}
	
	@PostMapping("/dologin")
	public String doLogin(@ModelAttribute EmployeeLogin eLogin,HttpSession session)
	{
		//send eLogin to BusinessLayer
		ReturnMessage returnMessage = new ReturnMessage();
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			returnPage = "redirect:/employeehome";
		}
		else
		{
			if(returnMessage.getMessage().equals(StringConstants.INVALID_EMPLOYEE))
			{
				session.setAttribute("condition", returnMessage.isValid());
				session.setAttribute("message", StringConstants.INVALID_CREDENTIALS);
			}
			else if(returnMessage.getMessage().equals(StringConstants.EMPLOYEE_NOT_FOUND))
			{
				session.setAttribute("condition", returnMessage.isValid());
				session.setAttribute("message", StringConstants.RECORD_NOT_FOUND);
			}		
			returnPage = "redirect:/login";
		}
		return returnPage;
	}
	
	@GetMapping("/details")
	public String showDetailsPage()
	{
		return "details";
	}
	
	@PostMapping("/dodetailssubmit")
	public String doDetailsSubmit(@ModelAttribute EmployeeDetails details,HttpSession session)
	{
		//send details to BuesinessLayer
		ReturnMessage returnMessage = new ReturnMessage();
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			returnPage = "redirect:/employeehome";
		}
		else
		{
			session.setAttribute("condition", returnMessage.isValid());
			session.setAttribute("message", StringConstants.INVALID_DETAILS);
			returnPage = "redirect:/details";
		}
		return returnPage;
	}
	
	@PostMapping("/doapplyleave")
	public String doApplyLeave(HttpSession session)
	{
		//call BusinessLayer
		ReturnMessage returnMessage = new ReturnMessage();
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			session.setAttribute("condition", returnMessage.isValid());
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/employeehome";		
		}
		else
		{
			session.setAttribute("condition", returnMessage.isValid());
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/employeehome";
		}
		return returnPage;
	}
	
	@GetMapping("/employeehome")
	public String showEmployeeHome()
	{
		return "employeehome";
	}
}
