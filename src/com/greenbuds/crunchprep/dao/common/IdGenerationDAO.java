package com.greenbuds.crunchprep.dao.common;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greenbuds.crunchprep.data.DatabaseConnection;





public class IdGenerationDAO implements IIDGenerationDAO {
private static IdGenerationDAO instance;
	
	
	public static IdGenerationDAO getInstance() {
		if(instance==null)
		{
			instance=new IdGenerationDAO();			
		}
		
		return instance;
	}


	public int createKey(String tableName, String fieldName)
	{
		
		ResultSet resultSet=null;
		java.sql.Connection connnection=null;
		java.sql.CallableStatement callableStatement=null;
		try
		{			
			connnection=DatabaseConnection.getCrunchPrepConnection();
			java.sql.Statement st=connnection.createStatement();
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
				connnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}


	
	

}
