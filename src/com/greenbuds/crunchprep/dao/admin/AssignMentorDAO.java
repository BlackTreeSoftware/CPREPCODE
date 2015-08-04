package com.greenbuds.crunchprep.dao.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.greenbuds.crunchprep.bo.adminbo.MentorBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;

public class AssignMentorDAO implements IAssignMentorDAO{
	
	public List<MentorBO> getMentors(int sectionid)throws CPException{
		List<MentorBO> list=new ArrayList<MentorBO>();
		MentorBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select r.admin_id,r.name from cp_admin_registration r where r.role_id=2 and r.section_id="+sectionid;
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO(); 
            	bo.setMentorId(rs.getInt("admin_id"));
            	bo.setMentorName(rs.getString("name"));
            	list.add(bo);
            	
        	   
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
				// TODO Auto-generated catch block
				
			}
		}
		
		return list;
	}
	
	public List<MentorBO> getStdents()throws CPException{
		List<MentorBO> studentList=new ArrayList<MentorBO>();
		MentorBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "SELECT p.user_id,p.first_name,p.last_name,r.email FROM cp_user_registration r,cp_user_profiles p,cp_user_activation act,cp_user_accounts a WHERE r.user_id=p.user_id and p.user_id=act.user_id and act.user_id=a.user_id  and a.end_date>=CURRENT_TIMESTAMP() and a.subscription_id in (1,2) GROUP BY r.user_id";
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO(); 
            	bo.setUser_id(rs.getInt("user_id"));
            	bo.setStudentName(rs.getString("first_name")+"  "+rs.getString("last_name"));
            	bo.setEmail(rs.getString("email"));
            	studentList.add(bo);
            	
        	   
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
				// TODO Auto-generated catch block
				
			}
		}
		
		return studentList;
	}
	
	public List<MentorBO> getMentorStdents(int sectionid)throws CPException{
		List<MentorBO> mentorStudentList=new ArrayList<MentorBO>();
		MentorBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "SELECT p.user_id,p.first_name,p.last_name,r.email,admin.name FROM cp_user_registration r," +
            		"cp_user_profiles p,cp_user_activation act,cp_user_accounts a,cp_user_mentors m," +
            		"cp_admin_registration admin WHERE r.user_id=p.user_id and p.user_id=act.user_id and " +
            		"act.user_id=a.user_id and m.user_id=r.user_id  and  m.mentor_id=admin.admin_id and admin.section_id="+sectionid;
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO(); 
            	bo.setUser_id(rs.getInt("user_id"));
            	bo.setStudentName(rs.getString("first_name")+"  "+rs.getString("last_name"));
            	bo.setEmail(rs.getString("email"));
            	bo.setMentorName(rs.getString("name"));
            	System.out.println("in DAO Mentor Name\t"+bo.getMentorName());
            	mentorStudentList.add(bo);
            	
        	   
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
				// TODO Auto-generated catch block
				
			}
		}
		
		return mentorStudentList;
	}
	
	
	public String addMentors(MentorBO bo,int userid[],int sectionId)throws CPException{
		String result="";
		
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            for(int i=0;i<userid.length;i++){
           	 query = "insert into cp_user_mentors(user_id,mentor_id,section_id,allocation_staus) values ("+userid[i]+","+bo.getMentorId()+","+sectionId+",'YES')";
           	 System.out.println(query);
           	 st.addBatch(query);
   		}
           //System.out.println(query);
           int[] i = st.executeBatch();
           //System.out.println("Batch size is\t"+i.length);
           if(i.length!=0) {
        	  
           	result="success";
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
				// TODO Auto-generated catch block
				
			}
		}
		
		
		return result;
	}
	
	public List<MentorBO> checkMentorAvilability(MentorBO bo,int[] userid,int sectionId)throws CPException{
		
		List<MentorBO> mentorStudentList=new ArrayList<MentorBO>();
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            for(int i=0;i<userid.length;i++){
            query = "select * from cp_user_mentors m where m.user_id="+userid[i]+" and m.section_id="+sectionId;
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO(); 
            	bo.setUser_id(rs.getInt("user_id"));
            	
            	mentorStudentList.add(bo);
            	
        	   
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
				// TODO Auto-generated catch block
				
			}
		}
		
		return mentorStudentList;
	}
	
	public int getSectionId(MentorBO bo)throws CPException{
		int i=0;
		
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select section_id from cp_admin_registration where admin_id="+bo.getMentorId();
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO(); 
            
            	i=rs.getInt("section_id");
            	
            	
        	   
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
				// TODO Auto-generated catch block
				
			}
		}
		
		return i;
	}
	
	public String deleteMentors(MentorBO bo,int userid[])throws CPException{
		String result="";
		
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            
            
            for(int i=0;i<userid.length;i++){
           	 query = "delete from  cp_user_mentors  where user_id="+userid[i]+" and mentor_id="+bo.getMentorId();
           	 System.out.println(query);
           	 st.addBatch(query);
   		}
            int k[];
           //System.out.println(query);
           int[] i = st.executeBatch();
          
           for(int j=0;j<i.length;j++){
        	  // System.out.println("the individaual values are-----------\t"+i[j]);
        	   
        	if(i[j]==1){
        		result="success";
        	}
        	   
           }
          /* 
           if(k==1){
        	   result="success";
           }else{
        	   result="no record";
           }*/
           System.out.println("Batch size is\t"+i.length);
          /* if(i.length!=0) {
        	  
           	result="success";
          }*/
           
           
           
           
           
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
				// TODO Auto-generated catch block
				
			}
		}
		
		System.out.println("Result Is\t"+result);
		return result;
	}
	
	
	
	public String updateMentors(MentorBO bo,int userid[])throws CPException{
		String result="";
		
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            for(int i=0;i<userid.length;i++){
           	 query = "update  cp_user_mentors set mentor_id="+bo.getMentorId()+" where user_id="+userid[i];
           	 System.out.println(query);
           	 st.addBatch(query);
   		}
           //System.out.println(query);
           int[] i = st.executeBatch();
           //System.out.println("Batch size is\t"+i.length);
           if(i.length!=0) {
        	  
           	result="success";
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
				// TODO Auto-generated catch block
				
			}
		}
		
		
		return result;
	}
	
	
	

}
