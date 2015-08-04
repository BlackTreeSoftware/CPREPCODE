/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.  
 * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.common;

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
import com.greenbuds.crunchprep.dao.common.CommonsDAO;
import com.greenbuds.crunchprep.dao.common.ICommonsDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonsController.
 */
public class CommonsController implements ICommonsController {

	/** The commons controller. */
	private static CommonsController commonsController;
	
	/** The commons dao. */
	private ICommonsDAO commonsDAO;
	private CommonsController(){
		
	}
	
	/**
	 * Gets the single instance of CommonsController.
	 *
	 * @return single instance of CommonsController
	 */
	public static synchronized CommonsController getInstance(){
		if(commonsController==null)
			commonsController=new CommonsController();
		return commonsController;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getSectionsList()
	 */
	public List<SectionBO> getSectionsList() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getSectionsList();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getCategoriesList()
	 */
	public List<CategoryBO> getCategoriesList() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getCategoriesList();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getTypesList()
	 */
	public List<TypeBO> getTypesList() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getTypesList();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getSkillsList()
	 */
	public List<SkillBO> getSkillsList(String sectionid) throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getSkillsList(sectionid);
	}
	
	public List<SkillBO> getSectionList1() throws CPException {
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getSectionList();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getDifficultiesList()
	 */
	public List<DifficultyBO> getDifficultiesList() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getDifficultiesList();
	}
	/**
	 * @author rrajulapati
	 */
	public ArrayList<TestsBO> getTestsList() throws ConnectionException, DBException{
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getTestsList();
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getSectionsMap()
	 */
	public Map<Integer, String> getSectionsMap() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getSectionsMap();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getCategoriesMap()
	 */
	public Map<Integer, String> getCategoriesMap() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getCategoriesMap();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getTypesMap()
	 */
	public Map<Integer, String> getTypesMap() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getTypesMap();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getSkillsMap()
	 */
	public Map<Integer, String> getSkillsMap() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getSkillsMap();
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.commons.ICommonsController#getDifficultiesMap()
	 */
	public Map<Integer, String> getDifficultiesMap() throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.getDifficultiesMap();
	}

	@Override
	public int createKey(String tableName, String fieldName) throws CPException {
		// TODO Auto-generated method stub
		commonsDAO=CommonsDAO.getInstance();
		return commonsDAO.createKey(tableName, fieldName);
	}

	
}
