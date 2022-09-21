package com.jack.salarymanagement.pojo;

/**
 * @author JACK
 *
 * POJO class - AuthenticationResponse
 * Stores Authentication Response Details
 * 
 * Attributes -jwt
 */
public class AuthenticationResponse {

	private String jwt;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
}
