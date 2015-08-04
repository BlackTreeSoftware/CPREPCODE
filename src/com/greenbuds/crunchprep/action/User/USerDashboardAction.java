package com.greenbuds.crunchprep.action.User;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.controller.user.UserController;
import com.greenbuds.crunchprep.exception.ConnectionException;

public class USerDashboardAction implements ServletResponseAware  {
	 
	private HttpServletResponse response;
	public void getSkillData()
	{
		 
		JSONObject jsonobject=new JSONObject();
		int userId=10069;
		int testId=1; 
			 
			 try {
				 jsonobject.put("data1", (UserController.getInstance()).getTop3Skillsdata(userId, testId));
				 response.getWriter().write(jsonobject.toString()); 
			// System.out.println(jsonobject);
			}  catch (ConnectionException e) {			 
				e.printStackTrace();
			} catch (JSONException e) {
				 System.out.println("JSON exception");
				e.printStackTrace();
			} catch (IOException e) {
				 System.out.println("IO Exception");
				e.printStackTrace();
			}
			 
		 
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		 this.response=arg0;
	}

}
