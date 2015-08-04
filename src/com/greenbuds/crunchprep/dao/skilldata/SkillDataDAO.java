package com.greenbuds.crunchprep.dao.skilldata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.dao.contentcreator.LessonsDAO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

public class SkillDataDAO implements ISkillDataDAO{
	
	private static SkillDataDAO skillDataDAO;
	private SkillDataDAO(){
		
	}
	public static synchronized SkillDataDAO getInstance(){
		if(skillDataDAO==null)
			skillDataDAO=new SkillDataDAO();
		return skillDataDAO;
	}
	@Override
	public List<SkillDataBO> getSkillData(int userid) throws CPException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SkillDataBO> skillData=new ArrayList<SkillDataBO>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
			
			/*	String query=" select  sectionId,sectionName,skillName,skillID,quecount,ansQue,totalQue,avgTime,TIME_TO_SEC(avgTime) as timetoSec, (queCount/ansQue)*100 as accuracy from" +
						" ( select sec.section_id as sectionId,sec.section_name as sectionName,slevel.skill_name skillName,skills as skillID,count(question_id) quecount ," +
						"(select  count(res.question_id) from cp_test_results res where res.userid like '"+userid+"'  and " +
						"res.test_date=(select max(re.test_date) from cp_test_results re where  re.userid like '"+userid+"' and " +
						"re.question_id=res.question_id) and (res.skill_id1=skills or res.skill_id2=skills or res.skill_id3=skills) and res.result not like 'SKIPPED'  " +
						"order by res.question_id) as ansQue,(select  count(res.question_id) from cp_test_results res where res.userid like '"+userid+"' " +
						"  and res.test_date=(select max(re.test_date) from cp_test_results re where   re.userid like '"+userid+"' and re.question_id=res.question_id)" +
						" and (res.skill_id1=skills or res.skill_id2=skills or res.skill_id3=skills)    order by res.question_id) as totalQue, (select   SEC_TO_TIME(sum(TIME_TO_SEC(res.user_time))/count(res.question_id)) avg " +
						"from cp_test_results res where res.userid like '"+userid+"'  and res.test_date=(select max(re.test_date) from cp_test_results re " +
						"where re.userid like '"+userid+"' and re.question_id=res.question_id) and (res.skill_id1=skills or res.skill_id2=skills or " +
						"res.skill_id3=skills)   order by res.question_id) as avgTime from ((select results.skill_id1 as skills  " +
						"from cp_test_results results where results.userid like '"+userid+"') union (select results.skill_id2 as skills   " +
						"from cp_test_results results where results.userid like '"+userid+"') union (select results.skill_id3  as skills   " +
						"from cp_test_results results where results.userid like '"+userid+"') ) as  skillset inner join  " +
						"(select res.question_id,res.skill_id1,res.skill_id2,res.skill_id3,res.result,res.test_date as testDate," +
						"res.user_time as time1,res.section_id as sectionId from cp_test_results res where res.userid like '"+userid+"'  " +
						"and res.test_date=(select max(re.test_date) from cp_test_results re where  re.userid like '"+userid+"' " +
						"and re.question_id=res.question_id) and res.result like 'CORRECT'  order by res.question_id) as ansQue," +
						"cp_skill_level slevel,cp_sections sec where  skillset.skills in(skill_id1,skill_id2,skill_id3) and " + 
						"slevel.skill_id=skills and sec.section_id=sectionId group by skillset.skills order by sectionId) as data1  order by sectionId,accuracy desc   "; 
			*/	
				String query="select sections.section_name,secId,skill_id,skill_name,correct_questions,ansQuestions,totalQuestions,avgTime," +
						" (correct_questions/ansQuestions)*100 as accuracy,TIME_TO_SEC(avgTime) as timetoSec from(select section_id as secId,skill_id,skill_name ,(select count(question_id)" +
						"  from (select   results.question_id,results.test_date,results.result,results.skill_id1,results.skill_id2,results.skill_id3 from cp_test_results results" +
						"  where results.userid like '"+userid+"' and results.result like 'CORRECT' group by results.question_id   order by results.question_id desc) " +
						" sub where  (sub.skill_id1=skill_id or sub.skill_id2=skill_id or sub.skill_id3=skill_id)) as correct_questions," +
						" (select count(question_id) from (select   results.question_id,results.test_date,results.result,results.skill_id1,results.skill_id2," +
						" results.skill_id3 from cp_test_results results where results.userid like '"+userid+"' and results.result not like 'SKIPPED' group by " +
						" results.question_id   order by results.question_id desc)  sub where  (sub.skill_id1=skill_id or sub.skill_id2=skill_id or sub.skill_id3=skill_id)) as ansQuestions," +
						" (select count(question_id) from (select   results.question_id,results.test_date,results.result,results.skill_id1,results.skill_id2,results.skill_id3" +
						" from cp_test_results results where results.userid like '"+userid+"'   group by results.question_id   order by results.question_id desc)  sub where " +
						" (sub.skill_id1=skill_id or sub.skill_id2=skill_id or sub.skill_id3=skill_id)) totalQuestions," +
						"   (select SEC_TO_TIME(sum(TIME_TO_SEC(sub.user_time))/count(sub.question_id)) as avgtime from (select   " +
						" results.question_id,results.test_date,results.result,results.skill_id1,results.skill_id2,results.skill_id3,results.user_time from cp_test_results results" +
						"  where results.userid like '"+userid+"' and results.result not like 'SKIPPED' group by results.question_id   order by results.question_id desc)" +
						"  sub where  (sub.skill_id1=skill_id or sub.skill_id2=skill_id or sub.skill_id3=skill_id)) as avgTime " +
						" from cp_skill_level )total,cp_sections sections where totalQuestions!=0 and sections.section_id=secId  order by secId,accuracy desc";
				   System.out.println("Skill Query Is : "+query);
 				rs=st.executeQuery(query);
				while(rs.next())
				{ 
					SkillDataBO skillDataBO=new SkillDataBO(); 
					skillDataBO.setSection_id(rs.getInt("secId"));
					//System.out.println("SectionId from DAO : "+skillDataBO.getSection_id());
					skillDataBO.setSection_name(rs.getString("section_name"));
					//String val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar progress-bar-success\" role=\"progressbar\"  aria-valuetransitiongoal=\""+rs.getString("accuracy")+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});  ";
					String val=""; 
					double correct=rs.getDouble("correct_questions"); 
			        double total=rs.getDouble("ansQuestions");
					 
				    double accuracyRange=((correct)/(total))*100; 
					if(accuracyRange<=35)
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-danger dk\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					else if(accuracyRange>=36&&accuracyRange<=45)
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-danger lt\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					else if(accuracyRange>=46&&accuracyRange<=55)
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-warning dker\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					else if(accuracyRange<=56&&accuracyRange<=75)
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-warning lt\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					else if(accuracyRange<=76&&accuracyRange<=90)
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-success dk\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					else
					{
						val="<div id=\"progressbarId\"> <div class=\"progress\"> <div class=\"progress-bar bg-success dker\" role=\"progressbar\"  aria-valuetransitiongoal=\""+accuracyRange+"\"></div></div>  <script type=\"text/javascript\">$(document).ready(function(){$(\".progress-bar\").progressbar({display_text:\"fill\"});});";
					}
					 DecimalFormat df = new DecimalFormat("#.##");
					skillDataBO.setAccuracy(val);  
					skillDataBO.setSkill(rs.getString("skill_name"));
					skillDataBO.setAverageTime(rs.getString("avgTime")); 
					
					double timeToSec=rs.getDouble("timetoSec")/60;
					skillDataBO.setTimeInSeconds(""+timeToSec);
					skillDataBO.setTotalAnswered(rs.getString("ansQuestions")+"/"+rs.getString("totalQuestions"));
					skillDataBO.setProgress(df.format(Double.parseDouble(rs.getString("accuracy"))));
					skillDataBO.setSkillId(rs.getInt("skill_id"));
					
					skillData.add(skillDataBO);
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
			return skillData;
	}
	
	
	
	
	
public List<LessonsmasterBO> displyLeastLessonsBasedOnSkillData(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<LessonsmasterBO> skillbasedLessonsData = new ArrayList<LessonsmasterBO>();
		
		 try {
			con = DatabaseConnection.getCrunchPrepConnection();
			st =con.createStatement();
			
			int skill[]=lessonsmasterBO.getSkill_id();
				
				//System.out.println(skill);
				
			String query = "select le.sublesson_id,le.lesson_id,le.lesson_name,mast.lesson_id,mast.section_id,mast.lesson_name as lessonsmaster,sec.section_name from cp_lessons le ,cp_lessons_masters mast,cp_sections sec where (le.skill_id1 in("+skill[0]+","+skill[1]+","+skill[2]+") or le.skill_id2 in("+skill[0]+","+skill[1]+","+skill[2]+") or le.skill_id3 in("+skill[0]+","+skill[1]+","+skill[2]+")) and mast.lesson_id=le.lesson_id and mast.section_id=sec.section_id limit 3";
			System.out.println("Lessons Display Query::::::::::::::::::::::::::::::::::"+query);
			
			rs =st.executeQuery(query);
			while(rs.next()){
				LessonsmasterBO bo = new LessonsmasterBO();
				bo.setSublesson_id(rs.getInt("sublesson_id"));
				bo.setLesson_id(rs.getInt("lesson_id"));
				bo.setLesson_name(rs.getString("lesson_name"));
				bo.setSection_id(rs.getInt("section_id"));
				bo.setLesson_master(rs.getString("lessonsmaster"));
				bo.setSection_name(rs.getString("section_name"));
				
				skillbasedLessonsData.add(bo);
				
				
				
			}
			
			
			
		} catch (ConnectionException e) {
	           throw new DBException(e);
		}finally{
			
			if(con!=null){
				
				con.close();
			}
			
			
		}
		
		
		
	 return skillbasedLessonsData;
		
		
		
	}
@Override
public List<ScoreBO> userSocregraphData(int userid) throws CPException {
	// TODO Auto-generated method stub
	Statement st=null;
	ResultSet rs=null;
		Connection connection=null;
		List<ScoreBO> scoreData=new ArrayList<ScoreBO>();
		try{
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			st=connection.createStatement(); 
		
		 
			String query="select * from cp_test_score where user_id like '"+userid+"'";
			   System.out.println("Skill Query Is : "+query);
				rs=st.executeQuery(query);
			while(rs.next())
			{ 
				ScoreBO scoreBO=new ScoreBO(); 
				scoreBO.setQuantMinimum(rs.getString("totalQuantScaledScore")); 
				scoreBO.setVerbalMinimum(rs.getString("totalVerbalScaledScore"));
				scoreData.add(scoreBO);
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
		return scoreData;
}
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
