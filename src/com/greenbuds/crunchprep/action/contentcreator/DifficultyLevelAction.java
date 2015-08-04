/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.action.contentcreator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.controller.contentcreator.IDifficultyController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionSupport;

// TODO: Auto-generated Javadoc
public class DifficultyLevelAction extends  ActionSupport  {
	
	/**
	 * implementing Log4j
	 */
	private static final Logger logger = Logger.getLogger(DifficultyLevelAction.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The difficulty list. */
	private List<DifficultyBO> difficultyList;
	
	/** The difficulty bo. */
	private DifficultyBO difficultyBO;
	
	/** The diff_id. */
	private String[] diff_id;
	
	/** The difficulty controller. */
	@Autowired
	private IDifficultyController difficultyController;
	
	private String successMsg;
	private String errorMsg;
	
	
	/**
	 * Gets the difficulties.
	 *
	 * @return the difficulties
	 */
	public String getDifficulties(){
		try {
			difficultyList=new ArrayList<DifficultyBO>();
			difficultyList=difficultyController.getDifficulties();
			//System.out.println("List Size is\t"+difficultyList.size());
		} catch (CPException e) {
			// TODO Auto-generated catch block
			logger.debug("Exception Raised", e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * Save difficultie.
	 *
	 * @return the string
	 */
	public String saveDifficultie(){
		String result="";
		//System.out.println("in savedissiculties");
		//System.out.println(difficultyBO.getDifficulty_name()+"\t"+difficultyBO.getDifficulty_rating()+"\t"+difficultyBO.getDifficulty_id());
		if(difficultyBO.getDifficulty_id()==0){
			try {
				if(difficultyController.saveDifficultie(getDifficultyBO()).equalsIgnoreCase("success")){
					difficultyList=difficultyController.getDifficulties();
					this.setSuccessMsg("Difficulty Inserted Successfully");
					result="success";
				}else if(difficultyController.saveDifficultie(getDifficultyBO()).equalsIgnoreCase("exits difficulty")){
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Difficulty Already Exists");
					result="error";
				} 
				else if(difficultyController.saveDifficultie(getDifficultyBO()).equalsIgnoreCase("exits rating")){
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Rating  Already Exists");
					result="error";
				} else{
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Difficulty Insertion Failed");
					result="error";
				}
			} catch (CPException e) {
				// TODO Auto-generated catch block
				logger.debug("Exception Raised", e);
				e.printStackTrace();
			}
		}else{
			try {
				if(difficultyController.updateDifficultie(getDifficultyBO()).equalsIgnoreCase("success")){
					difficultyList=difficultyController.getDifficulties();
					this.setSuccessMsg("Difficulty Updated Successfully");
					result="success";
				}else if(difficultyController.updateDifficultie(getDifficultyBO()).equalsIgnoreCase("exits difficulty")){
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Difficulty  Already Exists");
					result="error";
				}
				else if(difficultyController.updateDifficultie(getDifficultyBO()).equalsIgnoreCase("exits rating")){
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Rating  Already Exists");
					result="error";
				}
				else{
					difficultyList=difficultyController.getDifficulties();
					this.setErrorMsg("Difficulty Updation Failed");
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
	 * Delete difficultie.
	 *
	 * @return the string
	 */
	public String deleteDifficultie(){
		String result="";
		//System.out.println("in delete diffculties get data");
		//System.out.println("Deleted size is"+diff_id.length);
		/*for(int i=0;i<=this.getDiff_id().length;i++){
			System.out.println(diff_id[i]);
		}*/
		//System.out.println(diff_id.length);
		try {
			if(difficultyController.deleteDifficultie(getDiff_id()).equalsIgnoreCase("success")){
			difficultyList=difficultyController.getDifficulties();
			this.setSuccessMsg("Record Deleted Successfully");
			result="success";
			}else if(difficultyController.deleteDifficultie(getDiff_id()).equalsIgnoreCase("exits")){
				difficultyList=difficultyController.getDifficulties();
				this.setErrorMsg("Record Already In Use");
				result="error";
				}
			 else{
				difficultyList=difficultyController.getDifficulties();
				this.setErrorMsg("Record Deletion Failed");
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
	 * Gets the difficulty list.
	 *
	 * @return the difficulty list
	 */
	public List<DifficultyBO> getDifficultyList() {
		return difficultyList;
	}

	/**
	 * Sets the difficulty list.
	 *
	 * @param difficultyList the new difficulty list
	 */
	public void setDifficultyList(List<DifficultyBO> difficultyList) {
		this.difficultyList = difficultyList;
	}

	/**
	 * Gets the difficulty bo.
	 *
	 * @return the difficulty bo
	 */
	public DifficultyBO getDifficultyBO() {
		return difficultyBO;
	}

	/**
	 * Sets the difficulty bo.
	 *
	 * @param difficultyBO the new difficulty bo
	 */
	public void setDifficultyBO(DifficultyBO difficultyBO) {
		this.difficultyBO = difficultyBO;
	}
	
	/**
	 * Gets the diff_id.
	 *
	 * @return the diff_id
	 */
	public String[] getDiff_id() {
		return diff_id;
	}

	/**
	 * Sets the diff_id.
	 *
	 * @param diff_id the new diff_id
	 */
	public void setDiff_id(String[] diff_id) {
		this.diff_id = diff_id;
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
