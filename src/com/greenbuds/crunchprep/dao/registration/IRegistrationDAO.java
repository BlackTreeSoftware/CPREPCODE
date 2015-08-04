/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : CrunchPrep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.registration;

 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
 
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
 
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRegistrationDAO.
 */
public interface IRegistrationDAO {

	/**
	 * Register student.
	 *
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
 
	 //public boolean registerStudent() throws CPException;
	
	/**
	 * Get Countries List.
	 *
	 * @return map list Consisting of countries
	 * @throws CPException the CP exception
	 */
	public HashMap<String,String> getCountries()throws CPException;
	
	/**
	 * Get Countries List.
	 *
	 * @return List Consists of Orientation Question
	 * @throws CPException the CP exception
	 */
	public List<OrientationsQuestionsBO> getOrientationQuestions()throws CPException;
	
	
	/**
	 * Save orientation questions answers.
	 *
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean saveOrientationQuestionsAnswers(List<OrientationsQuestionsBO> answers,RegistrationBO registrationBO)throws CPException;
 
	public boolean registerStudent(RegistrationBO bo) throws CPException, MySQLIntegrityConstraintViolationException;
	public String getTermsofUse() throws ConnectionException, CPException;
	public String getPrivacyPolicy() throws ConnectionException, CPException;
	public boolean tokenGeneration(RegistrationBO registrationBO) throws DBException,ConnectionException,CommonExceptions,EmailExceptions;
	public boolean tokenReGeneration(RegistrationBO registrationBO) throws DBException,ConnectionException,CommonExceptions,EmailExceptions;
	public boolean studentAccountCreation(RegistrationBO registrationBO) throws DBException,ConnectionException,CommonExceptions;
	public boolean tokenVerification(RegistrationBO registrationBO) throws CPException;
 
	
	/**
	 * User subscription access.
	 *
	 * @param user_id the user_id
	 * @return the string
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 */
	public String userSubscriptionAccess(int user_id) throws DBException,SQLException;
	public String freeUserReferrals(RegistrationBO bo,int mathQuestions,int verbalQuestions) throws DBException, SQLException;
	public boolean tokenVerification2(String token,int userid)throws CPException;
	
	
}
