package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;

/**
 * @author kkatikala
 *
 */

public class SkillsDAO implements ISkillsDAO{
	
	private static SkillsDAO skillsDAO;
	
	
	/**
	 * @return
	 */
	public static SkillsDAO getInstance(){
		if (skillsDAO == null)
			skillsDAO = new SkillsDAO();
		    return skillsDAO;
	}

	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.dao.contentcreator.ISkillsDAO#saveSkills()
	 * @return
	 */
	@Override
	public String saveSkills(SkillBO skillbo) throws CPException {
		// TODO Auto-generated method stub
		String result=null;
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			HashMap<Integer, String> sections=new HashMap<Integer, String>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
				
			/*	String checkQuery="Select * from cp_skill_level  where skill_name = '"+skillbo.getSkill_name()+"'";
				System.out.println(checkQuery);
				rs=st.executeQuery(checkQuery);
				if(rs.next())
				{
					result="EXISTS";
				}
				else{*/
				String insertQuery="insert into cp_skill_level  (skill_name,section_id) values ('"+skillbo.getSkill_name()+"',"+skillbo.getSectionId()+")";
				System.out.println(insertQuery);
				int status=st.executeUpdate(insertQuery);
				if(status>0)
					result="SUCCESS"; 
							else result="ERROR";
							
				//}
				
				
			
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				if(exp.getMessage().contains("Duplicate entry '"+skillbo.getSkill_name()+"' for key 'skill_name'"))
					result="EXISTS";
				else
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
			
		System.out.println("Result :"+result);
		
		
		return result;
	}


	@Override
	public String updateSkills(SkillBO skillbo) throws CPException {
		// TODO Auto-generated method stub
		String result= null;
		Statement st=null;
		ResultSet rs=null;
			Connection connection=null;
			HashMap<Integer, String> sections=new HashMap<Integer, String>();
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 		
			
				String updateQuery="update cp_skill_level  set skill_name='"+skillbo.getSkill_name()+"',section_id="+skillbo.getSectionId()+" where skill_id="+skillbo.getSkill_id()+"";
				int status=st.executeUpdate(updateQuery);
				if(status>0)
					if(status>0)
						result="SUCCESS"; 
								else result="ERROR";
			
				
			
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				if(exp.getMessage().contains("Duplicate entry '"+skillbo.getSkill_name()+"' for key 'skill_name'"))
					result="EXISTS";
				else
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
			
		System.out.println("Result :"+result);
		
		
		return result;
	}


	
	
	
	@Override
	public String deleteSkills(SkillBO skillbo) throws CPException, SQLException {
		// TODO Auto-generated method stub
		String result=null;
		Statement st=null;
		ResultSet rs=null;
		String skillname = null;
			Connection connection=null;
			
			try{
				connection=DatabaseConnection.getCrunchPrepConnection(); 
				st=connection.createStatement(); 
			
				String deleteQuery="delete from cp_skill_level  where skill_id="+skillbo.getSkill_id()+"";
				int status=st.executeUpdate(deleteQuery);
				if(status>0)
					result="Deleted";
				else result="Error";
				
			
			}
			catch(ConnectionException exp){ 
				throw new ConnectionException(exp); 
			}
			catch(Exception exp)
			{
				if(exp.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")){
					String query="select skill_name from cp_skill_level where skill_id="+skillbo.getSkill_id()+"";
					rs=st.executeQuery(query);
					if(rs.next()){
						skillname=rs.getString(1);
					}
					
					result="foreign@"+skillname;
				}
				else{
				throw new CommonExceptions(exp);
				}
			}
			finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CPException(e);
				}
			}
			
		System.out.println("Result :"+result);
		
		
		return result;
	}

}
