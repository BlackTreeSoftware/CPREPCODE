/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.lessons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Class LessonsActionsDAO.
 */
public class LessonsActionsDAO implements ILessonsActionsDAO {
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.lessons.ILessonsActionsDAO#lessonsActions(com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	@SuppressWarnings("resource")
	public boolean lessonsActions(LessonsmasterBO bo)throws CPException{
		boolean flag=false;
		
		Connection connection=null;
		ResultSet rs=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query,query1;
            
				 java.sql.Statement st = connection.createStatement();
				 
				 query="select m.section_id from cp_lessons l,cp_lessons_masters m where   m.lesson_id=l.lesson_id  and  l.sublesson_id="+bo.getSublesson_id();
				 System.out.println("Section Id Query\t"+query);
				 rs = st.executeQuery(query);
				 if(rs.next()){
				 bo.setSection_id(rs.getInt("section_id"));
            	 query1 = "select a.email from cp_admin_registration a,cp_user_mentors m  where m.mentor_id=a.admin_id and m.user_id="+bo.getUser_id()+" and a.section_id="+bo.getSection_id();
            	 System.out.println("Mentor Email Query is\t"+query1);
            	 rs = st.executeQuery(query1);
     			 if(rs.next()){
     				
     				bo.setEmail(rs.getString("email"));
     				query = "insert into cp_lesson_actions(user_id,sublesson_id,lesson_action_type,lesson_action_title,lesson_action_description,lesson_action_date) values("+bo.getUser_id()+","+bo.getSublesson_id()+",'"+bo.getAction_type()+"','"+bo.getAction_title()+"','"+bo.getAction_desc()+"',"+"CURRENT_TIMESTAMP())";
     	            System.out.println("Insert Query is\t"+query);
     				
     				int i = st.executeUpdate(query);
     	            if(i==1) {
     				
     	            	flag=true;
        			}
        	  
                }
     			else{
     			//System.out.println("in else part");
     				bo.setEmail("mentor@crunchprep.com");
     				flag=true;
     				/*query = "insert into cp_lesson_actions(user_id,sublesson_id,lesson_action_type,lesson_action_title,lesson_action_description,lesson_action_date) values("+bo.getUser_id()+","+bo.getSublesson_id()+",'"+bo.getAction_type()+"','"+bo.getAction_title()+"','"+bo.getAction_desc()+"',"+"CURRENT_TIMESTAMP())";
     	            System.out.println("Insert Query is\t"+query);
     				
     				int i = st.executeUpdate(query);
     	            if(i==1) {
     				
     	            	flag=true;
        			}*/
					
				 }
     			 
     			 
				}
				 
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally{
			try {
				if(connection!=null){
				connection.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return flag;	
	}
	
	/**
	 * Gets the mentor email.
	 *
	 * @param bo the bo
	 * @return the mentor email
	 */
	public boolean getMentorEmail(LessonsmasterBO bo){
		
		boolean flag=true;
		Connection connection=null;
		ResultSet rs=null;
		String name="";
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query;
            
            query = "select a.email from cp_admin_registration a,cp_user_mentors m  where m.mentor_id=a.admin_id and m.user_id="+bo.getUser_id();
            System.out.println("Query is\t"+query);
			java.sql.Statement st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()){
				
				bo.setEmail(rs.getString("email"));
			}
            
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally{
			try {
				if(connection!=null){
				connection.close();
			}
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return flag;
		
	}
	

}
