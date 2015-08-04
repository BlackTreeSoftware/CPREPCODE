package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;

public class ReadingComprehensionDAO  implements IReadingComprehensionDAO{
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(ReadingComprehensionDAO.class );
	/** The ReadingComprehensionDAO */
	private static ReadingComprehensionDAO readingComprehensionDao;
	private ReadingComprehensionDAO(){
		
	}
	/**
	 * Gets the single instance of ReadingComprehensionDAO.
	 *
	 * @return single instance of ReadingComprehensionDAO.
	 */
	public static synchronized ReadingComprehensionDAO getInstance(){
		if(readingComprehensionDao==null)
			readingComprehensionDao=new ReadingComprehensionDAO();
		return readingComprehensionDao;
	}
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	List<ReadingComprehensionBO> readingComprehensionBOs;
	

	/**
	 * @return the readingComprehensionBOs
	 */
	public List<ReadingComprehensionBO> getReadingComprehensionBOs() {
		return readingComprehensionBOs;
	}

	/**
	 * @param readingComprehensionBOs the readingComprehensionBOs to set
	 */
	public void setReadingComprehensionBOs(
			List<ReadingComprehensionBO> readingComprehensionBOs) {
		this.readingComprehensionBOs = readingComprehensionBOs;
	}

	@Override
	public String saveReadingComprehension(ReadingComprehensionBO readingComprehensionBO) throws CPException {
		// TODO Auto-generated method stub
		String result="error";
		try {
			String qry = "INSERT INTO `cp_reading_comprehension_passage` (`passage_title`, `passage_type`, `passage`, `difficulty`, `access_type`, `status`) VALUES (?,?,?,?,?,?);";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
			
			preparedStatement.setString(1, readingComprehensionBO.getPassage_title());
			preparedStatement.setString(2, readingComprehensionBO.getPassage_type());
			preparedStatement.setString(3, readingComprehensionBO.getPassage());
			preparedStatement.setInt(4, readingComprehensionBO.getDifficulty());
			preparedStatement.setString(5, readingComprehensionBO.getAccess_type());
			preparedStatement.setString(6, readingComprehensionBO.getStatus());
			
			//System.out.println("Prepare qry "+preparedStatement);
			
			int res = preparedStatement.executeUpdate();
			if(res>0)
			{
				result = "success";
				readingComprehensionBO.setResult("Data Saved Successfully!");
				
			}else{
				
				result = "error";
				readingComprehensionBO.setResult("Data Saving Failed!");
			}
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			readingComprehensionBO.setResult("Data Already Exist!");
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(preparedStatement, connection);
			
		}
		
		return result;
	}

	@Override
	public List<ReadingComprehensionBO> getReadingComprehension()
			throws CPException {
		
		ResultSet resultSet =null;
		try {
			
			String qry = "SELECT  r.`passage_id`,  r.`passage_title`,  r.`passage_type`,  r.`passage` , r.`difficulty`,  r.`access_type`,  r.`status`,  r.`comments`, d.diff_name FROM cp_reading_comprehension_passage r,cp_difficulty_level d WHERE r.difficulty = d.diff_id";
			//System.out.println(qry);
			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();
			
			 resultSet = statement.executeQuery(qry);
			
			readingComprehensionBOs = new ArrayList<ReadingComprehensionBO>();
			
			while (resultSet.next()) {
				ReadingComprehensionBO comprehensionBO = new ReadingComprehensionBO();
				comprehensionBO.setPassage_id(resultSet.getInt("passage_id"));
				comprehensionBO.setPassage_title(resultSet.getString("passage_title"));
				comprehensionBO.setPassage_type(resultSet.getString("passage_type"));
				comprehensionBO.setPassage(resultSet.getString("passage"));
				comprehensionBO.setDifficulty(resultSet.getInt("difficulty"));
				comprehensionBO.setAccess_type(resultSet.getString("access_type"));
				comprehensionBO.setStatus(resultSet.getString("status"));
				comprehensionBO.setComments(resultSet.getString("comments"));
				comprehensionBO.setDifficulty_name(resultSet.getString("diff_name"));
				
				readingComprehensionBOs.add(comprehensionBO);
				
				
				
				
			}
			
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
			DBUtil.closeConnection(statement, connection);
			
		}
		
		
		
		// TODO Auto-generated method stub
		return readingComprehensionBOs;
	}

