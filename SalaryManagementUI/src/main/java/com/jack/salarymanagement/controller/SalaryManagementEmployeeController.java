package com.jack.salarymanagement.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.EmployeeDetails;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.pojo.ReturnMessage;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Controller class - Handles the Employee Activities
 */
@Controller
public class SalaryManagementEmployeeController {

	@Autowired
	private EmployeeClient eClient;
	private static final Logger logger = LoggerFactory.getLogger(SalaryManagementEmployeeController.class);

	
	/**
	 * API call to get Employee SignUp Page
	 * 
	 * @return signup page
	 */
	@GetMapping("/signup")
	public String showSignUpPage()
	{
		return "signup";
	}
	
	/**
	 * API call to process Employee SignUp
	 * 
	 * @param eLogin
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/dosignup")
	public String doSignUp(@ModelAttribute EmployeeLogin eLogin,HttpSession session)
	{
		logger.info("-----Employee SignUp Process Start-----");
		eLogin.setRole(StringConstants.USER);//Security-1.0
		ReturnMessage returnMessage = eClient.doSignUp(eLogin);
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Employee SignUp.Redirecting to Home-----");
			returnPage = "redirect:/";
		}
		else
		{
			logger.info("-----Unsuccessful Employee SignUp.Redirecting to signup-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", StringConstants.ERROR_SIGNUP);
			returnPage = "redirect:/signup";
		}
		
		return returnPage;
	}
	
	/**
	 * API call to get Employee Login Page
	 * 
	 * @return login page
	 */
	@GetMapping("/login")
	public String showLoginPage()
	{
		return "login";
	}
	
//Need to be changed for JWT
//	@PostMapping("/login")
//	public String doLogin(@ModelAttribute EmployeeLogin eLogin,HttpSession session)
//	{
//		//send eLogin to BusinessLayer
//		ReturnMessage returnMessage = eClient.doValidateEmployee(eLogin);
//		String returnPage = null;
//		
//		if(returnMessage.isValid())
//		{
//			returnPage = "redirect:/employeehome";
//		}
//		else
//		{
//			if(returnMessage.getMessage().equals(StringConstants.INVALID_EMPLOYEE))
//			{
//				session.setAttribute("condition", StringConstants.FALSE);
//				session.setAttribute("message", StringConstants.INVALID_CREDENTIALS);
//			}
//			else if(returnMessage.getMessage().equals(StringConstants.EMPLOYEE_NOT_FOUND))
//			{
//				session.setAttribute("condition", StringConstants.FALSE);
//				session.setAttribute("message", StringConstants.RECORD_NOT_FOUND);
//			}		
//			returnPage = "redirect:/login";
//		}
//		return returnPage;
//	}
	
//	@PostMapping("/login")
//	public String createAuthenticationToken(@ModelAttribute AuthenticationRequest authenticationRequest,HttpServletResponse response) throws Exception {
//		System.out.println(authenticationRequest.getUsername() + "---" + authenticationRequest.getPassword());
//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//			);
//		}
//		catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}
//
//
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//
//		final String jwt = jwtTokenUtil.generateToken(userDetails);
//		
//		ResponseCookie  springCookie = ResponseCookie.from("Authorization", "Bearer " + jwt).build();
//		
//		 ResponseEntity
//		    .ok()
//		    .header("COOKIE", springCookie.toString())
//		    .build();
//		
//		Cookie jwtTokenCookie = new Cookie("Authorization", "Bearer" + jwt);
//		response.addCookie(jwtTokenCookie);	
//		
//		//return "redirect:/employeehome";
//		return "employeehome";
//	}
	
	/**
	 * API call to get Employee Details Page
	 * 
	 * @return details page
	 */
	@GetMapping("/details")
	public String showDetailsPage()
	{
		return "details";
	}
	
	/**
	 * API call to Submit Employee Details 
	 * 
	 * @param details
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/dodetailssubmit")
	public String doDetailsSubmit(@ModelAttribute EmployeeDetails details,HttpSession session)
	{
		logger.info("-----Submit Details Process Start-----");
		ReturnMessage returnMessage = eClient.doSubmitDetails(details);
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Submit Details.Redirecting to Employeehome-----");
			returnPage = "redirect:/employeehome";
		}
		else
		{
			logger.info("-----Unsuccessful Submit Details.Redirecting to details-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", StringConstants.INVALID_DETAILS);
			returnPage = "redirect:/details";
		}
		
		return returnPage;
	}
	
	/**
	 * API call to Apply Leave for Employee
	 * 
	 * @param session
	 * @return returnPage
	 */
	@PostMapping("/doapplyleave")
	public String doApplyLeave(HttpSession session)
	{
		logger.info("-----Apply Leave Process Start-----");
		ReturnMessage returnMessage = eClient.doApplyLeave();
		String returnPage = null;
		
		if(returnMessage.isValid())
		{
			logger.info("-----Successful Apply Leave.Redirecting to Employeehome-----");
			session.setAttribute("condition", StringConstants.TRUE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/employeehome";		
		}
		else
		{
			logger.info("-----Unuccessful Submit Details.Redirecting to Employeehome-----");
			session.setAttribute("condition", StringConstants.FALSE);
			session.setAttribute("message", returnMessage.getMessage());
			returnPage = "redirect:/employeehome";
		}
		
		return returnPage;
	}
	
	/**
	 * API call to get Employee Home Page
	 * 
	 * @return employeehome page
	 */
	@GetMapping("/employeehome")
	public String showEmployeeHome()
	{
		return "employeehome";
	}
}
