/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.registration.OrientationsQuestionsBO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IOrientationQuestionController.
 */
public interface IOrientationQuestionController {
	
	/**
	 * Gets the difficulties.
	 *
	 * @return the difficulties
	 * @throws CPException the CP exception
	 */
	public List<OrientationsQuestionsBO> getDifficulties()throws CPException;
	
	/**
	 * Delete orientation questions.
	 *
	 * @param orientation_id the orientation_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String deleteOrientationQuestions(String[]  orientation_id)throws CPException;
	
	/**
	 * Save orientation questions.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String saveOrientationQuestions(OrientationsQuestionsBO bo)throws CPException;
	
	/**
	 * Update orientation questions.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String updateOrientationQuestions(OrientationsQuestionsBO bo)throws CPException;

}
