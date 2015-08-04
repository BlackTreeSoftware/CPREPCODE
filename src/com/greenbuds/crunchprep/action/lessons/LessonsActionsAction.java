/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.action.lessons;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.controller.lessons.ILessonsActionsController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.util.MailUtil;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
/**
 * The Class LessonsActionsAction.
 */
public class LessonsActionsAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(LessonsActionsAction.class);
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The session map. */
	private Map<String, Object> sessionMap;
	
	/** The response. */
	private HttpServletResponse response;
	
	/** The lesson bo. */
	private LessonsmasterBO lessonBO;
	

	/** The lessons actions controller. */
	@Autowired
	private ILessonsActionsController lessonsActionsController;
	
	/**
	 * Lessons actions.
	 *
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void lessonsActions() throws JSONException, IOException{
		JSONObject obj = new JSONObject();
		try {
			System.out.println("in lesson actions method");
			if(lessonBO.getUser_id()!=0){
				 LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
				// lessonBO= new LessonsmasterBO();
				 lessonBO.setUser_id(bo.getUser_id());   
				 lessonBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
			     System.out.println("UserId\t"+lessonBO.getUser_id()+"\t"+lessonBO.getSublesson_id()+"\t"+lessonBO.getAction_type()+"\t"+lessonBO.getAction_desc()+"\t"+lessonBO.getAction_title());					
						if(lessonsActionsController.lessonsActions(lessonBO)){	
						System.out.println("in Action Email\t"+lessonBO.getEmail()+"\t"+lessonBO.getAction_desc());
						MailUtil.sendMailToOne(lessonBO.getAction_title(),lessonBO.getAction_desc(),lessonBO.getEmail());		
						obj.put("message", "Mail Sent Successfully");
					    }else{
					    	obj.put("message", "Doesnt Have Mentor");
					    }
			}
		
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised", e);
		}
		
		
		
		response.getWriter().write(obj.toString());
		//lessonsActionsController.lessonsActions(lessonBO);
	}
	

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
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


	/**
	 * Gets the lesson bo.
	 *
	 * @return the lesson bo
	 */
	public LessonsmasterBO getLessonBO() {
		return lessonBO;
	}


	/**
	 * Sets the lesson bo.
	 *
	 * @param lessonBO the new lesson bo
	 */
	public void setLessonBO(LessonsmasterBO lessonBO) {
		this.lessonBO = lessonBO;
	}
	


}
