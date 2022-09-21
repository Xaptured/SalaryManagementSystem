package com.jack.salarymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jack.salarymanagement.client.AdminClient;
import com.jack.salarymanagement.models.AdminLogin;
import com.jack.salarymanagement.models.EmployeeAdminAccess;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 * 
 * Controller class - Handles the Admin Activities
 */
@Controller
public class SalaryManagementAdminController {

	private static final Logger logger = LoggerFactory.getLogger(SalaryManagementAdminController.class);

	@Autowired
	private AdminClient aClient;

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
	
	/**
	 * API call to get Admin Login Page
	 * 
	 * @return login_admin page
	 */
	@RequestMapping(value = "/loginadmin", method = { RequestMethod.GET, RequestMethod.POST })
	public String showLoginPage()
	{
		return "login_admin";
	}
	
	/**
	 * API call to perform Login operation for Admin
	 * 
	 * @param aLogin
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/dologinadmin")
	public String doLogin(@ModelAttribute AdminLogin aLogin,HttpSession session)
	{
		logger.info("-----Admin Login Process Start-----");
		ReturnMessage returnMessage = aClient.doValidateAdmin(aLogin);
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Admin Login.Redirecting to Adminhome-----");
			returnPage = "redirect:/adminhome";
		}
		else
		{
			logger.info("-----Error occured during Admin Login..Redirecting to Loginadmin-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", StringConstants.ERROR_LOGIN);
			returnPage = "redirect:/loginadmin";
		}
		
		return returnPage;
	}
	
	/**
	 * API call to get Admin Home Page
	 * 
	 * @param model
	 * @return adminhome page
	 */
	@GetMapping("/adminhome")
	public String showAdminHome(Model model)
	{
		model.addAttribute("designationList",designationList);
		model.addAttribute("employeeid", employeeid);
		return "adminhome";
	}
	
	/**
	 * API call to Set Designation By Admin
	 * 
	 * @param eAccess
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/dodesignation")
	public String doDesignation(@ModelAttribute EmployeeAdminAccess eAccess,HttpSession session)
	{
		logger.info("-----Set Designation Start-----");
		ReturnMessage returnMessage = aClient.doSetDesignation(eAccess);
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Designation Set.Redirecting to Adminhome-----");
			session.setAttribute("condition", StringConstants.TRUE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/adminhome";
		}
		else
		{
			logger.info("-----Unsuccessful Designation Set.Redirecting to Adminhome-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/adminhome";
		}
		
		return returnPage;
	}
	
	/**
	 * API call to Calculate Salary by Admin 
	 * 
	 * @param employeeid
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/docalculatesalary")
	public String doCalculateSalary(@RequestParam Integer employeeid,HttpSession session)
	{
		logger.info("-----Calculate Salary Start-----");
		ReturnMessage returnMessage = aClient.doCalculateSalary(employeeid);
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Calculate Salary.Redirecting to Adminhome-----");
			session.setAttribute("condition", StringConstants.TRUE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/adminhome";
		}
		else
		{
			logger.info("-----Unsuccessful Calculate Salary.Redirecting to Adminhome-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/adminhome";
		}
		
		return returnPage;
	}
}
