/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.registration;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.dao.registration.IForgotPasswordDAO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
public class ForgotPasswordController implements IForgotPasswordController {
	@Autowired
	private IForgotPasswordDAO forgotPasswordDAO;
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.registration.IForgotPasswordController#checkmail(java.lang.String)
	 */
	public boolean checkmail(String email) throws CPException{
		
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("checkmail DAO\t"+forgotPasswordDAO);
		
		return forgotPasswordDAO.checkmail(email);
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.registration.IForgotPasswordController#resetPassword(com.greenbuds.crunchprep.bo.registration.RegistrationBO, int)
	 */
	public boolean resetPassword(RegistrationBO bo,int user_id) throws CPException{
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("resetPassword DAO\t"+forgotPasswordDAO);
		return forgotPasswordDAO.resetPassword(bo,user_id);
	}
	
	/**
	 * Token save.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenSave(String token,int user_id) throws CPException{
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("tokenSave DAO\t"+forgotPasswordDAO);
		return forgotPasswordDAO.tokenSave(token,user_id);
	}
	
	
	/**
	 * Token verify.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenVerify(String token,int user_id) throws CPException{
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("tokenSave DAO\t"+forgotPasswordDAO);
		return forgotPasswordDAO.tokenVerify(token,user_id);
	}
	
	/**
	 * Update token.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean updateToken(String token,int user_id) throws CPException{
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("tokenSave DAO\t"+forgotPasswordDAO);
		return forgotPasswordDAO.updateToken(token,user_id);
	}
	
	/**
	 * Gets the user id.
	 *
	 * @param email the email
	 * @return the user id
	 * @throws CPException the CP exception
	 */
	public int getUserId(String email) throws CPException{
		//forgotPasswordDAO=new ForgotPasswordDAO();
		//System.out.println("tokenSave DAO\t"+forgotPasswordDAO);
		return forgotPasswordDAO.getUserId(email);
	}

}
