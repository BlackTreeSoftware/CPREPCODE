/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.action.registration;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.controller.registration.IForgotPasswordController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.util.MailUtil;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
public class ForgotPasswordAction extends  ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {

	/**
	 * implementing Log4j
	 */
	private static final Logger logger = Logger.getLogger(ForgotPasswordAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The registration bo. */
	private RegistrationBO registrationBO;
	
	private HttpServletRequest request;
	
	/** The session map. */
	private Map<String, Object> sessionMap;
	
	/** The response. */
	private HttpServletResponse response;
	
	@Autowired
	private IForgotPasswordController forgotPasswordController;
	
	
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


	/**
	 * 1)check The valid valid user email functionality
	 * 2)sending new password to the corresponding valid user.
	 *
	 * @return the string
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws CPException the CP exception
	 */
	public void forgotPassword() throws JSONException, IOException{
		String result="";
		String content;
		HttpServletRequest request=ServletActionContext.getRequest();
		int user_id;
		JSONObject obj = new JSONObject();
		try {
			user_id = forgotPasswordController.getUserId(registrationBO.getEmail_id());
		String subject="Forgot your password on CrunchPrep?";
		String token=RandomStringUtils.randomAlphanumeric(30).toUpperCase();
		String url="http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/password_reset.action?&uid="+user_id+"&id="+token;
		//System.out.println(url);
		if(forgotPasswordController.checkmail(registrationBO.getEmail_id())){
			System.out.println("in if part");
		content="<p><span style='font-family:verdana,geneva,sans-serif'>Hello ,</span></p>"+

			
			"<p><span style='font-family:verdana,geneva,sans-serif'>Forgot your password, huh? No big deal.</span></p>"+
			
			"<p><span style='font-family:verdana,geneva,sans-serif'>To create a new password, just follow this link:</span></p>"+
			
			
			"<p><span style='font-family:verdana,geneva,sans-serif'><a href='"+url+"' target='_blank'>Click here to create a new password</a></span></p>"+
			
			
			"<p><span style='font-family:verdana,geneva,sans-serif'>Link doesn&#39;t work? Copy the following link to your browser address bar:</span></p>"+
			
			"<p><span style='font-family:verdana,geneva,sans-serif'><a href='"+url+"' target='_blank'>"+url+"</a></span></p>"+
			
			
			"<p><span style='font-family:verdana,geneva,sans-serif'>If you did not make this request or have received this message by mistake, please DO NOT click the confirmation link. Simply delete this email. After a short time the request will be removed from the system.</span></p>"+
			
			"<p><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
			
			"<p><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p>";

		
		 if(forgotPasswordController.tokenSave(token,user_id))
		
			try {
				MailUtil.sendMailToOne(subject,content, registrationBO.getEmail_id());
			} catch (EmailExceptions e) {
				// TODO Auto-generated catch block
				logger.debug("Exception Raised", e);
				e.printStackTrace();
			}
		    obj.put("message", "Mail Sent Successfully");
			addActionMessage("Email Sent Successfully. Please Check The Mail");
			result="success";
		}
		else{
			//System.out.println("in else part");
			obj.put("message", "Enter CrunchPrep Email Id");
			addActionMessage("Enter CrunchPrep Email Id");
			result="input";
			
		}
		}catch( CPException e){
			logger.debug("Exception Raised", e);
		}
		response.getWriter().write(obj.toString());
			
		
		
		//return result;
	}
	
	/**
	 * password Reset functionality
	 *
	 * @return the string
	 */
	public String resetPassword(){
		String result="";
		//System.out.println(""+registrationBO.getEmail_id()+"\t"+registrationBO.getPassword());
		//forgotPasswordController=new ForgotPasswordController();
		//System.out.println("resetPassword Controller\t"+forgotPasswordController);
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			int user_id=Integer.parseInt(request.getParameter("uid"));
		if(forgotPasswordController.resetPassword(this.getRegistrationBO(), user_id)){
			//addActionMessage("Password Reset Has Done Successfully. Please Login");
			String subject="CrunchPrep Account password changed";
			String content="<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>Hi "+this.getRegistrationBO().getFirst_name()+" "+this.getRegistrationBO().getLast_name()+",&nbsp;</span></p>"+

					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'>&nbsp;</p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>The password for your CrunchPrep GRE account was recently changed.</span></p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>If you made this change, you don&#39;t need to do anything more.&nbsp;</span></p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'>&nbsp;</p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>If you didn&#39;t change your password, your account might have been compromised. To get back into your account, you&#39;ll need to&nbsp;<a href='http://crunchprep.com/reset_password' style='color: rgb(0, 0, 204); outline: none;' target='_blank'>reset your password.</a></span></p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'>&nbsp;</p>"+
					
					"<div style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>Happy studying!</span></p>"+
					
					"<p style='font-family: Helvetica, Arial, sans-serif; font-size: 12px; color: rgb(0, 0, 0); line-height: normal;'><span style='font-family:verdana,geneva,sans-serif'>The CrunchPrep Team</span></p></div>";
					

			MailUtil.sendMailToOne(subject,content,this.registrationBO.getEmail_id());
			request.setAttribute("message", "Password Has Reset Successfully");
			result="success";
		}else{
			addActionMessage("Password Reset Failed");
			result="input";
		}
		}catch(CPException e){
			logger.debug("Exception Raised", e);
			
		}
		return result;
		
	}
	
	/**
	 * Token verification functionality.
	 *
	 * @return the string
	 */
	public String tokenVerify(){
		boolean flag=false;
		String result="";
		HttpServletRequest request=ServletActionContext.getRequest();
		String id=request.getParameter("id");
		int user_id=Integer.parseInt(request.getParameter("uid"));
		try {
			if(forgotPasswordController.tokenVerify(id,user_id)){
				//addActionMessage("Already This Link Has Verified");
				result="error";
			}else{
				if(forgotPasswordController.updateToken(id,user_id)){
				//System.out.println("in notverified part");
				result="success";
				}else{
					result="error";
				}
			}
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Raised", e);
		}
		//System.out.println("ID Is\t"+id+"Result is\t"+result+"    User_id\t"+user_id);
		
	return result;
	}


	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap=sessionMap;
	}


}
