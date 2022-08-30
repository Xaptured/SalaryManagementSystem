package com.jack.salarymanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Configuration
	@Order(1)
	public static class EmployeeConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private UserDetailsService userDetailsService;
		
		public EmployeeConfiguration()
		{
			super();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 http
			  .formLogin()
		      .loginPage("/login")
		      .loginProcessingUrl("/dologin")
		      .defaultSuccessUrl("/employeehome", true)
		      .failureUrl("/login")
		      .and()
		      .csrf().disable()
		      .authorizeRequests()
		      .antMatchers("/details").hasRole("USER")
		      .antMatchers("/dodetailssubmit").hasRole("USER")
		      .antMatchers("/doapplyleave").hasRole("USER")
		      .antMatchers("/employeehome").hasRole("USER")
		      .antMatchers("/dologin").permitAll()		      
		      .antMatchers("/dosignup").permitAll()
		      .antMatchers("/").permitAll();   
		}
	}
	
	@Configuration
	@Order(2)
	public static class AdminConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private UserDetailsService userDetailsService;
		
		public AdminConfiguration()
		{
			super();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			 http
			  .formLogin()
		      .loginPage("/loginadmin")
		      .loginProcessingUrl("/dologinadmin")
		      .defaultSuccessUrl("/adminhome", true)
		      .failureUrl("/loginadmin")
		      .and()
		      .csrf().disable()
		      .authorizeRequests()
		      .antMatchers("/loginadmin").hasRole("ADMIN")
		      .antMatchers("/dologinadmin").hasRole("ADMIN")
		      .antMatchers("/adminhome").hasRole("ADMIN")
		      .antMatchers("/dodesignation").hasRole("ADMIN")
		      .antMatchers("/docalculatesalary").hasRole("ADMIN")
		      .antMatchers("/").permitAll();   
		}
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
}