	@Override
	public String editReadingComprehension(ReadingComprehensionBO readingComprehensionBO) throws CPException {
		// TODO Auto-generated method stub
		String result="error";
		
		try {
			String qry = "UPDATE `cp_reading_comprehension_passage` SET `passage_title`=?, `passage_type`=?, `passage`=?, `difficulty`=?, `access_type`=?, `status`=? WHERE  `passage_id`="+readingComprehensionBO.getPassage_id()+";";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
			
			preparedStatement.setString(1, readingComprehensionBO.getPassage_title());
			preparedStatement.setString(2, readingComprehensionBO.getPassage_type());
			preparedStatement.setString(3, readingComprehensionBO.getPassage());
			preparedStatement.setInt(4, readingComprehensionBO.getDifficulty());
			preparedStatement.setString(5, readingComprehensionBO.getAccess_type());
			preparedStatement.setString(6, readingComprehensionBO.getStatus());
			
			System.out.println("Prepare Stat : "+preparedStatement);
			
			int res = preparedStatement.executeUpdate();
			
			if(res>0)
			{
				result = "success";
				readingComprehensionBO.setResult("Data Updated Successfully!");
				
			}else{
				
				result = "error";
				readingComprehensionBO.setResult("Data Updating Failed!");
			}
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			readingComprehensionBO.setResult("Data can't Update!");
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(preparedStatement, connection);
			
		}
		
		return result;
	}

