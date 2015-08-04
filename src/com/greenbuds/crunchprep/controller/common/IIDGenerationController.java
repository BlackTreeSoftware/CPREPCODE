package com.greenbuds.crunchprep.controller.common;

public interface IIDGenerationController {

	public int createKey(String tableName, String fieldName) throws Exception;
}
