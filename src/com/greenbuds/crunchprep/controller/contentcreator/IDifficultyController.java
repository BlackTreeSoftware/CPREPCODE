/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
public interface IDifficultyController {
	
	/**
	 * Gets the difficulties.
	 *
	 * @return the difficulties
	 * @throws CPException the CP exception
	 */
	public List<DifficultyBO> getDifficulties()throws CPException;
	
	/**
	 * Save difficultie.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String saveDifficultie(DifficultyBO bo) throws CPException;
	
	/**
	 * Update difficultie.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String updateDifficultie(DifficultyBO bo) throws CPException;
	
	/**
	 * Delete difficultie.
	 *
	 * @param diff_id the diff_id
	 * @return true, if successful
	 * @throws CPException the CP exception
	 */
	public String deleteDifficultie(String[] diff_id) throws CPException;
	

}