	@Override
	public String deleteReadingComprehension(String[] deleterecords)
			throws CPException {
		String result ="error";
		int res =1;
		
		try {
			String qry = "DELETE FROM `cp_reading_comprehension_passage` WHERE  `passage_id`=?;";		
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
				  
		    for(String s:deleterecords){
		    	preparedStatement.setInt(1, Integer.parseInt(s));
		    	//System.out.println("Delete Qry : "+preparedStatement);
		    	preparedStatement.executeUpdate();
		    	res++;
		    }
		    
		    if(res>deleterecords.length)
		    {
		    	//Systemout.println("Data Deleted Done");
		    	result = "success";
		    	
		    }
			
		} catch (SQLException e) {
			// TODO: handle exception
			result ="error";
			//Systemout.println("Save Errotr: "+result);
			
			e.printStackTrace();
			
			
		}catch (ConnectionException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		finally{
			
			DBUtil.closeConnection( preparedStatement, connection);
			
		}
		
		
		return result;
	}
	public boolean saveNewReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException
	{
		boolean result=false;
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    try{
	    	
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			statement=connection.createStatement();
			connection.setAutoCommit(false);			
			String question_masters="INSERT INTO `cp_question_masters` (`section_id`, `category_id`, `type_id`, `test_id`,`passage_id`, `question_title`, `question_directions`) " +
					                                         "  VALUES ("+Categories.READING_COMPREHENSION.getSection().getSectionId()+", "+Categories.READING_COMPREHENSION.getCategorieId()+"," +
					                                         		" "+QuestionTypeUtil.getQuestionTypeID(Categories.READING_COMPREHENSION, bo.getQuestionType())+", " +
					                                         				""+bo.getTestId()+","+bo.getPassageId()+" ,'"+bo.getQuestionTitle()+"', '"+bo.getDirections()+"')";
		   // System.out.println("Questin Masters : "+question_masters);
			statement.executeUpdate(question_masters,Statement.RETURN_GENERATED_KEYS);
			
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
			
		     String question="INSERT INTO `cp_questions` (`question_id`, `skill_id1`, `skill_id2`, `skill_id3`, `diff_id`, `question`, `solution_text`, `solution_video`, `access_type`, `referral`, `status`) " +
		  		"VALUES ("+bo.getQuestionId()+","+skills1[0]+", "+skills1[1]+","+skills1[2]+",(select pa.difficulty from cp_reading_comprehension_passage pa where pa.passage_id like '"+bo.getPassageId()+"'), '"+bo.getQuestion()+"', '"+bo.getSolutionText()+"', '"+bo.getSolutionVideo()+"', '"+bo.getAccessType()+"', '"+bo.getReferral()+"', '"+bo.getStatus()+"')";
		     //Systemout.println("Questin Query : "+question);
		     statement.addBatch(question);
		     
		     int r=0;
		     String answers="";
		     if(bo.getQuestionType().equalsIgnoreCase(QuestionType.SINGLE_ANSWER.getQuestionType())||bo.getQuestionType().equalsIgnoreCase(QuestionType.SELECT_IN.getQuestionType()))
		      {
		    	 answers="INSERT INTO `cp_question_answers` (`question_id`,`answer_no`, `answer`) VALUES ("+bo.getQuestionId()+","+(++r)+",'"+bo.getAnswers()[0]+"')"; 
		    	 statement.addBatch(answers); 
		      }
		     else{ 
		     for(int i=1;i<bo.getAnswers().length;i++)
		     {
		    if(bo.getAnswers()[i]!=null && bo.getAnswers()[i].length()!=0){
		       answers="INSERT INTO `cp_question_answers` (`question_id`,`answer_no`, `answer`) VALUES ("+bo.getQuestionId()+","+(++r)+",'"+bo.getAnswers()[i]+"')";
		      }
		    	 else
		    	 {
		    		 answers="INSERT INTO `cp_question_answers` (`question_id`,`answer_no`, `answer`) VALUES ("+bo.getQuestionId()+","+(++r)+",'')";
		    		  
		    	 } 
			 statement.addBatch(answers); 
		     }
		   //Systemout.println("Questin answers"+answers);
		  
		     } 
		     
		     String choices="";
			 int k=0;
			  if(bo.getQuestionType().equalsIgnoreCase(QuestionType.SINGLE_ANSWER.getQuestionType()))
		      { 
			 for(int i=0;i<bo.getChoices().length;i++)
			 {
				 if(bo.getChoices()[i]!=null && bo.getChoices()[i].length()!=0){
					 choices="INSERT INTO `cp_question_choices` (`question_id`, `choice_no`, `choice`) VALUES " +
						 		"("+bo.getQuestionId()+","+(++k)+",'"+bo.getChoices()[i]+"')";
			     //Systemout.println("choices query : "+choices);
			      statement.addBatch(choices);
				 }
				 if(k>4)
		    	  {
		    		  break;
		    	  }
			 }
		      }
			    
			      else  if(bo.getQuestionType().equalsIgnoreCase(QuestionType.MULTIPLE_ANSWER.getQuestionType()))
			      {
			    	  for(int i=5;i<bo.getChoices().length;i++)
			 		 {
			 			 if(bo.getChoices()[i]!=null && bo.getChoices()[i].length()!=0){
			 				 choices="INSERT INTO `cp_question_choices` (`question_id`, `choice_no`, `choice`) VALUES " +
			 				 		"("+bo.getQuestionId()+","+(++k)+",'"+bo.getChoices()[i]+"')";   //Systemout.println("choices query : "+choices);
			 				 		 //Systemout.println("choices query : "+choices);
			 		      statement.addBatch(choices);
			 			 }
			 			 if(k>5)
				    	  {
				    		  break;
				    	  } 
			 		 }
			    	 
			      }
				    
		      
			
		 int p[]=statement.executeBatch();
		 connection.commit();
		 if(p.length==(1+k+r))
		 {
			 result=true;
			
		 }
	   //  System.out.println("plenght : "+p.length+"\t result "+(1+(k-1)+r));
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
			try {
				connection.rollback();
				} catch (SQLException e1) {					 
				e1.printStackTrace();
			} 
			
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
	public List<ReadingComprehensionQuestionBo> getAlltheReadingComprehensionQuestions() throws ConnectionException
	{
		List<ReadingComprehensionQuestionBo> list=new ArrayList<ReadingComprehensionQuestionBo>();
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    String sql="select qmas.question_id,qmas.section_id,qmas.category_id,qmas.type_id,qmas.test_id,qmas.passage_id, " +
	    		" (select CONCAT_WS('/',rc.passage_title,rc.passage_type) from cp_reading_comprehension_passage rc where rc.passage_id=qmas.passage_id) " +
	    		" as passageTitle, (select qt.question_type_name  from cp_question_type qt where qt.question_type_id=qmas.type_id) " +
	    		" questionTypeName, qmas.question_title,qmas.question_directions ,cq.skill_id1,cq.skill_id2,cq.skill_id3, (select ck.skill_name from cp_skill_level  ck where ck.skill_id=cq.skill_id1) as skill1," +
	    		"  (select ck.skill_name from cp_skill_level ck where ck.skill_id=cq.skill_id2)  as skill2, (select ck.skill_name from cp_skill_level ck " +
	    		" where ck.skill_id=cq.skill_id3) as skill3, cq.diff_id,(select cd.diff_name  from cp_difficulty_level cd " +
	    		" where cd.diff_id=cq.diff_id) as difficultyName, cq.question,cq.solution_text,cq.solution_video,cq.access_type,cq.referral,cq.`status`, GROUP_CONCAT(DISTINCT qa.answer  SEPARATOR ', ' ) answers," +
	    		" GROUP_CONCAT(DISTINCT qc.choice SEPARATOR ', ' ) as choices from cp_question_masters qmas left join cp_question_choices " +
	    		"  qc on  qc.question_id=qmas.question_id , cp_questions cq,cp_question_answers qa  where qmas.category_id in(3) and qmas.type_id in(14,4,5) and cq.question_id=qmas.question_id and " +
	    		" qa.question_id=qmas.question_id   group by cq.question_id" ;
	   //  System.out.println("Select query : "+sql);
	    try {
				connection=DatabaseConnection.getCrunchPrepConnection();
				statement=connection.createStatement();
				resultSet=statement.executeQuery(sql);
				while(resultSet.next())
				{
				
					if(resultSet.getString(1)!=null)
					{
						ReadingComprehensionQuestionBo bo=new ReadingComprehensionQuestionBo();
					
					bo.setQuestionId(resultSet.getInt(1));				 
					int questionType=resultSet.getInt(4);
					if(questionType==QuestionTypeUtil.getQuestionTypeID(Categories.CRITICAL_REASONING,QuestionType.SINGLE_ANSWER))
					{
						bo.setQuestionType(QuestionType.SINGLE_ANSWER.getQuestionType());
					}
					else if(questionType==QuestionTypeUtil.getQuestionTypeID(Categories.CRITICAL_REASONING,QuestionType.MULTIPLE_ANSWER))
					{
						bo.setQuestionType(QuestionType.MULTIPLE_ANSWER.getQuestionType());
					}
					else
					{
						bo.setQuestionType(QuestionType.SELECT_IN.getQuestionType());
					}
					bo.setTestId(resultSet.getInt(5));
					bo.setPassageId(resultSet.getInt(6));
					bo.setPassageName(resultSet.getString(7));
					bo.setQuestionTypeName(resultSet.getString(8));
					bo.setQuestionTitle(resultSet.getString(9)); 
					bo.setDirections(resultSet.getString(10));
					bo.setSkills(resultSet.getString(11)+(resultSet.getString(12)==null?"":","+resultSet.getString(12))+(resultSet.getString(13)==null?"":","+resultSet.getString(13)));
					bo.setSkillsName(resultSet.getString(14)+(resultSet.getString(15)==null?"":","+resultSet.getString(15))+(resultSet.getString(16)==null?"":","+resultSet.getString(16)));
					bo.setDifficultyId(resultSet.getInt(17));
					bo.setDifficultyName(resultSet.getString(18));
					bo.setQuestion(resultSet.getString(19));
					bo.setSolutionText(resultSet.getString(20));
					bo.setSolutionVideo(resultSet.getString(21));
					bo.setAccessType(resultSet.getString(22));
					bo.setReferral(resultSet.getString(23));
					bo.setStatus(resultSet.getString(24));
					bo.setAnswers(resultSet.getString(25).split(","));
					if(resultSet.getString(26)!=null)
					bo.setChoices(resultSet.getString(26).split(","));
					list.add(bo);
					}
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
	public List<ReadingComprehensionBO> getPassagesBasedonType(String TYPE) throws ConnectionException
	{
		List<ReadingComprehensionBO> list=new ArrayList<ReadingComprehensionBO>();
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    String sql="select cp.passage_id,cp.passage_title from cp_reading_comprehension_passage cp where cp.passage_type like";
	    if(TYPE.equalsIgnoreCase("ALL"))
	    	sql=sql+"'%'";
	    else
	      sql=sql+"'"+TYPE+"' ";
	     //System.out.println("Select query : "+sql);
	    try {
				connection=DatabaseConnection.getCrunchPrepConnection();
				statement=connection.createStatement();
				resultSet=statement.executeQuery(sql);
				while(resultSet.next())
				{
				ReadingComprehensionBO bo=new ReadingComprehensionBO();
				bo.setPassage_id(resultSet.getInt(1));
				bo.setPassage_title(resultSet.getString(2));
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
	public ReadingComprehensionQuestionBo gettingParticularRCquestion(int questionId) throws ConnectionException
	{
		 	 ReadingComprehensionQuestionBo  bo=new ReadingComprehensionQuestionBo();
			Statement statement=null;		 
		    Connection connection=null;
		    ResultSet resultSet=null;
		    String sql="select qmas.question_id,qmas.section_id,qmas.category_id,qmas.type_id,qmas.test_id,qmas.passage_id, " +
		    		" (select CONCAT_WS('/',rc.passage_title,rc.passage_type) from cp_reading_comprehension_passage rc where rc.passage_id=qmas.passage_id) " +
		    		" as passageTitle, (select qt.question_type_name  from cp_question_type qt where qt.question_type_id=qmas.type_id) " +
		    		" questionTypeName, qmas.question_title,qmas.question_directions ,cq.skill_id1,cq.skill_id2,cq.skill_id3, (select ck.skill_name from cp_skill_level  ck where ck.skill_id=cq.skill_id1) as skill1," +
		    		"  (select ck.skill_name from cp_skill_level ck where ck.skill_id=cq.skill_id2)  as skill2, (select ck.skill_name from cp_skill_level ck " +
		    		" where ck.skill_id=cq.skill_id3) as skill3, cq.diff_id,(select cd.diff_name  from cp_difficulty_level cd " +
		    		" where cd.diff_id=cq.diff_id) as difficultyName, cq.question,cq.solution_text,cq.solution_video,cq.access_type,cq.referral,cq.`status`, GROUP_CONCAT(DISTINCT qa.answer  SEPARATOR ', ' ) answers," +
		    		" GROUP_CONCAT(DISTINCT qc.choice SEPARATOR ', ' ) as choices from cp_question_masters qmas left join cp_question_choices " +
		    		"  qc on  qc.question_id=qmas.question_id , cp_questions cq,cp_question_answers qa  where qmas.category_id in(3) and qmas.type_id in(14,4,5) and cq.question_id=qmas.question_id and " +
		    		" qa.question_id=qmas.question_id    and qmas.question_id="+questionId+" group by cq.question_id" ;
		  
		  //Systemout.println("Select query : "+sql);
		    try {
					connection=DatabaseConnection.getCrunchPrepConnection();
					statement=connection.createStatement();
					resultSet=statement.executeQuery(sql);
					while(resultSet.next())
					{
					
						if(resultSet.getString(1)!=null)
						{
						 
						bo.setQuestionId(resultSet.getInt(1));				 
						int questionType=resultSet.getInt(4);
						if(questionType==QuestionTypeUtil.getQuestionTypeID(Categories.CRITICAL_REASONING,QuestionType.SINGLE_ANSWER))
						{
							bo.setQuestionType(QuestionType.SINGLE_ANSWER.getQuestionType());
						}
						else if(questionType==QuestionTypeUtil.getQuestionTypeID(Categories.CRITICAL_REASONING,QuestionType.MULTIPLE_ANSWER))
						{
							bo.setQuestionType(QuestionType.MULTIPLE_ANSWER.getQuestionType());
						}
						else
						{
							bo.setQuestionType(QuestionType.SELECT_IN.getQuestionType());
						}
						bo.setTestId(resultSet.getInt(5));
						bo.setPassageId(resultSet.getInt(6));
						String passage[]=resultSet.getString(7).split("/");//System.out.println(resultSet.getString(7)+"\t passageType");
						bo.setPassageName(passage[0]); 
						bo.setPassageType(passage[1].toString());//System.out.println(bo.getPassageType()+"\t passageType");
						bo.setQuestionTypeName(resultSet.getString(8).trim());
						bo.setQuestionTitle(resultSet.getString(9)); 
						bo.setDirections(resultSet.getString(10));
						bo.setSkills(resultSet.getString(11)+(resultSet.getString(12)==null?"":","+resultSet.getString(12))+(resultSet.getString(13)==null?"":","+resultSet.getString(13)));
						bo.setSkillsName(resultSet.getString(14)+(resultSet.getString(15)==null?"":","+resultSet.getString(15))+(resultSet.getString(16)==null?"":","+resultSet.getString(16)));
					    bo.setDifficultyId(resultSet.getInt(17));
						bo.setDifficultyName(resultSet.getString(18));
						bo.setQuestion(resultSet.getString(19));
						bo.setSolutionText(resultSet.getString(20));
						bo.setSolutionVideo(resultSet.getString(21));
						bo.setAccessType(resultSet.getString(22));
						bo.setReferral(resultSet.getString(23));
						bo.setStatus(resultSet.getString(24));
						bo.setAnswers(resultSet.getString(25).split(","));
						if(resultSet.getString(26)!=null)
						bo.setChoices(resultSet.getString(26).split(","));
						// System.out.println(bo.getSkills());
						}
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
	public boolean updateReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException
	{
		boolean result=false;
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    try{
	    	
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			statement=connection.createStatement();
			connection.setAutoCommit(false);			
			String question_masters="UPDATE `cp_question_masters` SET `test_id`="+bo.getTestId()+", `passage_id`="+bo.getPassageId()+"," +
					" `question_title`='"+bo.getQuestionTitle()+"', `question_directions`='"+bo.getDirections()+"' WHERE  `question_id`="+bo.getQuestionId();
		   //  System.out.println("Questin Masters : "+question_masters);
			 statement.addBatch(question_masters);
			 
			 
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
				
		     String question="UPDATE  `cp_questions` SET `skill_id1`="+ skills1[0] +", `skill_id2`="+ skills1[1] +", `skill_id3`="+ skills1[2] +", `diff_id`="+bo.getDifficultyId()+", `question`='"+bo.getQuestion()+"'," +
		     		" `solution_text`='"+bo.getSolutionText()+"', `solution_video`='"+bo.getSolutionVideo()+"', `access_type`='"+bo.getAccessType()+"', `referral`='"+bo.getReferral()+"' WHERE  `question_id`="+bo.getQuestionId();
		     statement.addBatch(question);
		    // System.out.println("Questin Query : "+question);
		     
		     
		     int r=0;
		     if(bo.getQuestionType().equalsIgnoreCase(QuestionType.SINGLE_ANSWER.getQuestionType())||bo.getQuestionType().equalsIgnoreCase(QuestionType.SELECT_IN.getQuestionType()))
		      {
		    	 for(int i=0;i<bo.getAnswers().length;i++)
			     {
			    	 if(bo.getAnswers()[i]!=null && bo.getAnswers()[i].length()!=0){
			     String answers="UPDATE  `cp_question_answers` SET `answer`='"+bo.getAnswers()[i]+"'  WHERE  answer_no="+(++r)+" and  `question_id`="+bo.getQuestionId();
			   //  System.out.println("Questin Query : "+answers+"\t lenght : "+bo.getAnswers()[i].length());
			     statement.addBatch(answers);
		    	
		      }
			    	  if(r>0)
			    	  {
			    		  break;
			    	  }
			     }
		      }
		    
		    
		     else if(bo.getQuestionType().equalsIgnoreCase(QuestionType.MULTIPLE_ANSWER.getQuestionType()))
		     {
		    	 
		    	 for(int i=0;i<bo.getAnswers().length;i++)
			     {
			    	 if(bo.getAnswers()[i]!=null && bo.getAnswers()[i].length()!=0){
			     String answers="UPDATE  `cp_question_answers` SET `answer`='"+bo.getAnswers()[i]+"'  WHERE  answer_no="+(++r)+" and  `question_id`="+bo.getQuestionId();
			     //System.out.println("Questin Query : "+answers+"\t lenght : "+bo.getAnswers()[i].length());
			     statement.addBatch(answers);
		    	
		      }
		    	 if(r>5)
		    	 {
		    		 break;
		    	 }
		     }
		    	 
		     }
		     
		     
		 String choices="";
		 int k=0;
		  if(bo.getQuestionType().equalsIgnoreCase(QuestionType.SINGLE_ANSWER.getQuestionType()))
	      { 
		 for(int i=0;i<bo.getChoices().length;i++)
		 {
			 if(bo.getChoices()[i]!=null && bo.getChoices()[i].length()!=0){
		     choices="UPDATE  `cp_question_choices` SET  `choice`='"+bo.getChoices()[i]+"' WHERE `choice_no`="+(++k)+" and `question_id`="+bo.getQuestionId();
		    // System.out.println("choices query : "+choices);
		      statement.addBatch(choices);
			 }
			 if(k>4)
	    	  {
	    		  break;
	    	  }
		 }
	      }
		    
		      else  if(bo.getQuestionType().equalsIgnoreCase(QuestionType.MULTIPLE_ANSWER.getQuestionType()))
		      {
		    	  for(int i=5;i<bo.getChoices().length;i++)
		 		 {
		 			 if(bo.getChoices()[i]!=null && bo.getChoices()[i].length()!=0){
		 		     choices="UPDATE  `cp_question_choices` SET  `choice`='"+bo.getChoices()[i]+"' WHERE `choice_no`="+(++k)+" and `question_id`="+bo.getQuestionId();
		 		     //System.out.println("choices query : "+choices);
		 		      statement.addBatch(choices);
		 			 }
		 			 if(k>5)
			    	  {
			    		  break;
			    	  } 
		 		 }
		    	 
		      }
			 
		 
   
				
		 int p[]=statement.executeBatch();
		 connection.commit();
		 if(p.length==(2+k+r))
		 {
			 result=true;
			
		 }
	   // System.out.println("plenght : "+p.length+"\t result "+(2+(k-1)+r)+"\t "+result);
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
			try {
				connection.rollback();
				} catch (SQLException e1) {					 
				e1.printStackTrace();
			} 
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
}
