package com.greenbuds.crunchprep.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.util.DBUtil;

public class UserDAO implements IUserDAO {
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(UserDAO.class );
	/** The UserDao */
	private static UserDAO userDAO;
	private UserDAO(){
		
	}
	/**
	 * Gets the single instance of ReadingComprehensionDAO.
	 *
	 * @return single instance of ReadingComprehensionDAO.
	 */
	public static synchronized UserDAO getInstance(){
		if(userDAO==null)
			userDAO=new UserDAO();
		return userDAO;
	}
	@Override
	public JSONArray getTop3Skillsdata(int userId,int testId) throws ConnectionException {		 
		 
		Statement statement=null;		 
	    Connection connection=null;
	    ResultSet resultSet=null;
	    JSONArray list=new JSONArray();
	    try{
	    	 
			connection=DatabaseConnection.getCrunchPrepConnection(); 
			statement=connection.createStatement();			 		
			String query="(SELECT" +
					"  catId,catName,countOftotalAnsQuestions, asCountofCorrectAns,FORMAT((asCountofCorrectAns/countOftotalAnsQuestions)*100,0) as precentage" +
					" FROM ( SELECT cat.category_id as catId,cat.category_name as catName, COUNT(results.question_id) countOftotalAnsQuestions," +
					" (select count(results1.question_id) from cp_test_results results1 where" +
					"  results1.userid like '"+userId+"'" +
					"  and results1.result like 'CORRECT' and results1.category_id=results.category_id) asCountofCorrectAns" +
					" FROM cp_test_results results,cp_question_categories cat" +
					" WHERE cat.category_id=results.category_id AND results.userid LIKE '"+userId+"' AND results.test_master_id LIKE '"+testId+"' GROUP BY results.category_id" +
					" ) AS sub order by precentage  desc limit 3)  union All  (SELECT  catId,catName,countOftotalAnsQuestions," +
					"  asCountofCorrectAns,FORMAT((asCountofCorrectAns/countOftotalAnsQuestions)*100,0) as precentage FROM  ( " +
					" SELECT cat.category_id as catId,cat.category_name as catName, COUNT(results.question_id) countOftotalAnsQuestions," +
					" (select count(results1.question_id)" +
					"  from cp_test_results results1 where  results1.userid like '"+userId+"'" +
					"  and results1.result like 'CORRECT' and results1.category_id=results.category_id) asCountofCorrectAns" +
					" FROM cp_test_results results,cp_question_categories cat WHERE cat.category_id=results.category_id AND " +
					"  results.userid LIKE '10069' AND results.test_master_id LIKE '"+testId+"' GROUP BY results.category_id " +
					"   ) AS sub order by precentage  asc limit 3) ";
			// System.out.println("TOP3 Skills qUERY : "+query);
			resultSet=statement.executeQuery(query);
			
			while(resultSet.next())
			{
				JSONArray array=new JSONArray();
				array.put(String.valueOf(resultSet.getInt("catId")));
				array.put(String.valueOf(resultSet.getString("catName")));
				array.put(String.valueOf(resultSet.getInt("countOftotalAnsQuestions")));
				array.put(String.valueOf(resultSet.getInt("asCountofCorrectAns")));
				array.put(String.valueOf(resultSet.getString("precentage")));
			    list.put(array);
				 
			}
			  
		     
		      }
			 
		 
    
		catch(ConnectionException exp){	
			 
			throw new ConnectionException(exp); 
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
	    finally
	    {
	    	 
	    	DBUtil.closeConnection(resultSet, statement, connection);
	    }
		return list;
	}
	 
}
