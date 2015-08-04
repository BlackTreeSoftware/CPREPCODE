/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.registration;

import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
public interface IForgotPasswordController {
	
	/**
	 * Checkmail.
	 *
	 * @param email the email
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean checkmail(String email) throws CPException;
	
	/**
	 * Reset password.
	 *
	 * @param bo the bo
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean resetPassword(RegistrationBO bo,int user_id)throws CPException;
	
	/**
	 * Token save.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenSave(String token,int user_id) throws CPException;
	
	/**
	 * Token verify.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenVerify(String token,int user_id) throws CPException;
	
	/**
	 * Update token.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean updateToken(String token,int user_id) throws CPException;
	
	/**
	 * Gets the user id.
	 *
	 * @param email the email
	 * @return the user id
	 * @throws CPException the CP exception
	 */
	public int getUserId(String email) throws CPException;

}
