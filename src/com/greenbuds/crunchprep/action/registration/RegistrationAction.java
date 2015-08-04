package com.greenbuds.crunchprep.action.registration;


 
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.SubscriptionBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
import com.greenbuds.crunchprep.controller.contentcreator.SubscriptionController;
import com.greenbuds.crunchprep.controller.registration.IRegistrationController;
import com.greenbuds.crunchprep.controller.registration.LoginController;
import com.greenbuds.crunchprep.controller.registration.RegistrationController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.MailUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionSupport;
//import org.apache.tomcat.util.threads.ResizableExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrationAction.
 */
public class RegistrationAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware {
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger( RegistrationAction.class );
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The response. */
	private HttpServletResponse response;
	
	/** The Registration POJO */
	 private RegistrationBO registrationBo;
	 private SubscriptionBO subscriptionBO;
	
	public RegistrationBO getRegistrationBo() {
		return registrationBo;
	}

	public void setRegistrationBo(RegistrationBO registrationBo) {
		this.registrationBo = registrationBo;
	}


	/** The registration controller. */
	@Autowired
	private RegistrationController registrationController; 
	
	@Autowired
	private SubscriptionController subscriptionController;
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The session. */
	private HttpSession session;
 
 
	
	/** The reg controller. */
	private IRegistrationController regController;
	
	
	
/**
 * User register.
 *
 * @return the string
 */
public String userRegister() {
	//JSONObject object=new JSONObject();
	//System.out.println("Mail id : "+bo.getEmail_id()+"\t password is : "+bo.getPassword());
	//regController=new RegistrationController();
	String result=ERROR;
	 
		//System.out.println(registrationBo.getReferralCode());
		
		
		
		 
		try {
			if(registrationController.registerStudent(registrationBo)){
				
				sessionMap.put("user_id",this.getRegistrationBo().getUserId());
				sessionMap.put("email_id", this.getRegistrationBo().getEmail_id());
				if(tokenGeneration(this.getRegistrationBo()))
				{ 
				    //addActionMessage("A verification email has been sent to the email address . Please click on the link in the email to confirm your email address.");
					result=SUCCESS;
				}
			
			
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			 LOGGER.error(e); 
			 addActionMessage("Email Id Already Existed");
			 result=ERROR;
		} catch (CPException e) {
			  LOGGER.error(e); 
			 addActionMessage("Registration Failed !Please Try Again");
			 result=ERROR;
		}
	 
 
	//object.put("result", result);
	//response.getWriter().write(object.toString());
	//System.out.println(result);
 	return result;
}

public String socialRegister(){
	
	String result=ERROR;
	System.out.println("Email id \t"+registrationBo.getEmail_id());
	System.out.println(request.getParameter("name"));
	try {
		if(registrationController.registerStudent(registrationBo)){
			sessionMap.put("user_id",this.getRegistrationBo().getUserId());
			sessionMap.put("email_id", this.getRegistrationBo().getEmail_id());
			sessionMap.put("user_name",request.getParameter("name").toString());
			System.out.println(sessionMap.get("user_name").toString());
			String token=RandomStringUtils.randomAlphanumeric(30).toUpperCase();
			registrationBo.setToken(token);
			if(registrationController.tokenGeneration(registrationBo)){
				if(registrationController.tokenVerification(registrationBo)){
						studentAccountCreation(getRegistrationBo());
						result=SUCCESS;
			}
			}
		}
	}catch (CPException e) {
		 LOGGER.error(e);
		 
		 addActionMessage("Operation Failed !");
		 result=ERROR;
	} catch (MySQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//object.put("result", result);
	//response.getWriter().write(object.toString());
	//System.out.println(result);
	return result;
}

/**
 * Token generation.
 */
public boolean tokenGeneration(RegistrationBO registrationBO){
	boolean b=false;
	String token=RandomStringUtils.randomAlphanumeric(30).toUpperCase();
	
	registrationBO.setToken(token);
	try {
		if(registrationController.tokenGeneration(registrationBO)){
		String subject="Please confirm your email address";
		String message2="<p><span style='font-family:verdana,geneva,sans-serif'>Hello,</span></p>"+

							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Please click the link below to confirm your CrunchPrep account:</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'><a href='http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"' target='_blank'>Click here to confirm your account</a></span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Do NOT reply to this email but click the link above. If clicking the above link doesn&#39;t work, copy this URL to your web browser:</span>&nbsp;</p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'><a href='http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"' target='_blank'>http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"</a></span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>You have received this email message because someone entered your email address to create a CrunchPrep account. If you ignore this email message, the account will be deleted automatically within 24 hours.</span></p>";
			b=MailUtil.sendMailToOne(subject,message2, getRegistrationBo().getEmail_id());
			
		}
	} catch (DBException | ConnectionException | CommonExceptions
			| EmailExceptions e) {		 
		e.printStackTrace();
	}
	return b;	
}

/**
 * Token re generation.
 *
 * @return the string
 */
public String tokenReGeneration(){
	
	String result=ERROR;
	String token=RandomStringUtils.randomAlphanumeric(30).toUpperCase();
	this.setRegistrationBo(new RegistrationBO());
	this.getRegistrationBo().setToken(token);
	int userid=Integer.parseInt(sessionMap.get("user_id").toString());
	
	this.getRegistrationBo().setUserId(userid);
	String emailId=sessionMap.get("email_id").toString();
	this.getRegistrationBo().setEmail_id(emailId);
	try {
		if(registrationController.tokenReGeneration(getRegistrationBo())){
		String subject="Please confirm your email address";
		String message2="<p><span style='font-family:verdana,geneva,sans-serif'>Hello,</span></p>"+

							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Please click the link below to confirm your CrunchPrep account:</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'><a href='http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"' target='_blank'>Click here to confirm your account</a></span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Do NOT reply to this email but click the link above. If clicking the above link doesn&#39;t work, copy this URL to your web browser:</span>&nbsp;</p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'><a href='http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"' target='_blank'>http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/verify.action?u="+getRegistrationBo().getUserId()+"&id="+token+"</a></span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>You have received this email message because someone entered your email address to create a CrunchPrep account. If you ignore this email message, the account will be deleted automatically within 24 hours.</span></p>";
		MailUtil.sendMailToOne(subject,message2, emailId);
		addActionMessage("A verification email has been sent to the email address . Please click on the link in the email to confirm your email address. ");
		result=SUCCESS;
		}
	} catch (DBException | ConnectionException | CommonExceptions
			| EmailExceptions e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		addActionMessage("Sending Mail Failed!");
		result = ERROR;
	}
	
	return result;
}

/**
 * Token verification.
 *
 * @return the string
 * @throws SQLException 
 * @throws DBException 
 */
public String tokenVerification() throws DBException, SQLException{
	String result=ERROR;
	String token=request.getParameter("id");
	String userId=request.getParameter("u");
	//System.out.println(token + "\t" + userId);
	int mathQuestions=0;
	int verbalQuestions=0;
	registrationBo=registrationController.getQuantVerbalQuestions();
	mathQuestions=registrationBo.getQuant_questions();
	verbalQuestions=registrationBo.getVerbal_questions();
	System.out.println(mathQuestions+"\t\t"+verbalQuestions+"\t"+ServerProperties.getInistance().getRefer_limit());
	this.setRegistrationBo(new RegistrationBO());
	this.getRegistrationBo().setUserId(Integer.parseInt(userId));
	this.getRegistrationBo().setToken(token);
	this.getRegistrationBo().setRefer_limit(ServerProperties.getInistance().getRefer_limit());
	System.out.println("Refer Limit is\t"+this.getRegistrationBo().getRefer_limit());
	sessionMap.put("user_id",userId);
	sessionMap.put("token_id",token);
	try {
		boolean flag=registrationController.tokenVerification(this.getRegistrationBo());
		System.out.println("Result is\t"+flag);
		if(flag==true){
			if(studentAccountCreation(this.getRegistrationBo())){
				
		System.out.println("UserId\t"+getRegistrationBo().getUserId()+"\t"+getRegistrationBo().getSubscription_id());
		
					registrationController.freeUserReferrals(this.getRegistrationBo(), mathQuestions, verbalQuestions);
			
			String subject="Thanks for signing up with CrunchPrep!";
			String content="<p><span style='font-family:verdana,geneva,sans-serif'>Thanks for signing up for CrunchPrep GRE!</span></p>"+

					
					"<p><span style='font-family:verdana,geneva,sans-serif'>You now have access to a sample of CrunchPrep GRE lesson videos and practice questions for 1 week. If you&#39;d like access to all the lesson videos and over 1000 practice questions, upgrade your account here:<a href='http://crunchprep.com/plans' style='color: rgb(0, 0, 204); outline: none;' target='_blank'>http://crunchprep.com/plans</a></span></p>"+
					
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>You can log in to your account at:&nbsp;<a href='https://crunchprep.com/login' style='color: rgb(0, 0, 204); outline: none;' target='_blank'>https://crunchprep.com/login</a></span></p>"+
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>Your login is: "+registrationBo.getEmail_id()+"</span></p>"+
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>Your account expires on: "+registrationBo.getEnd_date()+"</span></p>"+
					
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>If you have any questions, please send us an email at&nbsp;<a href='mailto:help@crunchprep.com' style='color: rgb(0, 0, 204); outline: none;'>help@crunchprep.com</a>&nbsp;and we&#39;ll get back to you as quickly as we can.</span></p>"+
					
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
					
					
					"<p><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p>";
			MailUtil.sendMailToOne(subject,content, registrationBo.getEmail_id());
			sessionMap.put("email_id",registrationBo.getEmail_id());
			
			result = SUCCESS;
			}
			result = SUCCESS;
		}
		else{
			//System.out.println("in error");
			result = ERROR;
		}
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result = ERROR;
	}
	
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result = ERROR;
	}
	
	return result;
}

/**
 * Student account creation.
 *
 * @return true, if successful
 */
public boolean studentAccountCreation(RegistrationBO registrationBO){
	boolean flag=false;
	
 	
	try {
		registrationBO.setSubscription_id(subscriptionController.getSubscriptionBO("Free", "Free").getSubscription_id());
		registrationBO.setNoDays(subscriptionController.getSubscriptionBO("Free", "Free").getDays_count());
		System.out.println(registrationBO.getSubscription_id());
		if(registrationController.studentAccountCreation(registrationBO)){
			
			userSubscriptionAccess(registrationBO);
			
			flag=true;
		}
		
		
		
	} catch (DBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ConnectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (CommonExceptions e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}


 /** The termsof use data. */
 private String termsofUseData;
 
 /** The privacy policy data. */
 private String privacyPolicyData;

private Map<String, Object> sessionMap;

/**
 * Gets the privacy policy data.
 *
 * @return the privacy policy data
 */
public String getPrivacyPolicyData() {
	return privacyPolicyData;
}




/**
 * Sets the privacy policy data.
 *
 * @param privacyPolicyData the new privacy policy data
 */
public void setPrivacyPolicyData(String privacyPolicyData) {
	this.privacyPolicyData = privacyPolicyData;
}

/**
 * Gets the termsof use data.
 *
 * @return the termsof use data
 */
public String getTermsofUseData() {
	return termsofUseData;
}

/**
 * Sets the termsof use data.
 *
 * @param termsofUseData the new termsof use data
 */
public void setTermsofUseData(String termsofUseData) {
	this.termsofUseData = termsofUseData;
}

/**
 * This method is used to get the Terms and Conditions for Registration
 *
 * @return the terms and conditions
 */
public String getTermsofUse()
{ 
	 regController=new RegistrationController(); 
	 try {
		setTermsofUseData(regController.getTermsofUse());
	} catch (ConnectionException e) {
		LOGGER.error(Exceptions.CONNECTION_REFUSED); 
	} catch (CPException e) {
		LOGGER.error("You have got CPException");
		e.printStackTrace();
	}  
	 //System.out.println("Terms of Use : "+getTermsofUseData());
	 return "success";
}

/**
 * This method is used to get the Terms and Conditions for Registration
 *
 * @return the privacy policy 
 */
public String getPrivacyPolicy()
{ regController=new RegistrationController();
  try {
	setPrivacyPolicyData(regController.getPrivacyPolicy());
} catch (ConnectionException e) {
	LOGGER.error(Exceptions.CONNECTION_REFUSED);  
} catch (CPException e) {	
	LOGGER.error("You have got CPException");
	e.printStackTrace();
}  
	return "success";
}


public String userSubscriptionAccess(RegistrationBO registrationBO){

  registrationController = new RegistrationController();
 
try {
	registrationController.userSubscriptionAccess(Integer.parseInt(sessionMap.get("user_id").toString()));
} catch (DBException e) {
     LOGGER.debug(e);
} catch (SQLException e) {
	LOGGER.debug(e);
}

return SUCCESS;
}



/* (non-Javadoc)
 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
 */
@Override
public void setServletResponse(HttpServletResponse arg0) {
	this.response = arg0;
	
}

/* (non-Javadoc)
 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
 */
@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.request=request;
}

@Override
public void setSession(Map<String, Object> sessionMap) {
	this.sessionMap=sessionMap;
	
}

}

