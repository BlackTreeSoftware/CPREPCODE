package com.greenbuds.crunchprep.dao.common;



public interface IIDGenerationDAO {
	public int createKey(String tableName, String fieldName) throws Exception;
	

}
