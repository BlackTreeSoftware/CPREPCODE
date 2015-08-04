package com.greenbuds.crunchprep.dao.admin;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.adminbo.AdminBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;

import com.greenbuds.crunchprep.exception.CPException;

import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.util.DBUtil;




public class AdminDAO implements IAdminDAO{
	
	public List<AdminBO> noofQuestions_per_Day(AdminBO adminBO) throws DBException, SQLException{
		
		
		String result = "Error".toLowerCase();
		
	     Connection con = null;
	     Statement st = null;
	     ResultSet rs = null;
	     List<AdminBO>noofQuestionsperDayList= new ArrayList<AdminBO>();
	     String query = "select cpp.first_name,cpp.last_name,cps.section_name,cpc.category_name,cpt.question_type_name," +
	     		"DATE_FORMAT(cptr.test_date, '%Y-%m-%d')testDate,cpm.test_name,COUNT(cptr.question_id)TotalQuestions," +
	     		"(select COUNT(*) CURRECTRESULT from cp_test_results tr where tr.result ='CORRECT'" +
	     		" and tr.userid=cpp.user_id)CORRECTQuestions ,(select COUNT(*) INCORRESULT " +
	     		"from cp_test_results tr where tr.result='INCORRECT' and tr.userid=cpp.user_id)INCORRECTQuestions," +
	     		"(select COUNT(*) SKIPPED from cp_test_results tr where tr.result='SKIPPED' " +
	     		"and tr.userid=cpp.user_id)SKIPPEDQuestions from cp_user_profiles cpp,cp_test_results cptr," +
	     		"cp_sections cps,cp_question_categories cpc,cp_question_type cpt,cp_testmaster cpm " +
	     		"where cptr.userid=cpp.user_id and cptr.section_id=cps.section_id and cptr.category_id=cpc.category_id" +
	     		" and cptr.type_id=cpt.question_type_id and cptr.test_master_id=cpm.test_id and " +
	     		"cptr.userid like '%' group by cptr.userid";
	     
	     try {
			con = DatabaseConnection.getCrunchPrepConnection();
			st= con.createStatement();
			rs= st.executeQuery(query);
			while(rs.next()){
				
				AdminBO bo = new AdminBO();
				 // bo.setId(rs.getInt("user_id"));
				  bo.setName(rs.getString("first_name")+" "+rs.getString("last_name"));
				  bo.setSection_name(rs.getString("section_name"));
				  bo.setCategory_name(rs.getString("category_name"));
				  bo.setType_name(rs.getString("question_type_name"));
				  bo.setTest_date(rs.getString("testDate"));
				  bo.setTest_name(rs.getString("test_name"));
				  bo.setTotal_questions(rs.getInt("TotalQuestions"));
				  bo.setCorrect_questions(rs.getInt("CORRECTQuestions"));
				  bo.setIncorrect_questions(rs.getInt("INCORRECTQuestions"));
				  bo.setSkipped_questions(rs.getInt("SKIPPEDQuestions"));
				  noofQuestionsperDayList.add(bo);
			
			}
		} catch (ConnectionException e) {
			 throw new DBException(e);
		}finally{
			if(con!=null)
	         con.close();
			
		}
		

	    return noofQuestionsperDayList;
		
		
	}
	
	
public List<AdminBO> noofLessons_per_Day(AdminBO adminBO) throws DBException, SQLException{
		
		
		String result = "Error".toLowerCase();
		
	     Connection con = null;
	     Statement st = null;
	     ResultSet rs = null;
	     List<AdminBO>noofLessonsperDayList= new ArrayList<AdminBO>();
	     String query = "select t4.section_name,t6.lesson_name as MainLesson,t1.lesson_name as SubLesson," +
	     		"t3.email,DATE_FORMAT(t2.lession_date,'%Y-%m-%d'),count(*) from cp_lessons t1,cp_lessons_taken t2," +
	     		"cp_user_registration t3,cp_sections t4,cp_lessons_masters t6 where t1.sublesson_id=t2.sublesson_id and " +
	     		"t3.user_id=t2.user_id and t3.user_id like '%' and t4.section_id=t6.section_id and t1.lesson_id=t6.lesson_id " +
	     		"group by t4.section_name,t6.lesson_name,t1.lesson_name,t3.email,DATE_FORMAT(t2.lession_date,'%Y-%m-%d')" +
	     		"order by DATE_FORMAT(t2.lession_date,'%Y-%m-%d')";
	     
	   //   System.out.println("Query@@@@@@@@@@@@@@:"+query);
	     
	     try {
			con = DatabaseConnection.getCrunchPrepConnection();
			st= con.createStatement();
			rs= st.executeQuery(query);
			while(rs.next()){
				
				AdminBO bo = new AdminBO();
				 // bo.setId(rs.getInt("user_id"));
				 // bo.setName(rs.getString("first_name")+" "+rs.getString("last_name"));
				    bo.setEmail(rs.getString("email"));
				    bo.setSection_name(rs.getString("section_name"));
				    bo.setMainlesson_name(rs.getString("MainLesson"));
				    bo.setSublesson_name(rs.getString("SubLesson"));
				    bo.setLesson_taken_date(rs.getString(5));
				    bo.setNo_lessons(rs.getInt(6));
				    noofLessonsperDayList.add(bo);
			
			}
		} catch (ConnectionException e) {
			 throw new DBException(e);
		}finally{
			if(con!=null)
	         con.close();
			
		}
		

	    return noofLessonsperDayList;
		
		
	}
	
