package com.greenbuds.crunchprep.dao.mentor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.greenbuds.crunchprep.bo.mentor.MentorBO;
import com.greenbuds.crunchprep.bo.mentor.UserProgressBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

public class MentorDAO implements IMentorDAO {
  
	public List<MentorBO> mentorAssignedStudentList(int mentorId) throws DBException, SQLException{
		
		Connection con = null;
		Statement st = null;
	    ResultSet rs = null;
	    List<MentorBO> mentorassignedStudentList = new ArrayList<MentorBO>();
	     String query ="select p.first_name,p.last_name,r.email,p.mobile,a.start_date,a.end_date,ac.status" +
	     		" from cp_user_mentors c,cp_user_profiles p,cp_user_registration r,cp_user_accounts a," +
	     		"cp_user_activation ac where c.mentor_id ="+mentorId+" and c.user_id=p.user_id and" +
	     		" c.user_id = r.user_id and c.user_id=a.user_id and c.user_id=ac.user_id";	
	       //System.out.println("MentorQuery@@@@@@@@@@@@@@@@@@@@@@:"+query);
		    try {
				con =DatabaseConnection.getCrunchPrepConnection();
				st=con.createStatement();
				rs= st.executeQuery(query);
				while(rs.next()){
					  MentorBO bo = new MentorBO();
					  bo.setStudentName(rs.getString("first_name")+""+rs.getString("last_name"));
					  bo.setEmail(rs.getString("email"));
					  bo.setMobile(rs.getString("mobile"));
					  bo.setStart_date(rs.getString("start_date"));
					  bo.setEnd_date(rs.getString("end_date"));
					  bo.setStatus(rs.getString("status"));
					  mentorassignedStudentList.add(bo);
					  					
				}
			} catch (ConnectionException e) {
				throw new DBException(e);
			}finally{
				
				if(con!=null)
					con.close();
				
			}
		
		
		return  mentorassignedStudentList;
		
	}

