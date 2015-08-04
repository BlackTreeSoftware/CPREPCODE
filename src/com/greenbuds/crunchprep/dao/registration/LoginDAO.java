/***************************************************************************
 * Copyright (c) 2014 , sganji , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.registration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;
import javax.servlet.http.HttpSession;
 

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.tomcat.dbcp.dbcp.DbcpException;

 
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.UserDashBoardBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.DateFormate;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.util.DateUtil;

/**
 * The Class LoginDAO.
 */
public class LoginDAO implements ILoginDAO {

	HttpSession session;
	private Map<String, Object> sessionMap;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/* (non-Javadoc) Retrieve user details to login
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#loginUser(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	@Override
	public LoginUserBO loginUser(LoginUserBO loginUserBO) throws CPException{
		// TODO Auto-generated method stub
		LoginUserBO loginuserBo =null;
		ResultSet resultset = null;
		Connection connection = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();

			String query = "SELECT acc.user_id,prof.FIRST_NAME,prof.LAST_NAME,reg.password,reg.email,rol.role , rol.role_id,acc.subscription_id"
					+ " FROM cp_user_accounts acc,cp_user_profiles prof,cp_user_registration reg,cp_roles rol "
					+ " WHERE acc.USER_ID=prof.USER_ID   AND rol.role_id=reg.role_id  and reg.user_id=prof.user_id  AND reg.email LIKE '"
					+ loginUserBO.getEmail_id() + "'";
			System.out.println(query);
			resultset = st.executeQuery(query);	
			if (resultset.next()) {
				loginuserBo = new LoginUserBO();
				loginuserBo.setUser_id(resultset.getInt("USER_ID"));
				loginuserBo.setFirst_name(resultset.getString("FIRST_NAME"));
				loginuserBo.setLast_name(resultset.getString("LAST_NAME"));
				loginuserBo.setEmail_id(resultset.getString("EMAIL"));
				loginuserBo.setPassword(resultset.getString("PASSWORD"));
				loginuserBo.setRole(resultset.getString("role"));
				loginuserBo.setRole_id(resultset.getInt("ROLE_ID"));
				loginuserBo.setSubscription_id(resultset.getInt("subscription_id"));
			}else{
				System.out.println("\n\n\n Quiree is not executed ");
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
		return loginuserBo;

	}
	
	
	public int logincount(LoginUserBO loginUserBO)throws CPException{
		
		int count=0;
		ResultSet resultset = null;
		Connection connection = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();

			String query = "select count(*) from cp_tourdemo h where h.user_id="+loginUserBO.getUser_id();
			System.out.println(query);
			resultset = st.executeQuery(query);	
			if (resultset.next()) {
				count=resultset.getInt(1);
			}else{
				System.out.println("\n\n\n Quiree is not executed ");
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
		return count;

	}

	/**
	 * Checks if is mail exists user.
	 *
	 * @param email the email
	 * @return true, if is mail exists user
	 */
	public boolean isMailExistsUser(LoginUserBO loginUserBO)  throws CPException {
		// TODO Auto-generated method stub
		boolean value = false;
		ResultSet resultSet = null;
		Statement st = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * from cp_user_registration where email like '"+ loginUserBO.getEmail_id() + "' and password like SHA1('"+loginUserBO.getPassword()+"')";
			System.out.println(query);
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				value = true;
				loginUserBO.setUser_id(resultSet.getInt("user_id"));
				loginUserBO.setEmail_id(resultSet.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new DBException(DBException.NOT_ACCESS_EXCEPTION);
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
		return value;
	}

	/**
	 * Checks if is mail exists admin.
	 *
	 * @param email the email
	 * @return true, if is mail exists admin
	 */
	public boolean isMailExistsAdmin(LoginUserBO loginUserBO) throws CPException {
		// TODO Auto-generated method stub
		boolean value = false;
		ResultSet resultSet = null;
		Connection connection = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * from cp_admin_registration where email like '" + loginUserBO.getEmail_id() + "' and password like SHA1('"+loginUserBO.getPassword()+"')";
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				value = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new DBException(DBException.NOT_ACCESS_EXCEPTION);
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
		return value;
	}

	
	
	/* (non-Javadoc) Login for admin,mentor,creator
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#loginOthers(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public LoginUserBO loginOthers(LoginUserBO bo)  throws CPException{
		// TODO Auto-generated method stub
		LoginUserBO loginuserBo = new LoginUserBO();
		Connection connection = null;
		ResultSet resultSet = null;
		Statement st = null;
		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select  r.admin_id,r.name,r.email,r.password,rl.role,r.role_id from cp_admin_registration r,cp_roles rl where r.email like '"
					+ bo.getEmail_id() + "' and r.role_id=rl.role_id";
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				loginuserBo.setUser_id(resultSet.getInt("admin_id"));
				loginuserBo.setFirst_name(resultSet.getString("name"));
				loginuserBo.setLast_name(" ");
				loginuserBo.setEmail_id(resultSet.getString("email"));
				loginuserBo.setPassword(resultSet.getString("password"));
				loginuserBo.setRole(resultSet.getString("role"));
				loginuserBo.setRole_id(resultSet.getInt("role_id"));
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
		return loginuserBo;
	}

	/* (non-Javadoc) save login details into history
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#saveLogin(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public String saveLogin(LoginUserBO loginUserBO)  throws CPException{
		// TODO Auto-generated method stub
		Connection connection = null;
 
		Date date = new Date();
		
		Statement st = null;
		ResultSet resultSet = null;
 
	    String result=null;
		
 
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "INSERT INTO `cp_user_login_history` (`role_id`, `user_id`, `ip_address`) VALUES ("+loginUserBO.getRole_id()+", "+loginUserBO.getUser_id()+", '"+loginUserBO.getIpaddress()+"')";
			System.out.println(query);
			int n = st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet keys = st.getGeneratedKeys();
            if(keys.next())
            {
           	 int key=keys.getInt(1);
             loginUserBO.setLoginHistoryId(key);
            }
			if (n > 1) {
				result = "Record saved";
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
		return result;

	}
	
	public String tourCompletion(LoginUserBO loginUserBO)  throws CPException{
		// TODO Auto-generated method stub
		Connection connection = null;
 
		Date date = new Date();
		
		Statement st = null;
		ResultSet resultSet = null;
 
	    String result=null;
		
 
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "INSERT INTO `cp_user_login_history` (`role_id`, `user_id`, `ip_address`) VALUES ("+loginUserBO.getRole_id()+", "+loginUserBO.getUser_id()+", '"+loginUserBO.getIpaddress()+"')";
			System.out.println(query);
			int n = st.executeUpdate(query);
			            
			if (n !=0) {
				
				query="update cp_user_subscription us set us.practicequestions_math = us.practicequestions_math+"+loginUserBO.getQuant_questions()+"," +
				  		"us.practicequestions_verbal=us.practicequestions_verbal+"+loginUserBO.getVerbal_question()+"" +
				  		" where  us.subscription_id =1 and us.user_id = "+loginUserBO.getUser_id()+"";
				System.out.println(query);
				int j = st.executeUpdate(query);
				            
				if (j !=0) {
					
					 query = "INSERT INTO `cp_tourdemo` ( `user_id`) VALUES ("+loginUserBO.getUser_id()+")";
						System.out.println(query);
						int k = st.executeUpdate(query);
						            
						if (k !=0) {
							result = "Record saved";
						}
				}
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
		return result;

	}
	
	
	
	public LoginUserBO getTourDemoQuestions()  throws CPException{
		// TODO Auto-generated method stub
		LoginUserBO loginuserBo = new LoginUserBO();
		Connection connection = null;
		ResultSet resultSet = null;
		Statement st = null;
		try {

			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select q.question_math,q.question_verbal from cp_add_questions_action q where q.action_type=2";
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				loginuserBo.setQuant_questions(resultSet.getInt("question_math"));
				loginuserBo.setVerbal_question(resultSet.getInt("question_verbal"));
				
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
		return loginuserBo;
	}


	/* (non-Javadoc) Logout
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#logout(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public String logout(LoginUserBO loginUserBO)  throws CPException{
		// TODO Auto-generated method stub

		String result = "";
		Connection connection = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			
			String query = "UPDATE `cp_user_login_history` SET `logout_date`=CURRENT_TIMESTAMP()  where  `login_history_rowid`="+loginUserBO.getLoginHistoryId();
			//String query_session="UPDATE cp_user_activity  set status='INACTIVE' , end_time=CURRENT_TIMESTAMP() where activity_id='"+loginUserBO.getActivity_id()+"'";
			//System.out.println(query_session+"       ");
			System.out.println(query);
			int n = st.executeUpdate(query);
			//if(!(loginUserBO.getActivity_id()==null)){
			//int m = st.executeUpdate(query_session);
			
			if (n > 1) {
				result = "Record Updated";
			}
		//	}

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
		return result;
	}

	/* (non-Javadoc) checks whether user subscription exits or not
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#isSubscriptionActive(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public boolean isSubscriptionActive(LoginUserBO loginUserBO)  throws CPException {
		// TODO Auto-generated method stub
		boolean value = false;
		ResultSet resultSet = null;
		Statement st = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * from cp_user_accounts where user_id like '"+ loginUserBO.getUser_id() + "'  and end_date > '"+DateUtil.getDate(DateFormate.JAVADATE)+"' and subscription_id ='"+loginUserBO.getSubscription_id()+"'";
			resultSet = st.executeQuery(query);
			
			if (resultSet.next()) {
				loginUserBO.setSubscription_status("Active");
				value = true;
			}
			else {
				loginUserBO.setSubscription_status("InActive");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new DBException(DBException.NOT_ACCESS_EXCEPTION);
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
		return value;
	}
	
	public boolean isProfileActive(LoginUserBO loginUserBO)  throws CPException {
		// TODO Auto-generated method stub
		boolean value = false;
		ResultSet resultSet = null;
		Statement st = null;
		Connection connection = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * from cp_user_activation where user_id="+ loginUserBO.getUser_id() + " and profile_status='ACTIVE'";
			resultSet = st.executeQuery(query);
			
			if (resultSet.next()) {
				loginUserBO.setProfile_status("Active");
				value = true;
			}
			else {
				loginUserBO.setProfile_status("InActive");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new DBException(DBException.NOT_ACCESS_EXCEPTION);
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
		return value;
	}
	
	/* (non-Javadoc) Checks whether user account is active or not
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#isAccountActive(com.greenbuds.crunchprep.bo.registration.LoginUserBO)
	 */
	public boolean isAccountActive(LoginUserBO loginUserBO) throws CPException {
		// TODO Auto-generated method stub
		boolean value = false;
		ResultSet resultSet = null;
		Connection connection = null;
		Statement st = null;
		try {
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			String query = "select * from cp_user_activation t1,cp_roles t2,cp_user_registration t3  where t1.user_id IN (select r1.user_id from cp_user_registration r1 where r1.email like '"+loginUserBO.getEmail_id()+"') " +
					       " and status like 'ACTIVE' and t1.user_id=t3.user_id and t2.role_id=t3.role_id ";
			resultSet = st.executeQuery(query);
			if (resultSet.next()) {
				loginUserBO.setAccount_status("Active");
				loginUserBO.setUser_id(resultSet.getInt("user_id"));
				loginUserBO.setRole_id(resultSet.getInt("role_id"));
				value = true;
			}
			else{	
				loginUserBO.setAccount_status("InActive");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new DBException(DBException.NOT_ACCESS_EXCEPTION);
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
		return value;
	}
	
	/**
	 * In this method we get the mail ID and this method is verified if the mail is already registered or not! If mail is already registered then Redirected to signin page otherwise It will saves the new user data and directly goes to user dashboard;
	 *
	 * @return the string
	 */
	public LoginUserBO socialloginUser(LoginUserBO loginUserBO) {
		// TODO Auto-generated method stub
		LoginUserBO us = new LoginUserBO();
		RegistrationBO registrationBO = new RegistrationBO();
		ResultSet resultSet = null;	
		Connection connection=null;
		Statement st = null;
		String email = loginUserBO.getEmail_id();
		String password = " ";
		String check = loginUserBO.getCheck();
		String status = "active";
		int user_id=1;
		int rollid=0;
		int subscrid=0;
		int days=0;
		 
		 

		
		try {
			
			if(check.equalsIgnoreCase("social"))
			{
			connection = DatabaseConnection.getCrunchPrepConnection();
			st = connection.createStatement();
			System.out.println("******************From Login DAO *************************"); 
			System.out.println("*****Email:"+email+"Passwoed: "+password+"Check : "+check);
			
			String qry = "select a.user_id from cp_user_registration a WHERE a.email like '"+email+"'";
			System.out.println("--------> "+qry);
			
			resultSet = st.executeQuery(qry);
			if(resultSet.next())
			{
				System.out.println("Already Registerd");
				loginUserBO.setStatus("registerd");
				loginUserBO.setMessage("Already Registerd! Use Normal Login");
				
			}
			else{
				
				System.out.println("Not Registred");
				
				
				qry = "select r.role_id from cp_roles r where r.role like 'user'";
				resultSet = st.executeQuery(qry);
				if(resultSet.next())
				{
					rollid = resultSet.getInt("role_id");
					System.out.println("ROLEID  : "+rollid);
					
				}
				
				qry = "select max(USER_ID)  FROM  cp_user_registration";
				resultSet = st.executeQuery(qry);
				if(resultSet.next())
				{
					user_id = resultSet.getInt(1);
					user_id++;
					System.out.println("ROLEID  : "+user_id);
					
				}
				
			 
				qry = "select s.subscription_id,s.days_count  from cp_subscription s where s.subscription_type like 'free'";
				System.out.println("Subscription qry : "+qry);
				resultSet = st.executeQuery(qry);
				if(resultSet.next())
				{
					subscrid = resultSet.getInt("subscription_id");
					days = resultSet.getInt("days_count");
					System.out.println("SUBID  : "+subscrid+"Days : "+days);
					
				}
				
				
				qry = "INSERT INTO  cp_user_registration VALUES("+user_id+","+rollid+",'"+email+"','"+password+"','FB&Gmail user')";
				System.out.println("cp_user_registration qry : "+qry);
				int res = st.executeUpdate(qry);
				if(res>0)
				{
					System.out.println(" cp_user_registration VALUES SAVED");
					
				}
				
				
				String startdate = DateUtil.getDate(DateFormate.JAVADATE);
				System.out.println("REG DATE : "+startdate);
				
				
				

				qry = "INSERT INTO `crunchprep`.`cp_user_activation` (`user_id`, `token`, `reg_date`, `active_date`, `status`, `comments`) VALUES ("+user_id+", '"+RandomStringUtils.randomAlphanumeric(30).toUpperCase()+"', '"+startdate+"', '"+startdate+"','ACTIVE', 'fb&gmail active')";
				System.out.println("Token  : "+qry);
				 res = st.executeUpdate(qry);
				if(res>0)
				{
					System.out.println(" Token done");
					//loginUserBO.setStatus("success");
					
				} 
				qry = "INSERT INTO `crunchprep`.`cp_user_accounts` (`user_id`, `subscription_id`, `start_date`, `end_date`) VALUES ("+user_id+","+subscrid+", '"+startdate+"', DATE_ADD('"+startdate+"',INTERVAL "+days+" day))";
				System.out.println("Cp ACCOUNT DATRS: "+qry);
				 res = st.executeUpdate(qry);
				if(res>0)
				{
					System.out.println(" cp_user_accounts VALUES SAVED");
					loginUserBO.setStatus("success");
					
				} 
			}
			
					
			}else{
				System.out.println("Write Code For Login Normol User");
				loginUserBO.setStatus("registerd");
				loginUserBO.setMessage("Already Registerd! Use Normal Login");
				
			} 

		} catch (Exception ex) {
			ex.printStackTrace();

		}finally{
			try {
				if(connection!=null){
					connection.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}	
		return loginUserBO;

	}

	@Override
	public UserDashBoardBO UserDashboard(LoginUserBO loginUserBO) {
		// TODO Auto-generated method stub		
		
		UserDashBoardBO userDashBoardBO = new UserDashBoardBO();
		ResultSet resultset = null;
		Connection connection = null;
		Statement statement = null;
		try {
			
			int userid = loginUserBO.getUser_id();
			System.out.println("******* IAM FROM UserDashBoard DAO***************"+loginUserBO.getUser_id());
			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();
			
			//******** Quantative Query
			String qry = "SELECT count(DISTINCT(r.question_id)) as totalqsns, ( select count(DISTINCT(r.question_id)) from cp_test_results r WHERE r.userid= "+userid+" and r.result='correct' and r.section_id =1) as correct, (select SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))/ COUNT(*))  from cp_test_results r WHERE r.userid= "+userid+"  and r.section_id =1) as avgtime, (SELECT COUNT(*) FROM cp_user_login_history h WHERE h.user_id="+userid+") as count from cp_test_results r WHERE r.userid= "+userid+" and r.section_id =1 ";
			
			System.out.println("Quant query : "+qry);
			resultset = statement.executeQuery(qry);
			
	           if(resultset.next())
	           {
	        	   int total = resultset.getInt(1);
	        	   int correct = resultset.getInt(2);
	        	   String time = resultset.getString(3);
	        	   int count=resultset.getInt(4);
	        	   if(time==null){
	        		   
	        		   time = "00:00:00";
	        	   }
	        	   
	        	   
	        	   int percentcorrect = 0;
	        	   if(total>0)
	        	   {
	        		   percentcorrect = (correct*100/total);
	        		   
	        	   }
	        	   
	        	   userDashBoardBO.setMath_questions_practiced(total);
	        	   userDashBoardBO.setMath_questions_correct(correct);
	        	   userDashBoardBO.setMath_avg_time(time);
	        	   userDashBoardBO.setMath_questions_correct_percent(percentcorrect);
	        	   userDashBoardBO.setNoOfTimesLogin(count);
	        	  
	        	   
	           }
				
	           qry = "SELECT count(DISTINCT(r.question_id)) as totalqsns, ( select count(DISTINCT(r.question_id)) from cp_test_results r WHERE r.userid= "+userid+" and r.result='correct' and r.section_id =2) as correct, (select SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))/ COUNT(*))  from cp_test_results r WHERE r.userid= "+userid+"  and r.section_id =2) as avgtime from cp_test_results r WHERE r.userid= "+userid+" and r.section_id =2 ";
	           System.out.println("Verbal query : "+qry);
				resultset = statement.executeQuery(qry);
				
	           if(resultset.next())
	           {
	        	   
	        	   int total = resultset.getInt(1);
	        	   int correct = resultset.getInt(2);
	        	   String time = resultset.getString(3);
	        	   
	        	   if(time==null){
	        		   
	        		   time = "00:00:00";
	        	   }
	        	   
	        	   
	        	   int percentcorrect = 0;
	        	   if(total>0)
	        	   {
	        		   percentcorrect = (correct*100/total);
	        		   
	        	   }
	        	   userDashBoardBO.setVerbal_questions_practiced(total);
	        	   userDashBoardBO.setVerbal_questions_correct(correct);
	        	   userDashBoardBO.setVerbal_avg_time(time);
	        	   userDashBoardBO.setVerbal_questions_correct_percent(percentcorrect);
	        	  
	        	   
	           } 
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{			
			DBUtil.closeConnection(resultset, statement, connection);
		}
		
		
		return userDashBoardBO;
	}

	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.registration.ILoginDAO#freeUserReferrals(int, int, int)
	 */
	
	
	

}
