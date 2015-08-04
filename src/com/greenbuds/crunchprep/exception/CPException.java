/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : CrunchPrep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.exception;

import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.setups.UserType;




// TODO: Auto-generated Javadoc
/**
 * The Class CPException.
 */
public class CPException extends Exception {
	
	
	
	/**
	 * @param exceptionType
	 */
	public CPException(Exception exception) {
		super(exception);
	}

	public CPException(Exceptions exceptionType) {
		super(exceptionType.getExceptionType());

	}

	public CPException(Exceptions exceptionType, Exception exception) {
		super(exceptionType.getExceptionType(), exception);

	}
}
