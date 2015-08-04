package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.greenbuds.crunchprep.bo.contentcreator.CriticalReasoningBo;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;

public class CriticalReasoningDAO implements ICriticalReasoningDAO {
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(CriticalReasoningDAO.class );
	/** The CriticalReasoningDAO */
	private static CriticalReasoningDAO criticalReasoningDAO;
	private CriticalReasoningDAO(){
		
	}
	/**
	 * Gets the single instance of LessonsDAO.
	 *
	 * @return single instance of LessonsDAO.
	 */
	public static synchronized CriticalReasoningDAO getInstance(){
		if(criticalReasoningDAO==null)
			criticalReasoningDAO=new CriticalReasoningDAO();
		return criticalReasoningDAO;
	}
	 
	@Override
	public boolean saveNewCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException
	{
		boolean result=false;
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    try{
	    	
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			statement=connection.createStatement();
			connection.setAutoCommit(false);			
			String question_masters="INSERT INTO `cp_question_masters` (`section_id`, `category_id`, `type_id`, `test_id`, `question_title`, `question_directions`) " +
					                                         "  VALUES ("+Categories.CRITICAL_REASONING.getSection().getSectionId()+", "+Categories.CRITICAL_REASONING.getCategorieId()+"," +
					                                         		" "+QuestionTypeUtil.getQuestionTypeID(Categories.CRITICAL_REASONING, QuestionType.SINGLE_ANSWER)+", " +
					                                         				""+bo.getTestId()+", '"+bo.getQuestionTititle()+"', '"+bo.getDirections()+"')";
			statement.executeUpdate(question_masters,Statement.RETURN_GENERATED_KEYS);
			
            //System.out.println("Question masters : "+question_masters);
            resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                bo.setQuestionId(resultSet.getInt(1));
            } 
			  
            String skills1[]=new String[3]; 
			String skills[]=bo.getSkills().split(",");	 
			 if(skills.length==1)
			 {
				 
			     skills1[0]=bo.getSkills();
			     skills1[1]=null;
			     skills1[2]=null;
			      
				 
			 }
			 else if(skills.length==2)
			 { 
				 skills1[0]=skills[0].trim();
			     skills1[1]=skills[1].trim();
			     skills1[2]=null;
			 }
			 else
			 {
				 skills1[0]=skills[0].trim();
				 skills1[1]=skills[1].trim();
				 skills1[2]=skills[2].trim(); 
			 }
		     String question="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question`, `solution_text`, `solution_video`, `access_type`, `referral`, `status`,`question_passage`) " +
		  		"VALUES ("+bo.getQuestionId()+","+ skills1[0]+", "+skills1[1]+","+skills1[2]+", "+bo.getDifficultyId()+", '"+bo.getQuestion()+"', '"+bo.getSolutionText()+"', '"+bo.getSolutionVideo()+"', '"+bo.getAccessType()+"', '"+bo.getReferralType()+"', '"+bo.getStatus()+"', '"+bo.getPassage()+"')";
	     // System.out.println("Questin Query : "+question);
		 String answers="INSERT INTO `cp_question_answers` (`question_id`, `answer`) VALUES ("+bo.getQuestionId()+", '"+bo.getAnswer()+"')";
		// System.out.println("Answers : "+answers);
		 String choices="";
		 int k=1;
		 for(int i=0;i<bo.getChoices().length;i++)
		 {
		 choices="INSERT INTO `cp_question_choices` (`question_id`, `choice_no`, `choice`) VALUES " +
		 		"("+bo.getQuestionId()+","+k+",'"+bo.getChoices()[i]+"')";
	//  System.out.println("choices query : "+choices);
		 statement.addBatch(choices);
		 k++;
		 }
   
		 statement.addBatch(question);
		 statement.addBatch(answers);
		 int p[]=statement.executeBatch();
		 connection.commit();
		// System.out.println(p.length);
		 if(p.length==7)
		 {
			 result=true;
			
		 }
	     
	    }
		catch(ConnectionException exp){	
			 if(connection!=null)
				try {
					connection.rollback();
					} catch (SQLException e) {					 
					e.printStackTrace();
				} 
			throw new ConnectionException(exp); 
		} catch (SQLException e) {			 
			e.printStackTrace();
		}
	    finally
	    {
	    	 try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {			 
				e.printStackTrace();
			}
	    	DBUtil.closeConnection(resultSet, statement, connection);
	    }
		return result;
	}
	
	@Override
	public boolean updateCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException
	{
		boolean result=false;
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    try{
	    	//System.out.println("Update");
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			statement=connection.createStatement();
			connection.setAutoCommit(false);			
			String question_masters="UPDATE `cp_question_masters` SET  `test_id`="+bo.getTestId()+", `question_title`='"+bo.getQuestionTititle()+"', `question_directions`='"+bo.getDirections()+"' WHERE  `question_id`="+bo.getQuestionId();
			   
			 String skills1[]=new String[3]; 
				String skills[]=bo.getSkills().split(",");	 
				 if(skills.length==1)
				 {
					 
				     skills1[0]=bo.getSkills();
				     skills1[1]=null;
				     skills1[2]=null;
				      
					 
				 }
				 else if(skills.length==2)
				 { 
					 skills1[0]=skills[0].trim();
				     skills1[1]=skills[1].trim();
				     skills1[2]=null;
				 }
				 else
				 {
					 skills1[0]=skills[0].trim();
					 skills1[1]=skills[1].trim();
					 skills1[2]=skills[2].trim(); 
				 }
		     String question="UPDATE `cp_questions` SET `skill_id1`="+skills1[0]+", `skill_id2`="+skills1[1]+", `skill_id3`="+skills1[2]+", `diff_id`="+bo.getDifficultyId()+", " +
		     		"`question`='"+bo.getQuestion()+"', `solution_text`='"+bo.getSolutionText()+"', " +
		     		"`solution_video`='"+bo.getSolutionVideo()+"', `access_type`='"+bo.getAccessType()+"', `referral`='"+bo.getReferralType()+"', `status`='"+bo.getStatus()+"', `question_passage`='"+bo.getPassage()+"' WHERE  `question_id`="+bo.getQuestionId();
	       statement.addBatch(question_masters);
		    	
		 String answers="UPDATE `crunchprep`.`cp_question_answers` SET `answer`='"+bo.getAnswer()+"' WHERE  `question_id`="+bo.getQuestionId();
		 String choices="";
		 int k=1;
		 for(int i=0;i<bo.getChoices().length;i++)
		 {
			 choices="UPDATE `cp_question_choices` SET `choice`='"+bo.getChoices()[i]+"' WHERE  `question_id`="+bo.getQuestionId()+" and choice_no="+k;		 
		    // System.out.println(choices);
			 statement.addBatch(choices);
			 k++;
		 }
		// System.out.println("question Masters : "+question_masters);
	    	//System.out.println("question : "+question);
		 statement.addBatch(question);
		 statement.addBatch(answers);
		 int p[]=statement.executeBatch();
		 connection.commit();
		 if(p.length==8)
		 {
			 result=true;
			
		 }
	     
	    }
		catch(ConnectionException exp){	
			 if(connection!=null)
				try {
					connection.rollback();
					} catch (SQLException e) {					 
					e.printStackTrace();
				} 
			throw new ConnectionException(exp); 
		} catch (SQLException e) {			 
			e.printStackTrace();
		}
	    finally
	    {
	    	 try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {			 
				e.printStackTrace();
			}
	    	DBUtil.closeConnection(resultSet, statement, connection);
	    }
		return result;
	}
	
	@Override
	public List<CriticalReasoningBo> getAlltheCriticalReasoningQuestions() throws ConnectionException
	{
		List<CriticalReasoningBo> list=new ArrayList<CriticalReasoningBo>();
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    String sql="select qmas.question_id,qmas.section_id,cs.section_name,qmas.category_id,cqc.category_name,qmas.type_id,cqt.question_type_name,qmas.question_title," +
	    		" qmas.question_directions,cq.skill_id1,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id1) as skillName," +
	    		" cq.skill_id2,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id2) as skillName," +
	    		" cq.skill_id3,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id3) as skillName," +
	    		" cq.diff_id,cd.diff_name,cq.question,cq.solution_text,cq.solution_video,cq.access_type,cq.referral,cq.`status`,cqa.answer,group_concat(qc.choice) choice" +
	    		"  from cp_question_masters qmas join  cp_question_choices qc on qmas.question_id = qc.question_id,cp_questions cq,cp_question_answers cqa,cp_sections cs," +
	    		" cp_question_categories cqc,cp_question_type cqt ,cp_difficulty_level cd where cq.question_id=qmas.question_id and cqa.question_id=cq.question_id and cs.section_id=qmas.section_id " +
	    		" and cqc.category_id=qmas.category_id and cqt.question_type_id=qmas.type_id  and cd.diff_id=cq.diff_id	and qmas.category_id=9  group by qmas.question_id";
       //System.out.println("Select query : "+sql);
	    try {
				connection=DatabaseConnection.getCrunchPrepConnection();
				statement=connection.createStatement();
				resultSet=statement.executeQuery(sql);
				while(resultSet.next())
				{
					CriticalReasoningBo bo=new CriticalReasoningBo();
					bo.setQuestionId(resultSet.getInt(1));
					bo.setSectionId(resultSet.getInt(2));
					bo.setSectionName(resultSet.getString(3));
					bo.setCategoryId(resultSet.getInt(4));
					bo.setCategoryName(resultSet.getString(5));
					bo.setTypeId(resultSet.getInt(6));
					bo.setTypeName(resultSet.getString(7));
					bo.setQuestionTititle(resultSet.getString(8));
					bo.setDirections(resultSet.getString(9));
					bo.setSkills(resultSet.getInt(10)+(resultSet.getString(12)==null?"":","+resultSet.getString(12))+(resultSet.getString(14)==null?"":","+resultSet.getString(14)));
					bo.setSkillNames(resultSet.getString(11)+(resultSet.getString(13)==null?"":","+resultSet.getString(13))+(resultSet.getString(15)==null?"":","+resultSet.getString(15)));
					bo.setDifficultyId(resultSet.getInt(16));
					bo.setDifficultyName(resultSet.getString(17));
					bo.setQuestion(resultSet.getString(18));//System.out.println("Question : "+bo.getQuestion());
					bo.setSolutionText(resultSet.getString(19));//System.out.println("Solution Text :" +bo.getSolutionText());
					bo.setSolutionVideo(resultSet.getString(20));
					bo.setAccessType(resultSet.getString(21));
					bo.setReferralType(resultSet.getString(22));
					bo.setStatus(resultSet.getString(23));
					bo.setAnswer(resultSet.getString(24));
					bo.setChoices(resultSet.getString(25).split(","));					
					list.add(bo);
					
				}
				
				
				
			} catch (ConnectionException e) {
				throw new ConnectionException(e);				 
			} catch (SQLException e) {				 
				e.printStackTrace();
			} 
        finally{
        	DBUtil.closeConnection(resultSet, statement, connection);
        }
			
	   
		return list;
	}
	
	@Override
	public CriticalReasoningBo getSingleCriticalQuestion(int questionId) throws ConnectionException
	{
	CriticalReasoningBo bo=new CriticalReasoningBo();
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    String sql="select qmas.question_id,qmas.section_id,cs.section_name,qmas.category_id,cqc.category_name,qmas.type_id,cqt.question_type_name,qmas.question_title," +
	    		" qmas.question_directions,cq.skill_id1,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id1) as skillName," +
	    		" cq.skill_id2,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id2) as skillName," +
	    		" cq.skill_id3,(select cskill.skill_name from cp_skill_level cskill where cskill.skill_id=cq.skill_id3) as skillName," +
	    		" cq.diff_id,cd.diff_name,cq.question,cq.solution_text,cq.solution_video,cq.access_type,cq.referral,cq.`status`,cq.question_passage,cqa.answer,group_concat(qc.choice) choice" +
	    		" ,qmas.test_id from cp_question_masters qmas join  cp_question_choices qc on qmas.question_id = qc.question_id,cp_questions cq,cp_question_answers cqa,cp_sections cs," +
	    		" cp_question_categories cqc,cp_question_type cqt ,cp_difficulty_level cd where cq.question_id=qmas.question_id and cqa.question_id=cq.question_id and cs.section_id=qmas.section_id " +
	    		" and cqc.category_id=qmas.category_id and cqt.question_type_id=qmas.type_id  and cd.diff_id=cq.diff_id	 and qmas.question_id="+questionId+" and qmas.category_id=9  group by qmas.question_id";
       //System.out.println("Select query : "+sql);
	    try {
				connection=DatabaseConnection.getCrunchPrepConnection();
				statement=connection.createStatement();
				resultSet=statement.executeQuery(sql);
				while(resultSet.next())
				{
				 
					bo.setQuestionId(resultSet.getInt(1));
					bo.setSectionId(resultSet.getInt(2));
					bo.setSectionName(resultSet.getString(3));
					bo.setCategoryId(resultSet.getInt(4));
					bo.setCategoryName(resultSet.getString(5));
					bo.setTypeId(resultSet.getInt(6));
					bo.setTypeName(resultSet.getString(7));
					bo.setQuestionTititle(resultSet.getString(8));
					bo.setDirections(resultSet.getString(9));
					bo.setSkills(resultSet.getInt(10)+(resultSet.getString(12)==null?"":","+resultSet.getString(12))+(resultSet.getString(14)==null?"":","+resultSet.getString(14)));
					bo.setSkillNames(resultSet.getString(11)+","+(resultSet.getString(13)==null?"":","+resultSet.getString(13))+","+(resultSet.getString(15)==null?"":","+resultSet.getString(15)));
					bo.setDifficultyId(resultSet.getInt(16));
					bo.setDifficultyName(resultSet.getString(17));
					bo.setQuestion(resultSet.getString(18));
					bo.setSolutionText(resultSet.getString(19));
					bo.setSolutionVideo(resultSet.getString(20));
					bo.setAccessType(resultSet.getString(21));
					bo.setReferralType(resultSet.getString(22));
					bo.setStatus(resultSet.getString(23));
					bo.setPassage(resultSet.getString(24));
					bo.setAnswer(resultSet.getString(25));
					bo.setChoices(resultSet.getString(26).split(","));		
					bo.setTestId(resultSet.getInt(27));
					 
					
				}
				
				
				
			} catch (ConnectionException e) {
				throw new ConnectionException(e);				 
			} catch (SQLException e) {				 
				e.printStackTrace();
			} 
        finally{
        	DBUtil.closeConnection(resultSet, statement, connection);
        }
			
	   
		return bo;
	}
	
	@Override
	public boolean deleteSelectedCriticalQuestions(String questionIds) throws ConnectionException
	{
		boolean result=false;
		Statement statement=null;		 
	    Connection connection=null;
	    try {
			connection=DatabaseConnection.getCrunchPrepConnection();
			statement=connection.createStatement();
			 String questionId[]=questionIds.split(",");
				// System.out.println("Str[]"+str);
				 if(questionIds.length()==1)
				 {
					 questionId[0]=questionIds;
				 }
			 
			//String answers="DELETE FROM `cp_question_answers` WHERE  `question_id`";
			//String choices="DELETE FROM `cp_question_choices` WHERE  `question_id`";
			//String questions="DELETE FROM `cp_questions` WHERE  `question_id";
			String question_masters="DELETE FROM `cp_question_masters` WHERE  `question_id`";
			if(questionId.length==1)
			{
				//answers=answers+"="+questionId[0];
			//	choices=choices+"="+questionId[0];
				//questions=questions+"="+questionId[0];
				question_masters=question_masters+"="+questionId[0];
			}
			else
			{
				//answers=answers+" in ("+questionIds+")";			 
				//choices=choices+" in ("+questionIds+")";			 
				//questions=questions+" in ("+questionIds+")";			 
				question_masters=question_masters+" in ("+questionIds+")";
			}
			//statement.addBatch(answers);
			//statement.addBatch(choices);
			//statement.addBatch(questions);
			statement.addBatch(question_masters);
			 
			int p[]=statement.executeBatch();
			//System.out.println("lenght of P : "+p.length);
			if(p.length==1){
				result=true;
			}
		} catch (ConnectionException e) {
			throw new ConnectionException(e);	
			 
		} catch (SQLException e) {			 
			e.printStackTrace();
		}
		finally
		{
			DBUtil.closeConnection(statement, connection);
		}
		return result;
	}

}
