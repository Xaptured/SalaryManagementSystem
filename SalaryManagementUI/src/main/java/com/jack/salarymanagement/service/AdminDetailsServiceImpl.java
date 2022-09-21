//package com.jack.salarymanagement.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.jack.salarymanagement.models.AdminLogin;
//import com.jack.salarymanagement.models.UserDetailsAdminImpl;
//import com.jack.salarymanagement.models.UserDetailsImpl;

/**
 * @author JACK
 * 
 * Service class - AdminDetailsServiceImpl
 * Fetch the A Login Credentioals for authentication
 */
//@Service
//public class AdminDetailsServiceImpl implements UserDetailsService {
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AdminLogin aLogin = new AdminLogin(username, "Jack@123", "ROLE_ADMIN");
//
//		return new UserDetailsAdminImpl(aLogin);
//	}
//
//}
