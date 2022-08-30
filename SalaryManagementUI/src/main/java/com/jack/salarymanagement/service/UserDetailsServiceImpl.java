package com.jack.salarymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jack.salarymanagement.client.EmployeeClient;
import com.jack.salarymanagement.models.EmployeeLogin;
import com.jack.salarymanagement.models.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private EmployeeClient eClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//call db layer to get the details of user
		EmployeeLogin eLogin = eClient.getEmployeeLoginDetails(username);
		
		return new UserDetailsImpl(eLogin);
	}

}
