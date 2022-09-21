package com.jack.salarymanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jack.salarymanagement.filter.CustomAuthenticationFilter;
import com.jack.salarymanagement.filter.JwtRequestFilter;
import com.jack.salarymanagement.handler.UrlAuthenticationSuccessHandler;
import com.jack.salarymanagement.service.UserDetailsServiceImpl;
import com.jack.salarymanagement.utilities.JWTUtil;

/**
 * @author JACK
 *
 * Configuration class - SecurityConfiguration
 * Configuration for Authentication and Authorization
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
	}

	/**
	 * @author JACK
	 *
	 * Configuration class - EmployeeConfiguration
	 */
	@Configuration
	@Order(1)
	public static class EmployeeConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private UserDetailsServiceImpl userDetailsService;
		
		@Autowired
		private JWTUtil jwtUtil;
		
		public EmployeeConfiguration()
		{
			super();
		}	
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web
            .ignoring()
            .antMatchers("/resources/**");
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(),userDetailsService,jwtUtil));
			http
			 .csrf().disable()
		      .authorizeRequests()
		      .antMatchers("/details").hasRole("USER")
		      .antMatchers("/dodetailssubmit").hasRole("USER")
		      .antMatchers("/doapplyleave").hasRole("USER")
		      .antMatchers("/employeehome").hasRole("USER")	
		      .antMatchers("/dosignup").permitAll()
		      .antMatchers("/").permitAll()
		      .and()
			  .formLogin()
		      .loginPage("/login")
		      .successHandler(authenticationSuccessHandler())
		      .failureUrl("/login");
			http.addFilterBefore(new JwtRequestFilter(userDetailsService,jwtUtil), UsernamePasswordAuthenticationFilter.class);		 
		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
    public static AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }
	
//	@Configuration
//	@Order(2)
//	public static class AdminConfiguration extends WebSecurityConfigurerAdapter
//	{
//		@Autowired
//		private AdminDetailsServiceImpl adminDetailsService;
//		
//		@Autowired
//		private JWTUtil jwtUtil;
//		
//		public AdminConfiguration()
//		{
//			super();
//		}
//		
//		@Override
//		public void configure(WebSecurity web) throws Exception {
//			web
//            .ignoring()
//            .antMatchers("/resources/**");
//		}
//		
//		@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.userDetailsService(adminDetailsService);
//		}
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			 http
//			  .csrf().disable()
//		      .authorizeRequests()
//		      .antMatchers("/adminhome").permitAll()
//		      .antMatchers("/dodesignation").permitAll()
//		      .antMatchers("/docalculatesalary").permitAll()
//		      .antMatchers("/loginadmin").permitAll()
//		      .antMatchers("/").permitAll()
//		      .and()
//			  .formLogin()
//		      .loginPage("/loginadmin");
//			 
//
//		}
//		
//		@Bean
//		public AuthenticationManager authenticationManagerAdminBean() throws Exception {
//			return super.authenticationManagerBean();
//		}
//		
//	}
}
