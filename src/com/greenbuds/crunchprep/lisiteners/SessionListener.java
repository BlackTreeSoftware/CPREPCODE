package com.greenbuds.crunchprep.lisiteners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.controller.registration.LoginController;
import com.greenbuds.crunchprep.exception.CPException;

public class SessionListener implements HttpSessionListener   {
 

@Override
public void sessionCreated(HttpSessionEvent arg0) {
	 
		System.out.println("Session Created");
	
}

@Override
public void sessionDestroyed(HttpSessionEvent event) { 
	   HttpSession session=event.getSession();
	   LoginUserBO bo=(LoginUserBO)session.getAttribute("user");
	 LoginController  loginController = new LoginController();
	    try {
			String str=loginController.logout(bo);
			//System.out.println("result : "+str);
			System.out.println("logout time updated successfylly when session expired");
		} catch (CPException e) {
			e.printStackTrace();
			 System.out.println("Getting Exception while Updating Logout time while session expired ");
			
		}		    	
		 
	   System.out.println(bo.getUser_id()+"\t user Id Logout Updated");
	
}
}