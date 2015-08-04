/**
 * 
 */
package com.greenbuds.crunchprep.interceptors;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.setups.ActionsSetups;
import com.greenbuds.crunchprep.setups.DateFormate;
import com.greenbuds.crunchprep.setups.ReviewReason;
import com.greenbuds.crunchprep.util.DateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author rrajulapati
 * 
 */
public class GatePass implements Interceptor {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private String requetString="";
    static Logger  logger=Logger.getLogger("Gate Pass Interceptor");
    private javax.servlet.http.HttpSession session=null; 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony
	 * .xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		logger.debug(" Intercptor Tracking");
		requetString=actionInvocation.getProxy().getActionName();
		 Map<String,Object> session1 = actionInvocation.getInvocationContext().getSession();
		String result=null;
		//System.out.println("\n\n\n Request String is : "+requetString);
		if(requetString.equalsIgnoreCase("Question-Analysis"))
		{
			ServletActionContext.getRequest().setAttribute("filterReasons", ReviewReason.getMyReasons().toString());
				
		}
           if(isValidRequest()){
        	/*  System.out.println("\n\n  It's Valid Request");*/
        	   if(requetString.equalsIgnoreCase("defaultAction"))
        	   {
        		   LoginUserBO userBO=(LoginUserBO) session.getAttribute("user");
        		   
        		   if(userBO!=null){
        			 //  System.out.println("\n\n user Bo Not Null and it's role id is: "+userBO.getRole_id());
        			   return ActionsSetups.LOGIN_PAGE.getStatus();
        		   }
        	   }
        	  
		 result= actionInvocation.invoke(); 
           }
           else  if(session1.isEmpty()){ 
        	   System.out.println("Sorry ! your session has expired Please Relogin");
              return ActionsSetups.SESSION_EXPIRED.getStatus(); // session is empty/expired
        	      
           }
           else{
        	 //  System.out.println("\t\t Session Expired ");
        	   result=ActionsSetups.SESSION_EXPIRED.getStatus();
           }
           System.out.println("REsult of GatePass  ---------------------------"+result);
          	return result;
	}

	private boolean isValidRequest(){
		/*System.out.println(" Requsst String is: "+requetString);*/
		this.request = ServletActionContext.getRequest();
      session=request.getSession(false);
	if(requetString.equalsIgnoreCase("password_reset")||requetString.equalsIgnoreCase("verify"))
	{
		System.out.println("value from isValidRequest()---- IF");
		return true;
	}
	else{
		System.out.println("value from isValidRequest()---- IF");
		return session!=null;	
	   }
		
	
	}
	
	
}
