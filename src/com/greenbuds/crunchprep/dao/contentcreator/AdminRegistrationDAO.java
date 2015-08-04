package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.AdminRegistrationBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Exceptions;

public class AdminRegistrationDAO  implements IAdminRegistrationDAO{

	@Override
	public boolean adminRegistrationSave(AdminRegistrationBO adminRegistrationBO)throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="INSERT INTO `cp_admin_registration` (`role_id`, `name`, `email`, `password`, `contact_no`,section_id,status) VALUES ('"+adminRegistrationBO.getRole_id()+"', '"+adminRegistrationBO.getAdmin_name()+"', '"+adminRegistrationBO.getEmail()+"', SHA1('"+adminRegistrationBO.getPassword()+"'), '"+adminRegistrationBO.getContact()+"','"+adminRegistrationBO.getSection_id()+"','"+adminRegistrationBO.getStatus()+"')";
			System.out.println(""+query);
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

	@Override
	public boolean adminRegistrationUpdate(AdminRegistrationBO adminRegistrationBO) throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="UPDATE `cp_admin_registration` SET `password`=SHA1('"+adminRegistrationBO.getPassword()+"'),contact_no='"+adminRegistrationBO.getContact()+"'," +
					     "section_id='"+adminRegistrationBO.getSection_id()+"',status='"+adminRegistrationBO.getStatus()+"' , name='"+adminRegistrationBO.getAdmin_name()+"' " +
					     ", email='"+adminRegistrationBO.getEmail()+"',role_id='"+adminRegistrationBO.getRole_id()+"'" +
					     " WHERE  `admin_id`='"+adminRegistrationBO.getAdmin_id()+"'";
			System.out.println("update query::"+query);
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

	@Override
	public boolean adminRegistrationDelete(AdminRegistrationBO adminRegistrationBO) throws CPException {
		// TODO Auto-generated method stub
		boolean flag=false;
		Connection connection=null;
        Statement st=null;
		ResultSet resultSet=null;
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
            st=connection.createStatement();
			String query="delete from cp_admin_registration where admin_id='"+adminRegistrationBO.getAdmin_id()+"'";
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

	@Override
	public List<AdminRegistrationBO> getAdminDetails() throws CPException {
		// TODO Auto-generated method stub
		List<AdminRegistrationBO> adminRegistrationList=new ArrayList<AdminRegistrationBO>(); 
		ResultSet resultSet=null;
		Statement st=null;
		Connection connection=null;
		try
		{
			connection=DatabaseConnection.getCrunchPrepConnection();
			st=connection.createStatement();
			String query="select distinct * from cp_roles rol,cp_admin_registration reg LEFT JOIN cp_sections sec on sec.section_id=reg.section_id where rol.role_id=reg.role_id";
			System.out.println("getdetails :::"+query);
			resultSet=st.executeQuery(query);
			while(resultSet.next())
			{
				AdminRegistrationBO adminRegistrationBO=new AdminRegistrationBO();
				adminRegistrationBO.setAdmin_id(resultSet.getInt("admin_id"));
				adminRegistrationBO.setAdmin_name(resultSet.getString("name"));
				adminRegistrationBO.setEmail(resultSet.getString("email"));
				adminRegistrationBO.setPassword(resultSet.getString("password"));
				adminRegistrationBO.setRole_id(resultSet.getInt("role_id"));
				adminRegistrationBO.setContact(resultSet.getString("contact_no"));
				adminRegistrationBO.setSection_id(resultSet.getInt("section_id"));
				adminRegistrationBO.setReg_date(resultSet.getString("reg_date"));
				adminRegistrationBO.setStatus(resultSet.getString("status"));
				adminRegistrationBO.setRole(resultSet.getString("role"));
				adminRegistrationBO.setSection_name(resultSet.getString("section_name"));
				adminRegistrationList.add(adminRegistrationBO);
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
		return adminRegistrationList;		
	}
	

}
