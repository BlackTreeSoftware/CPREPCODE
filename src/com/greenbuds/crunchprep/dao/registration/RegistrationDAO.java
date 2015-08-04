/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : CrunchPrep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.registration;

import java.io.BufferedReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
 
import java.sql.Statement;
 

 
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
 
import com.greenbuds.crunchprep.bo.contentcreator.PolicyBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
 
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.exception.EmailExceptions;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrationDAO.
 */
public class RegistrationDAO implements IRegistrationDAO {

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#registerStudent(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 *  @throws it might throw a Connection Exception 
	 */
	@Override


	/*public boolean registerStudent(RegistrationBO bo) throws ConnectionException, DBException, CommonExceptions  {	*/	// TODO Auto-generated method stub


	/*public boolean registerStudent(RegistrationBO bo) throws CPException {	 
*/

	public boolean registerStudent(RegistrationBO bo) throws CPException, MySQLIntegrityConstraintViolationException  {		 

		 boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
            System.out.println(bo.getReferralCode());
           // System.out.println(bo.getReferralCode()==null?"null":"not null");
            if(bo.getReferralCode()==null){
            	
            	System.out.println("null data");
			String sql="INSERT INTO `cp_user_registration` ( `email`, `password`, `role_id`) VALUES ('"+bo.getEmail_id()+"', SHA1('"+bo.getPassword()+"'), 4)";
			int i=st.executeUpdate(sql);			
			resultSet=st.executeQuery("select LAST_INSERT_ID()");
			if(resultSet.next()){
				bo.setUserId(resultSet.getInt(1));
			}
			if(i==1)
			{
			sql="UPDATE  `cp_user_registration` SET referrel_code='CRINV"+bo.getUserId()+"' WHERE user_id="+bo.getUserId();
			System.out.println(sql);
			int j=st.executeUpdate(sql);
			 if(j==1){
				flag=true;
			 }
			}
			
            }else{
            	System.out.println("in else block");
            	
            	String referUserId=bo.getReferralCode();
            	System.out.println("String Length is\t"+referUserId.length());
            	referUserId=referUserId.substring(5, referUserId.length());
            	System.out.println(referUserId);
            	
            	String sql="INSERT INTO `cp_user_registration` ( `email`, `password`, `role_id`) VALUES ('"+bo.getEmail_id()+"', SHA1('"+bo.getPassword()+"'), 4)";
    			int i=st.executeUpdate(sql);
    			resultSet=st.executeQuery("select LAST_INSERT_ID()");
    			if(resultSet.next()){
    				bo.setUserId(resultSet.getInt(1));
    			}
    			if(i==1)
    			{
    			sql="UPDATE  `cp_user_registration` SET referrel_code='CRINV"+bo.getUserId()+"' WHERE user_id="+bo.getUserId();
    			int j=st.executeUpdate(sql);
    			 if(j==1){
    				 sql="INSERT INTO cp_referral_user(user_id,referral_user_id) VALUES ("+bo.getUserId()+","+referUserId+")";
    				 int k=st.executeUpdate(sql);
    				 if(k==1){
    				flag=true;
    				 }
    			 }
    			}
            }
			 
		}catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e)
		{
			throw new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException();
		}
		catch (ConnectionException e) {
			throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
			 
		} catch (SQLException e) {
			 throw new  CPException(e);
		}  
		finally{			
			 DBUtil.closeConnection(resultSet, st, connection);
		}
 
		 
 
		return flag;
	}
	
	
	public String freeUserReferrals(RegistrationBO bo,int mathQuestions,int verbalQuestions) throws DBException, SQLException{
		
		 String result = "Error".toLowerCase();
		   Connection con = null;
		   Statement st = null;
		   ResultSet rs = null;
		   int count=0;
			try {
				con = DatabaseConnection.getCrunchPrepConnection();
				st = con.createStatement();
				String query="select referral_user_id from cp_referral_user where user_id="+bo.getUserId();
				
				rs =st.executeQuery(query);
				if(rs.next()){
					bo.setUser_id(rs.getInt("referral_user_id"));
					
					query="select count(*) from cp_referral_user where referral_user_id="+bo.getUser_id();
					System.out.println(query);
					rs =st.executeQuery(query);
					if(rs.next()){
						 count=rs.getInt(1);
						 System.out.println("Count in if\t"+count);
						
					}
					 System.out.println("Count at outside if\t"+count);
					if(count<=bo.getRefer_limit()){
					
				
				    query="update cp_user_subscription us set us.practicequestions_math = us.practicequestions_math+"+mathQuestions+"," +
				  		"us.practicequestions_verbal=us.practicequestions_verbal+"+verbalQuestions+"" +
				  		" where  us.subscription_id ="+bo.getSubscription_id()+" and us.user_id = "+bo.getUser_id()+"";
				    System.out.println("Query is\t"+query);
				     if( st.executeUpdate(query)==1){
				    	 
				    	 result ="success";
				     }
				     
					}
				}
			} catch (ConnectionException e) {
				 throw new DBException(e);
			}
			finally{
				if(con!=null){
			
				con.close();
				}
			}
		
		 return result;
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#getTermsofUse()
	 *  @throws it might throw a Connection Exception 
	 */
	@Override
	public String getTermsofUse() throws ConnectionException,CPException  {
		 
		Connection connection=null;
		Statement st=null;
		ResultSet rs=null;
		PolicyBO bo=new PolicyBO();
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			st=connection.createStatement();
			String sql="select ctp.policy_terms  from  cp_terms_policies ctp ";
			rs=st.executeQuery(sql);
			while(rs.next())
			{ 
				bo.setPolicy_terms(rs.getString(1));
			}
			 
			
		} catch (ConnectionException e) {
           throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e) ;
		} catch (SQLException e) {
			 throw new  CPException(e);
		} 
		 
		finally{
			 DBUtil.closeConnection(rs, st, connection);
		}
		return bo.getPolicy_terms();
	}

 
 
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#getCountries()
	 */
	public HashMap<String, String> getCountries() throws CPException {
		// TODO Auto-generated method stub
		HashMap<String,String> countries=new HashMap<String,String>();
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement();
				System.out.println("called");
				rs=st.executeQuery("select id,country_code,country_name from cp_countries order by country_name");
				while(rs.next())
				{ 
					countries.put(rs.getString("country_code"),rs.getString("country_name"));
				} 
			}
			catch(ConnectionException exp){
				throw new ConnectionException(exp);
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return countries;
	}


	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#getOrientationQuestions()
	 */
	@Override
	public List<OrientationsQuestionsBO> getOrientationQuestions()
			throws CPException {
		// TODO Auto-generated method stub
		List<OrientationsQuestionsBO> questions=new ArrayList<OrientationsQuestionsBO>();
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select orientation_question_id,orientation_question,orientation_type,orientation_choice1,orientation_choice2,orientation_choice3,orientation_choice4,orientation_choice4,orientation_choice5,orientation_choice6,orientation_choice7,orientation_choice8,orientation_choice9,orientation_choice10,orientation_instructions from cp_orientation_questions");
				while(rs.next())
				{ 
					 List<String> options=new ArrayList<String>();
					 OrientationsQuestionsBO orientationsQuestionsBO=new OrientationsQuestionsBO();
					 orientationsQuestionsBO.setQuestion(rs.getString("orientation_question"));
					 orientationsQuestionsBO.setQuestion_id(rs.getString("orientation_question_id"));
					 orientationsQuestionsBO.setQuestion_type(rs.getString("orientation_type"));
					 if(rs.getString("orientation_choice1")!=null&&!rs.getString("orientation_choice1").equals(""))
					 {
						 options.add(rs.getString("orientation_choice1"));
					 } 
					 if(rs.getString("orientation_choice2")!=null&&!rs.getString("orientation_choice2").equals(""))
					 {
						 options.add(rs.getString("orientation_choice2"));
					 }
					 if(rs.getString("orientation_choice3")!=null&&!rs.getString("orientation_choice3").equals(""))
					 {
						 options.add(rs.getString("orientation_choice3"));
					 }
					 if(rs.getString("orientation_choice4")!=null&&!rs.getString("orientation_choice4").equals(""))
					 {
						 options.add(rs.getString("orientation_choice4"));
					 }
					 if(rs.getString("orientation_choice5")!=null&&!rs.getString("orientation_choice5").equals(""))
					 {
						 options.add(rs.getString("orientation_choice5"));
					 }
					 if(rs.getString("orientation_choice6")!=null&&!rs.getString("orientation_choice6").equals(""))
					 {
						 options.add(rs.getString("orientation_choice6"));
					 }
					 if(rs.getString("orientation_choice7")!=null&&!rs.getString("orientation_choice7").equals(""))
					 {
						 options.add(rs.getString("orientation_choice7"));
					 }
					 if(rs.getString("orientation_choice8")!=null&&!rs.getString("orientation_choice8").equals(""))
					 {
						 options.add(rs.getString("orientation_choice8"));
					 }
					 if(rs.getString("orientation_choice9")!=null&&!rs.getString("orientation_choice9").equals(""))
					 {
						 options.add(rs.getString("orientation_choice9"));
					 }
					 if(rs.getString("orientation_choice10")!=null&&!rs.getString("orientation_choice10").equals(""))
					 {
						 options.add(rs.getString("orientation_choice10"));
					 }
					 orientationsQuestionsBO.setOptions(options);
					 if(rs.getString("orientation_instructions")==null||rs.getString("orientation_instructions").equals(""))
					 {
					 orientationsQuestionsBO.setInstructions("");
					 }
					 else
					 {
						 orientationsQuestionsBO.setInstructions(rs.getString("orientation_instructions"));
					 }
					 questions.add(orientationsQuestionsBO);
				} 
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return questions;
	}


	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#saveOrientationQuestionsAnswers(java.util.List, com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@Override
	public boolean saveOrientationQuestionsAnswers(List<OrientationsQuestionsBO> answers,RegistrationBO registrationBO) throws CPException {
		// TODO Auto-generated method stub
		 boolean result=false;
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null; 
			int count[] = null;
			try{
				if(savePersonalProfile(registrationBO))
				{
					//result=true;
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement();
					 String query="";

					 String query1="select * from cp_orientation_answers where user_id="+registrationBO.getUserId();
						rs=st.executeQuery(query1);
						if(rs.next()){
							result=true;
						}else{
							for(int i=0;i<answers.size();i++) {
						 OrientationsQuestionsBO orientationsQuestionsBO=(OrientationsQuestionsBO)answers.get(i);  
						 int initial_answer_size=orientationsQuestionsBO.getOptions().size(); 
						 List<String> initial_answers=orientationsQuestionsBO.getOptions();
						 
						 
						 for(int j=0;j<=9-initial_answer_size;j++)
						 {
							 initial_answers.add("");
						 } 
						 
						 query="INSERT INTO `cp_orientation_answers` ( `user_id`, `orientation_question_id`, `orientation_answer1`, `orientation_answer2`, `orientation_answer3`,`orientation_answer4`,`orientation_answer5`,`orientation_answer6`,`orientation_answer7`,`orientation_answer8`,`orientation_answer9`,`orientation_answer10`) VALUES ('"+orientationsQuestionsBO.getUser_id()+"','"+orientationsQuestionsBO.getQuestion_id()+"','"+initial_answers.get(0)+"','"+initial_answers.get(1)+"','"+initial_answers.get(3)+"','"+initial_answers.get(3)+"','"+initial_answers.get(4)+"','"+initial_answers.get(5)+"','"+initial_answers.get(6)+"','"+initial_answers.get(7)+"','"+initial_answers.get(8)+"','"+initial_answers.get(9)+"')";  
					      st.addBatch(query);
						 if(i%20==0)
						 {
							 count=st.executeBatch();
						 }					
						 count= st.executeBatch();
						
					 }
							if(count.length!=0){
								query="update cp_user_activation act set act.profile_status='ACTIVE' where act.user_id="+registrationBO.getUserId();
								System.out.println(query);
								int profile_status=st.executeUpdate(query);
								if(profile_status==1){
									result=true;
								}
							}
							
					 }
				}
				else
				{
					result=false;
				} 
				 
			}
			catch(ConnectionException exp){ 
				result=false;
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
				throw new CommonExceptions(exp);
				
			}
			finally{
				try {
					connection.close();
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new CPException(e);
				}
			}
		 
		return result;
	}
	
	/**
	 * Save personal profile.
	 *
	 * @param registrationBO the registration bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean savePersonalProfile(RegistrationBO registrationBO) throws CPException {
		// TODO Auto-generated method stub
		 boolean result=false;
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			try{

				connection=DatabaseConnection.getCrunchPrepConnection();  
				 String test_date=registrationBO.getTest_date(); 
				st=connection.createStatement();  
				String query1="select * from cp_user_profiles where user_id="+registrationBO.getUserId();
				rs=st.executeQuery(query1);
				if(rs.next()){
					result=true;
				}
				else{
				String query="INSERT INTO `cp_user_profiles` (`user_id`, `first_name`, `last_name`,`city`, `phone`,`previous_score`,`target_score_math`,`target_score_verbal`,`test_date`,`country`) VALUES ('"+registrationBO.getUserId()+"', '"+registrationBO.getFirst_name()+"', '"+registrationBO.getLast_name()+"', '"+registrationBO.getCity()+"',"+registrationBO.getPhone_number()+",'"+registrationBO.getActual_score()+"','"+registrationBO.getTarget_score_math()+"','"+registrationBO.getTarget_score_verbal()+"','"+test_date+"','')";
 

				int n=st.executeUpdate(query);
				if(n>0)
				{
					result=true;
				}
				}
			}
			catch(ConnectionException exp){
				result=false; 
				throw new ConnectionException(exp);
			}
			catch(Exception exp)
			{
				throw new CommonExceptions(exp);
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return result;
	}

 
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#getPrivacyPolicy()
	 *  @throws it might throw a Connection Exception 
	 */
	@Override
	public String getPrivacyPolicy() throws ConnectionException,CPException{
		Connection connection=null;
		Statement st=null;
		ResultSet rs=null;
		PolicyBO bo=new PolicyBO();
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			st=connection.createStatement();
			String sql="select ctp.policy_conditions  from  cp_terms_policies ctp ";
			rs=st.executeQuery(sql);
			while(rs.next())
			{ 
				bo.setPolicy_conditions(rs.getString(1));
			}
			 
		} catch (ConnectionException e) {
			 throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e) ;
		} catch (SQLException e) {
			 throw new  CPException(e);
		}
		 
		finally{
			 DBUtil.closeConnection(rs, st, connection);
		}
		return bo.getPolicy_conditions();
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#tokenGeneration(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@Override
	public boolean tokenGeneration(RegistrationBO registrationBO)
			throws DBException, ConnectionException, CommonExceptions,
			EmailExceptions {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		boolean flag=false;
		try {
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query="INSERT INTO `cp_user_activation` (`user_id`, `token`) VALUES (?,?)";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, registrationBO.getUserId());
			preparedStatement.setString(2, registrationBO.getToken());
			int i=preparedStatement.executeUpdate();
			flag=true;
		} 
		
		catch (ConnectionException connectionException) {
			// TODO: handle exception
			throw new ConnectionException(connectionException);
		}
		
		catch (Exception exception) {
			// TODO: handle exception
			throw new CommonExceptions(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CommonExceptions(e);
			}
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#studentAccountCreation(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@SuppressWarnings("resource")
	@Override
	public boolean studentAccountCreation(RegistrationBO registrationBO)
			throws DBException, ConnectionException, CommonExceptions {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			
			String verifyquery="select * from cp_user_accounts where user_id=?";
			preparedStatement=connection.prepareStatement(verifyquery);
			preparedStatement.setInt(1, registrationBO.getUserId());
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				String query="update cp_user_accounts set start_date=CURRENT_TIMESTAMP(),end_date=DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL ? DAY) where user_id=?";
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setInt(1, registrationBO.getNoDays());
				preparedStatement.setInt(2, registrationBO.getUserId());
				int i=preparedStatement.executeUpdate();
			}else{
			
			String query="INSERT INTO `cp_user_accounts` (`user_id`, `subscription_id`, `start_date`, `end_date`) VALUES (?, ?, CURRENT_TIMESTAMP(),DATE_ADD(CURRENT_TIMESTAMP(),INTERVAL ? DAY))";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, registrationBO.getUserId());
			preparedStatement.setInt(2, registrationBO.getSubscription_id());
			preparedStatement.setInt(3, registrationBO.getNoDays());
			int i=preparedStatement.executeUpdate();
			if(i!=0){
				flag=true;
				String query2="select t1.end_date,t2.email from cp_user_accounts t1,cp_user_registration t2 " +
						" where t1.user_id=t2.user_id and t2.user_id like ? ";
				preparedStatement=connection.prepareStatement(query2);
				preparedStatement.setInt(1, registrationBO.getUserId());
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next()){
					registrationBO.setEnd_date(resultSet.getString(1));
					registrationBO.setEmail_id(resultSet.getString(2));
				}
			    
			}
			}
				
		}
		catch(ConnectionException connectionException){
			throw new CommonExceptions(connectionException);
		}
		catch (Exception exception) {
			// TODO: handle exception
			throw new CommonExceptions(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CommonExceptions(e);
			}
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#tokenVerification(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@Override
	public boolean tokenVerification(RegistrationBO registrationBO)
			throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		Statement st=null;
		ResultSet resultSet=null;
		try{
		 connection=DatabaseConnection.getCrunchPrepConnection();
		 String query1="select TIMESTAMPDIFF(HOUR,cp.reg_date, CURRENT_TIMESTAMP()) as hours,cp.status from cp_user_activation cp where cp.user_id=?";
		 preparedStatement=connection.prepareStatement(query1);
		 preparedStatement.setInt(1, registrationBO.getUserId());
         resultSet=preparedStatement.executeQuery();
         int hours=0;
         String status="";
         if(resultSet.next()){
        	 hours=resultSet.getInt("hours");
        	 //System.out.println("Hours\t"+hours);
        	 status=resultSet.getString("status");
         }
         System.out.println("Hours\t"+hours);
         if(hours<=24 && !status.equalsIgnoreCase("active")){
         
		 String query2="UPDATE `cp_user_activation` SET `active_date`=CURRENT_TIMESTAMP(), status='ACTIVE'  WHERE  `user_id`=? and `token`=?";
		 preparedStatement=connection.prepareStatement(query2);
		 preparedStatement.setInt(1, registrationBO.getUserId());
		 preparedStatement.setString(2, registrationBO.getToken());
		 int i=preparedStatement.executeUpdate();
		 if(i!=0)
			 flag=true;
		}/*else if(hours>24 && !status.equalsIgnoreCase("active") ){
			
			String deletequery1="delete from cp_user_activation where user_id="+registrationBO.getUserId();
			String deletequery2="delete from cp_user_registration where user_id="+registrationBO.getUserId();
			st=connection.createStatement();
			st.addBatch(deletequery1);
			st.addBatch(deletequery2);
			st.executeBatch();
			 flag=false;
		}*/
         
		}
		catch(ConnectionException connectionException){
        	throw new CPException(connectionException);
		}
		catch (Exception exception) {
			// TODO: handle exception
			throw new CPException(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CommonExceptions(e);
			}
		}
		return flag;
	}
	
	
	public boolean tokenVerification2(String token,int userid)throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try{
		 connection=DatabaseConnection.getCrunchPrepConnection();
	
         
		 String query2="UPDATE `cp_user_activation` SET `active_date`=CURRENT_TIMESTAMP(),status='ACTIVE'  WHERE  `user_id`=? and `token`=?";
		 preparedStatement=connection.prepareStatement(query2);
		 preparedStatement.setInt(1, userid);
		 preparedStatement.setString(2, token);
		 int i=preparedStatement.executeUpdate();
		 if(i!=0){
			 flag=true;
		}
         
		}
		catch(ConnectionException connectionException){
        	throw new CPException(connectionException);
		}
		catch (Exception exception) {
			// TODO: handle exception
			throw new CPException(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CommonExceptions(e);
			}
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#tokenReGeneration(com.greenbuds.crunchprep.bo.registration.RegistrationBO)
	 */
	@Override
	public boolean tokenReGeneration(RegistrationBO registrationBO)
			throws DBException, ConnectionException, CommonExceptions,
			EmailExceptions {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		boolean flag=false;
		try {
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query="UPDATE `cp_user_activation` SET `token`=? WHERE `user_id`=?";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, registrationBO.getToken());
			preparedStatement.setInt(2, registrationBO.getUserId());
			int i=preparedStatement.executeUpdate();
			flag=true;
		} 
		
		catch (ConnectionException connectionException) {
			// TODO: handle exception
			throw new ConnectionException(connectionException);
		}
		
		catch (Exception exception) {
			// TODO: handle exception
			throw new CommonExceptions(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new CommonExceptions(e);
			}
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.IRegistrationDAO#userSubscriptionAccess(int)
	 */
	public String userSubscriptionAccess(int user_id) throws DBException,SQLException{
		
		 System.out.println("UserSubscription Acesss@@@@@@@@@@@@@@@@@@@@@@@@@After");
		 String result = "Error".toLowerCase();
		   Connection con = null;
		   Statement st = null;
		   ResultSet rs = null;
			try {
				con = DatabaseConnection.getCrunchPrepConnection();
				st = con.createStatement();
				String query="INSERT INTO cp_user_subscription(user_id,subscription_id,diagnostic_test,study_plan,skill_report," +
						"ask_doubt,notes,bookmark,discussion,score_predictor,confidence_levels,reports,lessons_math," +
						"lessons_verbal,practicequestions_math,practicequestions_verbal,flashcards_math,flashcards_verbal," +
						"fulllength_tests,personal_mentor,awa_feedback,score_guarantee,badges,referral_quesstions)  " +
						"SELECT "+user_id+",subscription_id,diagnostic_test,study_plan,skill_report,ask_doubt,notes,bookmark," +
						"discussion,score_predictor,confidence_levels,reports,lessons_math,lessons_verbal," +
						"practicequestions_math,practicequestions_verbal,flashcards_math,flashcards_verbal," +
						"fulllength_tests,personal_mentor,awa_feedback,score_guarantee,badges,referral_quesstions " +
						"FROM cp_subscription_access";
				    System.out.println("Subscription Query@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+query);
				     if( st.executeUpdate(query)==1){
				    	 
				    	 result ="success";
				     }
			} catch (ConnectionException e) {
				 throw new DBException(e);
			}finally{
				
				if(con!=null)
					con.close();
				
			}
		
		 return result;
		
		
	}
	
	
	public RegistrationBO getQuantVerbalQuestions() throws SQLException, DBException{
		
		Connection connection=null;
		Statement st=null;
		ResultSet rs = null;
		RegistrationBO bo=null;
		try {
			connection=DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query="select q.question_math,q.question_verbal from cp_add_questions_action q where q.action_type=1";
			rs=st.executeQuery(query);
			while(rs.next()){
				bo=new RegistrationBO();
				bo.setQuant_questions(rs.getInt("question_math"));
				bo.setVerbal_questions(rs.getInt("question_verbal"));
			}
			
		}catch (ConnectionException e) {
			 throw new DBException(e);
		}finally{
			
			if(connection!=null)
				connection.close();
			
		}
		
		return bo;
	}
 
}
