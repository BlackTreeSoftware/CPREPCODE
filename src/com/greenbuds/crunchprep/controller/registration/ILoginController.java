package com.greenbuds.crunchprep.controller.registration;

import java.util.List;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface ILoginController {
	
	/**
	 * Login user.
	 *
	 * @param loginUserBO the login user bo
	 * @return the login user bo
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
	 * Login others(Admin,Content creator, mentor).
	 *
	 * @param bo the bo
	 * @return the login user bo
	 */
	public LoginUserBO loginOthers(LoginUserBO loginUserBO) throws CPException;
		
	/**
	 * Save login history.
	 *
	 * @param bo the bo
	 * @return the string
	 */
	public String saveLogin(LoginUserBO loginUserBO) throws CPException;
	
	 
	/**
	 * Sociallogin user.: Login with Facebook and gmail
	 *
	 * @param loginUserBO the login user bo
	 * @return the login user bo
	 */
	public LoginUserBO socialloginUser(LoginUserBO loginUserBO);
	
	public UserDashBoardBO UserDashboard(LoginUserBO loginUserBO);
	
	/**
	 * Logout.
	 *
	 * @param bo the loginUserBO
	 * @return the string
	 */
	public String logout(LoginUserBO loginUserBO) throws CPException;
	
	/**
	 * Checks if user subscription active.
	 *
	 * @param loginUserBO the login user loginUserBO
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
	public boolean isProfileActive(LoginUserBO loginUserBO)  throws CPException;
	public String tourCompletion(LoginUserBO loginUserBO)  throws CPException;
	public int logincount(LoginUserBO loginUserBO) throws CPException;
	public LoginUserBO getTourDemoQuestions()  throws CPException;
}
