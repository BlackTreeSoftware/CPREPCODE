package com.greenbuds.crunchprep.action.lessons;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.controller.lessons.ILessonsController;
import com.greenbuds.crunchprep.controller.lessons.LessonsController;

public class LessonsViewingAction implements SessionAware, ServletRequestAware,
		ServletResponseAware, RequestAware {

	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map<String, Object> requestMap;
	ILessonsController lessonsController;

	public void lessonshirerachy() throws IOException, JSONException {
		//String userID = (String) sessionMap.get("user_id");
		JSONObject object = new JSONObject();
		try
		{
		
	//	System.out.println("In lessons data" + sessionMap.get("user_id"));
		LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
		String userID=""+bo.getUser_id();
     	lessonsController = LessonsController.getInstance();
	    object.put("userid", userID);
		lessonsController.getLessonsHierachyData(object);
		System.out.println("Object   :::: "+object);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.getWriter().write(object.toString());
	}

	
	@Override
	public void setRequest(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		this.requestMap = requestMap;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;
	}

}
