/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.  
 * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.dao.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.greenbuds.crunchprep.bo.common.CategoryBO;
import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SectionBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.common.TypeBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ICommonsDAO.
 */
public interface ICommonsDAO {

	/**
	 * Gets the sections list.
	 *
	 * @return the sections list
	 * @throws CPException the CP exception
	 */
	public List<SectionBO> getSectionsList() throws CPException;
	
	/**
	 * Gets the categories list.
	 *
	 * @return the categories list
	 * @throws CPException the CP exception
	 */
	public List<CategoryBO> getCategoriesList() throws CPException;
	
	/**
	 * Gets the types list.
	 *
	 * @return the types list
	 * @throws CPException the CP exception
	 */
	public List<TypeBO> getTypesList() throws CPException;
	
	/**
	 * Gets the skills list.
	 *
	 * @return the skills list
	 * @throws CPException the CP exception
	 */
	public List<SkillBO> getSkillsList(String sectionid) throws CPException;
	
	/**
	 * Gets the difficulties list.
	 *
	 * @return the difficulties list
	 * @throws CPException the CP exception
	 */
	public List<DifficultyBO> getDifficultiesList() throws CPException;
	
	
	public ArrayList<TestsBO> getTestsList() throws ConnectionException, DBException;
	
	/**
	 * Gets the sections map.
	 *
	 * @return the sections map
	 * @throws CPException the CP exception
	 */
	public Map<Integer, String> getSectionsMap() throws CPException;
	
	/**
	 * Gets the categories map.
	 *
	 * @return the categories map
	 * @throws CPException the CP exception
	 */
	public Map<Integer, String> getCategoriesMap() throws CPException;
	
	/**
	 * Gets the types map.
	 *
	 * @return the types map
	 * @throws CPException the CP exception
	 */
	public Map<Integer, String> getTypesMap() throws CPException;
	
	/**
	 * Gets the skills map.
	 *
	 * @return the skills map
	 * @throws CPException the CP exception
	 */
	public Map<Integer, String> getSkillsMap() throws CPException;
	
	/**
	 * Gets the difficulties map.
	 *
	 * @return the difficulties map
	 * @throws CPException the CP exception
	 */
	public Map<Integer, String> getDifficultiesMap() throws CPException;
	
	/**
	 * creates key.
	 *
	 * @return the key
	 * @throws CPException the CP exception
	 */
	public int createKey(String tableName, String fieldName) throws DBException;

	
	
	/**
	 * Creates the test number.
	 *
	 * @param tableName the table name
	 * @param fieldName the field name
	 * @return the int
	 * @throws DBException the DB exception
	 */
	public int createTestNumber(String tableName, String fieldName,int userid,int testid) throws DBException;
	public List<SkillBO> getSectionList() throws CPException;

}
