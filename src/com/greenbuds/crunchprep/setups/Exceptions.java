/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author SAIRAM RAJULAPATI
 *
 */
public enum Exceptions {

	CONNECTION_REFUSED("CONNECTION  REFUSED"),NOT_ABLE_TO_ACCESS_DATABASE("NOT_ABLE_TO_ACCESS_DATABASE"),
	CONNECTION_CLOSING_EXCEPTION("EXCEPTION RAISED WHILE CONNECTION CLOSING"),ADDRESS_EXCEPTION("EMAIL ADDRESS NOT FOUND"),
	MESSAGE_EXCEPTION("MESSAGE SENDING FAILED"),CONNECTION_COMMIT_FAILED("EXCEPTION RAISED WHEN COMMITING CONNECTIONS"),
	RESOURCES_NOT_OPENED("RESOURCES_NOT_OPENED"),KEY_EXISTED("KEY_EXISTED"),PROPERTIES_READ_FAILED("POPERTY_FILE_READING_FAILED"),
	STATEMENT_CLOSING_EXCEPTION("Exception Occured while closing the statement"),RESULTSET_CLOSING_EXCEPTION("Exception Occured While Closing the ResultSet"),
	SQL_EXCEPTION("SQL Query Exception");
	
	private String exceptionType;
	private Exceptions(String exceptionType)
	{
		this.exceptionType=exceptionType;
	}
	
	public String getExceptionType() {
		return exceptionType;
	}

	public boolean isKeyDuplicateKey(Exceptions exceptionType){
		return exceptionType==KEY_EXISTED;
		
	}
	public boolean isKeyDuplicateKey(){
		return this.exceptionType==KEY_EXISTED.exceptionType;
		
	}
}
