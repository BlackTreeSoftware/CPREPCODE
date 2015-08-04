package com.greenbuds.crunchprep.controller.contentcreator;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO;
import com.greenbuds.crunchprep.dao.contentcreator.DataInterpretionDAO;
import com.greenbuds.crunchprep.dao.contentcreator.IDataInterpretionDAO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Class DataInterpretionController.
 */
public class DataInterpretionController implements IDataInterpretionController {
	public static DataInterpretionController dataInterpretionController;
	private DataInterpretionController(){
		
	
	}
	public static synchronized DataInterpretionController getInstance(){
		
		 if(dataInterpretionController==null) dataInterpretionController = new DataInterpretionController();
		 return dataInterpretionController;
		
	}
	
	/** The data interpretion dao. */
	private DataInterpretionDAO dataInterpretionDAO;
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDataInterpretionController#addDataInterpretion(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public String addDataInterpretion(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException{
	
		dataInterpretionDAO = DataInterpretionDAO.getInstance();
		return dataInterpretionDAO.addDataInterpretion(dataInterpretionBO);
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDataInterpretionController#getDataInterpretionList(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public List<DataInterpretionBO>getDataInterpretionList(DataInterpretionBO dataInterpretionBO) throws ConnectionException, DBException{
		
		
		dataInterpretionDAO = DataInterpretionDAO.getInstance();
		return dataInterpretionDAO.getDataInterpretionList(dataInterpretionBO);
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDataInterpretionController#getDeleteDataInterpretation(java.lang.String[])
	 */
	public String getDeleteDataInterpretation(String[] dataIds) throws ConnectionException, DBException{
		
		dataInterpretionDAO = DataInterpretionDAO.getInstance();
		return dataInterpretionDAO.getDeleteDataInterpretation(dataIds);
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDataInterpretionController#updateDataInterpretion(com.greenbuds.crunchprep.bo.contentcreator.DataInterpretionBO)
	 */
	public String updateDataInterpretion(DataInterpretionBO dataInterpretionBO)throws DBException, SQLException{
		
		dataInterpretionDAO = DataInterpretionDAO.getInstance();
		return dataInterpretionDAO.updateDataInterpretion(dataInterpretionBO);
		
		
	}
	

}
