package com.jack.salarymanagement.pojo;

import org.springframework.stereotype.Component;

@Component
public class ReturnMessage {

	private boolean valid;
	private String message;

	public ReturnMessage() {
		super();
	}

	public ReturnMessage(boolean valid, String message) {
		super();
		this.valid = valid;
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ReturnMessage [valid=" + valid + ", message=" + message + "]";
	}

}
