package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;


/**
 * The Class MailmasterTemplateDAO.
 */
public class MailmasterTemplateDAO implements IMailmasterTemplateDAO {
	
	public static MailmasterTemplateDAO mailmasterTemplateDAO;
	
	private MailmasterTemplateDAO(){
		
		
		
	}
	public static synchronized MailmasterTemplateDAO getInstance(){
		
		 if(mailmasterTemplateDAO==null)
			 
			  mailmasterTemplateDAO = new MailmasterTemplateDAO();
			  return mailmasterTemplateDAO;
			 
		 
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IMailmasterTemplateDAO#addMailmasterTemplate(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public String addMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException {
		
		String result = "error";
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		
		String check="select cpmt.* from cp_mail_template cpmt where cpmt.template_title like'"+mailmasterTemplateBO.getTemplate_title()+"'";
		
		   try {
			   con=DatabaseConnection.getCrunchPrepConnection();
			   st = con.createStatement();
			   rs = st.executeQuery(check);
			   if(rs.next()){
				   result="exist";
			   }else{
				   
				   
				     String query="insert into cp_mail_template(template_title,template_subject,template_body,status) values (?,?,?,?)";
					 java.sql.PreparedStatement pst2 = con.prepareStatement(query);
				     pst2.setString(1, mailmasterTemplateBO.getTemplate_title());
					 pst2.setString(2, mailmasterTemplateBO.getTemplate_subject());
					 pst2.setString(3, mailmasterTemplateBO.getTemplate_body());
					 pst2.setString(4, mailmasterTemplateBO.getTemplate_status());
					 int i=pst2.executeUpdate();
					  if(i!=0){
						  
						  result="success";
						  
					  }
				  }
				   
		} catch (SQLException e) {
			throw new DBException(e);
		}finally{
			
			DBUtil.closeConnection(st, con);
			
			
		}
		   
		return result;
				
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IMailmasterTemplateDAO#deleteMailmasterTemplate(java.lang.String[])
	 */
	public  String deleteMailmasterTemplate(String[] template_ids) throws ConnectionException, DBException{
	
		
		String result = "Error".toLowerCase();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query;	
		try {
			con=DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			for(int i=0;i<template_ids.length;i++){
			query ="DELETE FROM cp_mail_template WHERE template_id="+template_ids[i];
		   // System.out.println("Delte Query:"+query);
			st.addBatch(query);
			
			
			}
			 int[] i = st.executeBatch();
			 if(i.length!=0) {
		    	  result="success";
		       }else{
		    	   
		    	   result="error";
		    	   
		       }
	
		} catch (SQLException e) {
			e.printStackTrace();
		
		   throw new DBException(Exceptions.CONNECTION_CLOSING_EXCEPTION);
		}finally{
			
		   DBUtil.closeConnection(st, con);
			
		}
	
	
		return result;
		

	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IMailmasterTemplateDAO#mailmasterTemplateList(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public List<MailmasterTemplateBO> mailmasterTemplateList(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException{
		   Connection con = null;
		   Statement st  = null;
		   ResultSet rs = null;
		   
		
		List<MailmasterTemplateBO> mailtemplateList = new ArrayList<MailmasterTemplateBO>();
		
		String query;
		
		query="SELECT template_id, template_title, template_subject, template_body, status FROM cp_mail_template WHERE  `template_id` like '%'";
		
		
		try {
			con=DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			rs =st.executeQuery(query);
			 while(rs.next()){
				 
				 mailmasterTemplateBO = new MailmasterTemplateBO();
				 mailmasterTemplateBO.setTemplate_id(rs.getInt("template_id"));
				 mailmasterTemplateBO.setTemplate_title(rs.getString("template_title"));
				 mailmasterTemplateBO.setTemplate_subject(rs.getString("template_subject"));
				 mailmasterTemplateBO.setTemplate_body(rs.getString("template_body"));
				 mailmasterTemplateBO.setTemplate_status(rs.getString("status"));
				 
				
				 mailtemplateList.add(mailmasterTemplateBO);
				 
			 }
			
		} catch (SQLException e) {
			throw new DBException(e);
		}finally{
			
			 DBUtil.closeConnection(st, con);
			
		}
		
		
		return  mailtemplateList;
	}
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IMailmasterTemplateDAO#editMailmasterTemplate(com.greenbuds.crunchprep.bo.contentcreator.MailmasterTemplateBO)
	 */
	public String editMailmasterTemplate(MailmasterTemplateBO mailmasterTemplateBO) throws ConnectionException, DBException{
		
		System.out.println("Edit function is calling@@@@@@@@@@@@@@@@@@@@");
		
		String result ="Error".toLowerCase();
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String query;
		
		try {
			con=DatabaseConnection.getCrunchPrepConnection();
		
	   	query="UPDATE cp_mail_template SET template_title='"+mailmasterTemplateBO.getTemplate_title()+"',template_subject='"+mailmasterTemplateBO.getTemplate_subject()+"',template_body='"+mailmasterTemplateBO.getTemplate_body()+"',status='"+mailmasterTemplateBO.getTemplate_status()+"' WHERE  template_id="+mailmasterTemplateBO.getTemplate_id()+"";
		
			
		 st = con.createStatement();
		  if(st.executeUpdate(query)==1){
			
			
			result ="success";
			
		}
		
		} catch (SQLException e) {
			throw new DBException(e);
			
		}finally{
			
			  DBUtil.closeConnection(st, con);
			
		}
		
		return result;
		
	}
	
}


	


