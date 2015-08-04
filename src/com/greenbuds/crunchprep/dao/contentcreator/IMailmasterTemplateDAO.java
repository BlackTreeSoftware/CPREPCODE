package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO;

import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Interface IMailmasterTemplateDAO.
 */
public interface IMailmasterTemplateDAO {
	
	
	/**
	 * Adds the mailmaster template.
	 *
	 * @param mailmasterTemplateBO the mailmaster template bo
	 * @return the mailmaster template bo
	 * @throws ConnectionException 
	 * @throws CPException 
	 * @throws DBException 
	 */
	public String addMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws  ConnectionException, DBException;
	
	/**
	 * Delete mailmaster template.
	 *
	 * @param template_id the template_id
	 * @return the string
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public  String deleteMailmasterTemplate(String[] template_id) throws ConnectionException, DBException;


	/**
	 * Mailmaster template list.
	 *
	 * @param mailmasterTemplateBO the mailmaster template bo
	 * @return the list
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public List<MailmasterTemplateBO> mailmasterTemplateList(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException;

	/**
	 * Edits the mailmaster template.
	 *
	 * @param mailmasterTemplateBO the mailmaster template bo
	 * @return the string
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public String editMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException;
	
	
}
