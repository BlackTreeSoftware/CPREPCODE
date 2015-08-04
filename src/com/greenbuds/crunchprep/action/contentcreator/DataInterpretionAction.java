package com.greenbuds.crunchprep.action.contentcreator;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.common.Logger;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.DataInterpretionController;
import com.greenbuds.crunchprep.controller.contentcreator.IDataInterpretionController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class DataInterpretionAction.
 */
public class DataInterpretionAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,RequestAware{

	private final static Logger LOGGER = Logger.getLogger(DataInterpretionAction.class);
	private Map<String, Object> sessionMap;
	
	private IDataInterpretionController dataInterpretionController;
	private DataInterpretionBO dataInterpretionBO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<DataInterpretionBO> dataInterpretionList;
	private ICommonsController commonsController;
	private DifficultyBO difficultyBO;
	private Map<String, Object> requestPage;
	private String successMsg;
	private String errorMsg;
	


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



	public DifficultyBO getDifficultyBO() {
		return difficultyBO;
	}



	public void setDifficultyBO(DifficultyBO difficultyBO) {
		this.difficultyBO = difficultyBO;
	}

	public void setDataInterpretionList(
			List<DataInterpretionBO> dataInterpretionList) {
		this.dataInterpretionList = dataInterpretionList;
	}



	public List<DataInterpretionBO> getDataInterpretionList() {
		return dataInterpretionList;
	}
	
	/**
	 * Adds the data interpretion.
	 *
	 * @return the string
	 */
	public String addDataInterpretion() throws ConnectionException{
			
		String result ="Error".toLowerCase();
		
		
	       if(dataInterpretionBO.getGraph_id()==0){
			try {
				 dataInterpretionController = DataInterpretionController.getInstance();
				if(dataInterpretionController.addDataInterpretion(dataInterpretionBO).equalsIgnoreCase("success")){
					getInterpretionList();
					this.setSuccessMsg("Insert Successfully");
					result ="success";
					
				}else {
					getInterpretionList();
					if(dataInterpretionController.addDataInterpretion(dataInterpretionBO).equalsIgnoreCase("exist")){
						
						this.setErrorMsg("Save Failed, Already Existed");
						result ="success";
						
				}else{
					getInterpretionList();
					this.setErrorMsg("Save Failed");
					result="error";
					
				
				}
					}
			} catch (ConnectionException e) {
				LOGGER.debug("It may be either Connection Exception or SQL Exception");
			} catch (DBException e) {
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
			  
			}
	       }else{
	    	      
	    		try {
	    			 dataInterpretionController = DataInterpretionController.getInstance();
					try {
						if(dataInterpretionController.updateDataInterpretion(dataInterpretionBO).equalsIgnoreCase("success")){
							
							getInterpretionList();
							this.setSuccessMsg("Update Successfully");
							result ="success";
							
							
                      }else{
						getInterpretionList();
						this.setErrorMsg("Update  Failed");
						result ="error";
						
						
          }
					} catch (SQLException e) {
						LOGGER.debug("It may be either Connection Exception or SQL Exception");
					
					}
				} catch (DBException e) {
				 LOGGER.debug("It may be either Connection Exception or SQL Exception");
				}
	    	  
	       }
		
	
	  return SUCCESS;	
		
		
	}
	
	
	
	/**
	 * Gets the interpretion list.
	 *
	 * @return the interpretion list
	 */
	public String getInterpretionList(){
		
		
		
		 String  result = "Error".toLowerCase();
		 
		 List<DifficultyBO> difficultyList=null;
		 String difficultyLevels="<option value=-1>Select</option>";
		
		  dataInterpretionController = DataInterpretionController.getInstance();
		 commonsController = CommonsController.getInstance();
			
		 try {
			 if((difficultyList=commonsController.getDifficultiesList())!=null){
				 
			
				 
				  for(DifficultyBO difficulty:difficultyList)
					  
				  {
					
					  difficultyLevels=difficultyLevels+"<option value="+difficulty.getDifficulty_id()+">"+difficulty.getDifficulty_name()+"</option>";
					 
					  
				  }
				
				  
			 }
			 
		} catch (CPException e) {
		
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
		}
		  try {
			    
			  setDataInterpretionList(dataInterpretionController.getDataInterpretionList(dataInterpretionBO));
			  
			        
		} catch (ConnectionException e) {
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
		} catch (DBException e) {
			LOGGER.debug(e);
		} catch (CPException e) {
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
		}
		 
		  requestPage.put("difficultyLevels",difficultyLevels);
		 
		 
		return SUCCESS;
	}
	
	
	/**
	 * Delete data interpretation.
	 *
	 * @return the string
	 */
	public String deleteDataInterpretation(){
	
        String  result = "Error".toLowerCase();
		
		  dataInterpretionController = DataInterpretionController.getInstance();
		  try {
			   System.out.println("Ener into the try catch block");
			  
		    // String ss[]=request.getParameterValues("dataId");
		     /*for(String ss:request.getParameterValues("dataId"))
			  System.out.println("Data Id:"+ss);*/
		    
			  
		   if(dataInterpretionController.getDeleteDataInterpretation(request.getParameterValues("dataId")).equalsIgnoreCase("success")){
			getInterpretionList();
			this.setSuccessMsg("Deleted Successfully");
			result ="success";
			}else{
				getInterpretionList();
				//dataInterpretionList=dataInterpretionController.getDataInterpretionList(getDataInterpretionBO());
				this.setErrorMsg("Deletion  Failed,some Records in use!");
				result ="error";
			}
			
		} catch (ConnectionException e) {
			//this.setErrorMsg("Deletion  Failed");
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
		} catch (DBException e) {
			//this.setErrorMsg("Deletion  Failed");
			LOGGER.debug("It may be either Connection Exception or SQL Exception");
		}
		
		return result;
		
	}
	
	
	
	
	
	
	public DataInterpretionBO getDataInterpretionBO() {
		return dataInterpretionBO;
	}



	public void setDataInterpretionBO(DataInterpretionBO dataInterpretionBO) {
		this.dataInterpretionBO = dataInterpretionBO;
	}
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
	   this.sessionMap = sessionMap;
		
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
	  this.request = request;
	}



	@Override
	public void setServletResponse(HttpServletResponse response) {

         this.response = response;
		
	}
	

	@Override
	public void setRequest(Map<String, Object> requestPage) {
             this.requestPage = requestPage;
		
	}
	
	

	
	

}
