package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.dao.common.CommonsDAO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.util.DBUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemSolvingDAO.
 */
public class ProblemSolvingDAO implements IProblemSolvingDAO{
	
	/** The problem solving dao. */
	private static ProblemSolvingDAO problemSolvingDAO;
	
	/**
	 * Gets the single instance of ProblemSolvingDAO.
	 *
	 * @return single instance of ProblemSolvingDAO
	 */
	public static synchronized ProblemSolvingDAO getInstance(){
		if(problemSolvingDAO==null)
			problemSolvingDAO=new ProblemSolvingDAO();
		return problemSolvingDAO;
	}
 CommonsDAO commonsDAO;
	@Override
	public boolean saveProblemSolvingQuestion(
			QuestionsUploadBO questionsUploadBO) throws CPException {
		// TODO Auto-generated method stub
		boolean result=false;
		Statement st=null;
		ResultSet rs=null;   
		    	Connection connection=null; 
				try{
					commonsDAO=commonsDAO.getInstance();
					int key=commonsDAO.createKey("cp_question_masters", "question_id");
					connection=DatabaseConnection.getCrunchPrepConnection();  
					connection.close();
					questionsUploadBO.setQuestion_id(key);
					if(saveProblemSolvingQuestionMaster(questionsUploadBO))
					{
						System.out.println("Master saved");
					}
					else
					{
						System.out.println("Master not saved");
					}
					if(saveProblemSolvingQuestions(questionsUploadBO))
					{
						System.out.println("Question saved");
					}
					else
					{
						System.out.println("Question not saved");
					}
					if(saveQuestionLessons(questionsUploadBO))
					{
						System.out.println("Question Lesson saved");
					}
					else
					{
						System.out.println("Question Lesson not saved");
					}
					if(saveProblemSolvingChoices(questionsUploadBO))
					{
						System.out.println("Choices saved");
					}
					else
					{
						System.out.println("Choices not saved");
					}
					if(saveProblemSolvingAnswers(questionsUploadBO))
					{
						System.out.println("answers saved");
					}
					else
					{
						System.out.println("answers not saved");
					}
					result=true;
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
	
	public boolean saveProblemSolvingQuestionMaster(QuestionsUploadBO questionsUploadBO) throws CPException
	{
	 boolean result=false;
	 Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				String query="";
				 
				if(questionsUploadBO.getGraphId()==0)
				{ 
					query="INSERT INTO `cp_question_masters` (`question_id`,`section_id`, `category_id`, `type_id`, `test_id`, `question_title`, `question_directions`,`graph_id`) VALUES ('"+questionsUploadBO.getQuestion_id()+"',1, '"+questionsUploadBO.getCategory_id()+"', '"+questionsUploadBO.getQuestiontype_id()+"', '"+questionsUploadBO.getTest_id()+"', '"+questionsUploadBO.getQuestion_title()+"','"+questionsUploadBO.getQuestion_directions()+"',null)";
				}
				else
				{
					query="INSERT INTO `cp_question_masters` (`question_id`,`section_id`, `category_id`, `type_id`, `test_id`, `question_title`, `question_directions`,`graph_id`) VALUES ('"+questionsUploadBO.getQuestion_id()+"',1, '"+questionsUploadBO.getCategory_id()+"', '"+questionsUploadBO.getQuestiontype_id()+"', '"+questionsUploadBO.getTest_id()+"', '"+questionsUploadBO.getQuestion_title()+"','"+questionsUploadBO.getQuestion_directions()+"','"+questionsUploadBO.getGraphId()+"')";
				}
				int n=st.executeUpdate(query);
				if(n>0)
				{
					result=true;
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
	 return result;
	}
	public boolean saveProblemSolvingQuestions(QuestionsUploadBO questionsUploadBO) throws CPException
	{
	 boolean result=false;
	 Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				String query="";
				int skillsize=questionsUploadBO.getSkills().size();
				if(skillsize==1)
				{
					query="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question_no`, `question`, `solution_text`, `solution_video`,`access_type`,`referral`,`status`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getSkills().get(0)+"', null, null, '"+questionsUploadBO.getDifficulty_id()+"', '"+questionsUploadBO.getQuestion_no()+"', '"+questionsUploadBO.getQuestion()+"', '"+questionsUploadBO.getSolution_text()+"', '"+questionsUploadBO.getSolution_video()+"','"+questionsUploadBO.getAccess_type()+"','"+questionsUploadBO.getRefferal()+"','"+questionsUploadBO.getStatus()+"')";
				}
				if(skillsize==2)
				{
					query="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question_no`, `question`, `solution_text`, `solution_video`,`access_type`,`referral`,`status`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getSkills().get(0)+"', '"+questionsUploadBO.getSkills().get(1)+"', null,'"+questionsUploadBO.getDifficulty_id()+"','"+questionsUploadBO.getQuestion_no()+"', '"+questionsUploadBO.getQuestion()+"','"+questionsUploadBO.getSolution_text()+"', '"+questionsUploadBO.getSolution_video()+"','"+questionsUploadBO.getAccess_type()+"','"+questionsUploadBO.getRefferal()+"','"+questionsUploadBO.getStatus()+"')";
				}
				if(skillsize==3)
				{
					query="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question_no`, `question`, `solution_text`, `solution_video`,`access_type`,`referral`,`status`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getSkills().get(0)+"','"+questionsUploadBO.getSkills().get(1)+"', '"+questionsUploadBO.getSkills().get(2)+"','"+questionsUploadBO.getDifficulty_id()+"','"+questionsUploadBO.getQuestion_no()+"', '"+questionsUploadBO.getQuestion()+"', '"+questionsUploadBO.getSolution_text()+"', '"+questionsUploadBO.getSolution_video()+"','"+questionsUploadBO.getAccess_type()+"','"+questionsUploadBO.getRefferal()+"','"+questionsUploadBO.getStatus()+"')";
				}
				//String query="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question_no`, `question`, `solution_text`, `solution_video`) VALUES (28, 48, 10564, 10585, 10588, 2, 1, 'sdfsdfsdfsfsf', 'sdfsdfsdfsf', 'sdfsdfsdfsf')";
				int n=st.executeUpdate(query);
				if(n>0)
				{
					result=true;
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
	 return result;
	}
	public boolean saveQuestionLessons(QuestionsUploadBO questionsUploadBO) throws CPException
	{
	 boolean result=false;
	 Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				String query="";
				for(int i=0;i<questionsUploadBO.getLessons().size();i++)
				{
					 query="INSERT INTO `cp_question_lessons` ( `question_id`, `sublesson_id`, `lesson_no`, `question_no`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getLessons().get(i)+"', '"+(i+1)+"','"+questionsUploadBO.getQuestion_no()+"')";
					 st.addBatch(query);
					 if(i==20)
					 {
						 st.executeBatch();
					 }
				} 
				int n[]=st.executeBatch();
				if(n.length>0){
					result=true;
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
	 return result;
	}
	
	public boolean saveProblemSolvingChoices(QuestionsUploadBO questionsUploadBO)throws CPException
	{
		 boolean result=false;
		 Statement st=null;
			ResultSet rs=null;  
				Connection connection=null;  
				try{
					String query="";
					connection=DatabaseConnection.getCrunchPrepConnection(); 
					st=connection.createStatement(); 
					for(int i=0;i<questionsUploadBO.getChoices().size();i++)
					{
						query="INSERT INTO `cp_question_choices` (`question_id`,`question_no`,`choice_no`, `choice`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getQuestion_no()+"', '"+(i+1)+"', '"+questionsUploadBO.getChoices().get(i)+"')";
						st.addBatch(query);
						if(i==20)
						{
							st.executeBatch();
						}
					}
					int n[]=st.executeBatch();
					if(n.length>0)
					{
						result=true;
					}
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
	public boolean saveProblemSolvingAnswers(QuestionsUploadBO questionsUploadBO) throws CPException
	{
	 boolean result=false;
	 Statement st=null;
		ResultSet rs=null;  
			Connection connection=null;  
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				String query="";
				for(int i=0;i<questionsUploadBO.getAnswers().size();i++)
				{
					query="INSERT INTO `cp_question_answers` ( `question_id`, `question_no`, `answer_no`, `answer`) VALUES ('"+questionsUploadBO.getQuestion_id()+"', '"+questionsUploadBO.getQuestion_no()+"','"+(i+1)+"' ,'"+questionsUploadBO.getAnswers().get(i)+"')";
					System.out.println("Quuuuu:"+query);
					st.addBatch(query);
					if(i==20)
					{
						st.executeBatch();
					}
				}
				int n[]=st.executeBatch();
				if(n.length>0)
				{
					result=true;
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
	 return result;
	}

	@Override
	public List<QuestionsUploadBO> getProblemSolvingQuesions()
			throws CPException {
		// TODO Auto-generated method stub
		return null;
	}
}
