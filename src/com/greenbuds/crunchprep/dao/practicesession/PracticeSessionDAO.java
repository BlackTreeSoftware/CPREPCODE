package com.greenbuds.crunchprep.dao.practicesession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ResumeBo;
import com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultLessionBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsExcelBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.ReviewReason;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.util.DateUtil;
 



public class PracticeSessionDAO implements IPracticeSessionDAO{

	private static PracticeSessionDAO practiceSessionDAO;
	ResumeBo resumeBo = null;
	
	/**
	 * Gets the single instance of LessonsDAO.
	 * 
	 * @return single instance of LessonsDAO
	 */
	public static synchronized PracticeSessionDAO getInstance() {

		if (practiceSessionDAO == null)
			practiceSessionDAO = new PracticeSessionDAO();
		return practiceSessionDAO;
	}	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.practicesession.IPracticeSessionDAO#getPerformanceBarValues(com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO)
	 */
	@Override
	public PracticeSessionQuestionsBO getPerformanceBarValues(
			PracticeSessionQuestionsBO practiceSessionQuestionsBO)
			throws ConnectionException, DBException, SQLException {
		// TODO Auto-generated method stub
		ResultSet resultSet=null;
		String avgtime=null;
		String gotitpercent=null;
		try{
		connection = DatabaseConnection.getCrunchPrepConnection();
		statement = connection.createStatement();		
		String getAvgTimeQuery="select avg_time from cp_questions where question_id="+practiceSessionQuestionsBO.getQuestionid()+"";
		
		String gotitPercentageQuery="SELECT (COUNT(DISTINCT(a.userid))*100)/(SELECT COUNT(DISTINCT(b.userid)) " +
				" FROM cp_test_results b WHERE  b.question_id="+practiceSessionQuestionsBO.getQuestionid()+")" +
						" FROM cp_test_results a WHERE a.question_id="+practiceSessionQuestionsBO.getQuestionid()+" AND a.result='CORRECT'  ";	
		
		String previousQuestionStatus="SELECT testResults.result,TIME_TO_SEC(testResults.user_time) FROM cp_test_results testResults " +
				"WHERE testResults.userid="+practiceSessionQuestionsBO.getUser_id()+" AND test_no="+practiceSessionQuestionsBO.getTest_no()+"  ORDER BY testResults.row_id  DESC LIMIT 5";
		
		
		////System.out.println(getAvgTimeQuery);
		////System.out.println(gotitPercentageQuery);
		////System.out.println(previousQuestionStatus);
		PreparedStatement getAvgTimePreparedStatement=null;
		PreparedStatement gotitPercentPreparedStatement=null;
		PreparedStatement previousQuesPreparedStatement=null;
		
		
		getAvgTimePreparedStatement=connection.prepareStatement(getAvgTimeQuery);
		gotitPercentPreparedStatement=connection.prepareStatement(gotitPercentageQuery);
		previousQuesPreparedStatement=connection.prepareStatement(previousQuestionStatus);
		
		
				
		resultSet=getAvgTimePreparedStatement.executeQuery();
		if(resultSet.next()){
		avgtime=resultSet.getString(1);
		}
		
		
		resultSet=gotitPercentPreparedStatement.executeQuery();
		if(resultSet.next()){
			gotitpercent=resultSet.getString(1);
			
		}
		
		List<String> results=new ArrayList<String>();
		resultSet=previousQuesPreparedStatement.executeQuery();
		while(resultSet.next()){
			results.add(resultSet.getString(1)+"@"+resultSet.getString(2));
		
			
		}
		
		practiceSessionQuestionsBO.setGotit_percent(gotitpercent);
		practiceSessionQuestionsBO.setLastFivequestions(results);
		practiceSessionQuestionsBO.setTotal_users_avg_time(avgtime);
		//System.out.println(practiceSessionQuestionsBO.getTotal_users_avg_time());
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			DBUtil.closeConnection(resultSet, statement, connection);
		}
		
		return practiceSessionQuestionsBO;
	}

	

	private Connection connection =null;
	private Statement statement =null,st=null,statement2=null;

	


