package com.jack.salarymanagement.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jack.salarymanagement.service.UserDetailsServiceImpl;
import com.jack.salarymanagement.utilities.JWTUtil;
import com.jack.salarymanagement.utilities.StringConstants;

/**
 * @author JACK
 *
 * Filter class - CustomAuthenticationFilter - Security 
 * Process authentication and post authentication 
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
	
	private AuthenticationManager authenticationManager = null;
	private UserDetailsServiceImpl userDetailsService = null;	
//	private AdminDetailsServiceImpl adminDetailsService = null;	
	private JWTUtil jwtTokenUtil = null;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager,UserDetailsServiceImpl userDetailsService,JWTUtil jwtTokenUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}
	
//	public CustomAuthenticationFilter(AuthenticationManager authenticationManager,AdminDetailsServiceImpl adminDetailsService,JWTUtil jwtTokenUtil) {
//		super();
//		this.authenticationManager = authenticationManager;
//		this.adminDetailsService = adminDetailsService;
//		this.jwtTokenUtil = jwtTokenUtil;
//	}

	/**
	 * Authientication Process
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LOGGER.info("-----Login Authentication Starts-----");
		String username = request.getParameter(StringConstants.USERNAME);
		String password = request.getParameter(StringConstants.PASSWORD);
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(token);
	}

	/**
	 * After Successful Authentication Process
	 * Creates JWT Token and save it in a cookie
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		LOGGER.info("-----After Successful Login Authentication Starts-----");
		UserDetails userDetails = null;
//		if(userDetailsService == null)
//		{
//			userDetails = this.adminDetailsService.loadUserByUsername(request.getParameter("username"));
//		}
//		else
//		{
			userDetails = this.userDetailsService.loadUserByUsername(request.getParameter(StringConstants.USERNAME));
//		}
			
		//Need to send username to business layer to get employeeid for apply Leave
			
		LOGGER.info("-----Creating JWT Token-----");	
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		Cookie jwtTokenCookie = new Cookie(StringConstants.AUTHORIZATION, StringConstants.BEARER + jwt);
		response.addCookie(jwtTokenCookie);
		LOGGER.info("-----JWT Token Saved in Cookie-----");
		
		chain.doFilter(request, response);
	}
}
