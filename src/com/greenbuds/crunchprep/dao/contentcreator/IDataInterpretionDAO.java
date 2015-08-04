package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

public interface IDataInterpretionDAO {
	
	public String addDataInterpretion(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException;
	public List<DataInterpretionBO>getDataInterpretionList(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException;
	public String getDeleteDataInterpretation(String[] dataIds) throws ConnectionException, DBException;
	public String updateDataInterpretion(DataInterpretionBO dataInterpretionBO)throws DBException, SQLException;
}