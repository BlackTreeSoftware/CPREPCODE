package com.greenbuds.crunchprep.controller.common;

import com.greenbuds.crunchprep.dao.common.IdGenerationDAO;

public class IDGenerationController implements IIDGenerationController {

	
	private IdGenerationDAO idGenerationDAO;
	
	private static IDGenerationController instance;
	
	private IDGenerationController(){
		
	}
	
	public static synchronized IDGenerationController getInstance(){
		if(instance==null){
			instance=new IDGenerationController();
		}
		return instance;
	}
	
	@Override
	public int createKey(String tableName, String fieldName) throws Exception {
		// TODO Auto-generated method stub
		idGenerationDAO=IdGenerationDAO.getInstance();
		return idGenerationDAO.createKey(tableName, fieldName);
	}

}
