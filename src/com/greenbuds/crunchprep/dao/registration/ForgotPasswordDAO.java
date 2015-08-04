/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.registration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;







// TODO: Auto-generated Javadoc
public class ForgotPasswordDAO implements   IForgotPasswordDAO{
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IForgotPasswordDAO#checkmail(java.lang.String)
	 */
	public boolean checkmail(String email)throws CPException{
		 boolean flag=false;
			Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select * from cp_user_registration where email='"+email+"'";
            
            //System.out.println(query);
            rs = st.executeQuery(query);
            if (rs.next()) {
            flag=true;	
            }
		}
		catch(Exception exception){
			
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
		
		return flag;
		
	}
	
	
	/**
	 * Gets the user id.
	 *
	 * @param email the email
	 * @return the user id
	 * @throws CPException the CP exception
	 */
	public int getUserId(String email)throws CPException{
		int user_id=0;
			Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
           String query;
           ResultSet rs;
           query = "select * from cp_user_registration where email='"+email+"'";
           
           //System.out.println(query);
           rs = st.executeQuery(query);
           if (rs.next()) {
          user_id=rs.getInt("user_id");
           }
		}
		catch(Exception exception){
			
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
		
		return user_id;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IForgotPasswordDAO#resetPassword(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 * password Reset functionality
	 */
	public boolean resetPassword(RegistrationBO bo,int user_id) throws CPException{
		boolean flag=false;
		Connection connection=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
           
            query = "update cp_user_registration set  password=SHA1('"+bo.getPassword()+"') where user_id="+user_id;
            
            //System.out.println(query);
           int i= st.executeUpdate(query);
            if (i==1) {
            String query2="select t1.email,t2.first_name,t2.last_name from cp_user_registration t1,cp_user_profiles t2 " +
            		"where t1.user_id="+user_id+" and t1.user_id=t2.user_id";
            resultSet=st.executeQuery(query2);
            if(resultSet.next()){
            	bo.setEmail_id(resultSet.getString("email"));
            	bo.setFirst_name(resultSet.getString("first_name"));
            	bo.setLast_name(resultSet.getString("last_name"));
            }
            flag=true;	
            }
		}
		catch(Exception exception){
			
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
		
		return flag;
		
	}
	
	/**
	 * Token save.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenSave(String token,int user_id)throws CPException{
		boolean flag=false;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
           
            query = "insert into cp_user_forgotpwd_token(user_id,token) values("+user_id+",'"+token+"') ";
            
           // System.out.println(query);
           int i= st.executeUpdate(query);
            if (i==1) {
            flag=true;	
            }
		}
		catch(Exception exception){
			
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
		
		return flag;
	}
	
	/**
	 * Token verify.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean tokenVerify(String token,int user_id)throws CPException{
		boolean flag=false;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select * from cp_user_forgotpwd_token where token='"+token+"' and status='Active'" +" and user_id="+user_id;
            
            //System.out.println(query);
           rs= st.executeQuery(query);
            if (rs.next()) {
            flag=true;	
            }
		}
		catch(Exception exception){
			
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
		
		return flag;
	}
	
	/**
	 * Update token.
	 *
	 * @param token the token
	 * @param user_id the user_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean updateToken(String token,int user_id)throws CPException{
		boolean flag=false;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "update  cp_user_forgotpwd_token set status='Active' where token='"+token+"'"+" and user_id="+user_id;
            
            //System.out.println(query);
           int i= st.executeUpdate(query);
            if (i==1) {
            flag=true;	
            }
		}
		catch(Exception exception){
			
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
		
		return flag;
	}
	

}
