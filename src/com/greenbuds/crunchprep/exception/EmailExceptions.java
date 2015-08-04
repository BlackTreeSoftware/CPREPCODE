package com.greenbuds.crunchprep.exception;

import com.greenbuds.crunchprep.setups.Exceptions;

public class EmailExceptions extends CPException {

	
	
	public EmailExceptions(Exception exception) {
		super(exception);
	}

	public EmailExceptions(Exceptions exceptionType) {
		super(exceptionType);

	}

	public EmailExceptions(Exceptions exceptionType, Exception exception) {
		super(exceptionType, exception);
	}

}
