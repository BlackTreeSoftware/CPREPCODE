package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.AddReferralQuestionBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;

public class AddReferralQuestionDAO implements IAddReferralQuestionDAO{
	
public List<AddReferralQuestionBO> getConditions()throws CPException{
		
		List<AddReferralQuestionBO> conditionList=new ArrayList<AddReferralQuestionBO>();
		AddReferralQuestionBO addReferralQuestionBO;
		Connection connection=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st = connection.createStatement();
            String query;
            ResultSet rs;
            query = "select * from cp_add_questions_action";
           
            //System.out.println(query);
            rs = st.executeQuery(query);
            while(rs.next()) {
            	addReferralQuestionBO=new AddReferralQuestionBO();
            	
            	addReferralQuestionBO.setQuestion_no(rs.getInt("condition_no"));
            	addReferralQuestionBO.setAction_type(rs.getInt("action_type"));
            	addReferralQuestionBO.setQuestion_math(rs.getInt("question_math"));
            	addReferralQuestionBO.setQuestion_verbal(rs.getInt("question_verbal"));
            	
        	   conditionList.add(addReferralQuestionBO);
        	   
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
		
		return conditionList;
		
	}


public String saveDifficultie(AddReferralQuestionBO bo)throws CPException{
	boolean flag=false;
	String result="";
	
	Connection connection=null;
	try{
		connection=DatabaseConnection.getCrunchPrepConnection();
		String query;
        
        query = "insert into cp_add_questions_action(action_type,question_math,question_verbal) values(?,?,?)";
		java.sql.PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, bo.getAction_type());
		pst.setInt(2, bo.getQuestion_math());
		pst.setInt(3, bo.getQuestion_verbal());
        
       
        //System.out.println(query);
        int i = pst.executeUpdate();
        if(i==1) {
    	  result="success";
       }
	}
	catch(Exception exception){
		//exception.printStackTrace();
		if(exception.getMessage().contains("Duplicate entry '"+bo.getAction_type()+"'")){
			result="exits action";
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
public String updateDifficultie(AddReferralQuestionBO bo)throws CPException{
boolean flag=false;
String result="";
Connection connection=null;
try{
	connection=DatabaseConnection.getCrunchPrepConnection();
	String query;
    
    query = "update cp_add_questions_action set action_type=?,question_math=?,question_verbal=? where condition_no=?";
	java.sql.PreparedStatement pst = connection.prepareStatement(query);
	pst.setInt(1, bo.getAction_type());
	pst.setInt(2, bo.getQuestion_math());
	pst.setInt(3, bo.getQuestion_verbal());
	pst.setInt(4, bo.getQuestion_no());
    
   
    //System.out.println(query);
    int i = pst.executeUpdate();
    if(i==1) {
    	result="success";
   }
}
catch(Exception exception){
	//exception.printStackTrace();
	if(exception.getMessage().contains("Duplicate entry '"+bo.getAction_type()+"'")){
		result="exits action";
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



public String deleteDifficultie(int[]  action_id)throws CPException{
	boolean flag=false;
	String result="";
	Connection connection=null;
	try{
		
		connection=DatabaseConnection.getCrunchPrepConnection();
		String query;
		//System.out.println("in DAO\t"+connection);
		java.sql.Statement st = connection.createStatement();
       
        for(int i=0;i<action_id.length;i++){
        	 query = "delete from cp_add_questions_action where condition_no="+action_id[i];
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
