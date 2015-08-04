package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO;
import com.greenbuds.crunchprep.dao.contentcreator.MailmasterTemplateDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Class MailmasterTemplateController.
 */
public class MailmasterTemplateController implements IMailmasterTemplateController {
	
	public static MailmasterTemplateController mailmasterTemplateController;
	
	private MailmasterTemplateController(){
		
		
	}
	
    public static synchronized MailmasterTemplateController getInstance(){
    	
    	 if(mailmasterTemplateController==null) mailmasterTemplateController = new MailmasterTemplateController();
		return mailmasterTemplateController;
    	
    }

   private MailmasterTemplateDAO  mailmasterTemplateDAO;	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IMailmasterTemplateController#addMailmasterTemplate(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public String addMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws  CPException{
		
		//System.out.println("MailmasterTemplateController is calling ");
		     mailmasterTemplateDAO=MailmasterTemplateDAO.getInstance();
		
		  return mailmasterTemplateDAO.addMailmasterTemplate(mailmasterTemplateBO);
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IMailmasterTemplateController#mailmasterTemplateList(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public List<MailmasterTemplateBO> mailmasterTemplateList(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException{
		
		//System.out.println("MailmasterTemplateController list  is calling ");
	     mailmasterTemplateDAO = MailmasterTemplateDAO.getInstance(); 
	
	  return mailmasterTemplateDAO.mailmasterTemplateList(mailmasterTemplateBO);
		
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IMailmasterTemplateController#deleteMailmasterTemplate(java.lang.String[])
	 */
	public  String deleteMailmasterTemplate(String[] template_id) throws ConnectionException, DBException{
		
		
		 mailmasterTemplateDAO=MailmasterTemplateDAO.getInstance();
			
		  return mailmasterTemplateDAO.deleteMailmasterTemplate(template_id);
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IMailmasterTemplateController#editMailmasterTemplate(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public String editMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException{
		
		
		 mailmasterTemplateDAO= MailmasterTemplateDAO.getInstance();
			
		  return mailmasterTemplateDAO.editMailmasterTemplate(mailmasterTemplateBO);
		
	}
	
	
	
	
}