	public List<MentorBO> studentProgressList(int mentorId) throws DBException, SQLException{
		
		Connection con = null;
		Statement st = null;
	    ResultSet rs = null;
	    List<MentorBO>studentProgressList = new ArrayList<MentorBO>();
		
	  /*  String query ="SELECT MEN.*, TOTALlESSIONS.COUNT TOTALLESSIONS," +
	    		"(SELECT CONCAT(count(CTK.sublesson_id),',',(count(CTK.sublesson_id)/TOTALlESSIONS.COUNT))" +
	    		"FROM cp_lessons_taken CTK WHERE CTK.user_id=MEN.user_id) lessionsTaken,TotalQuestions.COUNT TOTLAQUESTIONS," +
	    		"(select CONCAT(count(distinct results.question_id),',',(count(distinct results.question_id)/TotalQuestions.COUNT)) COUNT " +
	    		"from cp_test_results results where results.userid =MEN.user_id) as QuestionsTaken FROM cp_user_mentors MEN," +
	    		"(SELECT count(LS.sublesson_id) COUNT FROM cp_lessons LS ) TOTALlESSIONS,(select count(qmas.question_id) COUNT " +
	    		"from cp_question_masters qmas)  TotalQuestions WHERE MEN.mentor_id="+mentorId+"";*/
	    String query="SELECT MEN.*,UP.first_name,UP.last_name,UR.email, TOTALlESSIONS.COUNT TOTALLESSIONS," +
	    		"(SELECT CONCAT(count(CTK.sublesson_id),',',(count(CTK.sublesson_id)/TOTALlESSIONS.COUNT)*100)" +
	    		"FROM cp_lessons_taken CTK WHERE CTK.user_id=MEN.user_id) lessionsTaken,TotalQuestions.COUNT TOTLAQUESTIONS," +
	    		"(select CONCAT(count(distinct results.question_id),',',(count(distinct results.question_id)/TotalQuestions.COUNT)*100) COUNT " +
	    		"from cp_test_results results where results.userid =MEN.user_id) as QuestionsTaken" +
	    		" FROM cp_user_mentors MEN,cp_user_profiles UP,cp_user_registration UR,(SELECT count(LS.sublesson_id) COUNT" +
	    		" FROM cp_lessons LS ) TOTALlESSIONS,(select count(qmas.question_id) COUNT from cp_question_masters qmas)  TotalQuestions" +
	    		" WHERE MEN.mentor_id="+mentorId+" and UP.user_id=MEN.user_id and UR.user_id=MEN.user_id";
	       System.out.println("MentorQuery@@@@@@@@@@@@@@@@@@@@@@:"+query);
		    try {
				con =DatabaseConnection.getCrunchPrepConnection();
				st=con.createStatement();
				rs= st.executeQuery(query);
				while(rs.next()){
					  MentorBO bo = new MentorBO();
					  bo.setStudentName(rs.getString("first_name")+" "+rs.getString("last_name"));
					  bo.setEmail(rs.getString("email"));
					//  bo.setLessonsTaken(rs.getInt("lessionsTaken"));
					  String[] lession=rs.getString("lessionsTaken").split(",");
					  bo.setLessonsTaken(Integer.parseInt(lession[0]));
					  bo.setPercentageLessions(Double.parseDouble(lession[1]));
					  bo.setTotalLessons(rs.getInt("TOTALLESSIONS"));
					  String[] questions =rs.getString("QuestionsTaken").split(",");
					  bo.setQuestionsTaken(Integer.parseInt(questions[0]));
					  bo.setPercentageQuestions(Double.parseDouble(questions[1]));
					  bo.setTotalQuestions(rs.getInt("TOTLAQUESTIONS"));
					  studentProgressList.add(bo);
					  					
				}
			} catch (ConnectionException e) {
				throw new DBException(e);
			}finally{
				
				if(con!=null)
					con.close();
				
			}
		
		
		return  studentProgressList;
		
		
		
	}
	
	
public List<MentorBO> getMentorStudentLogs(int mentorId)throws CPException{
		
		List<MentorBO> studentLogsList=new ArrayList<MentorBO>();
		MentorBO bo;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "SELECT p.user_id,p.first_name,p.last_name,h.ip_address, MAX(h.login_date), " +
            		"DATEDIFF(CURRENT_TIMESTAMP(), MAX(h.login_date)) lastlogin FROM cp_user_mentors m," +
            		"cp_user_login_history h,cp_user_profiles p WHERE h.user_id=m.user_id AND m.user_id=p.user_id " +
            		"AND m.mentor_id="+mentorId+" GROUP BY h.user_id";
           
            System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	bo=new MentorBO();
            	bo.setUser_id(rs.getInt("user_id"));
            	bo.setStudentName(rs.getString("first_name")+" "+rs.getString("last_name"));
            	bo.setIpaddress(rs.getString("ip_address"));
            	bo.setLoginDate(rs.getString(5));
            	bo.setNoLastLoginDays(rs.getInt(6));
            	studentLogsList.add(bo);
            	   
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
 		
		
		
		return studentLogsList;
	}


public MentorBO getStudentDetails(int userid)throws CPException{
	
	
	MentorBO bo=null;
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		java.sql.Statement st = connection.createStatement();
        String query;
        ResultSet rs;
        query = "SELECT p.first_name,p.last_name,DATE_FORMAT(p.test_date,'%b-%d-%Y'),p.previous_score,p.target_score_math,p.target_score_verbal," +
        		"DATE_FORMAT(act.start_date,'%b-%d-%Y') ,DATE_FORMAT(act.end_date,'%b-%d-%Y') FROM cp_user_profiles p,cp_user_accounts act WHERE " +
        		" p.user_id=act.user_id and p.user_id="+userid;
       
        System.out.println(query);
        rs = st.executeQuery(query);
        while(rs.next()) {
        	bo=new MentorBO();
        	
        	bo.setStudentName(rs.getString("first_name")+" "+rs.getString("last_name"));
        	bo.setTest_date(rs.getString(3));
        	bo.setPrevious_score(rs.getInt("previous_score"));
        	bo.setTarget_score(rs.getInt("target_score_math")+rs.getInt("target_score_verbal"));
        	bo.setStart_date(rs.getString(7));
        	bo.setEnd_date(rs.getString(8));
        
        	   
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
	
	return bo;
}


public List<MentorBO> mentorStudentList(int mentorId) throws DBException, SQLException{
	
	Connection con = null;
	Statement st = null;
    ResultSet rs = null;
    List<MentorBO>studentProgressList = new ArrayList<MentorBO>();
	
 
    String query="SELECT MEN.user_id,UP.first_name,UP.last_name,UR.email,DATEDIFF(CURRENT_TIMESTAMP(),max(h.login_date)) LastLogin, TOTALlESSIONS.COUNT TOTALLESSIONS," +
    		"(SELECT CONCAT(count(CTK.sublesson_id),',',(count(CTK.sublesson_id)/TOTALlESSIONS.COUNT)*100)" +
    		"FROM cp_lessons_taken CTK WHERE CTK.user_id=MEN.user_id) lessionsTaken,TotalQuestions.COUNT TOTLAQUESTIONS," +
    		"(select CONCAT(count(distinct results.question_id),',',(count(distinct results.question_id)/TotalQuestions.COUNT)*100) COUNT " +
    		"from cp_test_results results where results.userid =MEN.user_id) as QuestionsTaken" +
    		" FROM cp_user_mentors MEN,cp_user_profiles UP,cp_user_registration UR,cp_user_login_history h,(SELECT count(LS.sublesson_id) COUNT" +
    		" FROM cp_lessons LS ) TOTALlESSIONS,(select count(qmas.question_id) COUNT from cp_question_masters qmas)  TotalQuestions" +
    		" WHERE MEN.mentor_id="+mentorId+" and UP.user_id=MEN.user_id and UR.user_id=MEN.user_id AND h.user_id=UP.user_id group by h.user_id";
       System.out.println("MentorQuery\t"+query);
	    try {
			con =DatabaseConnection.getCrunchPrepConnection();
			st=con.createStatement();
			rs= st.executeQuery(query);
			while(rs.next()){
				  MentorBO bo = new MentorBO();
				  bo.setUser_id(rs.getInt("user_id"));
				  bo.setStudentName(rs.getString("first_name")+" "+rs.getString("last_name"));
				  bo.setEmail(rs.getString("email"));
				//  bo.setLessonsTaken(rs.getInt("lessionsTaken"));
				  String[] lession=rs.getString("lessionsTaken").split(",");
				  bo.setLessonsTaken(Integer.parseInt(lession[0]));
				  bo.setPercentageLessions(Double.parseDouble(lession[1]));
				  bo.setTotalLessons(rs.getInt("TOTALLESSIONS"));
				  String[] questions =rs.getString("QuestionsTaken").split(",");
				  bo.setQuestionsTaken(Integer.parseInt(questions[0]));
				  bo.setPercentageQuestions(Double.parseDouble(questions[1]));
				  bo.setTotalQuestions(rs.getInt("TOTLAQUESTIONS"));
				  bo.setNoLastLoginDays(rs.getInt("LastLogin"));
				  studentProgressList.add(bo);
				  					
			}
		} catch (ConnectionException e) {
			throw new DBException(e);
		}finally{
			
			if(con!=null)
				con.close();
			
		}
	
	
	return  studentProgressList;
	
	
	
}

 
public List<UserProgressBO> getStudentProgress(int userid)
		throws DBException, SQLException {
	// TODO Auto-generated method stub
	Connection con = null;
	Statement st = null;
    ResultSet rs = null;
    List<UserProgressBO> studentProgressList = new ArrayList<UserProgressBO>();
	
 
    String query="select (select count(*) from cp_lessons l,cp_lessons_masters m where l.lesson_id=m.lesson_id and m.section_id like '1' ) " +
    		"as totalQuant,(select count(*) from cp_lessons l,cp_lessons_masters m where l.lesson_id=m.lesson_id and m.section_id like '2') " +
    		"as totalVerbal,(select count(*) from cp_lessons_taken lt,cp_lessons ls,cp_lessons_masters ms where user_id like '"+userid+"' and " +
    				"lt.sublesson_id=ls.sublesson_id and ls.lesson_id=ms.lesson_id and ms.section_id like '1') as totalQuantLessonsTaken," +
    				"(select count(*) from cp_lessons_taken lt,cp_lessons ls,cp_lessons_masters ms where user_id like '"+userid+"' " +
    				"and lt.sublesson_id=ls.sublesson_id and ls.lesson_id=ms.lesson_id and ms.section_id like '2')as totalVerbalLessonsTaken," +
    				"(select SEC_TO_TIME(sum(TIME_TO_SEC(lt.timespent))) from cp_lessons_taken lt,cp_lessons ls,cp_lessons_masters ms where " +
    				"user_id like '"+userid+"' and lt.sublesson_id=ls.sublesson_id and ls.lesson_id=ms.lesson_id and ms.section_id like '1')" +
    				"as timeForQuant,(select SEC_TO_TIME(sum(TIME_TO_SEC(lt.timespent))) from cp_lessons_taken lt,cp_lessons ls,cp_lessons_masters " +
    				"ms where user_id like '"+userid+"' and lt.sublesson_id=ls.sublesson_id and ls.lesson_id=ms.lesson_id and ms.section_id like '2')" +
    						"as timeForVerbal";
       System.out.println("MentorQuery\t"+query);
	    try {
			con =DatabaseConnection.getCrunchPrepConnection();
			st=con.createStatement();
			rs= st.executeQuery(query);
			while(rs.next()){
				UserProgressBO bo = new UserProgressBO();
				 bo.setTotalQuantLessons(rs.getString("totalQuant"));
				 if(rs.getString("totalQuantLessonsTaken")==null)
				 {
					 bo.setTotalQuantLessonsCompleted("0");
				 }
				 else
				 {
					 bo.setTotalQuantLessonsCompleted(rs.getString("totalQuantLessonsTaken"));
				 }
				 if(rs.getString("timeForQuant")==null)
				 {
					 bo.setTotalSpentForQuantLessons("00:00:00");
				 }
				 else
				 {
					 bo.setTotalSpentForQuantLessons(rs.getString("timeForQuant"));
				 } 
				 bo.setTotalVerbalLessons(rs.getString("totalVerbal"));
				 if(rs.getString("totalVerbalLessonsTaken")==null)
				 {
					 bo.setTotalVerbalLessonsCompleted("0");
				 }
				 else
				 {
					 bo.setTotalVerbalLessonsCompleted(rs.getString("totalVerbalLessonsTaken"));
				 } 
				 if(rs.getString("timeForVerbal")==null)
				 {
					 bo.setTotalSpentForVerbalLessons("00:00:00");
				 }
				 else
				 {
					 bo.setTotalSpentForVerbalLessons(rs.getString("timeForVerbal"));
				 } 
				 
				 studentProgressList.add(bo);					
			}
		} catch (ConnectionException e) {
			throw new DBException(e);
		}finally{
			
			if(con!=null)
				con.close();
			
		}
	
	
	return  studentProgressList;
}

@Override
public List<UserProgressBO> getStudentTestProgress(int userid)
		throws DBException, SQLException {
	// TODO Auto-generated method stub
	Connection con = null;
	Statement st = null;
    ResultSet rs = null;
    List<UserProgressBO> studentProgressList = new ArrayList<UserProgressBO>(); 
    String query="select count(*),tr.type_id,tr.section_id,tr.test_date,(select count(*) from cp_test_results trl where " +
    		"trl.userid like '"+userid+"' and trl.test_date=(select max(rr.test_date) from cp_test_results rr where " +
    		"trl.question_id=rr.question_id) and trl.result like 'CORRECT' and trl.type_id like tr.type_id) as totalCorrect," +
    		"((select count(*) from cp_test_results trl where trl.userid like '"+userid+"' and trl.test_date=(select max(rr.test_date) " +
    		"from cp_test_results rr where trl.question_id=rr.question_id) and trl.result like 'INCORRECT' and trl.type_id like tr.type_id)) " +
    		"as totalWrong, ((select count(*) from cp_test_results trl where trl.userid like '"+userid+"' and trl.test_date=(select max(rr.test_date) " +
    		"from cp_test_results rr where trl.question_id=rr.question_id) and trl.result like 'SKIPPED' and trl.type_id like tr.type_id)) " +
    		"as totalSkipped,qc.category_name,qt.question_type_name from cp_test_results tr,cp_question_categories qc,cp_question_type qt  " +
    		"where userid like '"+userid+"' and tr.test_date=(select max(r.test_date) from cp_test_results r where tr.question_id=r.question_id)  " +
    		"and qt.question_type_id=tr.type_id and qc.category_id=qt.category_id  group by tr.type_id"; 
	    try {
			con =DatabaseConnection.getCrunchPrepConnection();
			st=con.createStatement();
			rs= st.executeQuery(query);
			System.out.println("Progress Query:"+query);
			while(rs.next()){
				 UserProgressBO bo = new UserProgressBO();
				 bo.setQuestionType(rs.getString("category_name")+" "+rs.getString("question_type_name"));
				 bo.setCorrectCount(rs.getString("totalCorrect"));
				 bo.setWrongCont(rs.getString("totalWrong"));
				 bo.setSection_id(rs.getString("section_id"));
				 studentProgressList.add(bo);					
			}
		} catch (ConnectionException e) {
			throw new DBException(e);
		}finally{
			
			if(con!=null)
				con.close();
			
		}
	
	
	return  studentProgressList;
}



public String getUsermail(int userid)throws CPException{
	
	Connection connection=null;
	String email="";
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		java.sql.Statement st = connection.createStatement();
        String query;
        ResultSet rs;
        query = "select email from cp_user_registration where user_id="+userid;
       
        System.out.println(query);
        rs = st.executeQuery(query);
        while(rs.next()) {
        	email=rs.getString("email");
        	   
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
	
	
	
	return  email;
}


public List<MentorBO> mentorStudenLogstList(int mentorId)throws CPException{
	List<MentorBO> LogsList=new ArrayList<MentorBO>();
	MentorBO bo;
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		java.sql.Statement st = connection.createStatement();
        String query;
        ResultSet rs;
        query = "select DATE_FORMAT(h.login_date,'%b-%d-%Y'),DATE_FORMAT(h.logout_date,'%b-%d-%Y'),h.ip_address from cp_user_login_history h where h.user_id="+mentorId;
       
        System.out.println(query);
        rs = st.executeQuery(query);
        while(rs.next()) {
        	bo=new MentorBO();
        	bo.setLoginDate(rs.getString(1));
        	bo.setLogoutDate(rs.getString(2));
        	bo.setIpaddress(rs.getString("ip_address"));
        	
        	LogsList.add(bo);
        	   
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
		
	
	
	return LogsList;
	
}


public List<MentorBO> mentorStudentLessonData(int userid)throws CPException{
	List<MentorBO> lessonsList=new ArrayList<MentorBO>();
	MentorBO bo;
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		java.sql.Statement st = connection.createStatement();
        String query;
        ResultSet rs;
        query = "select l.lesson_name,t.lession_date,t.timespent from cp_lessons_taken t,cp_lessons l where t.sublesson_id=l.sublesson_id and t.user_id="+userid+"  ORDER BY t.lession_date";
       
        System.out.println(query);
        rs = st.executeQuery(query);
        while(rs.next()) {
        	bo=new MentorBO();
        	bo.setLesson_name(rs.getString("lesson_name"));
        	bo.setLesson_date(rs.getString("lession_date"));
        	bo.setLesson_timespent(rs.getString("timespent"));
        	
        	lessonsList.add(bo);
        	   
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
		
	
	
	return lessonsList;
	
}
 

	

}
