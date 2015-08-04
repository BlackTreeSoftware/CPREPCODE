/***************************************************************************
 * Copyright (c) 2014 , sganji , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.registration;




import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.dao.registration.ILoginDAO;
import com.greenbuds.crunchprep.dao.registration.LoginDAO;
import com.greenbuds.crunchprep.exception.CPException;

/**
 * The Class LoginController.
 */
public class LoginController implements ILoginController{
	
	/** The dao. */
	private ILoginDAO dao;
	
	
	
	/* (non-Javadoc) user details retrieving
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#loginUser(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public LoginUserBO loginUser(LoginUserBO loginUserBO) throws CPException {
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.loginUser(loginUserBO);
	}
		
		
	/* (non-Javadoc)mail existence verification for user
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#isMailExistsUser(java.lang.String)
	 */
	public boolean isMailExistsUser(LoginUserBO loginUserBO) throws CPException {
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.isMailExistsUser(loginUserBO);
	}
	
	public int logincount(LoginUserBO loginUserBO) throws CPException{
		dao=new LoginDAO();
		return dao.logincount(loginUserBO);
	}
	
	/* (non-Javadoc) mail existence verification for admin
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#isMailExistsAdmin(java.lang.String)
	 */
	public boolean isMailExistsAdmin(LoginUserBO loginUserBO)  throws CPException{
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.isMailExistsAdmin(loginUserBO);
	}
	
	/* (non-Javadoc) login details from cp_admin_registration
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#loginOthers(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public LoginUserBO loginOthers(LoginUserBO loginUserBO)  throws CPException{
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.loginOthers(loginUserBO);
	}

	
	/* (non-Javadoc) save into login history
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#saveLogin(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public String saveLogin(LoginUserBO bo) throws CPException {
		dao=new LoginDAO();
		return dao.saveLogin(bo);
	}
	
	public String tourCompletion(LoginUserBO loginUserBO)  throws CPException{
		dao=new LoginDAO();
		return dao.tourCompletion(loginUserBO);
	}
	public LoginUserBO getTourDemoQuestions()  throws CPException{
		dao=new LoginDAO();
		return dao.getTourDemoQuestions();
	}


	/* (non-Javadoc) Logout method
	 * @see com.greenbuds.crunchprep.controller.registration.ILoginController#logout(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public String logout(LoginUserBO loginUserBO) throws CPException {
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.logout(loginUserBO);
	}
	
	/* (non-Javadoc) checks whether user subscription exits or not
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#isSubscriptionActive(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public boolean isSubscriptionActive(LoginUserBO loginUserBO)  throws CPException{
		dao=new LoginDAO();
		return dao.isSubscriptionActive(loginUserBO);
	}
	
	public boolean isProfileActive(LoginUserBO loginUserBO)  throws CPException {
		dao=new LoginDAO();
		return dao.isProfileActive(loginUserBO);
	}
	
	/* (non-Javadoc) Checks whether user account is active or not
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#isAccountActive(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public boolean isAccountActive(LoginUserBO loginUserBO) throws CPException{	
		dao=new LoginDAO();
		return dao.isAccountActive(loginUserBO);
	}
	/**
	 * In this method we get the mail ID and this method is verified if the mail is already registered or not! If mail is already registered then Redirected to signin page otherwise It will saves the new user data and directly goes to user dashboard;
	 *
	 * @return the string
	 */
	public LoginUserBO socialloginUser(LoginUserBO loginUserBO) {
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.socialloginUser(loginUserBO);
	}


	@Override
	public UserDashBoardBO UserDashboard(LoginUserBO loginUserBO) {
		// TODO Auto-generated method stub
		dao=new LoginDAO();
		return dao.UserDashboard(loginUserBO);
	}


	 
		
	}
	


