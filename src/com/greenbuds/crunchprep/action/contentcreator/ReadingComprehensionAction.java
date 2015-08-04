package com.greenbuds.crunchprep.action.contentcreator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.controller.contentcreator.DifficultyController;
import com.greenbuds.crunchprep.controller.contentcreator.IReadingComprehensionController;
import com.greenbuds.crunchprep.controller.contentcreator.ReadingComprehensionController;
import com.greenbuds.crunchprep.controller.contentcreator.ReferralMasterController;
import com.opensymphony.xwork2.ActionSupport;

public class ReadingComprehensionAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	ReadingComprehensionBO readingComprehensionBO;
	DifficultyController difficultyController;
	List < DifficultyBO > difficultyBOs;
	List < ReadingComprehensionBO > readingComprehensionBOs;
	String[] deleterecords; 
	/**
	 * @return the deleterecords
	 */
	public String[] getDeleterecords() {
		return deleterecords;
	}


	/**
	 * @param deleterecords the deleterecords to set
	 */
	public void setDeleterecords(String[] deleterecords) {
		this.deleterecords = deleterecords;
	}


	/**
	 * @return the readingComprehensionBOs
	 */
	public List < ReadingComprehensionBO > getReadingComprehensionBOs() {
		return readingComprehensionBOs;
	}


	/**
	 * @param readingComprehensionBOs the readingComprehensionBOs to set
	 */
	public void setReadingComprehensionBOs(
	List < ReadingComprehensionBO > readingComprehensionBOs) {
		this.readingComprehensionBOs = readingComprehensionBOs;
	}


	IReadingComprehensionController readingComprehensionController;


	/**
	 * @return the difficultyBOs
	 */
	public List < DifficultyBO > getDifficultyBOs() {
		return difficultyBOs;
	}


	/**
	 * @param difficultyBOs the difficultyBOs to set
	 */
	public void setDifficultyBOs(List < DifficultyBO > difficultyBOs) {
		this.difficultyBOs = difficultyBOs;
	}


	public ReadingComprehensionBO getReadingComprehensionBO() {
		return readingComprehensionBO;
	}


	public void setReadingComprehensionBO(
	ReadingComprehensionBO readingComprehensionBO) {
		this.readingComprehensionBO = readingComprehensionBO;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub		
		this.request = request;

	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub		
		String result = "success";

		try {
			System.out.println("1.*****************iam from action class *************  ");
			difficultyController = new DifficultyController();
			difficultyBOs = difficultyController.getDifficulties();
			System.out.println("SIZW : " + difficultyBOs.size());


			readingComprehensionController =ReadingComprehensionController.getInstance();
			readingComprehensionBOs = readingComprehensionController.getReadingComprehension();
			System.out.println("readingComprehensionBOs : " + readingComprehensionBOs.size());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}


	public String saveReadingComprehension() {
		System.out.println("Passage data:" + request.getParameter("passageData"));
		String result = "error";
		try {
			readingComprehensionBO.setPassage(request.getParameter("passageData"));
			readingComprehensionBO = getReadingComprehensionBO();
			readingComprehensionController =ReadingComprehensionController.getInstance();

			int id = readingComprehensionBO.getPassage_id();


			if (id == 0) {
				result = readingComprehensionController.saveReadingComprehension(readingComprehensionBO);
			} else {
				result = readingComprehensionController.editReadingComprehension(readingComprehensionBO);
			}
			readingComprehensionBOs = readingComprehensionController.getReadingComprehension();
			request.setAttribute("message", getReadingComprehensionBO().getResult());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	    }
	
	
	
	
			 
		public String deleteReadingComprehension()  {
			
			 
			String result = "";
			
			try {
				readingComprehensionController =ReadingComprehensionController.getInstance();				
			   result = readingComprehensionController.deleteReadingComprehension(deleterecords);
			   readingComprehensionBOs = readingComprehensionController.getReadingComprehension();
			   
			   System.out.println("****** SIZE **********"+readingComprehensionBOs.size());
			   
			   if(result.equalsIgnoreCase("success")){
					request.setAttribute("message", result);
					 System.out.println("****** SUCCESS **********"+readingComprehensionBOs.size());
				}
			   
				else{						
					request.setAttribute("message", result);
					 System.out.println("****** ERROR **********"+readingComprehensionBOs.size());
				}
			   			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			 return result;
			
			 
			
		

		 
		
	}


		@Override
		public void setServletResponse(HttpServletResponse response) {
			// TODO Auto-generated method stub
			this.response = response;
		}


}