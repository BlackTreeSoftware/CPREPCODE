/*
 * @(#)CriticalReasoning.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */
package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.contentcreator.CriticalReasoningBo;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.CriticalReasoningController;
import com.greenbuds.crunchprep.controller.contentcreator.ICriticalReasoningController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.AccountStatus;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
* This class contains all the action methods required for the Critical Reasoning  frame in Content Creator
*
* @version 1.0
* @author Sahitya Mittapalli
*/
public class CriticalReasoning  extends ActionSupport implements ModelDriven<CriticalReasoningBo>,Preparable,ServletResponseAware ,ServletRequestAware{
	
	/*Class Implementation Model Driven Interface for Making CriticalReasoningBo available in the action class,
	 * and implements preparable for initializing the CriticalReasoning bean before calling Action method,
	 * and ServletResponseAware and ServletRequestAware for getting response and request Objects*/
	private static final long serialVersionUID = 1L;
	

	/** The Constant LOGGER. */
    private final static Logger LOGGER = Logger.getLogger(CriticalReasoning.class );
    
    /*Tests List*/
    private List<TestsBO> tests_list;
    
    /*Difficulties List*/
	private List<DifficultyBO> difficulties_list;
	
	/*Skills List*/
	private List<SkillBO> skills_list;
	
	/*All the critical Reasoning Beans List*/
	private List<CriticalReasoningBo> list;
	
	/*Variable used to hold all the selected question Id's While Deleting*/
	private String deleteIds; 
	
	/*Used to Hold Selected Question ID while Updating*/
	private int questionId;
	
	/*Critical Reasoning Bean object*/
	private CriticalReasoningBo criticalReasoningBo;	
    
	/*Controller Objects Used in  this Action Class*/
	private ICommonsController commonsController; 
	private ICriticalReasoningController criticalReasoningController;
	
	/*Request and Response Objects*/
	private HttpServletResponse response;
	private HttpServletRequest request;
	 
	

/**
 * this is used to get All the Tests List,Difficulty levels List,Skills List for Critical Reasoning 
 * @return "success" when all the data loaded
 */  
	public String getPredata()
	{
		commonsController=CommonsController.getInstance();
		try {
			setTests_list(commonsController.getTestsList());
			setDifficulties_list(commonsController.getDifficultiesList());
			setSkills_list(commonsController.getSkillsList(request.getParameter("sectionId").toString()));
			 
		} catch (ConnectionException e) {		 
			e.printStackTrace();
		} catch (DBException e) {			 
			e.printStackTrace();
		}catch (CPException e) {			 
			e.printStackTrace();
		}
		return "success";
	}
	

/**
 * this is used save/update New CriticalReasoning Question
 * @return "success" when New Data Added
 *         "update" when Already Existed Question Updated
 *         "error"   when saving/updating failed
 */  
	public void savenewCriticalReasoning()
	{
		JSONObject jsonObject=new JSONObject();
		String result="error";
		criticalReasoningController=CriticalReasoningController.getInstance();
		try {
			if(criticalReasoningBo.getAccessType()!=null && criticalReasoningBo.getAccessType().equalsIgnoreCase("Free"))
			{
				criticalReasoningBo.setAccessType(AccountStatus.FREE.name());
			}
			else
				criticalReasoningBo.setAccessType(AccountStatus.PAID.name());
			if(criticalReasoningBo.getStatus().equalsIgnoreCase("ACTIVE"))
			{
				criticalReasoningBo.setStatus(AccountStatus.ACTIVE.name());
			}
			else
			{
				criticalReasoningBo.setStatus(AccountStatus.INACTIVE.name());
			}
			 
			if(criticalReasoningBo.getReferralType()!=null && criticalReasoningBo.getReferralType().equalsIgnoreCase("Referal"))
			{
				criticalReasoningBo.setReferralType("YES");
			}
			else
			{
				criticalReasoningBo.setReferralType("NO");
			}
			if(criticalReasoningBo.getQuestionId()==0)
			{
			
			
			if(criticalReasoningController.saveNewCriticalReasoning(criticalReasoningBo))
			{
				result="success";
				criticalReasoningBo.setQuestionId(0);
			}
			}
			else
			{
				//System.out.println("Calling Update Critical Reasoning");
				if(criticalReasoningController.updateCriticalReasoning(criticalReasoningBo)){
					result="update";
					criticalReasoningBo.setQuestionId(0);
				}
				else
				{
					result="error";
				}
			}
		} catch (ConnectionException e) {			 
			e.printStackTrace();
		}
		try {
			jsonObject.put("result",result);
			response.getWriter().write(jsonObject.toString());
		} catch (JSONException e) {		 
			e.printStackTrace();
		} catch (IOException e) {			 
			e.printStackTrace();
		}
	
		 
	}
	

/**
 * this is used to get All criticalReasoning Questions from the question pool 
 * @return "success" when all the data loaded
 */  
	public String getAllquestions()
	{
		criticalReasoningController=CriticalReasoningController.getInstance();
		try {
			setList(criticalReasoningController.getAlltheCriticalReasoningQuestions());
		} catch (ConnectionException e) {			 
			e.printStackTrace();
		}
		
		return "success";
	}
	

/**
 * this is used to delete the selected question 
 * @return "success" when record deleted successfully
 *         "error"   when record deleting failed
 */  
	public String deleteQuestions()
	{
		String result="error";
		criticalReasoningController=CriticalReasoningController.getInstance();
		try {
			if(criticalReasoningController.deleteSelectedCriticalQuestions(deleteIds))
			{
				result="success";
				setList(criticalReasoningController.getAlltheCriticalReasoningQuestions());
			}
		} catch (ConnectionException e) {			 
			e.printStackTrace();
		}
		return result;
	}
	

/**
 * this is used to get Selected Question data while Edit
 * @return "success" when question data loaded successfully
 */  
	public String editCriticalReasoning()
	{   
	     setQuestionId((Integer)request.getAttribute("questionId"));
		 criticalReasoningController=CriticalReasoningController.getInstance();
		 try {
			setCriticalReasoningBo(criticalReasoningController.getSingleCriticalQuestion(getQuestionId()));
		} catch (ConnectionException e) {			 
			e.printStackTrace();
		}
		return "success";
	}
	
	/*Setter and getter Methods*/
	public List<TestsBO> getTests_list() {
		return tests_list;
	}
	public void setTests_list(List<TestsBO> tests_list) {
		this.tests_list = tests_list;
	}
	public List<DifficultyBO> getDifficulties_list() {
		return difficulties_list;
	} 
	public void setDifficulties_list(List<DifficultyBO> difficulties_list) {
		this.difficulties_list = difficulties_list;
	}
	public List<SkillBO> getSkills_list() {
		return skills_list;
	}
	public void setSkills_list(List<SkillBO> skills_list) {
		this.skills_list = skills_list;
	}
	public CriticalReasoningBo getCriticalReasoningBo() {
		return criticalReasoningBo;
	}
	public void setCriticalReasoningBo(CriticalReasoningBo criticalReasoningBo) {
		this.criticalReasoningBo = criticalReasoningBo;
	}
	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public List<CriticalReasoningBo> getList() {
		return list;
	}

	public void setList(List<CriticalReasoningBo> list) {
		this.list = list;
	}
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	/*Overriden Methods*/
	@Override
	public void prepare() throws Exception {
		criticalReasoningBo=new CriticalReasoningBo();
		
	}
	@Override
	public CriticalReasoningBo getModel() {
		 
		return criticalReasoningBo;
	}
   
	@Override
	public void setServletResponse(HttpServletResponse response) {
		 this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
		
	} 
		
}