	@Override
	public ResultsBO resultPage(ResultsBO resultsBO) {
		// TODO Auto-generated method stub	
		ResultSet resultSet=null,rs=null;
		ResultSet resultSet2=null;
		String result_page = "";		
		
		//resultsBO = new ResultsBO();
		
		//Result set Data
		int  question_no=0;
		String  result;
		String  question_title;		 
		String  lesson_name;
		String  diff_name;
		String  user_time;
		String  avg_time;
		String  flagstatus;
		String  section_name;
		String  category_name;
		int sectionid=0;
		String  user_answer=null;
		int type_id=0;
		int ques_id=0;
		
		//sumof ABG Times 
		String avg_userpace="00:00";
		String avg_otherspace="00:00";
		String total_userpace ="00:00";
		String total_otherspace="00:00";
		int avg_percentcorrect = 0;
		int test_no=1;
		
		int correct=0;
		int wrong =0;
		String  question_type_name;
		String  guess;
		String  test_date;
		String  guessPercentage= "0";
		String  correctPercentage = "0";
		String  flaggedPercentage = "0";
		String  guessIncorrectPercentage= "0";
		
		String reason_id;
		String stack_correct="[0,0],";
		String stack_wrong="[0,0],";
		
		System.out.println("_____________________________________________________  RESULT PAGE START _________________________________________________________");
		
		
		
		try {
						
			String query="SELECT r.result,m.question_title,l.lesson_name,qt.question_type_name, d.diff_name,r.guess,r.flagstatus,DATE_FORMAT(r.test_date,'%b %d %Y %h:%i %p') as test_date, s.section_id,r.test_no,r.user_answer,m.type_id,r.question_id, " +
					"r.user_time,q.avg_time,s.section_name,ct.category_name,r.reason FROM cp_test_results r," +
					"cp_question_masters m, cp_sections s,cp_lessons l,cp_difficulty_level d,cp_questions q,cp_question_type qt,cp_question_categories ct WHERE r.question_id= m.question_id AND r.section_id = s.section_id AND r.sublession_id = l.sublesson_id AND r.diff_id = d.diff_id  AND qt.question_type_id = r.type_id AND q.question_id = r.question_id AND ct.category_id = r.category_id AND r.userid="+resultsBO.getUserid()+" and r.test_no= "+resultsBO.getTest_no()+"";			
			
			
			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();
			
			
			//System.out.println(" *** STEP1 : BIG QUERY : \n\t"+query);			
			resultSet = statement.executeQuery(query);
			
						
			while (resultSet.next()) {
				
				
				question_no++;
				result  				= resultSet.getString("result"); 
				question_title 			= resultSet.getString("question_title"); 				
				lesson_name  			= resultSet.getString("lesson_name"); 
				question_type_name  	= resultSet.getString("question_type_name"); 			
				diff_name  				= resultSet.getString("diff_name"); 
				guess  					= resultSet.getString("guess"); 			
				flagstatus 				= resultSet.getString("flagstatus");
				test_date 				= resultSet.getString("test_date");
				user_time 				= resultSet.getString("user_time"); 
				avg_time  				= resultSet.getString("avg_time"); 				
				sectionid				= resultSet.getInt("section_id");
				user_answer 			= resultSet.getString("user_answer"); 
				type_id					= resultSet.getInt("type_id");
				test_no         		=resultSet.getInt("test_no");
				ques_id        			=resultSet.getInt("question_id");
				section_name			= resultSet.getString("section_name"); 
				category_name			= resultSet.getString("category_name"); 
				reason_id				= resultSet.getString("reason"); 
				String op = "";
				if (result.equalsIgnoreCase("correct"))
				{
					correct++;
					op = op + "<option>Correct</option>";
				}	
				
				if (result.equalsIgnoreCase("incorrect"))
				{
					wrong++;

				// ********************
				// REASON***********************************
				
				ReviewReason val[] = ReviewReason.values();
				for (int i = 0; i < val.length; i++) {
					if (reason_id.equalsIgnoreCase(val[i].toString())) {
						op = op + "<option value=\'" + val[i]
								+ "\' selected=\'selected\'>" + val[i].reason
								+ "</option>";
					} else {

						op = op + "<option value=\'" + val[i] + "\'>"
								+ val[i].reason + "</option>";
					}
				
				}
			}
				
			    //reason(userid,qid,testid,reasonid)
				String onchage = "onchange=\'javascript:reason("+resultsBO.getUserid()+","+ques_id+","+test_no+",$(this).val());\'";
				String reason="<select class=\'form-control\' style=\'width:100%\' name=\'reason\'"+onchage+" >"+op+"</select>";
			//**************************** REASON END SATYA	 
				 
				
				
				
				
				String subQuery="select DISTINCT IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="+ques_id+" and p.guess='GUESS')" +
						"*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="+ques_id+")),0) as guessPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="+ques_id+" and  p.result='CORRECT')*100)/" +
						"(select count(p.row_id) from cp_test_results p where p.question_id="+ques_id+")),0) as correctPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where p.question_id="+ques_id+" and  p.flagstatus='FLAG')*100)/" +
						"(select count(p.row_id) from cp_test_results p where p.question_id="+ques_id+")),0) as flaggedPercentage," +
						"IFNULL(ROUND(((select count(p.row_id) from cp_test_results p where  p.question_id="+ques_id+" and p.guess='GUESS' and p.result='INCORRECT')" +
						"*100)/(select count(p.row_id) from cp_test_results p where  p.question_id="+ques_id+")),0) as guessIncorrectPercentage FROM cp_test_results";
				System.out.println("STEP 2 :   IN SUB QUERTY ******************\n\t\n"+subQuery);
				st = connection.createStatement();
				rs=st.executeQuery(subQuery);
				
				if(rs.next()){
					guessPercentage  		= rs.getString("guessPercentage"); 			
					correctPercentage  		= rs.getString("correctPercentage"); 			
					flaggedPercentage  		= rs.getString("flaggedPercentage"); 			
					guessIncorrectPercentage= rs.getString("guessIncorrectPercentage"); 
				}
							
				System.out.println("UserTime : "+user_answer);
				System.out.println("question : "+ques_id);
				System.out.println("type : "+type_id);
			
				String hisdiv = "";
						
				//String qry = "select test.result,TIME_FORMAT(test.user_time,'%i:%S') user_time,DATE_FORMAT(test.test_date,'%b %d %Y %h:%i %p') test_date, test.question_id from cp_test_results test where test.question_id="+ques_id+" and test.userid = "+resultsBO.getUserid()+"  order by test.test_date limit 5 "    ;
			    //String qry="(SELECT test.result, TIME_FORMAT(test.user_time,'%i:%S') user_time, DATE_FORMAT(test.test_date,'%b %d %Y %h:%i %p') test_date, test.question_id,test.guess 	FROM cp_test_results test  	WHERE test.question_id="+ques_id+" and test.userid = "+resultsBO.getUserid()+"  order by test.test_date limit 5) 		UNION ALL 		(SELECT qh.question_status, TIME_FORMAT(qh.question_time,'%i:%S') user_time,DATE_FORMAT(qh.question_date,'%b %d %Y %h:%i %p'),qh.question_id,qh.question_guess 	FROM cp_question_history qh where qh.user_id= "+resultsBO.getUserid()+"  and qh.question_id="+ques_id+"  order by qh.question_date limit 5 ) order by   test_date  limit 5";
			   
			   String qry="(SELECT test.result, TIME_FORMAT(test.user_time,'%i:%S') user_time, test.test_date test_date, test.question_id,test.guess 	FROM cp_test_results test  	WHERE test.question_id="+ques_id+" and test.userid = "+resultsBO.getUserid()+"  order by test.test_date desc limit 5) 		UNION ALL 		(SELECT qh.question_status, TIME_FORMAT(qh.question_time,'%i:%S') user_time,qh.question_date test_date ,qh.question_id,qh.question_guess 	FROM cp_question_history qh where qh.user_id= "+resultsBO.getUserid()+"  and qh.question_id="+ques_id+"  order by qh.question_date desc limit 5 ) order by   test_date  limit 5";
				
               System.out.println("  *** STEP 3: History :\n\t"+qry); 

				
				statement2 = connection.createStatement();
				resultSet2 = statement2.executeQuery(qry);
				
				while(resultSet2.next())
				{
						String res =   resultSet2.getString("result");
						String usertime = resultSet2.getString("user_time");
						String tdate = resultSet2.getString("test_date");
						String guessdata = resultSet2.getString("guess");
						SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat dmyFormat = new SimpleDateFormat("MMM dd yyy hh:mm a");						    
						tdate=dmyFormat.format(mdyFormat.parse(tdate));				    
						//String s=MonthFORMAT1.format(MonthFORMAT1.parse(tdate));
						if(res.equalsIgnoreCase("correct"))
						{
							//result="Correct";
			                hisdiv = hisdiv+ "<span class=@font-15@><i class=@fa fa-check-circle text-success@></i><span class=@p-l-10 p-r-10@>Correct</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
			               //   hisdiv = hisdiv+"<span class=@font-15@><i class=@fa fa-check-circle text-success@></i><span class=@p-l-10 p-r-10@>Correct</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
                          //  correct++;
			              // System.out.println("In Correct---------------------- \n\t: "+correct);
							
						}
						else{
							//result="Wrong";
							hisdiv = hisdiv+ "<span class=@font-15@><i class=@fa fa-times-circle text-danger@></i><span class=@p-l-10 p-r-10 text-danger@> Wrong</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
							//hisdiv = hisdiv+"<span class=@font-15@><i class=@fa fa-times-circle text-danger@></i><span class=@p-l-10 p-r-10 text-danger@> Wrong</span><i class=@fa fa-arrow-right@></i> <i class=@fa fa-clock-circle@></i> <span class=@p-l-10 p-r-10@>"+usertime+" ("+tdate+")  "+guessdata+"</span></span><br>";
							//wrong++;
							//System.out.println("In Wrong-------------------- \t: "+wrong);
						}
				}
                     //	System.out.println("******  QUERY ***********HIS DIV********\n\t"+hisdiv);
                    // System.out.println("Result : "+result);
				
				String flagicon = "<i class=\'fa fa-flag text-success\'><i style=\'display:none\'>FLAG<i/></i>";
				if(flagstatus.equalsIgnoreCase("UNFLAG"))
				{
					flagicon = "<i class=\'fa fa-flag-o\'><i style=\'display:none\'>UNFLAG<i/></i>";
					
				}
				
				 
				result_page = result_page+"{" +
						"	\"result\": \""+result+"\"," +
						"   \"quesTitle\": \""+question_title+"\"," +
						"   \"topic\": \""+lesson_name+"\"," +
						"    \"type\": \""+question_type_name+"\"," +
						"     \"difficulty\": \""+diff_name+"\",	" +
						"      \"guessed\": \""+guess+"\"," +
						"  \"flag\": \""+flagicon+"\"," +
						"  \"date\": \""+test_date+"\"," +
						" \"retry\": \"<a href='javascript:getPracticeSessionQuestion("+ques_id+","+type_id+","+sectionid+","+test_no+");' class=\'btn btn-default\'><i class=\'fa fa-refresh \'></i>&nbsp;Retry</a>\",	" +
						"  \"percentCorrect\":\""+correctPercentage+"\"," +
						"  \"flaggedPercentage\": \""+flaggedPercentage+"\"," +
						"  \"user_time\": \""+user_time+"\"," +
						"  \"avg_time\": \""+avg_time+"\"," +	
						"  \"section_name\": \""+section_name+"\"," +
						"  \"category_name\": \""+category_name+"\"," +
						"  \"guessPercentage\": \""+guessPercentage+"\"," +		
						"  \"guessIncorrectPercentage\": \""+guessIncorrectPercentage+"\"," +		
						"  \"hisdiv\": \""+hisdiv+"\"," +
						"  \"reason\": \""+reason+"\"" +
						"    },";
				
				//***************************  NEW GRAPHS ***************************************
				
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^   New Graphs Start ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ : "+result );
				
				if(result.equalsIgnoreCase("CORRECT"))
				{
					
					String temp = user_time.substring(4, user_time.length()).replace(":", ".");
					stack_correct = stack_correct+"["+temp+", "+correctPercentage+"],";
					
				}else if(result.equalsIgnoreCase("INCORRECT"))
				{
					String temp = user_time.substring(4, user_time.length()).replace(":", ".");
					stack_wrong = stack_wrong+"["+temp+", "+correctPercentage+"],";
					
				}else{
					
					
				}
				
				System.out.println("Stack Correct : "+stack_correct+"\n STcakworng : "+stack_wrong);
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^   New Graphs End ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				
				
				//***************************  NEW GRAPHS ***************************************
				
			}//while end
			
						
		String	qry ="select SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))/ COUNT(*)) as avg_userpace, SEC_TO_TIME((SUM(TIME_TO_SEC(q.avg_time)))/ COUNT(*)) as avg_otherspace,SEC_TO_TIME((SUM(TIME_TO_SEC(r.user_time)))) as total_userpace, SEC_TO_TIME((SUM(TIME_TO_SEC(q.avg_time)))) as total_otherspace from cp_test_results r,cp_question_masters m, cp_sections s,cp_lessons l,cp_difficulty_level d,cp_questions q  where r.question_id= m.question_id and r.section_id = s.section_id and r.sublession_id = l.sublesson_id and r.diff_id = d.diff_id and q.question_id = r.question_id and r.userid="+resultsBO.getUserid()+" and r.test_no= "+resultsBO.getTest_no()+"";
		//    System.out.println("  **  STEP4 : AverageQuery:"+qry);	
		resultSet = statement.executeQuery(qry);
			if(resultSet.next())
			{
				avg_userpace = resultSet.getString("avg_userpace");
				avg_otherspace = resultSet.getString("avg_otherspace");				
				total_userpace = resultSet.getString("total_userpace");
				total_otherspace =resultSet.getString("total_otherspace");
			}
			
		
			//System.out.println("******************************  \n  Correct: "+correct+"Question No : "+question_no+"Wrong :"+wrong);
			
			if(question_no>0)
			{			
			avg_percentcorrect = (correct*100/question_no);
			}
			//System.out.println("Avg percent1 : "+avg_percentcorrect);
			//System.out.println("Avg percent1 : "+avg_percentcorrect);
			
			
		//	System.out.println("Correct: "+correct+"Question No : "+question_no+"Avg percent : "+avg_percentcorrect);
		
			resultsBO.setTotal_qsns(question_no);	
			resultsBO.setCorrect(correct);
			resultsBO.setWrong(wrong);			
			resultsBO.setAvg_userpace(avg_userpace);
			resultsBO.setAvg_otherspace(avg_otherspace);
			resultsBO.setTotal_userpace(total_userpace);
			resultsBO.setTotal_otherspace(total_otherspace);
			resultsBO.setAvg_percentcorrect(avg_percentcorrect);			
			resultsBO.setResult_page(result_page);
			resultsBO.setSection_id(sectionid);
			resultsBO.setTest_no(test_no);
			resultsBO.setUser_answer(user_answer);
			resultsBO.setType_id(type_id);
			
			
			resultsBO.setStack_correct(stack_correct);
			resultsBO.setStack_wrong(stack_wrong);
				
				
			//System.out.println("result : "+result_page);
			
			System.out.println("_____________________________________________________  RESULT PAGE END _________________________________________________________");	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{			
			DBUtil.closeConnection(resultSet, statement, connection);
			try {
				if(resultSet2!=null)				
				resultSet2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return resultsBO;
	}
	
	
	public ResultsBO updateReason(ResultsBO resultsBO) {
		 
		Statement st = null;
		Connection con = null;
		try {

			con = DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			String qry = "UPDATE `cp_test_results` r SET r.reason='"+resultsBO.getReason()+"' WHERE  r.userid= "+resultsBO.getUserid()+" and r.question_id = "+resultsBO.getQuestion_id()+" and r.test_no = "+resultsBO.getTest_no();
			System.out.println("UPDATE QRY------------> "+qry);
			int k = st.executeUpdate(qry);
			if(k>0)
			{
				System.out.println("Reason Updated Successfully");
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				con.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		  return resultPage(resultsBO);
	}
	
	
	
	public boolean testSessions(QuestionsUploadBO bo)throws CPException{
		
    boolean flag=false;
		
		Connection connection=null;
		ResultSet rs=null;
		try{
			String query,query1;
			connection=DatabaseConnection.getCrunchPrepConnection();
			 java.sql.Statement st = connection.createStatement();
			 System.out.println("In activity creation..........");
			 String testname=(bo.getTest_name()==null?"PRACTICE TEST":bo.getTest_name()); 
			 query="insert into cp_user_activity(user_id,activity_type,test_no) values ("+bo.getUser_id()+",'"+testname+"','"+bo.getTest_no()+"')";
			 System.out.println("Activity Id Query\t"+query);
			 int i = st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			 ResultSet keys = st.getGeneratedKeys();
	         if(keys.next())         {
	        	 int key=keys.getInt(1);
	        	 bo.setActivity_id(key);
	         }
	         if(i==1){
	        	 flag=true;
	         }
			
		}catch(Exception exception){
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
	
	public boolean testSessionSaving(QuestionsUploadBO bo)throws CPException{
		boolean flag=false;
		Connection connection=null;
		ResultSet rs=null;
		try{
			String query;
			connection=DatabaseConnection.getCrunchPrepConnection();
			 java.sql.Statement st = connection.createStatement();
			 
			 query="insert into cp_test_session(activity_id,test_no,question_id) values("+bo.getActivity_id()+","+bo.getTest_no()+","+bo.getQuestion_id()+")";
			 //System.out.println("Test Session Saving Query\t"+query);
			 int i = st.executeUpdate(query);
			
	         if(i==1){
	        	 flag=true;
	         }
			
		}catch(Exception exception){
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
	



	public boolean submitPracticeSessionQuestion(List<QuestionsUploadBO> questionsUploadBO) throws CPException{
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
		String user_answer=""; 
		    	Connection connection=null; 
		    	QuestionsUploadBO bo=new QuestionsUploadBO();
				bo=questionsUploadBO.get(0);
		    	
				try{
					//System.out.println("bo.getUser_answer().size()"+bo.getUser_answer().size());
					if(bo.getUser_answer().toString() .length()!=0){
					for(int i=0;i<bo.getUser_answer().size();i++){
						 if(i==0){ 
							user_answer+=bo.getUser_answer().get(i).toString().substring(0,bo.getUser_answer().get(i).toString().length() ); 
						 
						}
						else{  
							user_answer+="@"+bo.getUser_answer().get(i).toString().substring(0,bo.getUser_answer().get(i).toString().length() ); 
						}
					} 
					}
					else{
						user_answer="";
					}
					//System.out.println("User Answer "+user_answer);
					 if(isQuestionSubmitted(bo))
					 {
						  System.out.println("Updating question.............................................");
						 connection=DatabaseConnection.getCrunchPrepConnection(); 
						 st=connection.createStatement();   
						 String query="Update cp_test_results set test_date='"+bo.getTest_date()+"',result='"+bo.getResult()+"',user_answer='"+user_answer+"',flagstatus='"+bo.getFlag_status()+"',guess='"+bo.getGuessed()+"',user_time='"+bo.getUser_time()+"' where userid like '"+bo.getUser_id()+"' and test_no like '"+bo.getTest_no()+"' and test_master_id like '"+bo.getTest_id()+"' and question_id like '"+bo.getQuestion_id()+"'";
						 int n=st.executeUpdate(query);  
							if(n>0)
							{
								result=true; 
								//System.out.println("query executed");
							}
							connection.close(); 
					 }
					 else
					 {  
						System.out.println("Submitting question...");
						connection=DatabaseConnection.getCrunchPrepConnection(); 
						st=connection.createStatement();   
						 String query="";
						       if(bo.getSkillid1()==0)
								 {
							      query="INSERT INTO `cp_test_results` ( `userid`, `section_id`, `category_id`, `type_id`, `sublession_id`, `test_master_id`, `question_id`, `diff_id`, `skill_id1`, `skill_id2`, `skill_id3`, `test_date`, `user_time`, `result`,`user_answer`,`flagstatus`, `test_no`,`session_type`,`guess`) " +
						 		"VALUES ("+bo.getUser_id()+", '"+bo.getSection_id()+"', '"+bo.getCategory_id()+"', '"+bo.getTypeId()+"', '"+bo.getLesson_id()+"', '"+bo.getTest_id()+"', '"+bo.getQuestion_id()+"','"+bo.getDifficulty_id()+"',null, null, null,'"+bo.getTest_date()+"', '"+bo.getUser_time()+"','"+bo.getResult()+"','"+user_answer+"','"+bo.getFlag_status()+"', '"+bo.getTest_no()+"','"+bo.getSession_type()+"','"+bo.getGuessed()+"')";
								 }
						       else if(bo.getSkillid2()==0)
								 {
							      query="INSERT INTO `cp_test_results` ( `userid`, `section_id`, `category_id`, `type_id`, `sublession_id`, `test_master_id`, `question_id`, `diff_id`, `skill_id1`, `skill_id2`, `skill_id3`, `test_date`, `user_time`, `result`,`user_answer`,`flagstatus`, `test_no`,`session_type`,`guess`) " +
						 		"VALUES ("+bo.getUser_id()+", '"+bo.getSection_id()+"', '"+bo.getCategory_id()+"', '"+bo.getTypeId()+"', '"+bo.getLesson_id()+"', '"+bo.getTest_id()+"', '"+bo.getQuestion_id()+"','"+bo.getDifficulty_id()+"','"+bo.getSkillid1()+"', null, null,'"+bo.getTest_date()+"', '"+bo.getUser_time()+"','"+bo.getResult()+"','"+user_answer+"','"+bo.getFlag_status()+"', '"+bo.getTest_no()+"','"+bo.getSession_type()+"','"+bo.getGuessed()+"')";
								 }
						       else if(bo.getSkillid3()==0)
								 {
							      query="INSERT INTO `cp_test_results` ( `userid`, `section_id`, `category_id`, `type_id`, `sublession_id`, `test_master_id`, `question_id`, `diff_id`, `skill_id1`, `skill_id2`, `skill_id3`, `test_date`, `user_time`, `result`,`user_answer`,`flagstatus`, `test_no`,`session_type`,`guess`) " +
						 		"VALUES ("+bo.getUser_id()+", '"+bo.getSection_id()+"', '"+bo.getCategory_id()+"', '"+bo.getTypeId()+"', '"+bo.getLesson_id()+"', '"+bo.getTest_id()+"', '"+bo.getQuestion_id()+"','"+bo.getDifficulty_id()+"','"+bo.getSkillid1()+"', '"+bo.getSkillid2()+"', null,'"+bo.getTest_date()+"', '"+bo.getUser_time()+"','"+bo.getResult()+"','"+user_answer+"','"+bo.getFlag_status()+"', '"+bo.getTest_no()+"','"+bo.getSession_type()+"','"+bo.getGuessed()+"')";
								 }
							       else
							       {
							    	   query="INSERT INTO `cp_test_results` ( `userid`, `section_id`, `category_id`, `type_id`, `sublession_id`, `test_master_id`, `question_id`, `diff_id`, `skill_id1`, `skill_id2`, `skill_id3`, `test_date`, `user_time`, `result`,`user_answer`,`flagstatus`, `test_no`,`session_type`,`guess`) " +
										 		"VALUES ("+bo.getUser_id()+", '"+bo.getSection_id()+"', '"+bo.getCategory_id()+"', '"+bo.getTypeId()+"', '"+bo.getLesson_id()+"', '"+bo.getTest_id()+"', '"+bo.getQuestion_id()+"','"+bo.getDifficulty_id()+"','"+bo.getSkillid1()+"', '"+bo.getSkillid2()+"', '"+bo.getSkillid3()+"','"+bo.getTest_date()+"', '"+bo.getUser_time()+"','"+bo.getResult()+"','"+user_answer+"','"+bo.getFlag_status()+"', '"+bo.getTest_no()+"','"+bo.getSession_type()+"','"+bo.getGuessed()+"')";
							       }
						  System.out.println("Result ::"+query); 
						int n=st.executeUpdate(query);  
						if(n>0)
						{
							result=true; 
							//System.out.println("query executed");
						}
						connection.close(); 
						testSessionSaving(bo);
						averageTimeCalcultation(bo);
					 }
					 
				}
				catch(ConnectionException exp){ 
					exp.printStackTrace();
					throw new ConnectionException(exp); 
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
					throw new CommonExceptions(exp); 
				}
				finally{
					try {if(connection!=null){
						connection.close();
					}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		   
			
		return result;
	}

	@Override
	public int createTestNumber(int userid, int testid,String testname) throws CPException{
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null; 
		String user_answer="";
		int key=0;
		 QuestionsUploadBO bo = new QuestionsUploadBO();
		    	Connection connection=null; 
				try{ 
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement();   
 
					String query="INSERT INTO `cp_testcreation` (`testmaster_id`,user_id,session_name) VALUES ("+testid+", "+userid+",'"+testname+"')";
 
					//System.out.println("test creation query::"+query);
					int n = st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
					ResultSet keys = st.getGeneratedKeys();
		            if(keys.next())
		            {
		           	 key=keys.getInt(1);       
		             bo.setTest_no(key);
		             user_answer=""+key;
		            }
					if (n > 1) {
						result = false;
					}
					connection.close(); 
				}
				catch(ConnectionException exp){ 
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
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						throw new CPException(e);
					}
				} 
		return key;
	} 

	/**
	 * @param section SectionType
	 * @return
	 * @throws ConnectionException
	 */
	public List<QuestionsUploadBO> gettingQuestionsforPracticeTest(String query) throws ConnectionException
	{
		 	List<QuestionsUploadBO> list=new ArrayList<QuestionsUploadBO>();
			Statement statement=null;		 
		    Connection connection=null;
		    ResultSet resultSet=null;
		    java.sql.ResultSet	resultSet1=null;
		    Statement stmt1=null;
		   /* String sql="select qmaster.question_id,qmaster.section_id,qmaster.category_id,qmaster.type_id,qmaster.test_id,qmaster.passage_id,qmaster.graph_id,qmaster.question_title,qmaster.question_directions" +
		    		" ,question.skill_id1,question.skill_id2,question.skill_id3,question.diff_id,question.question_no,question.question,question.avg_time,question.quantityA,question.quantityB,question.solution_text,question.solution_video,question.access_type,question.referral,question.`status`,question.question_passage" +
		    		" , GROUP_CONCAT(distinct answers.answer SEPARATOR '@gbst@') as answers,GROUP_CONCAT(distinct choices.choice SEPARATOR '@gbst@') as choices," +
		    				" (select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=question.diff_id) as difficultyLevel," +
		    				" (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(question.skill_id1,question.skill_id2,question.skill_id3)) as skills" +
		    				" from cp_question_masters qmaster left join cp_question_choices choices  on qmaster.question_id=choices.question_id ," +
		    				" cp_questions question,cp_question_answers answers,cp_question_lessons lessons" +
		    				"  where qmaster.question_id=question.question_id and qmaster.section_id in(1) and qmaster.category_id in(4,6)" +
		    				"  and qmaster.type_id in(select GROUP_CONCAT(type.question_type_id)  from cp_question_categories cat,cp_question_type type where cat.category_id=type.category_id and cat.category_id in(4,6))" +
		    				"  and qmaster.test_id in(1) and qmaster.question_id=answers.question_id group by answers.question_id  " ;
		  */
		    /*String sql=" select lessons.sublesson_id,qmaster.question_id,qmaster.section_id,qmaster.category_id,cat.category_name,qmaster.type_id,qtype.question_type_name," +
		    		" qmaster.test_id,question.diff_id,(select dlevel.diff_description from cp_difficulty_level dlevel " +
		    		" where dlevel.diff_id=question.diff_id) as difficultyLevel,question.question_no,ifNull(question.question,'') " +
		    		" as question,question.avg_time,ifNull(question.quantityA,'') as quantityA,ifNUll(question.quantityB,'') as quantityB," +
		    		" ifNull(question.solution_text,'') as solutionText,ifNull(question.solution_video,'') as solutionVideo," +
		    		" ifNull(question.access_type,'') as accessType,ifNull(question.referral,'') as referral,ifNull(question.`status`,'') as" +
		    		" `status`,ifNull(question.question_passage,'') as passage,GROUP_CONCAT(distinct answers.answer SEPARATOR '@gbst@') as answers,ifNull(GROUP_CONCAT(distinct choices.choice SEPARATOR '@gbst@'),'') as choices," +
		    		" (select GROUP_CONCAT(skill.skill_name) from cp_skill_level skill where skill.skill_id in(question.skill_id1,question.skill_id2,question.skill_id3)) as skills" +
		    		" ,ifNull(qmaster.passage_id,'') as passageId,ifNull(qmaster.graph_id,'') as graphId,ifNull(qmaster.question_title,'') as questionTitle,ifNull(qmaster.question_directions,'') as directions,question.skill_id1,ifNull(question.skill_id2,'') as skillid2 ," +
		    		" ifNull(question.skill_id3,'') as skillid3 from cp_question_masters qmaster left join cp_question_choices choices " +
		    		" on qmaster.question_id=choices.question_id ,cp_questions question,cp_question_answers answers,cp_question_lessons lessons,cp_question_categories cat,cp_question_type qtype" +
		    		"  where qmaster.question_id=question.question_id  and cat.category_id=qmaster.category_id and qtype.question_type_id=qmaster.type_id" +
		    		"  and lessons.question_id=qmaster.question_id and qmaster.question_id=answers.question_id and qmaster.section_id in(1,2) " +
		    		" and qmaster.category_id in(1,4,6)  and qmaster.type_id in(select type.question_type_id  from cp_question_categories cat," +
		    		" cp_question_type type where cat.category_id=type.category_id and cat.category_id in(1,2,3,4,5,6,7,8,9,10,11,12)) and qmaster.test_id in(1) and" +
		    		" question.diff_id in(1,2,3,4,5,6,7,8,9,10,11,12,11,20,21) and qmaster.question_id in(select qlessons.question_id from cp_question_lessons qlessons " +
		    		"  where qlessons.sublesson_id in(1,2,3,4,5,6,7,8,9,10,11,12) and qlessons.sublesson_id=lessons.sublesson_id)" +
		    		"  and qmaster.question_id not in(select results.question_id  from cp_test_results results  where results.userid like '1')" +
		    		" group by qmaster.question_id  order by rand() ";*/
		  /*  String sql="select '', qmaster.question_id,qmaster.section_id,qmaster.category_id,cat.category_name,qmaster.type_id,qtype.question_type_name," +
		    		" qmaster.test_id,question.diff_id,(select dlevel.diff_description from cp_difficulty_level dlevel where dlevel.diff_id=question.diff_id) " +
		    		" as difficultyLevel,question.question_no,ifNull(question.question,'') as question,question.avg_time,ifNull(question.quantityA,'') as " +
		    		" quantityA,ifNUll(question.quantityB,'') as quantityB,ifNull(question.solution_text,'') as solutionText, ifNull(question.solution_video,'')" +
		    		"  as solutionVideo,ifNull(question.access_type,'') as accessType,ifNull(question.referral,'') as referral,ifNull(question.`status`,'') " +
		    		" as `status`,ifNull(question.question_passage,'') as passage,GROUP_CONCAT(distinct answers.answer SEPARATOR '@gbst@') as answers,ifNull" +
		    		" (GROUP_CONCAT(distinct choices.choice SEPARATOR '@gbst@'),'') as choices,(select GROUP_CONCAT(skill.skill_name) " +
		    		" from cp_skill_level skill where skill.skill_id in(question.skill_id1,question.skill_id2,question.skill_id3)) as skills" +
		    		"  ,ifNull(qmaster.passage_id,'') as passageId,ifNull(qmaster.graph_id,'') as graphId,ifNull(qmaster.question_title,'') as questionTitle," +
		    		" ifNull(qmaster.question_directions,'') as directions,question.skill_id1,ifNull(question.skill_id2,'') as skillid2 ," +
		    		"  ifNull(question.skill_id3,'') as skillid3 from cp_question_masters qmaster left join cp_question_choices choices  " +
		    		" on qmaster.question_id=choices.question_id , cp_questions question,cp_question_answers answers,cp_question_categories cat," +
		    		" cp_question_type qtype where qmaster.question_id=question.question_id and cat.category_id=qmaster.category_id and " +
 
		    		" qtype.question_type_id=qmaster.type_id and qmaster.question_id=answers.question_id and qmaster.section_id in(1) and qmaster.category_id in(1,2,3,4,5,6,7,8,9,10,11,12,13) " +
 
		    		"  and qmaster.type_id in(select type.question_type_id  from cp_question_categories cat,cp_question_type type where " +
		    		" cat.category_id=type.category_id and cat.category_id in(1,2,3,4,5,6,7,8,9,10,11,12,13) ) and qmaster.test_id in(1)  and question.diff_id in(2,11,20,21)" +
		    		" group by qmaster.question_id  order by rand() ";
		    */
		    //and qlessons.master_lesson like 'YES'
		    String sql=query;
		    System.out.println("Select query From DAO : "+sql);
		    try {
					connection=DatabaseConnection.getCrunchPrepConnection();
					statement=connection.createStatement();
					stmt1=connection.createStatement();
					resultSet=statement.executeQuery(sql);
					while(resultSet.next())
					{
						QuestionsUploadBO bo=new QuestionsUploadBO();
						
						bo.setQuestion_id(resultSet.getInt(8));
						bo.setSection_id(resultSet.getInt("section_id"));
						bo.setCategory_id(resultSet.getInt("category_id"));
						bo.setTypeId(resultSet.getInt("type_id"));
						bo.setTest_id(resultSet.getInt("test_id"));
						bo.setLesson_id(resultSet.getInt("sublesson_id"));
				        bo.setPassageId(resultSet.getString("passage_id")!=null ?resultSet.getInt("passage_id") :0);				  
						if(bo.getPassageId()!=0)
						{
							String sql1="select passage.passage_title,passage.passage_type,passage.passage,passage.difficulty from cp_reading_comprehension_passage passage where passage.passage_id='"+bo.getPassageId()+"'";
							resultSet1=stmt1.executeQuery(sql1);
							while(resultSet1.next())
							{
								bo.setPassageTitle(resultSet1.getString("passage_title"));
								bo.setPassage_type(resultSet1.getString("passage_type"));
								bo.setPassage(resultSet1.getString("passage"));
								bo.setPassage_difficultyId(resultSet1.getInt("difficulty"));
							}							
						}				 
					    bo.setGraphId(resultSet.getString("graph_id")!=null ?resultSet.getInt("graph_id") :0);
						if(bo.getGraphId()!=0)
						{
							String sql2="select graph.graph_title,graph.graph from cp_data_interpretation_graph graph where graph.graph_id='"+bo.getGraphId()+"'";
						    resultSet1=stmt1.executeQuery(sql2);
							while(resultSet1.next())
							{
								 bo.setGraphTitle(resultSet1.getString("graph_title"));
								 bo.setGraph(resultSet1.getString("graph"));
							}												
						}						
						bo.setQuestion_title(resultSet.getString("question_title"));
						bo.setQuestion_directions(resultSet.getString("question_directions"));
						bo.setSkillid1(resultSet.getInt("skill_id1"));
						bo.setSkillid2(resultSet.getString("skill_id2")!=null ?resultSet.getInt("skill_id2") :0);	
						bo.setSkillid3(resultSet.getString("skill_id3")!=null ?resultSet.getInt("skill_id3") :0); 
						bo.setDifficulty_id(resultSet.getInt("diff_id"));
						bo.setQuestion_no(resultSet.getString("question_no")!=null?resultSet.getInt("question_no") :0);
						bo.setQuestion(resultSet.getString("question"));
						bo.setAvg_time(resultSet.getString("avg_time"));
						if(resultSet.getString("quantityA")!=null)
						{
						bo.setQuantityA(resultSet.getString("quantityA"));}
						if(resultSet.getString("quantityB")!=null)
						{
						bo.setQuantityB(resultSet.getString("quantityB"));}
						if(resultSet.getString("solution_text")!=null)
						{
						bo.setSolution_text(resultSet.getString("solution_text"));}
						if(resultSet.getString("solution_video")!=null)
						{						
						bo.setSolution_video(resultSet.getString("solution_video"));}
						if(resultSet.getString("access_type")!=null)
						{
						bo.setAccess_type(resultSet.getString("access_type"));}
						bo.setRefferal(resultSet.getString("referral"));
						bo.setStatus(resultSet.getString("status"));
						if(resultSet.getString("question_passage")!=null)
						{
							bo.setQuestionPassage(resultSet.getString("question_passage"));
						}
						
						String sql4="select  answers.answer  from cp_question_masters qmas left join " +
								" cp_question_answers answers on qmas.question_id=answers.question_id where qmas.question_id like "+bo.getQuestion_id();
					   resultSet1=stmt1.executeQuery(sql4);
					  System.out.println("Answers Query : "+sql4);
					   ArrayList<String> al1=new ArrayList<String>();
						while(resultSet1.next())
						{
							if(resultSet1.getString(1)!=null)
							{
				             al1.add(resultSet1.getString(1));
						  }
						}
							 
						bo.setAnswers(al1);
						bo.setAnswers_length(bo.getAnswers().size());
						
						
						
					System.out.println(bo.getAnswers_length()+"\t "+bo.getAnswers());
						String sql3="select choices.choice  from cp_question_masters qmas left join " +
							     " cp_question_choices choices on qmas.question_id=choices.question_id where qmas.question_id like'"+bo.getQuestion_id()+"'";
					   resultSet1=stmt1.executeQuery(sql3);
					   System.out.println("Choices Quewry : "+sql3);
					   ArrayList<String> al=new ArrayList<String>();
						while(resultSet1.next())
						{
							if(resultSet1.getString(1)!=null)
							{
				             al.add(resultSet1.getString(1));
						    }
							 
								
					System.out.println(bo.getChoices_length()+"\t"+bo.getChoices());
					}
				 bo.setChoices(al);
				 bo.setChoices_length(bo.getChoices().size());					
					String skill_arr[]=resultSet.getString("skills").split(",");
					bo.setSkills(Arrays.asList(skill_arr));
					bo.setDifficulty_name(resultSet.getString("difficultyLevel"));
					bo.setCategory_name(resultSet.getString("category_name"));
					list.add(bo);
					}
					 
				} catch (ConnectionException e) {
					throw new ConnectionException(e);				 
				} catch (SQLException e) {				 
					e.printStackTrace();
				} 
	        finally{
	        	
					try {
						if(resultSet1!=null)
						resultSet1.close();
						if(stmt1!=null)
			        	    stmt1.close();
			        	
					} catch (SQLException e) {						 
						e.printStackTrace();
					}
	        	
	        	DBUtil.closeConnection(resultSet, statement, connection);
	        }
			return list;
				
		   
			 
		}
	
	
	
	
public String averageTimeCalcultation(QuestionsUploadBO questionUploadBO) throws DBException, SQLException{
		
		String result ="Error".toLowerCase();
		
		Statement st=null;
		ResultSet rs=null; 
		Connection con=null;
		
		
		try {
			con = DatabaseConnection.getCrunchPrepConnection();
			String query="update cp_questions set avg_time=(select SEC_TO_TIME(sum(TIME_TO_SEC(a.user_time))/count(a.userid)) from cp_test_results a where question_id="+questionUploadBO.getQuestion_id()+") where question_id="+questionUploadBO.getQuestion_id()+"";
	        st= con.createStatement();
	        if(st.executeUpdate(query)==1){ 
	        	result ="success"; 
	        }    
		
		} catch (ConnectionException e) {
			throw new DBException(e);
		} 
		finally{
			try {if(con!=null){
				con.close();
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBException(e);
			}
		} 
		return result;
		
		
	}


/*
 * @author
 * MEthod to get the percentage for categories
 *  (non-Javadoc)
 * @see com.greenbuds.crunchprep.dao.practicesession.IPracticeSessionDAO#getPercentageForCategories(java.lang.String)
 */
@Override
public JSONObject getPercentageForCategories(String inputQuery) throws SQLException, DBException, JSONException {
	// TODO Auto-generated method stub
	int percentage=0;
	Statement st=null;
	ResultSet rs=null; 
	
	JSONObject jsonObject=new JSONObject();
	try {
	
		String query=inputQuery;
		String quiree=inputQuery;
		System.out.println(quiree);
		JSONArray array=DBUtil.convertResultSetIntoJSONFormate(quiree);
		
		jsonObject.put("isDataAvailable", array!=null);
		
		if(array!=null) {
			jsonObject.put("percentageCount",array.toString()).put("datarowCount", array.length());
		}
		 System.out.println(jsonObject.toString());    
	
	} catch (ConnectionException e) {
		throw new DBException(e);
	} 
	
	
	return jsonObject;
}
	
public boolean isQuestionSubmitted(QuestionsUploadBO bo)throws DBException, SQLException,CPException
{
	boolean result=false; 
	
	Statement st=null;
	ResultSet rs=null; 
	Connection con=null; 
	try {
		con = DatabaseConnection.getCrunchPrepConnection();
		String query="select * from cp_test_results t where t.userid like '"+bo.getUser_id()+"' and t.test_no like '"+bo.getTest_no()+"' and t.test_master_id like '"+bo.getTest_id()+"' and t.question_id like '"+bo.getQuestion_id()+"'  ";
        st= con.createStatement();
        rs=st.executeQuery(query);
        while(rs.next())
        {
        	result=true;
        } 
	
	} catch (ConnectionException e) {
		throw new DBException(e);
	}  
	finally{
		try { 
			con.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CPException(e);
		}
	} 
	return result;
}
	
	
public List<ResultsBO> practiseSessionHistoryDetails(QuestionsUploadBO questionUploadBO) throws  ConnectionException{ 
	Statement st=null;
	ResultSet rs=null; 
	Connection con=null;
	
	ArrayList<ResultsBO> resultsBOs=new ArrayList<ResultsBO>();
	 
		con = DatabaseConnection.getCrunchPrepConnection();
		/*String query="SELECT tcre.session_name,tres.test_date,tres.test_no,(select count(tres.question_id) )TotalQuestions," +
				" (select count(tres.question_id) from cp_test_results tres  where tres.result like 'CORRECT' and tres.userid like '"+questionUploadBO.getUser_id()+"' " +
				" and tres.test_no like '1')CorrectQuestions,Format((((select count(tres.question_id)" +
				" from cp_test_results tres  where tres.result like  'CORRECT' and tres.userid like '"+questionUploadBO.getUser_id()+"' and tres.test_no like '1')" +
				" /(select count(tres.question_id) ))*100),2)percentage from " +
				" cp_test_results tres ,cp_testcreation tcre  where tcre.test_no=tres.test_no and tcre.testmaster_id=tres.test_master_id  " +
				" and tres.test_master_id=1 and tres.userid='"+questionUploadBO.getUser_id()+"' GROUP by tres.test_no order by tres.test_date ";*/
		
		String query="SELECT tcre.session_name,tres.test_date,tres.test_no,(select count(tres.question_id) )TotalQuestions," +
				" (select count(tres.question_id) from cp_test_results tres  where tres.result like 'CORRECT' and tres.userid like '"+questionUploadBO.getUser_id()+"'" +
				" and tres.test_no=tcre.test_no)CorrectQuestions," +
				" FORMAT(((select count(tres.question_id) from cp_test_results tres  where tres.result like 'CORRECT' and tres.userid like '"+questionUploadBO.getUser_id()+"' " +
				" and tres.test_no=tcre.test_no)/(select count(tres.question_id) ))*100,2) as percentage" +
				" from  cp_test_results tres ,cp_testcreation tcre  where tcre.test_no=tres.test_no and tcre.testmaster_id=tres.test_master_id   " +
				" and tres.test_master_id=1 and tres.userid='"+questionUploadBO.getUser_id()+"' GROUP by tres.test_no order by tres.test_date desc ";
     
		System.out.println(query);
		
		try {
			st= con.createStatement();
			 rs=st.executeQuery(query);
			    while(rs.next())
			    {
			    	ResultsBO resultsBO=new ResultsBO();
			    	resultsBO.setPractiseSessioName(rs.getString("session_name"));
			    	resultsBO.setTest_date(rs.getString("test_date"));
			    	resultsBO.setTest_no(rs.getInt("test_no"));
			    	resultsBO.setTotal_qsns(rs.getInt("TotalQuestions"));
			    	resultsBO.setCorrect(rs.getInt("CorrectQuestions"));
			    	resultsBO.setSessionCorrectPercentage(rs.getString("percentage"));
			    	resultsBOs.add(resultsBO);
			    }
				
			 
		} catch (SQLException e) {
			 e.printStackTrace();
		}
   finally
   {
	   DBUtil.closeConnection(rs, st, con);
   }
	
	return resultsBOs;
	
	
}
	
public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException{
	// TODO Auto-generated method stub
	boolean result=false;
	Statement st=null,st1=null;
	ResultSet rs=null; 
	    	Connection connection=null; 
			try{ 
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement();
				st1=connection.createStatement();   
				String query="INSERT INTO `cp_question_history` (`user_id`, `question_id`, `question_date`, `question_time`, `question_status`, `question_guess`,section_id,testno,test_master_id,typeid) VALUES ('"+uploadBO.getUser_id()+"', '"+uploadBO.getQuestion_id()+"', CURRENT_TIMESTAMP(), '"+uploadBO.getUser_time()+"', '"+uploadBO.getResult()+"', '"+uploadBO.getGuessed()+"','"+uploadBO.getSection_id()+"',"+uploadBO.getTest_no()+",1,"+uploadBO.getTypeId()+")";
				
				String query1="update cp_test_results   set   flagstatus='"+uploadBO.getFlag_status()+"' where  userid ="+uploadBO.getUser_id()+"   and question_id="+uploadBO.getQuestion_id()+" and section_id="+uploadBO.getSection_id()+" and test_no="+uploadBO.getTest_no()+" ";
				System.out.println("Retry :"+query+"\nthe update query-----------------------------------------------------------"+query1);
				int n = st.executeUpdate(query);
				if (n > 0) {
					//System.out.println("nserrt doneeeeeeeeeeeeeeeee");
					int n1 = st1.executeUpdate(query1);
					System.out.println("the value sis"+n1);
					result = false;
				}
				connection.close(); 
			}
			catch(ConnectionException exp){ 
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			} 
	return result;
} 
	

@Override
public JSONObject getUserPaceValues(String inputQuery) throws SQLException, DBException, JSONException {
	// TODO Auto-generated method stub

	JSONObject jsonObject=new JSONObject();
	try {
	
	    String quiree=inputQuery;
		System.out.println(quiree);
		JSONArray array=DBUtil.convertResultSetIntoJSONFormate(quiree);
		
		jsonObject.put("isDataAvailable", array!=null);
		
		if(array!=null) {
			jsonObject.put("timeDifference",array.toString()).put("timeDiffCount", array.length());
		}
		 System.out.println(jsonObject.toString());    
	

	} catch (ConnectionException e) {
		throw new DBException(e);
	} 
	
	
	return jsonObject;
}
	
	
public List<ResultsExcelBO> getExcelsheetDataPreparing(ResultsExcelBO resultsExcelBO) throws DBException, SQLException{
	
	System.out.println("Excel sheet data query is calling");
	
	
	Connection con=null; 
	Statement st=null;
	ResultSet rs=null; 
	List<ResultsExcelBO> excelData = new ArrayList<ResultsExcelBO>();
	
	String query1 ="SELECT prof.first_name,prof.last_name,CATE.section_id,CATE.category_id,CATE.category_name,QTYPE.question_type_id," +
			"QTYPE.question_type_name,(SELECT RE.session_type FROM cp_test_results RE where RE.userid like "+resultsExcelBO.getUserid()+" and RE.test_no like "+resultsExcelBO.getTest_no()+" order by RE.session_type DESC LIMIT 1) TEST_NAME,(SELECT DATE_FORMAT(RE.test_date,'%b-%d-%Y') FROM cp_test_results RE where RE.userid like "+resultsExcelBO.getUserid()+" and RE.test_no like "+resultsExcelBO.getTest_no()+" order by RE.test_date DESC LIMIT 1) TEST_DATE,(SELECT COUNT(*)  FROM cp_test_results RES where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND " +
			"RES.type_id=QTYPE.question_type_id AND RES.test_no ="+resultsExcelBO.getTest_no()+") ATTEMPT,(SELECT COUNT(*)  " +
			"FROM cp_test_results RES where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND RES.type_id=QTYPE.question_type_id AND" +
			" RES.test_no ="+resultsExcelBO.getTest_no()+" AND RES.result LIKE 'CORRECT') CORRECT,(SELECT COUNT(*)  FROM " +
			"cp_test_results RES where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND RES.type_id=QTYPE.question_type_id AND " +
			"RES.test_no ="+resultsExcelBO.getTest_no()+" AND RES.result LIKE 'INCORRECT') INCORRECT,(SELECT COUNT(*)  FROM cp_test_results RES" +
			" where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND RES.type_id=QTYPE.question_type_id AND RES.test_no ="+resultsExcelBO.getTest_no()+" AND " +
			"RES.result LIKE 'SKIPPED') SKIPPED,IFNULL((SELECT SUM(DLE.diff_description)  FROM cp_test_results RES," +
			"cp_difficulty_level DLE where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND RES.type_id=QTYPE.question_type_id AND " +
			"RES.test_no ="+resultsExcelBO.getTest_no()+" AND RES.result LIKE 'CORRECT' AND DLE.diff_id=RES.diff_id),0) CORRECT_DIFFICULTY," +
			"IFNULL((SELECT SUM(DLE.diff_description)  FROM cp_test_results RES,cp_difficulty_level DLE " +
			"where RES.userid LIKE "+resultsExcelBO.getUserid()+" AND RES.type_id=QTYPE.question_type_id AND RES.test_no ="+resultsExcelBO.getTest_no()+" AND " +
			"RES.result LIKE 'INCORRECT' AND DLE.diff_id=RES.diff_id),0) INCORRECT_DIFFICULTY  FROM " +
			"cp_question_categories CATE,cp_question_type QTYPE,cp_user_profiles prof WHERE CATE.section_id="+resultsExcelBO.getSection_id()+" AND " +
			"QTYPE.category_id=CATE.category_id and prof.user_id= "+resultsExcelBO.getUserid()+" ORDER BY CATE.category_id,QTYPE.question_type_id";
       System.out.println("Excel Query::::::::@@@@@@@@@::::::::"+query1);     
       
         
       
	try {
			con = DatabaseConnection.getCrunchPrepConnection();
			st = con.createStatement();
			rs= st.executeQuery(query1);
			
			while(rs.next()){
				
				ResultsExcelBO bo = new ResultsExcelBO();
				 bo.setFirst_name(rs.getString("first_name"));
				 bo.setLast_name(rs.getString("last_name"));
				 bo.setTest_date(rs.getString("test_date"));
				 bo.setTest_name(rs.getString("TEST_NAME"));
				 bo.setSection_id(rs.getInt("section_id"));
				 bo.setCategory_id(rs.getInt("category_id"));
				 bo.setCategory_name(rs.getString("category_name"));
				 bo.setQuestion_type_id(rs.getInt("question_type_id"));
				 bo.setQuestion_type_name(rs.getString("question_type_name"));
				 bo.setAttemptQuestions(rs.getInt("ATTEMPT"));
				 bo.setCorrectQuestions(rs.getInt("CORRECT"));
				 bo.setIncorrectQuestions(rs.getInt("INCORRECT"));
				 bo.setSkippedQuestions(rs.getInt("SKIPPED"));
				 bo.setIncorrectDifficultyQuestions(rs.getInt("INCORRECT_DIFFICULTY"));
				 bo.setCorrectDifficultyQuestions(rs.getInt("Correct_DIFFICULTY"));
				 
				 
				
				
				 double rightanswers=0.0,avgDifficultyRight=0.0,avgDifficultyWorng=0.0;
				
				 
				 if(bo.getAttemptQuestions()!=0){
					 rightanswers=Double.parseDouble(""+bo.getCorrectQuestions())/Double.parseDouble(""+bo.getAttemptQuestions());
					 rightanswers*=100;
					 if(bo.getCorrectQuestions()!=0)
						 avgDifficultyRight=(bo.getCorrectDifficultyQuestions()/bo.getCorrectQuestions());
					   if(bo.getIncorrectQuestions()!=0)
						   avgDifficultyWorng=(bo.getIncorrectDifficultyQuestions()/bo.getIncorrectQuestions());
				 }
				 
				/* System.out.println("IncorrectDifficutl!!!!!!!!!!!!!!:"+bo.getIncorrectDifficultyQuestions());
				 System.out.println("CorrectDifficulty!!!!!!!!!!!!!!!:"+bo.getCorrectDifficultyQuestions());
				    System.out.println("RightAnswers!!!!!!!:"+rightanswers);
				    System.out.println("average Difficulty Right answers!!!!!!!! :"+avgDifficultyRight);
				    System.out.println("average Difficulty Wrong answers!!!!!!!! :"+avgDifficultyWorng);*/
				 
				  bo.setPercentageCorrectQuestions(rightanswers);
				  bo.setAvgDifficultyRightAnswers(avgDifficultyRight);
				  bo.setAvgDifficultyWorngAnswers(avgDifficultyWorng);
				  
		
				 excelData.add(bo);
			
			}
			
			
			
		} catch (ConnectionException e) {
			 throw new DBException(e);
		}finally{
			
			 if(con!=null){
				 con.close();
				 
			 }
			
		}
         
	  return excelData;
	
}

      
   public List<ResultLessionBO> getLessionExcelsheetData(ResultLessionBO resultLessionBO) throws DBException, SQLException{
	    Connection con=null; 
		Statement st=null;
		ResultSet rs=null; 
		List<ResultLessionBO> LessionExcelData = new ArrayList<ResultLessionBO>();
	  
		String query="SELECT LES.lesson_id,LES.lesson_name,(SELECT COUNT(*)  FROM cp_test_results RES where RES.userid LIKE "+resultLessionBO.getUser_id()+" and RES.test_no ="+ resultLessionBO.getTest_no()+" AND RES.question_id AND RES.question_id IN (SELECT QLES.question_id FROM cp_question_lessons QLES" +
        		" WHERE QLES.sublesson_id =LES.lesson_id))ATTEMPT_LESSONS,(SELECT COUNT(*)  FROM cp_test_results RES where RES.userid LIKE "+resultLessionBO.getUser_id()+"" +
        		" AND RES.test_no ="+resultLessionBO.getTest_no()+" AND RES.result LIKE 'CORRECT' AND RES.question_id AND RES.question_id IN (SELECT QLES.question_id " +
        		"FROM cp_question_lessons QLES WHERE QLES.sublesson_id =LES.lesson_id))LESSIONS_CORRECT,(SELECT COUNT(*)  FROM cp_test_results RES" +
        		" where RES.userid LIKE "+resultLessionBO.getUser_id()+" AND RES.test_no ="+resultLessionBO.getTest_no()+" AND RES.result LIKE 'INCORRECT' AND RES.question_id AND " +
        		"RES.question_id IN (SELECT QLES.question_id FROM cp_question_lessons QLES WHERE QLES.sublesson_id =LES.lesson_id))LESSIONS_INCORRECT " +
        		"FROM cp_lessons LES,cp_lessons_masters LMAS,cp_question_lessons QLES WHERE LMAS.lesson_id=LES.lesson_id AND LMAS.section_id="+resultLessionBO.getSection_id()+"" +
        		" AND QLES.sublesson_id=LES.sublesson_id GROUP BY LES.lesson_id";
		  
		   System.out.println("Result lessions Query:::::::::::::::::::"+query);
		
		       try {
				con = DatabaseConnection.getCrunchPrepConnection();
				st = con.createStatement();
				rs =st.executeQuery(query);
				double percentageLessonsRight=0.0;
				while(rs.next()){
					
					ResultLessionBO bo = new ResultLessionBO();
					bo.setLesson_id(rs.getInt("lesson_id"));
					  bo.setLesson_name(rs.getString("lesson_name"));
					  bo.setAttemp_lessons(rs.getInt("ATTEMPT_LESSONS"));
					  bo.setLessons_correct(rs.getInt("LESSIONS_CORRECT"));
					  bo.setLessons_incorrect(rs.getInt("LESSIONS_INCORRECT"));
					  if(bo.getAttemp_lessons()!=0){
					  percentageLessonsRight =(Double.parseDouble(""+bo.getLessons_correct())/Double.parseDouble(""+bo.getAttemp_lessons()));
					   percentageLessonsRight*=100;
			            System.out.println("% percentage of lessons @@@@@@@@@@@@@@@@@@@@@@@@@@###################:"+percentageLessonsRight);
					    bo.setPercentageOfLessons( percentageLessonsRight);
					  }
					  LessionExcelData.add(bo);
					  
                      
				}
		               		
			} catch (ConnectionException e) {
				 throw new DBException(e);
			}finally{
				
				if(con!=null)
					con.close();
			
			}
				
	   
	   return LessionExcelData ;
   }


@Override
public String isResumeTest(int userid) {
	// SELECT f.`status` FROM  cp_fulllength_test f WHERE f.userId=10278
	String result = "normal";
	Connection con=null; 
	Statement st=null;
	ResultSet rs=null; 
	 
	try {
		String query="SELECT * FROM  cp_fulllength_test f WHERE f.userId="+userid+" order by f.test_starting_date desc limit 1" ;
		System.out.println("********** CHECKING IS RESUME TEST************"+query);
		
		
		con = DatabaseConnection.getCrunchPrepConnection();
		st = con.createStatement();
		rs =st.executeQuery(query);
		if(rs.next())
		{
			result = rs.getString("status");
			if(result.equalsIgnoreCase("INCOMPLETE"))
			{
				result="resume";
			}
			else{
				
				result="normal";
			}
		}
		System.out.println("********** CHECKING IS RESUME TEST************"+result);
		
		//'COMPLETE','INCOMPLETE'
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		
		if(con!=null)
			try {
				
				con.close();
				st.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	return result;
}


@Override
public ResumeBo startResumeTest(int user_id) {
	// TODO Auto-generated method stub
	Connection con=null; 
	Statement st=null;
	ResultSet rs=null; 
	resumeBo = new  ResumeBo(); 
	
	String qry = "SELECT f.testNo,s.activity_id FROM cp_fulllength_test f, cp_user_activity s WHERE f.testNo = s.test_no and f.userId = s.user_id and f.userId = "+user_id;
	try {
		con = DatabaseConnection.getCrunchPrepConnection();
		st = con.createStatement();
		rs =st.executeQuery(qry);
		
		if(rs.next())
		{
			resumeBo.setTestNo(rs.getInt("testNo"));
			resumeBo.setActivity_id(rs.getInt("activity_id"));		
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally{
		
		if(con!=null)
			try {
				
				con.close();
				st.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	return resumeBo;
}

}
