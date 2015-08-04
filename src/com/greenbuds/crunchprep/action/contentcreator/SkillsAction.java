

/*
 * @(#)SkillsAction.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */

package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 

import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.ISkillsController;
import com.greenbuds.crunchprep.controller.contentcreator.SkillsController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;
/*java API's*/
/*other third Party API's*/
/*Project API's*/



/**
 * @author kkatikala
 * @version 1.0
 * SkillsAction Class which consists of all actions for skill levels
 */
public class SkillsAction extends ActionSupport implements RequestAware,
		ServletRequestAware, SessionAware, ServletResponseAware {
	
	/*Class Implementation Comments*/
	
	/*Skill Levels Bean Class */
	SkillBO skillBO;
	
	/* List of Skill levels*/
	List<SkillBO> skills;
	List<SkillBO> sectionList;
	
	/* Httpservlet request instance to get or put data*/
	HttpServletRequest request;
	
	/*Object to get the session objects or to put the objects in session*/
	private Map<String, Object> sessionMap;
	
	
	/* Request to get or put data required*/
	private Map<String, Object> requestMap;
	
	/*HttpServlet Response to send response objects*/
	private HttpServletResponse response;
	
	
	/*Controller object to access all the skill levels actions*/
	private ISkillsController skillsController;
	
	/*Controller object for using common Dao's*/
	private ICommonsController commonsController;

	
	/*No-Argument Constructor*/
	public SkillsAction(){
		
	}
	
	
	
	/**
	 * Method to get the list of skill levels
	 * @return "SUCCESS"  If list of skill levels has been retrieved succesfully
	 * @throws CPException
	 */
	public String getSkillLevels() throws CPException {

		System.out.println("IN GET SKILSSSSSSS");
		commonsController = CommonsController.getInstance();
		sectionList=commonsController.getSectionList1();
		this.setSkills(commonsController.getSkillsList("-1"));

		return SUCCESS;
	}
	
	

	/**
	 * Save,Update,Delete Actions for skill levels
	 * @throws CPException
	 * @throws JSONException
	 * @throws IOException
	 */
	
	public void skillsActions() throws CPException, JSONException, IOException {
		
		
		JSONObject responseData = new JSONObject();		
		JSONArray array = new JSONArray();  					// JSON Array to store multiple status for all deleting records
		List<SkillBO> deleteStatus = new ArrayList<SkillBO>(); // List to save the status that is returned after deleting records
		List<String> statusArray = new ArrayList<String>();    // Status array after actions performed which has been added to JSON Array
		
		String status = null;
		String action = request.getParameter("action");       // Type of action to perform based on user selection
		skillsController = SkillsController.getInstance();

		
		/** Action for Saving skill Levels
		 * @returns boolean "true" if Skill name saved successfully
		 * 					"false" If error occured when saving skill name
		 * **/
		
		
		
		if (action.equalsIgnoreCase("save")) {
			String result=null;
			result=skillsController.saveSkills(skillBO);
			if(result.equalsIgnoreCase("SUCCESS")){
				status = "Skill has been saved.. Thank You!!";
			} else if(result.equalsIgnoreCase("EXISTS")) {
				status = "Oops!! Sorry.. Skill name already exists!!";
			}
			else {
				status = "Oops!! Sorry.. data insertion failed!!";
			}
			System.out.println("RESULT "+status);
			responseData.put("result", status);
		} 
		
		
		/** Action for Updating skill Levels
		 *  @returns boolean	
		 *  @return  true  If data updated successfully..
		 *  @return   false If error occured when updating skill name
		 * **/
		
		
		else if (action.equalsIgnoreCase("update")) {
			String result=null;
			result=skillsController.updateSkills(skillBO);
			if (result.equalsIgnoreCase("SUCCESS")) {
				status = "Skill has been updated.. Thank You!!";
			} 
			else if(result.equalsIgnoreCase("EXISTS")) {
				status = "Oops!! Sorry.. Skill name already exists!!";
			}
			else {
				status = "Oops!! Sorry.. data updation failed!!";
			}
			System.out.println("RESULT "+status);
			responseData.put("result", status);
		}

		/** Action for deleting skill Levels
		 *  @returns String
		 * 			"SUCCESS"	If all the records has been deleted successfully
		 * 			"foreign@skillnames " When skill names have child records
		 * 			""Error"  When error occured while deleting records
		 * **/
		
		else if (action.equalsIgnoreCase("delete")) {

			try {
				String arrayValues = request.getParameter("checkboxvalues");
				StringTokenizer tokens = new StringTokenizer(arrayValues, ",");
				while (tokens.hasMoreElements()) {
					String nextId = (String) tokens.nextElement();
					int id = Integer.parseInt("" + nextId);
					System.out.println("Nextt     " + id);
					SkillBO skillBO = new SkillBO();
					skillBO.setSkill_id(Integer.parseInt("" + nextId));
					status = skillsController.deleteSkills(skillBO);
					if (status.contains("foreign")) {
						skillBO.setSkill_name(status.substring(status
								.indexOf("@") + 1));
						deleteStatus.add(skillBO);
					}
				}

			}

			catch (Exception e) {		
				// TODO: handle exception
				e.printStackTrace();
			}
			for (int i = 0; i < deleteStatus.size(); i++) {
				array.put(deleteStatus.get(i).getSkill_name());
			}

			if (array.length() > 0) {
				try {
					responseData.put("result", array);
				} catch (JSONException e) {
					// TODO: handle JSONexception
					e.printStackTrace();
				}
			} else {
				responseData.put("result", "SUCCESS");
			}
		}

		
		response.getWriter().write(responseData.toString());

	}
	

	/**
	 * @return response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	

	/**
	 * @return SkillBO object
	 */
	public SkillBO getSkillBO() {
		return skillBO;
	}

	/**
	 * @param skillBO
	 */
	public void setSkillBO(SkillBO skillBO) {
		this.skillBO = skillBO;
	}

	/**
	 * @return List object for SkillBO
	 */
	public List<SkillBO> getSkills() {
		return skills;
	}

	/**
	 * @param skills - Sets List of skills
	 */
	public void setSkills(List<SkillBO> skills) {
		this.skills = skills;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;

	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.RequestAware#setRequest(java.util.Map)
	 */
	@Override
	public void setRequest(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		this.requestMap = requestMap;

	}
	

	public List<SkillBO> getSectionList() {
		return sectionList;
	}



	public void setSectionList(List<SkillBO> sectionList) {
		this.sectionList = sectionList;
	}


}
