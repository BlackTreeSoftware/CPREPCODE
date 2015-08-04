/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.lessons;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.dao.lessons.ILessonsActionsDAO;
import com.greenbuds.crunchprep.dao.lessons.LessonsActionsDAO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Class LessonsActionsController.
 */
public class LessonsActionsController implements ILessonsActionsController{
	
	/** The lessons actions dao. */
	@Autowired
	private ILessonsActionsDAO lessonsActionsDAO;
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.lessons.ILessonsActionsController#lessonsActions(com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public boolean lessonsActions(LessonsmasterBO bo) throws CPException{
		
		return lessonsActionsDAO.lessonsActions(bo);	
	}
	
	/**
	 * Gets the mentor email.
	 *
	 * @param bo the bo
	 * @return the mentor email
	 */
	public boolean getMentorEmail(LessonsmasterBO bo){
		
		return lessonsActionsDAO.getMentorEmail(bo);
	}

}
