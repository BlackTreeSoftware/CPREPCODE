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
 * The Class ConnectionException.
 */
public class ConnectionException extends CPException{
	
	
	
	public ConnectionException(Exception exception) {
		super(exception);
	}

	public ConnectionException(Exceptions exceptionType) {
		super(exceptionType);

	}

	public ConnectionException(Exceptions exceptionType, Exception exception) {
		super(exceptionType, exception);
	}

	
}
