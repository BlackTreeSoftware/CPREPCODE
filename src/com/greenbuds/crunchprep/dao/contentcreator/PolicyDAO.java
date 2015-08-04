package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.PolicyBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.greenbuds.crunchprep.util.DBUtil;

public class PolicyDAO implements IPolicyDAO{

	
	
	public boolean policySave(PolicyBO policyBO) throws CPException  {		 

		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="INSERT INTO `cp_terms_policies` (`policy_terms`, `policy_conditions`,status) VALUES ('"+policyBO.getPolicy_terms()+"','"+policyBO.getPolicy_conditions()+"','"+policyBO.getStatus()+"')";
			System.out.println("Save"+query);
			int i=st.executeUpdate(query);
			if(i==1)
			{
				flag=true;
			}
			 
		} catch (ConnectionException e) {
			throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
			 
		} catch (SQLException e) {
			 throw new  CPException(e);
		}  
		finally{			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}


	public boolean policyUpdate(PolicyBO policyBO) throws CPException {
		// TODO Auto-generated method stub	
		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="UPDATE `cp_terms_policies` SET `policy_terms`='"+policyBO.getPolicy_terms()+"'," +
					     " `policy_conditions`='"+policyBO.getPolicy_conditions()+"' , status='"+policyBO.getStatus()+"' " +
					     "  WHERE  `policy_id`="+policyBO.getPolicy_id()+"";
			int i=st.executeUpdate(query);
			if(i==1)
			{
				flag=true;
			}
			 
		} catch (ConnectionException e) {
			throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
			 
		} catch (SQLException e) {
			 throw new  CPException(e);
		}  
		finally{			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}


	public boolean policyDelete(PolicyBO policyBO) throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="delete from  `cp_terms_policies` WHERE  `policy_id`="+policyBO.getPolicy_id()+"";
			System.out.println("delete query"+query);
			int i=st.executeUpdate(query);
			if(i==1)
			{
				flag=true;
			}
			 
		} catch (ConnectionException e) {
			throw new ConnectionException(Exceptions.CONNECTION_REFUSED,e);
			 
		} catch (SQLException e) {
			 throw new  CPException(e);
		}  
		finally{			
			 try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public List<PolicyBO> getTermsPolicies() throws CPException {
		// TODO Auto-generated method stub
		List<PolicyBO> termspolicies=new ArrayList<PolicyBO>(); 
		ResultSet resultSet=null;
		Statement st=null;
		Connection connection=null;
		try
		{
			connection=DatabaseConnection.getCrunchPrepConnection();
			st=connection.createStatement();
			String query="select * from cp_terms_policies";
			System.out.println("terms"+query);
			resultSet=st.executeQuery(query);
			
			while(resultSet.next())
			{
				PolicyBO policyBO=new PolicyBO();
				policyBO.setPolicy_id(resultSet.getInt("policy_id"));
				policyBO.setPolicy_terms(resultSet.getString("policy_terms"));
				policyBO.setPolicy_conditions(resultSet.getString("policy_conditions"));
				policyBO.setStatus(resultSet.getString("status"));
				
				System.out.println(""+policyBO.getPolicy_terms());
				termspolicies.add(policyBO);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		finally{
			try {
				 connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	} 
		return termspolicies;		
	}
}
