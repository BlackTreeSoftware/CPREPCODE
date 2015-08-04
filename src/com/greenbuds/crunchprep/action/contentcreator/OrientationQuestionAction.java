/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.action.contentcreator;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.controller.contentcreator.IOrientationQuestionController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
public class OrientationQuestionAction extends  ActionSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** implementing Log4j. */
	private static final Logger logger = Logger.getLogger(OrientationQuestionAction.class);
	
	/** The orientations questions bo. */
	private OrientationsQuestionsBO orientationsQuestionsBO;
	
	/** The orientation list. */
	private List<OrientationsQuestionsBO> orientationList;
	
	/** The orientation_id. */
	private String[] orientation_id; 
	
	
	/** The orientation question controller. */
	@Autowired
	private IOrientationQuestionController orientationQuestionController;
	
	/** The success msg. */
	private String successMsg;
	
	/** The error msg. */
	private String errorMsg;
	
	
	

	/**
	 * Gets the orientation questions.
	 *
	 * @return the orientation questions
	 */
	public String getOrientationQuestions(){
		try {
			orientationList=orientationQuestionController.getDifficulties();
			//System.out.println("List Size is\t"+orientationList.size());
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Raised", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * Save orientation questions.
	 *
	 * @return the string
	 */
	public String saveOrientationQuestions(){
		String result="";
		//System.out.println("in save orientation");
		//System.out.println(orientationsQuestionsBO.getQuestion()+"\t"+orientationsQuestionsBO.getQuestion_type()+"\t"+orientationsQuestionsBO.getChoice1());
		
		
			if(orientationsQuestionsBO.getOrientation_question_id()==0){
			try {
				if(orientationQuestionController.saveOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("success")){
					orientationList=orientationQuestionController.getDifficulties();
					this.setSuccessMsg("Question Added Successfully");
					result="success";
				}else if(orientationQuestionController.saveOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("too long data")){
					orientationList=orientationQuestionController.getDifficulties();
					this.setErrorMsg("Question Data Is Too Long");
					result="error";
				}else if(orientationQuestionController.saveOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("exits")){
					orientationList=orientationQuestionController.getDifficulties();
					this.setErrorMsg("Question Is Already Exits");
					result="error";
				}
				else{
						orientationList=orientationQuestionController.getDifficulties();
						this.setErrorMsg("Question Insertion Failed");
						result="error";
					}
			} catch (CPException e) {
				// TODO Auto-generated catch block
				this.setErrorMsg("Question Insertion Failed"+e);
				logger.debug("Exception Raised", e);
				e.printStackTrace();
			}
			
			}else{
				try {
					if(orientationQuestionController.updateOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("success")){
					orientationList=orientationQuestionController.getDifficulties();
					this.setSuccessMsg("Question Updated Successfully");
					result="success";
					}else if(orientationQuestionController.updateOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("too long data")){
						orientationList=orientationQuestionController.getDifficulties();
						this.setErrorMsg("Question Data Is Too Long");
						result="success";
						}
					else if(orientationQuestionController.updateOrientationQuestions(getOrientationsQuestionsBO()).equalsIgnoreCase("exits")){
						orientationList=orientationQuestionController.getDifficulties();
						this.setErrorMsg("Question Is Already Exits");
						result="success";
						}
						else{
						orientationList=orientationQuestionController.getDifficulties();
						this.setErrorMsg("Question Updation Failed");
						result="error";
					}
				} catch (CPException e) {
					// TODO Auto-generated catch block
					logger.debug("Exception Raised", e);
					e.printStackTrace();
				}
								
			}
		
		return result;
	}
	
	/**
	 * Delete orientation questions.
	 *
	 * @return the string
	 */
	public String deleteOrientationQuestions(){
		String result="";
		//System.out.println("Delete Length\t"+orientation_id.length);
		try {
			if(orientationQuestionController.deleteOrientationQuestions(getOrientation_id()).equalsIgnoreCase("success")){
			orientationList=orientationQuestionController.getDifficulties();
			this.setSuccessMsg("Question Deleted Successfully");
			result="success";
			}else if(orientationQuestionController.deleteOrientationQuestions(getOrientation_id()).equalsIgnoreCase("exits")){
				orientationList=orientationQuestionController.getDifficulties();
				this.setErrorMsg("Question Already In Use");
				result="error";
				}
			else{
				orientationList=orientationQuestionController.getDifficulties();
				this.setErrorMsg("Question Deleted Failed");
				result="error";
			}
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Raised", e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * Gets the orientations questions bo.
	 *
	 * @return the orientations questions bo
	 */
	public OrientationsQuestionsBO getOrientationsQuestionsBO() {
		return orientationsQuestionsBO;
	}
	
	/**
	 * Sets the orientations questions bo.
	 *
	 * @param orientationsQuestionsBO the new orientations questions bo
	 */
	public void setOrientationsQuestionsBO(
			OrientationsQuestionsBO orientationsQuestionsBO) {
		this.orientationsQuestionsBO = orientationsQuestionsBO;
	}
	
	/**
	 * Gets the orientation list.
	 *
	 * @return the orientation list
	 */
	public List<OrientationsQuestionsBO> getOrientationList() {
		return orientationList;
	}
	
	/**
	 * Sets the orientation list.
	 *
	 * @param orientationList the new orientation list
	 */
	public void setOrientationList(List<OrientationsQuestionsBO> orientationList) {
		this.orientationList = orientationList;
	}
	
	/**
	 * Gets the orientation_id.
	 *
	 * @return the orientation_id
	 */
	public String[] getOrientation_id() {
		return orientation_id;
	}



	/**
	 * Sets the orientation_id.
	 *
	 * @param orientation_id the new orientation_id
	 */
	public void setOrientation_id(String[] orientation_id) {
		this.orientation_id = orientation_id;
	}
	
	/**
	 * Gets the success msg.
	 *
	 * @return the success msg
	 */
	public String getSuccessMsg() {
		return successMsg;
	}

	/**
	 * Sets the success msg.
	 *
	 * @param successMsg the new success msg
	 */
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg the new error msg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
