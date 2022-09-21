package com.jack.salarymanagement.models;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author JACK
 *
 * UserDetails Class - UserDetailsImpl
 * Stores Login Details for Employee- Applied to Employee - Security
 * 
 * Attributes - username,password,role
 */
@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private String username;
	private String password;
	private String role;

	public UserDetailsImpl(EmployeeLogin eLogin) {
		super();
		this.username = eLogin.getUsername();
		this.password = eLogin.getPassword();
		this.role = eLogin.getRole();
	}

	public UserDetailsImpl() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
