/**
 * Course 390 - Java RESTful Web Services
 * www.activelearning.ph
 */

package com.grocerific.exceptions;

import com.grocerific.domain.ErrorCodes;

public class DataNotFoundException extends GrocerificException {
	public DataNotFoundException() { }
	
	public DataNotFoundException(String message) {
		super(message);
		int errorCode = ErrorCodes.DATA_NOT_FOUND; 
		setCode(errorCode);
	}
}
