package com.greenbuds.crunchprep.action.registration;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.controller.contentcreator.ISubscriptionController;
import com.greenbuds.crunchprep.controller.contentcreator.SubscriptionController;
import com.greenbuds.crunchprep.controller.registration.ILoginController;
import com.greenbuds.crunchprep.controller.registration.LoginController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.DateFormate;
import com.greenbuds.crunchprep.setups.UserType;
import com.greenbuds.crunchprep.util.DateUtil;
import com.greenbuds.crunchprep.util.MathQuestions;
import com.greenbuds.crunchprep.util.UtilsUsage;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware,
		ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LoginUserBO userBO;
	public UserDashBoardBO userDashBoardBO;
	public UserDashBoardBO getUserDashBoardBO() {
		return userDashBoardBO;
	}



	public void setUserDashBoardBO(UserDashBoardBO userDashBoardBO) {
		this.userDashBoardBO = userDashBoardBO;
	}



	public LoginController lcontrol;
	private Map<String, Object> sessionMap;
	public HttpServletRequest servletRequest;
	public ILoginController loginController;
	public static String result;
	private InetAddress ip;
	private HttpServletResponse response;
	public static Logger logger = Logger.getLogger(LoginAction.class);
	@Autowired
	private ISubscriptionController subscriptionController;

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	

	public LoginUserBO getUserBO() {
		return userBO;
	}



	public void setUserBO(LoginUserBO userBO) {
		this.userBO = userBO;
	}



	public Map<String, Object> getMap() {
		return sessionMap;
	}

	public void setMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String execute() {

		System.out.println("in execute");
		return "success";
	}

	/**
	 * Method to login user with different user access levels
	 *
	 * @return the string
	 * @throws CPException 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void loginUser() throws CPException, JSONException, IOException {

	//	MathQuestions.main("".split(""));
		JSONObject responseData=new JSONObject();
		boolean errorRaise=false;
		boolean isValidUser=false;
		boolean profile=true;
		loginController = new LoginController();	
			if (loginController.isMailExistsUser(userBO)) {
				
				isValidUser=true;	
				try {		  
						 if(loginController.isAccountActive(userBO)){	
							 userBO.setSubscription_id(subscriptionController.getSubscriptionBO("FREE", "FREE").getSubscription_id());
							 if(loginController.isSubscriptionActive(userBO)){
								 //for checking profile existed or not
								 if(loginController.isProfileActive(userBO)){
								 System.out.println("Login action ");	
								    userBO = loginController.loginUser(userBO);
								    ip = InetAddress.getLocalHost();
									String ipaddr = ip.getHostAddress();
									userBO.setIpaddress(ipaddr);
									loginController.saveLogin(userBO);		
									userBO.setAccount_status("Active");
									userBO.setSubscription_status("Active");
									userBO.setProfile_status("Active");
									sessionMap.put("user",userBO);	
									userBO=(LoginUserBO)sessionMap.get("user");
									System.out.println(userBO);	
									result = "user";	 
								 }else{
									 userBO.setProfile_status("InActive");
									 userBO.setAccount_status("Active");
								     userBO.setRole_id(4);
								     sessionMap.put("user",userBO);
								     result = "profile";
								 }
								 
							 } else {	
								 System.out.println("subscription");
								 userBO = loginController.loginUser(userBO);
								 userBO.setAccount_status("Active");
								 userBO.setSubscription_status("InActive");
								 sessionMap.put("user",userBO);	
								 addActionMessage("Please upgrade your subscription"); 
								 result = "subscription";	 
							 }		    
						 } else{
							// System.out.println("inactive");
							 System.out.println(userBO.getUser_id());
							 System.out.println(userBO.getEmail_id());
							// userBO = loginController.loginUser(userBO);
							 userBO.setAccount_status("InActive");
						     userBO.setRole_id(4);
							// sessionMap.put("user",userBO);		 
							// addActionMessage("Please activate ur account before logging in");
							 sessionMap.put("email_id", userBO.getEmail_id());
							 sessionMap.put("user_id", userBO.getUser_id());
							 sessionMap.put("user",userBO);
							 result = "inactive";
						 }
						 
						 String imagepath = servletRequest.getSession().getServletContext()
									.getInitParameter("userimagepath");
							String filePath = ServletActionContext.getServletContext()
									.getRealPath("/");
							   System.out.println("File Path################:"+filePath);
							
							String temppath = servletRequest.getSession().getServletContext()
									.getInitParameter("DefaultImage");

						  File fileToCreate = new File(filePath+imagepath + userBO.getUser_id()	+ ".jpg");
						  String userimagepath=imagepath + userBO.getUser_id()	+ ".jpg";
						
						  
							filePath += userBO.getUser_id() + ".jpg";
							if (fileToCreate.exists()) {
								userBO.setAvatar_path(userimagepath);
							} else {
							
							    userBO.setAvatar_path(temppath);
							}
                        userBO=(LoginUserBO) sessionMap.get("user");
						 System.out.println("\n From Session object "+userBO.getAvatar_path()); 
				} catch (Exception e) {
				
					result = "error";
					addActionMessage("Invalid Email or Password");
					errorRaise=true;
					logger.error(e);
				}
				//}
			} else if (loginController.isMailExistsAdmin(userBO)) {
				System.out.println("In else");
				isValidUser=true;
				try {
					userBO = loginController.loginOthers(userBO);
					if(userBO!=null){
					if (userBO.getRole_id()==UserType.Admin.getROLE_ID()) {
						sessionMap.put("user",userBO);
						result = "admin";
					} else if (userBO.getRole_id()==UserType.Content_Creator.getROLE_ID()) {
						sessionMap.put("user",userBO);
						result = "creator";
					} else if (userBO.getRole_id()==UserType.Mentor.getROLE_ID()) {
						sessionMap.put("user",userBO);
						result = "mentor";
					} else {
						addActionMessage("Invalid Email or Password");
						result = "error";
					}
					}
				} catch (Exception e) {
				
					result = "error";
					errorRaise=true;
					addActionMessage("Invalid Email or Password");
					logger.error(e);
				}
			} 
			if(isValidUser)
			{
				System.out.println();
				isValidUser=userBO!=null;
			}
        responseData.put("validEmail", isValidUser);
        if(isValidUser)
        {
        	responseData.put("accountStatus", userBO.getAccount_status());
        	responseData.put("SubscriptionStatus", userBO.getSubscription_status());

        }
        responseData.put("errorRaised",errorRaise);
        System.out.println(" \n\n\n Email Verification \n Final Result is: "+result);
	    response.getWriter().write(responseData.toString());
		
	
		
	}// loginuser
	
	
	
	public void skipOrDonelogincount(){
		System.out.println("in logincount");
		/*int userid=Integer.parseInt(sessionMap.get("user_id").toString());
		System.out.println("User Id\t\n"+userid);*/
		
		if(sessionMap.get("user_id")!=null){
			
		userBO=new LoginUserBO();
		int userid=Integer.parseInt(sessionMap.get("user_id").toString());
		userBO.setUser_id(userid);
		
		}else{
			
		userBO=(LoginUserBO) sessionMap.get("user");
		
		}
		loginController = new LoginController();
		try {
			ip = InetAddress.getLocalHost();
		
		String ipaddr = ip.getHostAddress();
		userBO.setIpaddress(ipaddr);
		userBO.setRole_id(4);
		loginController.saveLogin(userBO);
		} catch (UnknownHostException | CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tourCompletion(){
		/**
		 * Here we r passing subscription as free
		 * 
		 */
		/*int userid=Integer.parseInt(sessionMap.get("user_id").toString());
		System.out.println("User Id\t\n"+userid);*/
		int question_quant=0,question_verbal=0;
		try {
		loginController = new LoginController();
		userBO=loginController.getTourDemoQuestions();
		question_quant=userBO.getQuant_questions();
		question_verbal=userBO.getVerbal_question();
		if(sessionMap.get("user_id")!=null){
			
		userBO=new LoginUserBO();
		int userid=Integer.parseInt(sessionMap.get("user_id").toString());
		userBO.setUser_id(userid);
		
		}else{
			
		userBO=(LoginUserBO) sessionMap.get("user");
		
		}
		
		
		
			System.out.println(userBO.getQuant_questions()+"\t\t\t\t"+userBO.getVerbal_question());
			ip = InetAddress.getLocalHost();
		
		String ipaddr = ip.getHostAddress();
		userBO.setIpaddress(ipaddr);
		userBO.setRole_id(4);
		userBO.setQuant_questions(question_quant);
		userBO.setVerbal_question(question_verbal);
		
		loginController.tourCompletion(userBO);
		} catch (UnknownHostException | CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public String loginNavigation(){
	System.out.println("\n\n\n\n in login navogation");
	//System.out.println(userBO.getAccount_status());
	/*System.out.println();
	if(userBO.getAccount_status().equalsIgnoreCase("InActive")){
		return "inactive";
	}else{*/
	
  String usertype =userType();
  System.out.println("********  USER TYPE *********"+usertype);
	
  if(usertype.equalsIgnoreCase("user"))
  {
	 
	  this.userDashboard();
	  
  }
	return usertype;
	/*}*/
}


public String userDashboard(){
	
	 
	loginController = new LoginController();	
	
	LoginUserBO user=(LoginUserBO) sessionMap.get("user");
	System.out.println("********************  SESSION USEID +"+user.getUser_id());
	 UserDashBoardBO userDashBoardBO =  loginController.UserDashboard(user);	  
	  System.out.println("************** MATH*********** COREECT :"+userDashBoardBO.getMath_questions_correct()+"TOTAL  :   "+userDashBoardBO.getMath_questions_practiced()+" AVG TIME :   "+userDashBoardBO.getMath_avg_time()+"%  : "+userDashBoardBO.getMath_questions_correct_percent());
	  
	  System.out.println("************** Verbal***********"+userDashBoardBO.getVerbal_questions_correct()+"  :   "+userDashBoardBO.getVerbal_questions_practiced()+"  :   "+userDashBoardBO.getVerbal_avg_time()+"%  : "+userDashBoardBO.getVerbal_questions_correct_percent());
	  
	  this.setUserDashBoardBO(userDashBoardBO);
	 // System.out.println("Count :------------------------------------ "+this.getUserDashBoardBO().getNoOfTimesLogin());
	  //sessionMap.put("logincount",this.getUserDashBoardBO().getNoOfTimesLogin());	
	  try {
		  System.out.println("count of tour"+loginController.logincount(user));
		sessionMap.put("logincount",loginController.logincount(user));
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return SUCCESS;
	
}


private String userType()
{
	userBO = (LoginUserBO) sessionMap.get("user");
	System.out.println("userbo object is"+userBO);
	System.out.println("User ROLE :  "+UserType.User.getROLE()+" UID : "+userBO.getUser_id()+"\t"+userBO.getAccount_status()+"User RoleID\t"+userBO.getRole_id());
	
	if(userBO.getRole_id()==UserType.User.getROLE_ID()){
		if(userBO.getAccount_status()!="Active" || userBO.getAccount_status()==null){
			System.out.println("user status is inactive");
			 return "inactive";
		 }		
		 else if(userBO.getSubscription_status()!="Active"){
		    return "subscription";
	     }else if(userBO.getProfile_status()!="Active"){
			    return "profile";
		     } else{
		 return UserType.User.getROLE();
	     }
	}
	else if(userBO.getRole_id()==UserType.Admin.getROLE_ID()) return UserType.Admin.getROLE();
	else if(userBO.getRole_id()==UserType.Content_Creator.getROLE_ID()) return UserType.Content_Creator.getROLE();
	else if(userBO.getRole_id()==UserType.Mentor.getROLE_ID()){		
		return UserType.Mentor.getROLE();	
	}
	
	else return UserType.User.getROLE();
	
	
	
}
	
	/**
	 * Method for user logout with cp_login_history update with logout time.
	 *
	 * @return the string
	 * @throws CPException 
	 */
	public String logout() throws CPException {
		
		userBO=(LoginUserBO) sessionMap.get("user");
		
		{
			//userBO.setActivity_id(Integer.parseInt(sessionMap.get("session_id").toString()));
			loginController = new LoginController();
			if(userBO!=null) loginController.logout(userBO);		    	
			sessionMap.clear();
			if(sessionMap.isEmpty()){
				System.out.println("empty");
			}
			return SUCCESS;
		}
	}
	
	/**
	 * In this method we get the mail ID and this method is verified if the mail is already registered or not! If mail is already registered then Redirected to signin page otherwise It will saves the new user data and directly goes to user dashboard;
	 *
	 * @return the string
	 */
	
	public String socialUser()
	{
		String result = "";
		try {
			 
			//System.out.println("*********  Im mfrom  Loginuser ********** ");
		//	System.out.println("Email id		:	"+userBO.getEmail_id()+"PWD: "+userBO.getPassword()+"CHECK : "+userBO.getCheck());
			loginController = new LoginController();
			userBO = loginController.socialloginUser(userBO);
			result  = userBO.getStatus();
			
		//	System.out.println("result is  "+result);
				
				
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		return result;
	}
	
	
	
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}



	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
}// class

