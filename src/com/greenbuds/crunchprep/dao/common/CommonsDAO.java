/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.  
 * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.greenbuds.crunchprep.bo.common.CategoryBO;
import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SectionBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.common.TypeBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.util.DBUtil;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;


// TODO: Auto-generated Javadoc
/**
 * The Class CommonsDAO.
 */
/**
 * @author smittapalli
 *
 */
 
 
public class CommonsDAO implements ICommonsDAO{

	/** The commons dao. */
	private static CommonsDAO commonsDAO;
	private CommonsDAO(){
		
	}
	
	/**
	 * Gets the single instance of CommonsDAO.
	 *
	 * @return single instance of CommonsDAO
	 */
	public static synchronized CommonsDAO getInstance(){
		if(commonsDAO==null)
			commonsDAO=new CommonsDAO();
		return commonsDAO;
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getSectionsList()
	 */
	public List<SectionBO> getSectionsList() throws CPException {
		// TODO Auto-generated method stub
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SectionBO> sections=new ArrayList<SectionBO>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select s.section_id,s.section_name from cp_sections s");
				while(rs.next())
				{ 
					SectionBO sectionBO=new SectionBO();
					sectionBO.setSectionId(rs.getInt("section_id"));
					sectionBO.setSectionName(rs.getString("section_name"));
					sections.add(sectionBO); 
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
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return sections;
		 
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getCategoriesList()
	 */
	public List<CategoryBO> getCategoriesList() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getTypesList()
	 */
	public List<TypeBO> getTypesList() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getSkillsList()
	 */
	public List<SkillBO> getSkillsList(String sectionid) throws CPException {
		// TODO Auto-generated method stub
		 String section_id="";
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SkillBO> skills=new ArrayList<SkillBO>();
			try{
				if(sectionid.equals("-1"))
				{
					section_id="%";
				}
				else
				{
					section_id=sectionid;
				}
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select s.skill_id,s.skill_name,s.section_id from cp_skill_level s where section_id like '"+section_id+"'");  
				while(rs.next())
				{ 
					SkillBO skillBO=new SkillBO();
					skillBO.setSkill_id(rs.getInt("skill_id"));
					skillBO.setSkill_name(rs.getString("skill_name"));
					skillBO.setSectionId(rs.getInt("section_id"));
					skills.add(skillBO); 
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
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return skills;
	}
	
	
	
	public List<SkillBO> getSectionList() throws CPException {
		// TODO Auto-generated method stub
		 
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			List<SkillBO> sectionList=new ArrayList<SkillBO>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				rs=st.executeQuery("select section_id,section_name from cp_sections");
				while(rs.next())
				{ 
					SkillBO skillBO=new SkillBO();
					skillBO.setSectionId(rs.getInt("section_id"));
					skillBO.setSectionName(rs.getString("section_name"));
					sectionList.add(skillBO); 
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
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			return sectionList;
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getDifficultiesList()
	 */
	public List<DifficultyBO> getDifficultiesList() throws CPException {
		// TODO Auto-generated method stub
		List<DifficultyBO> difficultyList=new ArrayList<DifficultyBO>();
		DifficultyBO difficultyBO;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select * from cp_difficulty_level";
           
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
        	   difficultyBO=new DifficultyBO();
        	   difficultyBO.setDifficulty_id(rs.getInt("diff_id"));
        	   difficultyBO.setDifficulty_name(rs.getString("diff_name"));
        	   difficultyBO.setDifficulty_rating(rs.getInt("diff_description"));
        	   difficultyList.add(difficultyBO);
        	   
           }
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally{
			try {
				if(connection!=null) connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
		return difficultyList;
		
	}

	public ArrayList<TestsBO> getTestsList() throws ConnectionException, DBException{
		ArrayList<TestsBO> tests=new ArrayList<TestsBO>();
		TestsBO test=null;
		Statement st=null;
		ResultSet rs=null;
		Connection connection=null;
		connection=DatabaseConnection.getCrunchPrepConnection();
		
		try {
			st=connection.createStatement();
			//rs=st.executeQuery("SELECT * FROM `cp_testmaster`");
			rs=st.executeQuery("SELECT * FROM `cp_testmaster` where test_name not like 'Practice Test' and test_name not like 'Diagnostic Test'");
			while(rs.next())
			{
				if(rs.isFirst()) tests=new ArrayList<TestsBO>();
				test=new TestsBO();
				test.setTestId(rs.getInt("test_id"));
				test.setTestName(rs.getString("test_name"));
				test.setTestDiscription(rs.getString("test_description"));
				tests.add(test);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBException(Exceptions.SQL_EXCEPTION,e);
		}finally{
			if(connection!=null)
			{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBException(Exceptions.CONNECTION_CLOSING_EXCEPTION);
				}
			}
		}
		
		return tests;
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getSectionsMap()
	 */
	public Map<Integer, String> getSectionsMap() throws CPException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getCategoriesMap()
	 */
	public Map<Integer, String> getCategoriesMap() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getTypesMap()
	 */
	public Map<Integer, String> getTypesMap() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getSkillsMap()
	 */
	public Map<Integer, String> getSkillsMap() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.commons.ICommonsDAO#getDifficultiesMap()
	 */
	public Map<Integer, String> getDifficultiesMap() throws CPException {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public int createKey(String tableName, String fieldName) throws DBException {
		// TODO Auto-generated method stub
		ResultSet resultSet=null;
	    Connection connection=null;
		java.sql.CallableStatement callableStatement=null;
		try
		{			
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st=connection.createStatement();
			String sql="select IF(max("+fieldName+") IS NULL,1,max("+fieldName+")+1)" +" From "+tableName+" ";
			//System.out.println(sql);
			resultSet=st.executeQuery(sql);
			if(resultSet.next())
			{
				int newKey=resultSet.getInt(1);
				//System.out.println("ID : "+newKey);
				return newKey;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	} 
 
	
	public int createTestNumber(String tableName, String fieldName,int userid,int testid) throws DBException {
		// TODO Auto-generated method stub
		ResultSet resultSet=null;
	    Connection connection=null;
		java.sql.CallableStatement callableStatement=null;
		try
		{			
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st=connection.createStatement();
			//String sql="select IF(max("+fieldName+") IS NULL,1,max("+fieldName+")+1)" +" From "+tableName+" where user_id like '"+userid+"' and testmaster_id like '"+testid+"'";
			String sql="select IF(max("+fieldName+") IS NULL,1,max("+fieldName+")+1)" +" From "+tableName+""; 
			resultSet=st.executeQuery(sql);
			if(resultSet.next())
			{
				int newKey=resultSet.getInt(1);
				//System.out.println("ID : "+newKey);
				return newKey;
			} 
		}catch(Exception e)
		{
			e.printStackTrace();
		} 
		finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}  
}
