/*
 * 
 */
package com.greenbuds.crunchprep.action.registration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
import java.util.Map;

 
import org.apache.log4j.Logger;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

 
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.controller.contentcreator.SubscriptionController;
import com.greenbuds.crunchprep.controller.registration.LoginController;
import com.greenbuds.crunchprep.controller.registration.RegistrationController;
import com.greenbuds.crunchprep.dao.registration.LoginDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.util.MailUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.greenbuds.crunchprep.dao.registration.LoginDAO;
 
// TODO: Auto-generated Javadoc
/**
 * The Class PersonalProfileAction.
 */
 
public class PersonalProfileAction extends ActionSupport implements ServletRequestAware,SessionAware{
 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	 
   /** The orient_questions. */
   private List<OrientationsQuestionsBO> orient_questions; 
   
   /** The registration controller. */
   private RegistrationController registrationController; 
   
   /** The questions_answers. */
   private List<OrientationsQuestionsBO> questions_answers; 
	
	/** The logger. */
	private Logger logger=Logger.getLogger(PersonalProfileAction.class);
	
	@Autowired
	private LoginController loginController;
	@Autowired
	private SubscriptionController subscriptionController;
	
	/**
	 * Gets the questions_answers.
	 *
	 * @return the questions_answers
	 */
	
	private HttpServletRequest request;
	private HttpSession session;
	
	public List<OrientationsQuestionsBO> getQuestions_answers() {
		return questions_answers;
	}
	
	/**
	 * Sets the questions_answers.
	 *
	 * @param questions_answers the new questions_answers
	 */
	public void setQuestions_answers(List<OrientationsQuestionsBO> questions_answers) {
		this.questions_answers = questions_answers;
	}
	 
	/**
	 * Gets the orient_questions.
	 *
	 * @return the orient_questions
	 */
	public List<OrientationsQuestionsBO> getOrient_questions() {
		return orient_questions;
	}
	
	/**
	 * Sets the orient_questions.
	 *
	 * @param orient_questions the new orient_questions
	 */
	public void setOrient_questions(List<OrientationsQuestionsBO> orient_questions) {
		this.orient_questions = orient_questions;
	}
	
	/** The registration bo. */
	RegistrationBO registrationBO;

	private Map<String, Object> sessionMap;
	 
	/**
	 * Gets the registration bo.
	 *
	 * @return the registration bo
	 */
	public RegistrationBO getRegistrationBO() {
		return registrationBO;
	}
	
	/**
	 * Sets the registration bo.
	 *
	 * @param registrationBO the new registration bo
	 */
	public void setRegistrationBO(RegistrationBO registrationBO) {
		this.registrationBO = registrationBO;
	} 
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	/**
	 * Gets orientation questions.
	 * And displays them on web page
	 * @return the string
	 */
	public String execute() 
	{
		String result="";
		result="success";
		registrationController=new RegistrationController(); 
		try {
			orient_questions=registrationController.getOrientationQuestions();
			registrationBO=new RegistrationBO();
			if(sessionMap.get("user_name")!=null)
			registrationBO.setFirst_name(sessionMap.get("user_name").toString());
			System.out.println("in success orientation");
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
		} 
		return result;
	}
	
