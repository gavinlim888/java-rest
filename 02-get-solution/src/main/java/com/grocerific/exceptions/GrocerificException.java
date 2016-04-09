/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */
package com.grocerific.exceptions;

public class GrocerificException extends RuntimeException {
	private int code;

	public GrocerificException() { 
		super("An error occurred within the Grocerific application.");
	}
	
	public GrocerificException(String message) {
		super(message);
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
