/*
 * @(#)SkillsController.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */
package com.greenbuds.crunchprep.controller.contentcreator;

import java.sql.SQLException;

import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.dao.contentcreator.ISkillsDAO;
import com.greenbuds.crunchprep.dao.contentcreator.SkillsDAO;
import com.greenbuds.crunchprep.exception.CPException;

/**
 * @author kkatikala
 * @version 1.0
 * Controller class sends data to the SkillDAO class and returns return types to the Skillsaction class
 *
 */

public class SkillsController implements ISkillsController {

	/**
	 * SkillsController Object
	 */
	private static SkillsController controller;
	/**
	 * Object for ISkillsDAO interface to access methods from SkillDAO implemented Class
	 */
	private static ISkillsDAO skillsDAO;

	 /**
	 * Default Constructor
	 */
	public SkillsController() {
		// TODO Auto-generated constructor stub
		 super();
	}

	/**
	 * @return instance of SkillsController
	 */
	
	public static SkillsController getInstance() {
		if (controller == null)
			controller = new SkillsController();
		return controller;
	}

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.ISkillsController#saveSkills()
	 * @return boolean value 
	 */
	
	@Override
	public String saveSkills(SkillBO skillbo) throws CPException {
		// TODO Auto-generated method stub
		String result=null;
		skillsDAO =SkillsDAO.getInstance();
		result=skillsDAO.saveSkills(skillbo);
		
		System.out.println("In Controllerrr"+result);
		return result;
		
	}

	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.ISkillsController#updateSkills(com.greenbuds.crunchprep.bo.common.SkillBO)
	 * @return boolean value
	 */
	@Override
	public String updateSkills(SkillBO skillbo) throws CPException {
		// TODO Auto-generated method stub
		String result=null;
		skillsDAO =SkillsDAO.getInstance();
		result=skillsDAO.updateSkills(skillbo);
			
		return result;
	}
	
	

	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.ISkillsController#deleteSkills(com.greenbuds.crunchprep.bo.common.SkillBO)
	 * @return String
	 */
	@Override
	public String deleteSkills(SkillBO skillbo) throws CPException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("Delete COntroller");
		skillsDAO =SkillsDAO.getInstance();
		String result=skillsDAO.deleteSkills(skillbo);	
			
			return result;
		
	}
	
	
}
