/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.lessons;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ILessonsActionsController.
 */
public interface ILessonsActionsController {
	
	/**
	 * Lessons actions.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public boolean lessonsActions(LessonsmasterBO bo) throws CPException;

}
