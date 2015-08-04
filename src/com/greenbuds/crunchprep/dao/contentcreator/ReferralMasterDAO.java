package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
 
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.util.DBUtil;
 
 

public class ReferralMasterDAO implements IReferralMasterDAO {
	
	//ReferralMasterBO referralMasterBO;
	ReferralConditionBO referralConditionBO;
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	

	@Override
	public List<ReferralConditionBO> getReferralConditions()  {	
		ResultSet resultSet =null;
		List<ReferralConditionBO>  conditionBOs = new ArrayList<ReferralConditionBO>();		
		try {
			
		
			String qry = "SELECT  cf.condition_id,cf.condition_title,cf.`condition`,cf.`status`,cf.comments  FROM cp_referral_condition cf ORDER BY cf.condition_id";
			
			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();
			 resultSet =  statement.executeQuery(qry);
			
			while (resultSet.next()) {
				
				ReferralConditionBO referralConditionBO = new ReferralConditionBO();
				referralConditionBO.setCondition_id(resultSet.getInt("condition_id"));
				referralConditionBO.setCondition_title(resultSet.getString("condition_title"));
				referralConditionBO.setCondition(resultSet.getString("condition"));
				referralConditionBO.setStatus(resultSet.getString("status"));
				referralConditionBO.setComments(resultSet.getString("comments"));
				
				conditionBOs.add(referralConditionBO);
				
				
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(statement, connection);
			
		}
		// TODO Auto-generated method stub
		return conditionBOs;
	}

	@Override
	public String saveReferralMaster(ReferralMasterBO referralMasterBO)
	{
		String result = "error";
		// TODO Auto-generated method stub
		try {
			
		
			String qry = "INSERT INTO `cp_referral_master` (`condition_id`, `referral_master_name`, `referral_master_quant`, `referral_master_verbal`, `referral_master_limit`,`status`) VALUES (?,?,?,?,?,?);";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
			
		
			
			preparedStatement.setInt(1, referralMasterBO.getCondition_id());
			preparedStatement.setString(2, referralMasterBO.getReferral_master_name());
			preparedStatement.setInt(3, referralMasterBO.getReferral_master_quant());
			preparedStatement.setInt(4, referralMasterBO.getReferral_master_verbal());
			preparedStatement.setInt(5, referralMasterBO.getReferral_master_limit());
			preparedStatement.setString(6, referralMasterBO.getStatus());
		
			System.out.println("Saving  : "+ preparedStatement);
			
			int res = preparedStatement.executeUpdate();
			
			if(res>0)
			{
				
				result = "success";
				referralMasterBO.setResult("Data Is Inserted Succesfully");
				System.out.println("Data Is Instrted Succesfully");
			}
			else{
				
				result ="error";
				referralMasterBO.setResult("Data Is Instrted Failed");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			result ="error";
			referralMasterBO.setResult("Data Is Alreday Exist!");
			
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

	@Override
	public List<ReferralMasterBO> getReferralMasters() throws CPException {
		// TODO Auto-generated method stub
		ResultSet resultSet =null;
		
		List<ReferralMasterBO> referralMasterBOs = new ArrayList<ReferralMasterBO>();
		try {
		connection = DatabaseConnection.getCrunchPrepConnection();		
		statement = connection.createStatement();
		String qry = "SELECT m.referral_master_id,m.condition_id,c.condition_title,m.referral_master_quant,m.referral_master_verbal,m.referral_master_limit,m.`status`,m.comments,c.`condition` FROM  cp_referral_master m,cp_referral_condition c WHERE c.condition_id = m.condition_id ORDER BY m.referral_master_id ASC ";
		System.out.println(qry);
		
		 resultSet = statement.executeQuery(qry);
		
		while (resultSet.next()) {
			ReferralMasterBO referralMasterBO = new ReferralMasterBO();
			referralMasterBO.setReferral_master_id(resultSet.getInt("referral_master_id"));
			referralMasterBO.setCondition_id(resultSet.getInt("condition_id"));
			referralMasterBO.setReferral_master_name(resultSet.getString("condition_title"));
			referralMasterBO.setReferral_master_quant(resultSet.getInt("referral_master_quant"));
			referralMasterBO.setReferral_master_verbal(resultSet.getInt("referral_master_verbal"));
			referralMasterBO.setReferral_master_limit(resultSet.getInt("referral_master_limit"));
			referralMasterBO.setStatus(resultSet.getString("status"));
			referralMasterBO.setComments(resultSet.getString("comments"));
			referralMasterBO.setCondition_title(resultSet.getString("condition"));
			
			System.out.println("STATUS : "+referralMasterBO.getStatus());
			referralMasterBOs.add(referralMasterBO);
		}
			
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			
			DBUtil.closeConnection( statement, connection);
		}
		
		
		return referralMasterBOs;
	}

	@Override
	public String editReferralMasters(ReferralMasterBO referralMasterBO)
			throws CPException {
		// TODO Auto-generated method stub
		
		String result = "error";
		
		try {
			String qry = "UPDATE `cp_referral_master`  SET  condition_id = ?, `referral_master_name`= ?, `referral_master_quant`= ? ,`referral_master_verbal`= ?, `referral_master_limit`= ?, `status`= ? WHERE  `referral_master_id`= "+referralMasterBO.getReferral_master_id()+";";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
			
			preparedStatement.setInt(1, referralMasterBO.getCondition_id());
			preparedStatement.setString(2, referralMasterBO.getReferral_master_name());
			preparedStatement.setInt(3, referralMasterBO.getReferral_master_quant());
			preparedStatement.setInt(4, referralMasterBO.getReferral_master_verbal());
			preparedStatement.setInt(5, referralMasterBO.getReferral_master_limit());
			preparedStatement.setString(6, referralMasterBO.getStatus());
			
			System.out.println("preparedStatement  :  "+preparedStatement);

			int res = preparedStatement.executeUpdate();
			
			if(res>0)
			{
				
				result = "success";
				referralMasterBO.setResult("Data Is Updated Succesfully");
				System.out.println("Data Is Updated Succesfully");
			}
			else{
				result ="error";
				referralMasterBO.setResult("Data Failed to  Update!");
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			result ="error";
			
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
	
	
	
	
	@Override
	public String deleteReferralMasters(String[] deleterecords )
			throws CPException {
		// TODO Auto-generated method stub
		
		String result ="error";
		int res =1;
		
		try {
			String qry = "DELETE FROM `cp_referral_master` WHERE  `referral_master_id`=?;";		
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);
				  
		    for(String s:deleterecords){
		    	preparedStatement.setInt(1, Integer.parseInt(s));
		    	System.out.println("Delete Qry : "+preparedStatement);
		    	preparedStatement.executeUpdate();
		    	res++;
		    }
		    
		    if(res>deleterecords.length)
		    {
		    	System.out.println("Data Deleted Done");
		    	result = "success";
		    	
		    }
			
		} catch (SQLException e) {
			// TODO: handle exception
			result ="error";
			System.out.println("Save Errotr: "+result);
			
			e.printStackTrace();
			
			
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

}
