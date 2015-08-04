package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.AdminRegistrationBO;
import com.greenbuds.crunchprep.controller.contentcreator.AdminRegistrationController;
import com.greenbuds.crunchprep.controller.contentcreator.IAdminRegistrationController;
import com.greenbuds.crunchprep.controller.contentcreator.PolicyController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.setups.UserType;
import com.opensymphony.xwork2.ActionSupport;

public class AdminRegistrationAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private final static Logger LOGGER = Logger.getLogger(PolicyAction.class);
	private String result=null;
	private IAdminRegistrationController adminRegistrationController;
	private AdminRegistrationBO adminRegistrationBO;
	private List<AdminRegistrationBO> adminRegistrationList;
	
	
	public List<AdminRegistrationBO> getAdminRegistrationList() {
		return adminRegistrationList;
	}

	public void setAdminRegistrationList(
			List<AdminRegistrationBO> adminRegistrationList) {
		this.adminRegistrationList = adminRegistrationList;
	}

	public void adminRegistrationSave() throws JSONException, IOException {
		JSONObject responseData = new JSONObject();
		adminRegistrationController = new AdminRegistrationController();
	//	System.out.println("admin id ::"+adminRegistrationBO.getAdmin_id());
		System.out.println("admin"+adminRegistrationBO.getRole()+"     "+UserType.Admin.getROLE());
		System.out.println("admin"+adminRegistrationBO.getRole()+"     "+UserType.Mentor.getROLE());
		System.out.println("admin"+adminRegistrationBO.getSection_name()+"     "+Section.QUANTITATIVE.getSectionName());
		try {
		if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Admin.getROLE())) {
			System.out.println("In if");
			adminRegistrationBO.setRole_id(UserType.Admin.getROLE_ID());
			adminRegistrationBO.setSection_id(0);
		}
		else if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Content_Creator.getROLE())) {
			System.out.println("In if content");
			adminRegistrationBO.setRole_id(UserType.Content_Creator.getROLE_ID());
			adminRegistrationBO.setSection_id(0);
		}
		else if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Mentor.getROLE())) {
			System.out.println("In if mentor");
			adminRegistrationBO.setRole_id(UserType.Mentor.getROLE_ID());
			if(adminRegistrationBO.getSection_name().equalsIgnoreCase(Section.QUANTITATIVE.getSectionName())){
				adminRegistrationBO.setSection_id(Section.QUANTITATIVE.getSectionId());
			}
			else if(adminRegistrationBO.getSection_name().equalsIgnoreCase(Section.VERBAL.getSectionName())){
				adminRegistrationBO.setSection_id(Section.VERBAL.getSectionId());
			}
			else{
				adminRegistrationBO.setSection_id(0);
			}
		}
		
		System.out.println("role::"+adminRegistrationBO.getRole_id()+"section:::"+adminRegistrationBO.getSection_id());
			if (adminRegistrationController.adminRegistrationSave(adminRegistrationBO)) {
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e);
			addActionMessage("Admin Registration Failed");
			e.printStackTrace();
			result = "error";
		}
		responseData.put("data", result);
		response.getWriter().write(responseData.toString());
	}
	
	public void adminRegistrationUpdate() throws JSONException, IOException {
		JSONObject responseData = new JSONObject();
		adminRegistrationController = new AdminRegistrationController();
		System.out.println("admin id ::"+adminRegistrationBO.getAdmin_id());
		try {
			if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Admin.getROLE())) {
				adminRegistrationBO.setRole_id(UserType.Admin.getROLE_ID());
				adminRegistrationBO.setSection_id(0);
			}
			else if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Content_Creator.getROLE())) {
				adminRegistrationBO.setRole_id(UserType.Content_Creator.getROLE_ID());
				adminRegistrationBO.setSection_id(0);
			}
			else if (adminRegistrationBO.getRole().equalsIgnoreCase(UserType.Mentor.getROLE())) {
				adminRegistrationBO.setRole_id(UserType.Mentor.getROLE_ID());
				if(adminRegistrationBO.getSection_name().equalsIgnoreCase(Section.QUANTITATIVE.getSectionName())){
					adminRegistrationBO.setSection_id(Section.QUANTITATIVE.getSectionId());
				}
				else if(adminRegistrationBO.getSection_name().equalsIgnoreCase(Section.VERBAL.getSectionName())){
					adminRegistrationBO.setSection_id(Section.VERBAL.getSectionId());
				}
				else{
					adminRegistrationBO.setSection_id(0);
				}
			}
			
			if (adminRegistrationController.adminRegistrationUpdate(adminRegistrationBO)) {
				result = "success";
			}
		} catch (CPException e) {
			LOGGER.error(e);
			addActionMessage("Details Update Failed");
			e.printStackTrace();
			result = "error";
		}
		responseData.put("data", result);
		response.getWriter().write(responseData.toString());
	}
	
	public void adminRegistrationDelete() throws JSONException, IOException {
		JSONObject responseData = new JSONObject();
		adminRegistrationController = new AdminRegistrationController();
		String arrayValues=request.getParameter("checkboxvalues");
		StringTokenizer tokens=new StringTokenizer(arrayValues,",");
		try {
		while (tokens.hasMoreElements()) {
			String nextId=(String) tokens.nextElement();
			AdminRegistrationBO bo = new AdminRegistrationBO();
			bo.setAdmin_id(Integer.parseInt(nextId));
			
				if (adminRegistrationController.adminRegistrationDelete(bo)) {
					result = "success";
					setAdminRegistrationList(adminRegistrationController.getAdminDetails());
				}
		      }
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e);
				addActionMessage("Delete Failed");
				result = "error";
			}
		
		responseData.put("data", result);
		response.getWriter().write(responseData.toString());
		
		
	}
	
	public String getAdminDetails() throws CPException {
		adminRegistrationController = new AdminRegistrationController();
		setAdminRegistrationList(adminRegistrationController.getAdminDetails());
		return "success";
	}
	
	
	public AdminRegistrationBO getAdminRegistrationBO() {
		return adminRegistrationBO;
	}

	public void setAdminRegistrationBO(AdminRegistrationBO adminRegistrationBO) {
		this.adminRegistrationBO = adminRegistrationBO;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap= sessionMap;
	}
	

}
