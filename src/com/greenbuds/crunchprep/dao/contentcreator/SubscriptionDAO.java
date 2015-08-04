package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.SubscriptionBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.util.DBUtil;

public class SubscriptionDAO implements ISubscriptionDAO {

	@Override
	public List<SubscriptionBO> getSubscriptionMasterData()
			throws CPException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		List<SubscriptionBO> subscriptionBOs;
				
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query="SELECT  `subscription_id`,  `subscription_type`,  `subscription_plan`,  `amount`,  `days_count` " +
					" FROM `crunchprep`.`cp_subscription`";
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			subscriptionBOs=new ArrayList<SubscriptionBO>();
			while(resultSet.next()){
				SubscriptionBO subscriptionBO=new SubscriptionBO();
				subscriptionBO.setSubscription_id(resultSet.getInt("subscription_id"));
				subscriptionBO.setSubscription_type(resultSet.getString("subscription_type"));
				subscriptionBO.setSubscription_plan(resultSet.getString("subscription_plan"));
				subscriptionBO.setAmount(resultSet.getDouble("amount"));
				subscriptionBO.setDays_count(resultSet.getInt("days_count"));
				subscriptionBOs.add(subscriptionBO);
			}
		}
		catch(ConnectionException connectionException){
			throw new CPException(connectionException);
			
		}
		catch(Exception exception){
			throw new CPException(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				throw new CPException(exception);
			}
		}
		return subscriptionBOs;
	}

	@Override
	public SubscriptionBO getSubscriptionBO(String type, String name)
			throws CPException {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
	    SubscriptionBO subscriptionBO=null;
				
		try{
			connection=DatabaseConnection.getCrunchPrepConnection();
			String query="SELECT  `subscription_id`,  `subscription_type`,  `subscription_plan`,  `amount`,  `days_count` " +
					" FROM `crunchprep`.`cp_subscription` s1 where s1.`subscription_type` like ? and s1.`subscription_plan` like ?";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, name);
			System.out.println(preparedStatement.toString());
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				subscriptionBO=new SubscriptionBO();
				subscriptionBO.setSubscription_id(resultSet.getInt("subscription_id"));
				subscriptionBO.setSubscription_type(resultSet.getString("subscription_type"));
				subscriptionBO.setSubscription_plan(resultSet.getString("subscription_plan"));
				subscriptionBO.setAmount(resultSet.getDouble("amount"));
				subscriptionBO.setDays_count(resultSet.getInt("days_count"));
				
			}
		}
		catch(ConnectionException connectionException){
			throw new CPException(connectionException);
			
		}
		catch(Exception exception){
			throw new CPException(exception);
		}
		finally{
			try {
				connection.close();
			} catch (SQLException exception) {
				// TODO Auto-generated catch block
				throw new CPException(exception);
			}
		}
		return subscriptionBO;
	}

}
