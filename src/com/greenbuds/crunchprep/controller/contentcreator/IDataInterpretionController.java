package com.greenbuds.crunchprep.controller.contentcreator;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Interface IDataInterpretionController.
 */
public interface IDataInterpretionController {
	
	/**
	 * Adds the data interpretion.
	 *
	 * @param dataInterpretionBO the data interpretion bo
	 * @return the string
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public String addDataInterpretion(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException;
	
	/**
	 * Gets the data interpretion list.
	 *
	 * @param dataInterpretionBO the data interpretion bo
	 * @return the data interpretion list
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public List<DataInterpretionBO>getDataInterpretionList(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException;

	/**
	 * Gets the delete data interpretation.
	 *
	 * @param dataIds the data ids
	 * @return the delete data interpretation
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 */
	public String getDeleteDataInterpretation(String[] dataIds) throws ConnectionException, DBException;
	
	/**
	 * Update data interpretion.
	 *
	 * @param dataInterpretionBO the data interpretion bo
	 * @return the string
	 * @throws ConnectionException the connection exception
	 * @throws DBException the DB exception
	 * @throws SQLException 
	 */
	public String updateDataInterpretion(DataInterpretionBO dataInterpretionBO)throws DBException, SQLException;
}
