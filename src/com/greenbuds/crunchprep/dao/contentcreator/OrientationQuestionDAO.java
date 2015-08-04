/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Class OrientationQuestionDAO.
 */
public class OrientationQuestionDAO implements IOrientationQuestionDAO {
	
	/**
	 * Gets the difficulties.
	 *
	 * @return the difficulties
	 * @throws CPException the CP exception
	 */
	public List<OrientationsQuestionsBO> getDifficulties()throws CPException{
			
			List<OrientationsQuestionsBO> orientationList=new ArrayList<OrientationsQuestionsBO>();
			OrientationsQuestionsBO bo;
			Connection connection=null;
			try{
				connection=DatabaseConnection.getCrunchPrepConnection();
				java.sql.Statement st = connection.createStatement();
	            String query;
	            ResultSet rs;
	            query = "select * from cp_orientation_questions";
	           
	            //System.out.println(query);
	            rs = st.executeQuery(query);
	            while(rs.next()) {
	            	bo=new OrientationsQuestionsBO();
	            	bo.setOrientation_question_id(rs.getInt("orientation_question_id"));
	            	bo.setQuestion(rs.getString("orientation_question"));
	            	bo.setQuestion_type(rs.getString("orientation_type"));
	            	bo.setChoice1(rs.getString("orientation_choice1"));
	            	bo.setChoice2(rs.getString("orientation_choice2"));
	            	bo.setChoice3(rs.getString("orientation_choice3"));
	            	bo.setChoice4(rs.getString("orientation_choice4"));
	            	bo.setChoice5(rs.getString("orientation_choice5"));
	            	bo.setChoice6(rs.getString("orientation_choice6"));
	            	bo.setChoice7(rs.getString("orientation_choice7"));
	            	bo.setChoice8(rs.getString("orientation_choice8"));
	            	bo.setChoice9(rs.getString("orientation_choice9"));
	            	bo.setChoice10(rs.getString("orientation_choice10"));
	            	bo.setInstructions(rs.getString("orientation_instructions"));
	        	  
	        	   orientationList.add(bo);
	        	   
	           }
			}
			catch(Exception exception){
				
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
			
			return orientationList;
			
		}

	/**
	 * Delete orientation questions.
	 *
	 * @param orientation_id the orientation_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String deleteOrientationQuestions(String[]  orientation_id)throws CPException{
		boolean flag=false;
		String result="";
		Connection connection=null;
		try{
			
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query;
			//System.out.println("in DAO\t"+connection);
			java.sql.Statement st = connection.createStatement();
	       
	        for(int i=0;i<orientation_id.length;i++){
	        	 query = "delete from cp_orientation_questions where orientation_question_id="+orientation_id[i];
			//System.out.println(query);
	        	 st.addBatch(query);
			}
	        //System.out.println(query);
	        int[] i = st.executeBatch();
	        //System.out.println("Batch size is\t"+i.length);
	        if(i.length!=0) {
	        	result="success";
	       }
		}
		catch(Exception exception){
			//exception.printStackTrace();
			if(exception.getMessage().contains("a foreign key constraint fails")){
				result="exits";
			}
			
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
		
		return result;
		
	}
	
	/**
	 * Save orientation questions.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String saveOrientationQuestions(OrientationsQuestionsBO bo)throws CPException{
		boolean flag=false;
		String result="";
		//System.out.println("in dao");
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query;
            
            query = "insert into cp_orientation_questions(orientation_question,orientation_type,orientation_choice1,orientation_choice2,orientation_choice3,orientation_choice4,orientation_choice5,orientation_choice6,orientation_choice7,orientation_choice8,orientation_choice9,orientation_choice10,orientation_instructions) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, bo.getQuestion());
			pst.setString(2, bo.getQuestion_type());
			pst.setString(3, bo.getChoice1());
			pst.setString(4, bo.getChoice2());
			pst.setString(5, bo.getChoice3());
			pst.setString(6, bo.getChoice4());
			pst.setString(7, bo.getChoice5());
			pst.setString(8, bo.getChoice6());
			pst.setString(9, bo.getChoice7());
			pst.setString(10, bo.getChoice8());
			pst.setString(11, bo.getChoice9());
			pst.setString(12, bo.getChoice10());
			pst.setString(13, bo.getInstructions());
           
            //System.out.println(query);
            int i = pst.executeUpdate();
            if(i==1) {
        	  result="success";
           }
		}
		catch(Exception exception){
			if(exception.getMessage().contains("Data too long ")){
				result="too long data";
			}else if(exception.getMessage().contains("Duplicate entry")){
				result="exits";
			}
			//exception.printStackTrace();
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
		
		return result;
		
	}

	
	/**
	 * Update orientation questions.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String updateOrientationQuestions(OrientationsQuestionsBO bo)throws CPException{
		boolean flag=false;
		String result="";
		//System.out.println("in update dao");
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query;
            
            query = "update cp_orientation_questions set orientation_question=?,orientation_type=?,orientation_choice1=?,orientation_choice2=?,orientation_choice3=?,orientation_choice4=?,orientation_choice5=?,orientation_choice6=?,orientation_choice7=?,orientation_choice8=?,orientation_choice9=?,orientation_choice10=?,orientation_instructions=? where orientation_question_id=?";
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, bo.getQuestion());
			pst.setString(2, bo.getQuestion_type());
			pst.setString(3, bo.getChoice1());
			pst.setString(4, bo.getChoice2());
			pst.setString(5, bo.getChoice3());
			pst.setString(6, bo.getChoice4());
			pst.setString(7, bo.getChoice5());
			pst.setString(8, bo.getChoice6());
			pst.setString(9, bo.getChoice7());
			pst.setString(10, bo.getChoice8());
			pst.setString(11, bo.getChoice9());
			pst.setString(12, bo.getChoice10());
			pst.setString(13, bo.getInstructions());
			pst.setInt(14, bo.getOrientation_question_id());
           
            //System.out.println(query);
            int i = pst.executeUpdate();
            if(i==1) {
            	result="success";
           }
		}
		catch(Exception exception){
			if(exception.getMessage().contains("Data too long ")){
				result="too long data";
			}else if(exception.getMessage().contains("Duplicate entry")){
				result="exits";
			}
			
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
		//System.out.println("Flag Result is\t"+flag);
		
		return result;
		
	}




}
