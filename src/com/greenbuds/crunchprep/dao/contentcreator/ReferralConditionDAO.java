package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.data.DatabaseConnection;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.util.DBUtil;

public class ReferralConditionDAO implements IReferralConditionsDAO {

	// ReferralMasterBO referralMasterBO;
	ReferralConditionBO referralConditionBO;
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;

	private static ReferralConditionDAO referralConditionDAO;

	/**
	 * @return
	 */
	public static ReferralConditionDAO getInstance() {
		if (referralConditionDAO == null)
			referralConditionDAO = new ReferralConditionDAO();
		return referralConditionDAO;
	}

	@Override
	public List<ReferralConditionBO> getReferralConditions() {
		ResultSet resultSet = null;
		List<ReferralConditionBO> conditionBOs = new ArrayList<ReferralConditionBO>();
		try {

			String qry = "SELECT  cf.condition_id,cf.condition_title,cf.`condition`,cf.`status`,cf.comments  FROM cp_referral_condition cf ORDER BY cf.condition_id";

			connection = DatabaseConnection.getCrunchPrepConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(qry);

			while (resultSet.next()) {

				ReferralConditionBO referralConditionBO = new ReferralConditionBO();
				referralConditionBO.setCondition_id(resultSet
						.getInt("condition_id"));
				referralConditionBO.setCondition_title(resultSet
						.getString("condition_title"));
				referralConditionBO.setCondition(resultSet
						.getString("condition"));
				referralConditionBO.setStatus(resultSet.getString("status"));
				referralConditionBO
						.setComments(resultSet.getString("comments"));

				conditionBOs.add(referralConditionBO);

			}

		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(statement, connection);

		}
		// TODO Auto-generated method stub
		return conditionBOs;
	}

	@Override
	public String saveReferralConditions(ReferralConditionBO referralConditionBO) {
		String result = "error";
		// TODO Auto-generated method stub
		try {

			String qry = "INSERT INTO `cp_referral_condition` (`condition_title`, `condition`, `status`) VALUES (?,?,?);";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);

			preparedStatement.setString(1,
					referralConditionBO.getCondition_title());
			preparedStatement.setString(2, referralConditionBO.getCondition());
			preparedStatement.setString(3, referralConditionBO.getStatus());

			int res = preparedStatement.executeUpdate();

			if (res > 0) {

				result = "Referral Condition has been added successfully!!!";
				System.out.println("Data Is Inserted Succesfully");
			}
			else{
				result="Oops!! Sorry.. There's an error occured.. Please insert again!!!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			if(e.getMessage().contains("Duplicate entry '"+referralConditionBO.getCondition_title()+"' for key 'condition_title'"))
				result="Oops!! Sorry.. Cannot enter duplicate titles for conditions";
			else if(e.getMessage().contains("Duplicate entry '"+referralConditionBO.getCondition()+"' for key 'condition'"))
				result="Oops!! Sorry.. Cannot enter duplicate conditions for titles";
			else			
			result="Oops!! Sorry.. There's an error occured.. Please insert again!!!";

			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		finally {

			DBUtil.closeConnection(preparedStatement, connection);

		}

		return result;
	}

	@Override
	public String editReferralConditions(ReferralConditionBO referralConditionBO)
			throws CPException {
		// TODO Auto-generated method stub

		String result = "update_error";

		try {
			String qry = "UPDATE `cp_referral_condition`  SET   `condition_title`= ?, `condition`= ? ,`status`= ? WHERE  `condition_id`= "
					+ referralConditionBO.getCondition_id() + ";";
			connection = DatabaseConnection.getCrunchPrepConnection();
			preparedStatement = connection.prepareStatement(qry);

			preparedStatement.setString(1,
					referralConditionBO.getCondition_title());
			preparedStatement.setString(2, referralConditionBO.getCondition());
			preparedStatement.setString(3, referralConditionBO.getStatus());

			System.out.println("preparedStatement  :  " + preparedStatement);

			int res = preparedStatement.executeUpdate();

			if (res > 0) {

				result = "Data has been updated successfully..";
				System.out.println("Data Is Updated Succesfully");
			}
			else
				result="Oops!! Sorry.. There's an error occured.. Please verify the data!!!";
		} catch (SQLException e) {
			// TODO: handle exception
			result="Oops!! Sorry.. There's an error occured.. Please verify the data!!!";

			e.printStackTrace();
		} catch (ConnectionException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		finally {

			DBUtil.closeConnection(preparedStatement, connection);

		}

		return result;
	}

	@Override
	public String deleteReferralConditions(String[] deleterecords)
			throws CPException {
		// TODO Auto-generated method stub
		List<String> foreign = new ArrayList<String>();
		String result = "delete_error";
		String referral_condition = "";
		int res = 1;
		Statement st = null;
		ResultSet rs = null;
		for (String s : deleterecords) {
			try {
				String qry = "DELETE FROM `cp_referral_condition` WHERE  `condition_id`=?;";
				connection = DatabaseConnection.getCrunchPrepConnection();
				preparedStatement = connection.prepareStatement(qry);
				st = connection.createStatement();
				preparedStatement.setInt(1, Integer.parseInt(s));
				System.out.println("Delete Qry : " + preparedStatement);
				preparedStatement.executeUpdate();
				res++;

			} catch (SQLException e) {
				// TODO: handle exception
				if (e.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
					String query = "select condition_title from cp_referral_condition where condition_id="
							+ s + "";
					try {
						rs = st.executeQuery(query);
						if (rs.next()) {
							referral_condition = rs.getString(1);
						}
						foreign.add(referral_condition);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				e.printStackTrace();
			} catch (ConnectionException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			finally {

				DBUtil.closeConnection(preparedStatement, connection);

			}
		}

		if (foreign.size() > 0) {
			result = "foreign@" + foreign;
		} else if (res > deleterecords.length) {
			System.out.println("Data Deleted Done");
			result = "delete_done";

		} else
			result = "delete_error";

		return result;
	}

}
