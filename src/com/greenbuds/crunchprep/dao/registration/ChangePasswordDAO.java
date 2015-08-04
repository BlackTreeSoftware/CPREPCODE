package com.greenbuds.crunchprep.dao.registration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.greenbuds.crunchprep.bo.registration.ChangePasswordBo;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.VrplBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

public class ChangePasswordDAO implements IChangepasswordDAO {

	public String updateUserProfile(RegistrationBO registrationBO) {
		// TODO Auto-generated method stub
		String ss = "";
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();

			String query = "UPDATE cp_user_profiles SET first_name='"
					+ registrationBO.getFirst_name() + "', last_name='"
					+ registrationBO.getLast_name() + "', city='"
					+ registrationBO.getCity() + "', phone='"
					+ registrationBO.getPhone_number() + "', previous_score='"
					+ registrationBO.getActual_score()
					+ "', target_score_math="
					+ registrationBO.getTarget_score_math()
					+ ",target_score_verbal="
					+ registrationBO.getTarget_score_verbal()
					+ " WHERE  user_id like '" + registrationBO.getUser_id()
					+ "'";
			System.out.println("update query id " + query);

			int v = st.executeUpdate(query);
			if (v > 0) {
				ss = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ss = "error";
				return ss;
			}

		}
		return ss;
	}

	public RegistrationBO getUserProfile(Integer userid) {
		String ss = "";
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		RegistrationBO bo = null;
		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();

			String query = "SELECT u.user_id,u.first_name,u.last_name,u.address,u.city,u.country,u.phone,u.mobile,u.previous_score,u.target_score_math,u.target_score_verbal,u.test_date,a.start_date,a.end_date FROM cp_user_profiles u ,cp_user_accounts a where     u.user_id=a.user_id  and u.user_id LIKE '"
					+ userid + "'";
			System.out.println("select  query id " + query);

			resultSet = st.executeQuery(query);
			while (resultSet.next()) {
				bo = new RegistrationBO();
				bo.setUser_id(resultSet.getInt("user_id"));
				bo.setFirst_name(resultSet.getString("first_name"));
				bo.setLast_name(resultSet.getString("last_name"));
				bo.setAddress(resultSet.getString("address"));
				bo.setCity(resultSet.getString("city"));
				bo.setCountry(resultSet.getString("country"));
				bo.setPhone_number(resultSet.getString("phone"));
				bo.setActual_score(resultSet.getInt("previous_score"));
				bo.setTarget_score_math(resultSet.getInt("target_score_math"));
				bo.setTarget_score_verbal(resultSet
						.getInt("target_score_verbal"));
				bo.setTest_date(resultSet.getString("test_date"));
				bo.setStart_date(resultSet.getString("start_date"));
				bo.setEnd_date(resultSet.getString("end_date"));

			}

			String query1 = "SELECT s.user_id,s.test_performance,s.profile FROM cp_user_settings s where s.user_id LIKE '"
					+ userid + "'";
			resultSet = st.executeQuery(query1);
			if (resultSet.next()) {

				bo.setTestperformance(resultSet.getString("test_performance"));
				bo.setProfile(resultSet.getString("profile"));

			} else {
				bo.setTestperformance("INACTIVE");
				bo.setProfile("INACTIVE");
			}

			resultSet = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ss = "error";
				return bo;
			}

		}
		return bo;
	}

	public String changePassword(ChangePasswordBo changePasswordBo)
			throws DBException {
		String ss = "";
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {

			System.out.println("In DAO class>>>>>>>>");
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "UPDATE `cp_user_registration` SET password=SHA1('"
					+ changePasswordBo.getPassword() + "') WHERE  `user_id`='"
					+ changePasswordBo.getUserid() + "' ";
			System.out.println("update query id " + query);

			int v = st.executeUpdate(query);
			if (v > 0) {
				ss = "PassWord Changed";
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// return "error";
			}

		}
		return "success";
	}

	public String usersettings(RegistrationBO regBo) throws DBException {
		String s = "";
		ResultSet resultSet = null;
		Connection connection = null;
		CallableStatement cs = null;
		Statement st = null;
		try {
			// String currentuserid = (String)
			// session.getAttribute("userid");pass userid from session
			System.out.println("In DAO class ................");
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String checkuserid = "select * from cp_user_settings us where us.user_id ="
					+ regBo.getUser_id();

			System.out.println("The query is------------" + checkuserid);
			ResultSet rs = st.executeQuery(checkuserid);
			if (rs.next()) {

				String query = "UPDATE `cp_user_settings` SET `test_performance`='"
						+ regBo.getTestperformance()
						+ "' , `profile`='"
						+ regBo.getProfile()
						+ "'      WHERE  `user_id`="
						+ regBo.getUser_id();

				System.out.println("update query id " + query);
				int v = st.executeUpdate(query);
				if (v > 0) {
					s = "settings activated";
				}

			} else {
				String query = "INSERT INTO `cp_user_settings` (`user_id`, `test_performance`,`profile`) VALUES ('"
						+ regBo.getUser_id()
						+ "', '"
						+ regBo.getTestperformance()
						+ "','"
						+ regBo.getProfile() + "')";
				System.out.println("insert query id " + query);
				int v = st.executeUpdate(query);
				if (v > 0) {
					s = "settings activated";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}

		}
		return "success";
	}

	@Override
	public String vrplMethod(VrplBO vrplBO) {
		// TODO Auto-generated method stub
		int votes=0;
		ResultSet resultSet = null;
		Connection connection = null;		
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();	
		
			String action =	vrplBO.getActiontype();
			int userid = vrplBO.getUser_id();
			String insert ="no";
			System.out.println("********** im from DAO *****************"+action+userid);
			String qry = "select *from cp_vrpl vr WHERE vr.user_id = "+userid; 
			resultSet = st.executeQuery(qry);
			int i=1;
			int count=0;
			if(resultSet.next())
			{
			
			while(i<=10)
			{
				String temp =resultSet.getString(i);
				System.out.println("Temp : "+temp);
					if(temp.equalsIgnoreCase("Yes"))
					{
						count++;
						
					}
					
				i++;
			}
			}else{
			  	
				insert = "yes";
			}
			votes = count;
			System.out.println("Total count : "+count+" Votes : "+votes);
			
		 
			//****************** Checking for INSERT OR UPDATE *****************************************
			if(insert=="yes")
			{
				System.out.println("Insert Yes ************************* ");
				qry = "INSERT INTO  `cp_vrpl` (`user_id`, `fb_like`, `fb_share`, `g_like`, `g_share`, `g_follow`, `tw_tweet`, `tw_follow`, `votes`) VALUES ("+userid+", 'No', 'No', 'No', 'No', 'No', 'No', 'No', 0);";
				System.out.println("***** INSERT QRY ************"+qry);
				int k = st.executeUpdate(qry);
				if(k<0){
				System.out.println("New User Data Inserted");					
				}
				insert="no";
			}
			
			
			
			
			if(insert=="no")
			{
				
				if(action.equalsIgnoreCase("fb_like"))
				{
					
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `fb_like`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("fb_like");					
					}
					
				}else if(action.equalsIgnoreCase("fb_unlike"))
				{
					
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `fb_like`='No' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("fb_unlike User Data Inserted");					
					}
					
				}
				
				else if(action.equalsIgnoreCase("fb_share"))
				{
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `fb_share`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("fb_share User Data Inserted");					
					}
					
					
				}
				
				else if(action.equalsIgnoreCase("g_like"))
				{
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `g_like`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("g_like User Data Inserted");					
					}
					
					
					
				}
				
				else if(action.equalsIgnoreCase("g_share"))
				{
					
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `g_share`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("g_share User Data Inserted");					
					}
								
					
					
				}
				
				else if(action.equalsIgnoreCase("g_follow"))
				{
					
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `g_follow`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("g_follow User Data Inserted");					
					}
					
				}
				
				else if(action.equalsIgnoreCase("tw_tweet"))
				{
					
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `tw_tweet`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("tw_tweet User Data Inserted");					
					}
					
				}
				
				else if(action.equalsIgnoreCase("tw_follow"))
				{
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `tw_follow`='Yes' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("tw_follow User Data Inserted");					
					}
					
					
					
				}
				
				else if(action.equalsIgnoreCase("tw_unfollow"))
				{
					System.out.println("Insert Yes ************************* ");
					qry = "UPDATE  `cp_vrpl` SET `tw_follow`='No' WHERE  user_id = "+userid;
					System.out.println("***** INSERT QRY ************"+qry);
					int k = st.executeUpdate(qry);
					if(k>0){
					System.out.println("tw_unfollow User Data Inserted");					
					}
					
					
					
				}else{
					
					System.out.println("***********************  iam From Else of last ***********************************");
					
					
				}	
				
			}
			
			qry = "select *from cp_vrpl vr WHERE vr.user_id = "+userid; 
			resultSet = st.executeQuery(qry);
			 i=1;
			 count=0;
			if(resultSet.next())
			{
			
			while(i<=10)
			{
				String temp =resultSet.getString(i);
				System.out.println("Temp : "+temp);
					if(temp.equalsIgnoreCase("Yes"))
					{
						count++;
						
					}
					
				i++;
			}
			
			
			
			
			}
			
			votes = count;
			if(votes==7){
				
				
		    /**
		     * 
		     * Here the subscription id is free
		     * 		
		     */
			String query="";
			st = connection.createStatement();
			query="select * from cp_user_subscription where user_id="+vrplBO.getUser_id()+" and comments=''";
			System.out.println(query);
			resultSet=st.executeQuery(query);
			if(resultSet.next()){
				query="update cp_user_subscription us set us.comments='7/7 updated',us.practicequestions_math = us.practicequestions_math+"+vrplBO.getQuant_questions()+"," +
				  		"us.practicequestions_verbal=us.practicequestions_verbal+"+vrplBO.getVerbal_question()+"" +
				  		" where  us.subscription_id =1 and us.user_id = "+vrplBO.getUser_id();
			System.out.println(query);
			int k = st.executeUpdate(query);
			if(k!=0){
			System.out.println("tw_unfollow User Data Inserted");					
			}
				
			}
			}
			
			System.out.println("Total count : "+count+" Votes : "+votes);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			
			try {
				//st.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return votes+"";
	}
	
	
	
	public VrplBO getQuestions()  throws CPException{
		// TODO Auto-generated method stub
		VrplBO bo = new VrplBO();
		Connection connection = null;
		ResultSet resultSet = null;
		Statement st = null;
		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select q.question_math,q.question_verbal from cp_add_questions_action q where q.action_type=3";
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				bo.setQuant_questions(resultSet.getInt("question_math"));
				bo.setVerbal_question(resultSet.getInt("question_verbal"));
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return bo;
	}

}
