package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.controller.contentcreator.ReferralMasterController;
import com.opensymphony.xwork2.ActionSupport;

public class ReferralMasterAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger LOGGER = Logger.getLogger( ReferralMasterAction.class );
	ReferralMasterBO referralMasterBO;
	HttpServletRequest request;
	HttpServletResponse response;
	ReferralMasterController referralMasterController; 
	private List<ReferralConditionBO> conditionBOs;
	private List<ReferralMasterBO> referralMasterBOs;
    private String[] deleterecords;
	
	
	/**
	 * @return the recordsToDelete
	 */
	 


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
	 * @return the referralMasterBOs
	 */
	public List<ReferralMasterBO> getReferralMasterBOs() {
		return referralMasterBOs;
	}



	/**
	 * @param referralMasterBOs the referralMasterBOs to set
	 */
	public void setReferralMasterBOs(List<ReferralMasterBO> referralMasterBOs) {
		this.referralMasterBOs = referralMasterBOs;
	}



	/**
	 * @return the referralMasterBO
	 */
	public ReferralMasterBO getReferralMasterBO() {
		return referralMasterBO;
	}



	/**
	 * @param referralMasterBO the referralMasterBO to set
	 */
	public void setReferralMasterBO(ReferralMasterBO referralMasterBO) {
		this.referralMasterBO = referralMasterBO;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request =  request;
	}
	
	

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
		
	}
	
	

	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		String result = SUCCESS;
		referralMasterController = new ReferralMasterController();
		
		conditionBOs = referralMasterController.getReferralConditions();
		System.out.println("List size:"+conditionBOs.size());
		
		
		referralMasterBOs = referralMasterController.getReferralMasters();
		System.out.println("********************************************************\nReferralMasterBO List Size : "+referralMasterBOs.size()+"\n******************************************************\n");
		 
		
		return result;
	}
	
	
	/**
	 * @return the conditionBOs
	 */
	public List<ReferralConditionBO> getConditionBOs() {
		return conditionBOs;
	}



	/**
	 * @param conditionBOs the conditionBOs to set
	 */
	public void setConditionBOs(List<ReferralConditionBO> conditionBOs) {
		this.conditionBOs = conditionBOs;
	}



	public String saveReferralMaster()  {
		
		 
		String result = "";
		
		try {
			referralMasterBO = getReferralMasterBO();			
			
			referralMasterController = new ReferralMasterController();
			//System.out.println("\n****************************************************************  \niam from save : "+referralMasterBO.getCondition_id()+""+referralMasterBO.getReferral_master_name()+""+referralMasterBO.getReferral_master_quant()+""+referralMasterBO.getReferral_master_verbal()+""+referralMasterBO.getReferral_master_limit()+""+referralMasterBO.getStatus());
			
		int check = 	referralMasterBO.getReferral_master_id();
		System.out.println("Save Checkinh   :  "+referralMasterBO.getReferral_master_id());
		
		//do save operation
		    if(check==0)
		    {		 
		       System.out.println("Saving adata");	
			result = referralMasterController.saveReferralMaster(referralMasterBO);
		
			 
			 System.out.println("Result : "+result);
		    }else{
		    	System.out.println("UPDATE ME ******************"+referralMasterBO.getReferral_master_id());
		    	result = referralMasterController.editReferralMasters(referralMasterBO);
		    	  
				 System.out.println("Result : "+result);
			
		    	
		    	
		    }
		    
			referralMasterBOs = referralMasterController.getReferralMasters();  
			
			request.setAttribute("message", getReferralMasterBO().getResult());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		 return result;
		
		
		 
		
	}



public String deleteReferralMaster()  {
		
		 
		String result = "";
		
		try {
			referralMasterController = new ReferralMasterController();					
		   result = referralMasterController.deleteReferralMasters(deleterecords);	
		   referralMasterBOs = referralMasterController.getReferralMasters();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(result.equalsIgnoreCase("success")){
		request.setAttribute("message", "Data Deleted Sucessfully");}
		else{
			
			request.setAttribute("message", "Data Deleted Failed");}
		 return result;
		
		 
		
	}

}
