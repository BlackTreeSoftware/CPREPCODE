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
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Class DifficultyDAO.
 */
public class DifficultyDAO implements IDifficultyDAO{
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.IDifficultyDAO#getDifficulties()
	 */
	public List<DifficultyBO> getDifficulties()throws CPException{
		
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
		
		return difficultyList;
		
	}
	
/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.dao.contentcreator.IDifficultyDAO#saveDifficultie(com.greenbuds.crunchprep.bo.common.DifficultyBO)
 */
public String saveDifficultie(DifficultyBO bo)throws CPException{
		boolean flag=false;
		String result="";
		
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query;
            
            query = "insert into cp_difficulty_level(diff_name,diff_description) values(?,?)";
			java.sql.PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, bo.getDifficulty_name());
			pst.setInt(2, bo.getDifficulty_rating());
            
           
            //System.out.println(query);
            int i = pst.executeUpdate();
            if(i==1) {
        	  result="success";
           }
		}
		catch(Exception exception){
			//exception.printStackTrace();
			if(exception.getMessage().contains("Duplicate entry '"+bo.getDifficulty_name()+"'")){
				result="exits difficulty";
			}else if(exception.getMessage().contains("Duplicate entry '"+bo.getDifficulty_rating()+"'")){
				result="exits rating";
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

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.dao.contentcreator.IDifficultyDAO#updateDifficultie(com.greenbuds.crunchprep.bo.common.DifficultyBO)
 */
public String updateDifficultie(DifficultyBO bo)throws CPException{
	boolean flag=false;
	String result="";
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		String query;
        
        query = "update cp_difficulty_level set diff_name=?,diff_description=? where diff_id=?";
		java.sql.PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, bo.getDifficulty_name());
		pst.setInt(2, bo.getDifficulty_rating());
		pst.setInt(3, bo.getDifficulty_id());
        
       
        //System.out.println(query);
        int i = pst.executeUpdate();
        if(i==1) {
        	result="success";
       }
	}
	catch(Exception exception){
		//exception.printStackTrace();
		if(exception.getMessage().contains("Duplicate entry '"+bo.getDifficulty_name()+"'")){
			result="exits difficulty";
		}else if(exception.getMessage().contains("Duplicate entry '"+bo.getDifficulty_rating()+"'")){
			result="exits rating";
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

/* (non-Javadoc)
 * @see com.greenbuds.crunchprep.dao.contentcreator.IDifficultyDAO#deleteDifficultie(java.lang.String[])
 */
public String deleteDifficultie(String[]  diff_id)throws CPException{
	boolean flag=false;
	String result="";
	Connection connection=null;
	try{
		
		connection=DatabaseConnection.getCrunchPrepConnection();
		String query;
		//System.out.println("in DAO\t"+connection);
		java.sql.Statement st = connection.createStatement();
       
        for(int i=0;i<diff_id.length;i++){
        	 query = "delete from cp_difficulty_level where diff_id="+diff_id[i];
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



}
