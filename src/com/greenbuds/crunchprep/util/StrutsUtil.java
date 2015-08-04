/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : CrunchPrep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.util;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

// TODO: Auto-generated Javadoc
/**
 * The Class StrutsUtil.
 *
 * @author bnandigama
 * @version 1.0
 * @see inorder to maintain a session handling
 */
public class StrutsUtil  {

	  
	
	/**
	 *  
	 *
	 * @return true, if successful
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	public static boolean checkSession(){
	HttpSession session=ServletActionContext.getRequest().getSession(false);
	if(session==null || session.getAttribute("user_id")==null){
		return false;
	}
	else{
		return true;
	}
	}
	

}