	public String userSubscriptionAccess(RegistrationBO registrationBO){

		  registrationController = new RegistrationController();
		 
		try {
			registrationController.userSubscriptionAccess(Integer.parseInt(sessionMap.get("user_id").toString()));
		} catch (DBException e) {
		    
		} catch (SQLException e) {
		
		}

		return SUCCESS;
		}
	
	
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
	
	
	/**
	 * Save personal details.
	 * Saves user personal details and answers of the orientation questions given by the user
	 * @return the string
	 */
	public String savePersonalDetails() 
	{ 
		String result="";
		result="success";
 
		System.out.println(sessionMap.get("user_id"));
		System.out.println(sessionMap.get("user"));
		try {
		List<OrientationsQuestionsBO> saveUserAnswers=new ArrayList<OrientationsQuestionsBO>();  
		registrationController=new RegistrationController();
	
		System.out.println("firstname \t"+getRegistrationBO().getFirst_name());
		
		LoginDAO loginDAO=new LoginDAO();
		if(sessionMap.get("user_id")!=null){
			
		this.getRegistrationBO().setUserId(Integer.parseInt(sessionMap.get("user_id").toString()));
		//System.out.println("userid");
		}else{
			LoginUserBO userBO=(LoginUserBO) sessionMap.get("user");
			this.getRegistrationBO().setUserId(userBO.getUser_id());
			//System.out.println("bean session");
		}
		for(int i=1;i<getQuestions_answers().size();i++)
		{  
			OrientationsQuestionsBO orientationsQuestionsBO=(OrientationsQuestionsBO)getQuestions_answers().get(i); 
			OrientationsQuestionsBO answers=new OrientationsQuestionsBO(); 
			List<String> answers_list=new ArrayList<String>(); 
			 
			answers.setQuestion_id(orientationsQuestionsBO.getQuestion_id());  
			answers.setUser_id(this.getRegistrationBO().getUserId()); 
			//System.out.println("User IIIIIDDD:"+sessionMap.get("user_id"));
			/*LoginUserBO loginUserBO=new LoginUserBO();
			loginUserBO.setUser_id(Integer.parseInt(sessionMap.get("user_id").toString()));
			UserDashBoardBO userDashBoardBO=loginDAO.UserDashboard(loginUserBO);*/
			//System.out.println("Number of Logins"+userDashBoardBO.getNoOfTimesLogin());
			//System.out.println("Question id:"+bo.getQuestion_id());
			//System.out.println("Answers:");
			
			for(String b:orientationsQuestionsBO.getOptions()) 
			{
				 answers_list.add(b);
			}  
			answers.setOptions(answers_list);
			saveUserAnswers.add(answers); 
		}  
		
			if(registrationController.saveOrientationQuestionsAnswers(saveUserAnswers,registrationBO))
			 {
				 result="success";
			 }
			 else
			 {
				 result="error";
			 }
			
			//System.out.println("Result is\t"+result);
			/*
			int mathQuestions=5;
			int verbalQuestions=5;
			
			if(studentAccountCreation(registrationBO)){
				
				System.out.println("UserId\t"+registrationBO.getUserId()+"\t"+registrationBO.getSubscription_id());
							
								registrationController.freeUserReferrals(registrationBO, mathQuestions, verbalQuestions);
							
					String subject="Thanks for signing up with CrunchPrep!";
					String content="<p><span style='font-family:verdana,geneva,sans-serif'>Thanks for signing up for CrunchPrep GRE!</span></p>"+

							
							"<p><span style='font-family:verdana,geneva,sans-serif'>You now have access to a sample of CrunchPrep GRE lesson videos and practice questions for 1 week. If you&#39;d like access to all the lesson videos and over 1000 practice questions, upgrade your account here:<a href='http://crunchprep.com/plans' style='color: rgb(0, 0, 204); outline: none;' target='_blank'>http://crunchprep.com/plans</a></span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>You can log in to your account at:&nbsp;<a href='https://crunchprep.com/login' style='color: rgb(0, 0, 204); outline: none;' target='_blank'>https://crunchprep.com/login</a></span></p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Your login is: "+registrationBO.getEmail_id()+"</span></p>"+
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Your account expires on: "+registrationBO.getEnd_date()+"</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>If you have any questions, please send us an email at&nbsp;<a href='mailto:help@crunchprep.com' style='color: rgb(0, 0, 204); outline: none;'>help@crunchprep.com</a>&nbsp;and we&#39;ll get back to you as quickly as we can.</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
							
							
							"<p><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p>";
					MailUtil.sendMailToOne(subject,content, registrationBO.getEmail_id());
					sessionMap.put("email_id",registrationBO.getEmail_id());
					
					LoginUserBO userBO=new LoginUserBO();
					userBO.setEmail_id(sessionMap.get("email_id").toString());
					userBO=loginController.loginUser(userBO);
					
					sessionMap.put("user",userBO);
					System.out.println("Count is\t"+loginController.logincount(userBO));
					sessionMap.put("logincount",loginController.logincount(userBO));
					String token=sessionMap.get("token_id").toString();
					System.out.println("Token\t"+token+"\t"+"UserId"+userBO.getUser_id());
					registrationController.tokenVerification2(token,userBO.getUser_id());
					
					result = SUCCESS;
					}else{
						LoginUserBO userBO=new LoginUserBO();
						userBO.setEmail_id(sessionMap.get("email_id").toString());
						userBO=loginController.loginUser(userBO);
						
						sessionMap.put("user",userBO);
						System.out.println("Count is\t"+loginController.logincount(userBO));
						sessionMap.put("logincount",loginController.logincount(userBO));
						result = SUCCESS;
					}*/
			
			//System.out.println("Email Session value\t"+sessionMap.get("email_id"));
			//System.out.println("Bean Session value\t"+sessionMap.get("user"));
			if(sessionMap.get("email_id")!=null){
			LoginUserBO userBO=new LoginUserBO();
			userBO.setEmail_id(sessionMap.get("email_id").toString());
			userBO=loginController.loginUser(userBO);
			
			sessionMap.put("user",userBO);
			System.out.println("Count is\t"+loginController.logincount(userBO));
			sessionMap.put("logincount",loginController.logincount(userBO));
			}else{
				//System.out.println("bean session hai");
				LoginUserBO userBO=(LoginUserBO) sessionMap.get("user");
				userBO=loginController.loginUser(userBO);				
				sessionMap.put("user",userBO);
				System.out.println("Count is\t"+loginController.logincount(userBO));
				sessionMap.put("logincount",loginController.logincount(userBO));
			}
			result = SUCCESS;
			
			
			
			
			//registrationController.tokenVerification(registrationBO);
			System.out.println("first name \t"+sessionMap.get("user.first_name"));
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
 
		System.out.println(result);
		return result; 
	} 
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;
	}
	
}
