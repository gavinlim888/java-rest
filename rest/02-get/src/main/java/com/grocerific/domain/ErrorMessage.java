/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private int status;
	private String message;
	private int code;
	
	public ErrorMessage() { }

	public ErrorMessage(int status, String message, int code) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
