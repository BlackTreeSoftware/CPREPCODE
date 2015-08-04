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
 * The Class DBException.
 */
public class DBException extends CPException{
	
	
	public DBException(Exception exception) {
		super(exception);
	}

	public DBException(Exceptions exceptionType) {
		super(exceptionType);

	}

	public DBException(Exceptions exceptionType, Exception exception) {
		super(exceptionType, exception);
	}

	
	
}
