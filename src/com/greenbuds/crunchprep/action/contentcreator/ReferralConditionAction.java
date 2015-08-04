
/*
 * @(#)ReferralConditionAction.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */


package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.controller.contentcreator.IReferralConditionController;
import com.greenbuds.crunchprep.controller.contentcreator.IReferralMasterController;
import com.greenbuds.crunchprep.controller.contentcreator.ReferralConditionController;
import com.greenbuds.crunchprep.controller.contentcreator.ReferralMasterController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;
/*java API's*/
/*other third Party API's*/
/*Project API's*/

/**
 * @author kkatikala
 * @version 1.0
 * Class to perform actions for referral condition panel in the Content Creator
 *
 */
public class ReferralConditionAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Constant LOGGER.
	 */
	private final static Logger LOGGER = Logger
			.getLogger(ReferralConditionAction.class);
	/**
	 * Referral Master BO to get the list of condition form DAOs
	 */
	ReferralMasterBO referralMasterBO;	
	/**
	 * Controller object for referral Condition Controller to access all methods
	 */
	IReferralConditionController referralConditionController;
	
	/**
	 * Controller Object for Referral Master Controller
	 */
	IReferralMasterController referralMasterController;
	
	/**
	 * List of Referral Conditions
	 */
	private List<ReferralConditionBO> conditionBOs;
	
	/**
	 * ReferralConditionBO class object
	 */
	private ReferralConditionBO referralConditionBO;
	
	/**
	 * Array of String which consists referral condition ids to delete
	 */
	private String[] deleterecords;
	

	HttpServletRequest request;
	HttpServletResponse response;

	

	/*
	 * @return SUCCESS If all the list of conditions are retrieved
	 * Method to get list of all referral conditions
	 * 
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		String result = SUCCESS;
		
		referralMasterController = new ReferralMasterController();
		conditionBOs = referralMasterController.getReferralConditions();
		System.out.println("List size:" + conditionBOs.size());

		return result;
	}

	
	
	
	
	
	
	/**
	 * Actions that are to be performed on referral Conditional Panel
	 * Actions include Save,Update,Delete operations
	 * @throws CPException 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void actionsOnReferralConditions() throws CPException, JSONException, IOException {

		JSONObject jsonObject = new JSONObject();
		String result = "";		

		
			referralConditionBO = getReferralConditionBO();
			referralConditionController = ReferralConditionController.getInstance(); // Creates instance of the ReferralConditionController controller
			
			
			
			String action=request.getParameter("action"); // Parameter to find out the action to be performed
			
			
			
			
			/** Action for Saving referral condition
			 * @returns String "Success" if referral condition saved successfully
			 * 					"Error" If error occured when saving referral condition
			 **/
			
			if (action.equalsIgnoreCase("save")) {
				System.out.println("Saving referral conditions");
				result = referralConditionController
						.saveReferralConditions(referralConditionBO);
				jsonObject.put("result", result);
				System.out.println("Result : " + result);
			} 
			
			
			
			/** Action for Updating referral condition
			 * @returns String "Success" if referral condition updated successfully
			 * 					"Error" If error occured when updating referral condition
			 **/
			
			
			else if(action.equalsIgnoreCase("update")){
				System.out.println("Update Referral  :::: "
						+ referralConditionBO.getCondition_id());
				result = referralConditionController
						.editReferralConditions(referralConditionBO);
				jsonObject.put("result", result);
				System.out.println("Result : " + result);

			}
			
			/** Action for Saving referral condition
			 * @returns String "delete_done" if all records are deleted successfully
			 * 					"foreign@ Referral Condition titles" If there are any child records exists
			 * 					"delete_error" If error occured when deleting referral conditions
			 **/
			
			
			 else if(action.equalsIgnoreCase("delete")){
					System.out.println("Delete Referral  :::: "
							+ deleterecords);
					result = referralConditionController
							.deleteReferralConditions(deleterecords);
					
					
					if(result.contains("foreign"))
						result=result.substring(9,result.length()-1)+" referral conditions have records cannot be deleted..";
					else if(result.equalsIgnoreCase("delete_done"))
						result="Successfully deleted all records";
					else
						result="Oops!!! Sorry... data has not been deleted";
					
					
					jsonObject.put("result", result);
					System.out.println("Result : " + result);

				}

	
			response.getWriter().write(jsonObject.toString()); // Writes response to the Referral condition panel

	}
	
	
	
	
	
	
	
	/**
	 * @return referralConditionBO
	 */
	public ReferralConditionBO getReferralConditionBO() {
		return referralConditionBO;
	}

	/**
	 * @param referralConditionBO
	 */
	public void setReferralConditionBO(ReferralConditionBO referralConditionBO) {
		this.referralConditionBO = referralConditionBO;
	}	

	/**
	 * @return the deleterecords
	 */
	public String[] getDeleterecords() {
		return deleterecords;
	}

	/**
	 * @param deleterecords
	 *            the deleterecords to set
	 */
	public void setDeleterecords(String[] deleterecords) {
		this.deleterecords = deleterecords;
	}

	/**
	 * @return the conditionBOs
	 */
	public List<ReferralConditionBO> getConditionBOs() {
		return conditionBOs;
	}

	/**
	 * @param conditionBOs
	 *            the conditionBOs to set
	 */
	public void setConditionBOs(List<ReferralConditionBO> conditionBOs) {
		this.conditionBOs = conditionBOs;
	}

	/**
	 * @param request
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	/**
	 * @param response
	 */
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;

	}

}
