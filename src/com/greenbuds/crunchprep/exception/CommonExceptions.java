package com.greenbuds.crunchprep.exception;

import com.greenbuds.crunchprep.setups.Exceptions;

public class CommonExceptions extends CPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public CommonExceptions(Exception exception) {
		super(exception);
	}

	public CommonExceptions(Exceptions exceptionType) {
		super(exceptionType);

	}

	public CommonExceptions(Exceptions exceptionType, Exception exception) {
		super(exceptionType, exception);
	}
}
