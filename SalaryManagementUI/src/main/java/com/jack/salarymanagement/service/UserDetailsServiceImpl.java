package com.jack.salarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.models.UserDetailsImpl;

/**
 * @author JACK
 *
 * Service class - UserDetailsServiceImpl
 * Fetch the Employee Login Credentioals for authentication
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EmployeeClient eClient;

	/**
	 * Get UserDetails related to given username
	 * 
	 * @param username
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeLogin eLogin = eClient.getEmployeeLoginDetails(username);
		
		return new UserDetailsImpl(eLogin);
	}
}
