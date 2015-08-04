package com.greenbuds.crunchprep.action.contentcreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.AddReferralQuestionBO;
import com.greenbuds.crunchprep.controller.contentcreator.AddReferralQuestionController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;

public class AddReferralQuestionAction extends  ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<AddReferralQuestionBO> conditionList;
	private AddReferralQuestionBO bo;
	@Autowired
	private AddReferralQuestionController addReferralQuestionController;
	private int[] action_id;	
	private String successMsg;
	private String errorMsg;
	
	


	public String getConditions(){
		try {
			conditionList=addReferralQuestionController.getConditions();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String saveQuestionActions(){
		String result="";
		System.out.println("in save method");
		try {
			if(bo.getQuestion_no()==0){
				if(addReferralQuestionController.saveDifficultie(bo).equalsIgnoreCase("success")){
					conditionList=addReferralQuestionController.getConditions();
					this.setSuccessMsg("Action Inserted Successfully");
					result="success";
				}else if(addReferralQuestionController.saveDifficultie(bo).equalsIgnoreCase("exits action")){
					conditionList=addReferralQuestionController.getConditions();
					this.setErrorMsg("Action Already Exists");
					result="error";
				}
			}else{
				if(addReferralQuestionController.updateDifficultie(bo).equalsIgnoreCase("success")){
					conditionList=addReferralQuestionController.getConditions();
					this.setSuccessMsg("Action Updated Successfully");
					result="success";
				}else if(addReferralQuestionController.updateDifficultie(bo).equalsIgnoreCase("exits action")){
					conditionList=addReferralQuestionController.getConditions();
					this.setErrorMsg("Action Updation Failed");
					result="error";
				}
				
			}
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String deleteQuestionActions(){
		String result="";
		try {
			if(addReferralQuestionController.deleteDifficultie(getAction_id()).equalsIgnoreCase("success")){
			conditionList=addReferralQuestionController.getConditions();
			this.setSuccessMsg("Record Deleted Successfully");
			result="success";
		}else {
			conditionList=addReferralQuestionController.getConditions();
			this.setErrorMsg("Record Deleted Failed");
			result="error";
		}
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	public List<AddReferralQuestionBO> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<AddReferralQuestionBO> conditionList) {
		this.conditionList = conditionList;
	}
	public AddReferralQuestionBO getBo() {
		return bo;
	}
	public void setBo(AddReferralQuestionBO bo) {
		this.bo = bo;
	}
	
	public int[] getAction_id() {
		return action_id;
	}

	public void setAction_id(int[] action_id) {
		this.action_id = action_id;
	}
	

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	

}