	public void noofUserGraph(JSONObject object) throws JSONException{
		String inputQuiree="select  concat('[',UNIX_TIMESTAMP(t2.reg_date),'000',',',count(t2.reg_date),']') data " +
				"from cp_user_registration t1,cp_user_accounts t2 where t1.user_id=t2.user_id" +
				" group by DATE_FORMAT(t2.reg_date,'%d-%m-%y') order by t2.reg_date asc";
	
		JSONArray array = null;
		Connection con = null;
	    Statement st = null;
	    ResultSet rs = null;
	    
	    try {
			con = DatabaseConnection.getCrunchPrepConnection();
			
			st= con.createStatement();
			rs= st.executeQuery(inputQuiree);
			while(rs.next()){
				if(rs.isFirst()) {
					array=new JSONArray();
					}
				array.put(rs.getString("data"));
			}
	    }catch(ConnectionException ex)
	    {
	    	ex.printStackTrace();
	    	
	    } catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			 
			object.put("data", ""+array.toString().replace("\"", "")+"");
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				 
			}
		}
	    
	    
	}



	public List<AdminBO> getViewLogs()throws CPException{
		
		List<AdminBO> list=new ArrayList<AdminBO>();
		AdminBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "SELECT p.first_name,p.last_name,h.user_id,h.ip_address,DATE_FORMAT(MAX(h.login_date),'%b-%a-%y-%r'), DATE_FORMAT(MAX(h.logout_date),'%b-%a-%y-%r') FROM cp_user_login_history h,cp_user_profiles p WHERE p.user_id=h.user_id group by h.user_id";
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new AdminBO();
            	bo.setName(rs.getString("first_name")+" "+rs.getString("last_name"));
            	bo.setId(rs.getInt("user_id"));
            	bo.setIp(rs.getString("ip_address"));
            	bo.setLogin(rs.getString(5));
            	bo.setLogout(rs.getString(6));
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
				
				
			}
		}
		
		return list;

	}
	
	
	
public List<AdminBO> getMentorAccounts()throws CPException{
		
		List<AdminBO> mentorlist=new ArrayList<AdminBO>();
		AdminBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            int sectionid;
            query = "select r.admin_id,r.name,r.email,r.section_id from cp_admin_registration r where r.role_id=2";
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new AdminBO();
            	bo.setId(rs.getInt("admin_id"));
            	
            	sectionid=rs.getInt("section_id");
            	if(sectionid==1){
            		bo.setType_name("Quantitative");
            	}else if(sectionid==2){
            		bo.setType_name("Verbal");
            	}
            	bo.setName(rs.getString("name")+" : "+bo.getType_name());
            	bo.setEmail(rs.getString("email"));
            	
            	mentorlist.add(bo);
            	
        	   
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
				
				
			}
		}
		
		return mentorlist;

	}
	
	
	
public List<AdminBO> ipAddresses()throws CPException{
		
		List<AdminBO> iplist=new ArrayList<AdminBO>();
		AdminBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "SELECT p.first_name,p.last_name,h.user_id,h.ip_address,DATE_FORMAT(h.login_date,'%b-%d-%Y') FROM cp_user_login_history h,cp_user_profiles p WHERE p.user_id=h.user_id order by h.login_date";
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new AdminBO();
            	bo.setName(rs.getString("first_name")+" "+rs.getString("last_name"));
            	bo.setId(rs.getInt("user_id"));
            	bo.setIp(rs.getString("ip_address"));
            	bo.setLogin(rs.getString(5));
            	iplist.add(bo);
            	
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
		
		return iplist;

	}


public List<AdminBO> adminMentorStudents()throws CPException{
	
	List<AdminBO> adminMentorStudents=new ArrayList<AdminBO>();
	AdminBO bo;
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		java.sql.Statement st = connection.createStatement();
        String query;
        ResultSet rs;
        query = "select distinct p.user_id,p.first_name,p.last_name,r.name from cp_user_profiles p,cp_user_mentors m,cp_admin_registration r where r.admin_id=m.mentor_id and m.user_id=p.user_id ORDER BY p.user_id";
       
        System.out.println(query);
        rs = st.executeQuery(query);
        while(rs.next()) {
        	bo=new AdminBO();
        	bo.setId(rs.getInt("user_id"));
        	bo.setName(rs.getString("first_name")+" "+rs.getString("last_name"));        	
        	bo.setMentorName(rs.getString("name"));
        	
        	adminMentorStudents.add(bo);
        	
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
	
	return adminMentorStudents;

}

}
