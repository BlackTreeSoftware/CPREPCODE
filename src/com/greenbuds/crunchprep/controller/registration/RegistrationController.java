package com.greenbuds.crunchprep.controller.registration;

 
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

//import org.apache.tomcat.util.threads.ResizableExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.dao.registration.RegistrationDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.exception.EmailExceptions;

 
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.dao.registration.IRegistrationDAO;
import com.greenbuds.crunchprep.dao.registration.LoginDAO;
import com.greenbuds.crunchprep.dao.registration.RegistrationDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

 
// TODO: Auto-generated Javadoc
/**
 * The Class RegistrationController.
 */
 
public class RegistrationController implements IRegistrationController{
 
 /** The registration dao. */
 @Autowired
 private RegistrationDAO registrationDAO;
/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#getCountries()
 */
public HashMap<String, String> getCountries() throws CPException {
	// TODO Auto-generated method stub
	dao=new RegistrationDAO();
	return dao.getCountries();
}
	
	/** The dao. */
	private IRegistrationDAO dao;
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#registerStudent(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@Override
	public boolean registerStudent(RegistrationBO bo) throws CPException, MySQLIntegrityConstraintViolationException {
		 dao=new RegistrationDAO();
		return dao.registerStudent(bo);
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#getTermsofUse()
	 *  @throws it might throw a Connection Exception 
	 */
	@Override
	public String getTermsofUse() throws CPException {		 
		dao=new RegistrationDAO();
		return dao.getTermsofUse();
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#getPrivacyPolicy()
	 * @throws it might throw a Connection Exception 
	 */
	@Override
	public String getPrivacyPolicy() throws CPException {
		dao=new RegistrationDAO();
		return dao.getPrivacyPolicy();
	}
 

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#getOrientationQuestions()
 */
@Override
public List<OrientationsQuestionsBO> getOrientationQuestions()
		throws CPException {
	// TODO Auto-generated method stub
	dao=new RegistrationDAO();
	return dao.getOrientationQuestions();
}


/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#saveOrientationQuestionsAnswers(java.util.List, com.greenbuds.crunchprep.bo.registration.RegistrationBO)
 */
@Override
public boolean saveOrientationQuestionsAnswers(List<OrientationsQuestionsBO> answers,RegistrationBO registrationBO) throws CPException {
	// TODO Auto-generated method stub
	dao=new RegistrationDAO();
	return dao.saveOrientationQuestionsAnswers(answers,registrationBO);
}

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#tokenGeneration(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
 */
@Override
public boolean tokenGeneration(RegistrationBO registrationBO)
		throws DBException, ConnectionException, CommonExceptions,
		EmailExceptions {
	// TODO Auto-generated method stub
	
	return registrationDAO.tokenGeneration(registrationBO);
}

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#studentAccountCreation(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
 */
@Override
public boolean studentAccountCreation(RegistrationBO registrationBO)
		throws DBException, ConnectionException, CommonExceptions {
	// TODO Auto-generated method stub
	dao=new RegistrationDAO();
	return dao.studentAccountCreation(registrationBO);
}

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#tokenVerification(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
 */
@Override
public boolean tokenVerification(RegistrationBO registrationBO)
		throws CPException {
	// TODO Auto-generated method stub
	System.out.println(registrationDAO);
	return registrationDAO.tokenVerification(registrationBO);
}

public boolean tokenVerification2(String token,int userid)
		throws CPException {
	// TODO Auto-generated method stub
	System.out.println(registrationDAO);
	dao=new RegistrationDAO();
	return dao.tokenVerification2(token, userid);
}

public RegistrationBO getQuantVerbalQuestions() throws SQLException, DBException{
	return registrationDAO.getQuantVerbalQuestions();
}
/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.controller.registration.IRegistrationController#tokenReGeneration(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
 */
@Override
public boolean tokenReGeneration(RegistrationBO registrationBO)
		throws DBException, ConnectionException, CommonExceptions,
		EmailExceptions {
	// TODO Auto-generated method stub
	return registrationDAO.tokenReGeneration(registrationBO);
}



public String freeUserReferrals(RegistrationBO bo,int mathQuestions,int verbalQuestions) throws DBException, SQLException{
	return registrationDAO.freeUserReferrals(bo, mathQuestions, verbalQuestions);
}

/**
 * User subscription access.
 *
 * @param user_id the user_id
 * @return the string
 * @throws DBException the DB exception
 * @throws SQLException the SQL exception
 */
public String userSubscriptionAccess(int user_id) throws DBException,SQLException{
	registrationDAO=new RegistrationDAO();
	return registrationDAO.userSubscriptionAccess(user_id);
	

}








}

