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

import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO;
import com.greenbuds.crunchprep.controller.contentcreator.IMailmasterTemplateController;
import com.greenbuds.crunchprep.controller.contentcreator.MailmasterTemplateController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class MailmasterTemplateAction.
 */
public class MailmasterTemplateAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware {
	private static final Logger LOGGER = Logger.getLogger(MailmasterTemplateAction.class);

	private Map<String, Object> sessionMap;
    public MailmasterTemplateBO mailmasterTemplateBO;
    public IMailmasterTemplateController mailmasterTemplateController;
	private HttpServletResponse response;
	private List<MailmasterTemplateBO> mailmasterTemplateList;
	private HttpServletRequest request;
	public void setMailmasterTemplateBO(MailmasterTemplateBO mailmasterTemplateBO) {
		this.mailmasterTemplateBO = mailmasterTemplateBO;
	}

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



	/**
	 * Adds the mailmaster template.
	 *
	 * @return the string
	 */
    
	public List<MailmasterTemplateBO> getMailmasterTemplateList() {
		return mailmasterTemplateList;
	}

	public void setMailmasterTemplateList(
			List<MailmasterTemplateBO> mailmasterTemplateList) {
		this.mailmasterTemplateList = mailmasterTemplateList;
	}

	/**
	 * Adds the mailmaster template.
	 *
	 * @throws JSONException the JSON exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String addMailmasterTemplate() {
			 
		  String result ="Error".toLowerCase();
		
		   System.out.println("Get Template_idDDDDDDDDDDD:"+mailmasterTemplateBO.getTemplate_id());
		   if(mailmasterTemplateBO.getTemplate_id()==0){
		  try{
		           System.out.println("Insert block executed");
			   mailmasterTemplateController = MailmasterTemplateController.getInstance();
			   if(mailmasterTemplateController.addMailmasterTemplate(mailmasterTemplateBO).equalsIgnoreCase("success")){
				  getTemplateList();
				  this.setSuccessMsg("Insert Successfully");
					result="success";
				  
			  }else{
				   System.out.println("Exist block executed");
				  
				  getTemplateList();
				  if(mailmasterTemplateController.addMailmasterTemplate(mailmasterTemplateBO).equalsIgnoreCase("exist")){				 
					     this.setErrorMsg("Save Failed, Record already existed");
					     result="error";
					  
				  }else{
				  
				 this.setErrorMsg("Save Failed");
					result="error";
			   }
			  }
		} catch (CPException e) {
			
			LOGGER.debug(e);
		}
		
		   }else{
			   
			   mailmasterTemplateController = MailmasterTemplateController.getInstance();
				 try {
					if(mailmasterTemplateController.editMailmasterTemplate(mailmasterTemplateBO).equalsIgnoreCase("success")){
						
						getTemplateList();
						
						 this.setSuccessMsg("Update Successfully");
							result="success";
						
					}else{
						
						getTemplateList();
						
						 this.setErrorMsg("Update Failed");
							result="error";
						
					}
				
				} catch (ConnectionException e) {
					LOGGER.debug(e);
				} catch (DBException e) {
				
				   LOGGER.debug(e);
				}
			   
			   
		   }
		return result;
		
		
		
	}
	
	
	
	/**
	 * Gets the template list.
	 *
	 * @return the template list
	 */
	public String getTemplateList(){
		
		
		String result = "Error".toLowerCase();
		  mailmasterTemplateController = MailmasterTemplateController.getInstance();
		
	   try {
		setMailmasterTemplateList(mailmasterTemplateController.mailmasterTemplateList(mailmasterTemplateBO));
		
		result ="success";
	} catch (ConnectionException e) {
		LOGGER.debug(e);
		e.printStackTrace();
	} catch (DBException e) {
		LOGGER.debug(e);
	}
		   

		return result;


		
	}
	
	
	
	/**
	 * Delete mailmaster template.
	 *
	 * @return the string
	 */
	public String deleteMailmasterTemplate() {
		
		
		String result ="Error".toLowerCase();
		
		
		mailmasterTemplateController = MailmasterTemplateController.getInstance();
		try {
			
		
			if(mailmasterTemplateController.deleteMailmasterTemplate(request.getParameterValues("template_ids")).equalsIgnoreCase("success")){
				
				getTemplateList();
				
				 this.setSuccessMsg("Delete Successfully");
				result="success";
			}else if(mailmasterTemplateController.deleteMailmasterTemplate(request.getParameterValues("template_ids")).equalsIgnoreCase("error")){
				
				
				getTemplateList();
				 this.setErrorMsg("Delete Failed");
				result="success";
				
			}
			
			
		} catch (ConnectionException e) {
			LOGGER.debug(e);
		} catch (DBException e) {
			LOGGER.debug(e);
		}
		
	
		return result;
		
	}
	
	

	
	
	public MailmasterTemplateBO getMailmasterTemplateBO() {
		return mailmasterTemplateBO;
	}






	public void setMailMasterTemplateBO(MailmasterTemplateBO mailmasterTemplateBO) {
		this.mailmasterTemplateBO = mailmasterTemplateBO;
	}



	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		 this.sessionMap = sessionMap;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		 this.response = response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		 this.request = request;
		
	}

}
