package com.jack.salarymanagement.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jack.salarymanagement.service.UserDetailsServiceImpl;
import com.jack.salarymanagement.utilities.JWTUtil;
import com.jack.salarymanagement.utilities.StringConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JACK
 * 
 * Filter class - JwtRequestFilter - Security 
 * Intercept every request to validate the JWT token 
 */
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    private UserDetailsServiceImpl userDetailsService = null;
//  private AdminDetailsServiceImpl adminDetailsService = null;   
    private JWTUtil jwtUtil = null;
    
    
    
//    public JwtRequestFilter(UserDetailsServiceImpl userDetailsService, AdminDetailsServiceImpl adminDetailsService,
//			JWTUtil jwtUtil) {
//		super();
//		this.userDetailsService = userDetailsService;
//		this.adminDetailsService = adminDetailsService;
//		this.jwtUtil = jwtUtil;
//	}

	public JwtRequestFilter(UserDetailsServiceImpl userDetailsService, JWTUtil jwtUtil) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtUtil = jwtUtil;
	}
    
//    public JwtRequestFilter(AdminDetailsServiceImpl adminDetailsService, JWTUtil jwtUtil) {
//		super();
//		this.adminDetailsService = adminDetailsService;
//		this.jwtUtil = jwtUtil;
//	}

	/**
	 * Filter every request to validate the JWT token
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
		
		LOGGER.info("-----JWT Validation Start-----");
	    boolean isStaticResource = false;
	    
	    if(request.getRequestURI().startsWith(StringConstants.CSS) ||
	    		request.getRequestURI().startsWith(StringConstants.IMAGES))
	    {
	    	isStaticResource = true;
	    }
	    
		if(isStaticResource || request.getServletPath().equals(StringConstants.SLASH) || 
				request.getServletPath().equals(StringConstants.SIGNUP) || 
				request.getServletPath().equals(StringConstants.DOSIGNUP) || 
				request.getServletPath().equals(StringConstants.LOGIN))
		{
			LOGGER.info("-----No JWT Validation Required-----");
			chain.doFilter(request, response);
		}
		else 
		{
			LOGGER.info("-----JWT Validation Required-----");
			final Cookie[] cookies = request.getCookies();
	        String authorizationHeader = null;
	        
	        LOGGER.info("-----Fetching Cookies-----");
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(StringConstants.AUTHORIZATION)) {
						authorizationHeader = cookie.getValue();
					}
				}
			}

	        String username = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith(StringConstants.BEARER)) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtUtil.extractUsername(jwt);
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = null;
	            
//	            if(userDetailsService == null)
//	            {
//	            	userDetails = this.adminDetailsService.loadUserByUsername(username);
//	            }
//	            else
//	            {
	            	userDetails = this.userDetailsService.loadUserByUsername(username);
//	            }          		

	            if (jwtUtil.validateToken(jwt, userDetails)) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
	        chain.doFilter(request, response);
	        LOGGER.info("-----JWT Validation End-----");
		}
    }
}