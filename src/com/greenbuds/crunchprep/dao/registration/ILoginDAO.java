/***************************************************************************
 * Copyright (c) 2014 , sganji , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.registration;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;



/**
 * The Interface ILoginDAO.
 */
public interface ILoginDAO {
	
	/**
	 * Login user.
	 *
	 * @param loginUserBO the login user loginUserBO
	 * @return the login user loginUserBO
	 */
	public LoginUserBO loginUser(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Checks if is mail exists user.
	 *
	 * @param email the email
	 * @return true, if is mail exists user
	 */
	public boolean isMailExistsUser(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Checks if is mail exists admin.
	 *
	 * @param email the email
	 * @return true, if is mail exists admin
	 */
	public boolean isMailExistsAdmin(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Login others(admin,mentor,content creator).
	 *
	 * @param bo the loginUserBO
	 * @return the login user loginUserBO
	 */
	public LoginUserBO loginOthers(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Save login history.
	 *
	 * @param bo the loginUserBO
	 * @return the string
	 */
	public String saveLogin(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Sociallogin user.: Login with Facebook and Gmail
	 *
	 * @param loginUserBO the login user bo
	 * @return the login user bo
	 */
	public LoginUserBO socialloginUser(LoginUserBO loginUserBO);
	
	/**
	 * Logout.
	 *
	 * @param bo the bo
	 * @return the string
	 */
	public String logout(LoginUserBO bo) throws CPException;
	
	/**
	 * Checks if user subscription active.
	 *
	 * @param loginUserBO the login user bo
	 * @return true, if is subscription active
	 * @throws CPException the CP exception
	 */
	public boolean isSubscriptionActive(LoginUserBO loginUserBO)  throws CPException;
	
	/**
	 * Checks if user account active.
	 *
	 * @param loginUserBO the login user bo
	 * @return true, if is account active
	 * @throws CPException the CP exception
	 */
	public boolean isAccountActive(LoginUserBO loginUserBO) throws CPException;
	
	
	public UserDashBoardBO UserDashboard(LoginUserBO loginUserBO);
	
	/**
	 * Free user referrals.
	 *
	 * @param user_id the user_id
	 * @param mathQuestions the math questions
	 * @param verbalQuestions the verbal questions
	 * @return the string
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 */
	
	public int logincount(LoginUserBO loginUserBO)throws CPException;
	public boolean isProfileActive(LoginUserBO loginUserBO)  throws CPException;
	public String tourCompletion(LoginUserBO loginUserBO)  throws CPException;
	public LoginUserBO getTourDemoQuestions()  throws CPException;
		
	

	
}
