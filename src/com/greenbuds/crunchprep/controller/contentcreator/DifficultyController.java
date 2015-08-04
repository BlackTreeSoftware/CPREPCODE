/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.dao.contentcreator.DifficultyDAO;
import com.greenbuds.crunchprep.dao.contentcreator.IDifficultyDAO;


// TODO: Auto-generated Javadoc
public class DifficultyController implements IDifficultyController{
	
	/** The difficulty dao. */
	@Autowired
	private IDifficultyDAO difficultyDAO;
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDifficultyController#getDifficulties()
	 */
	public List<DifficultyBO> getDifficulties()throws CPException{
		difficultyDAO = new DifficultyDAO();
		
		return difficultyDAO.getDifficulties();
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDifficultyController#saveDifficultie(com.greenbuds.crunchprep.bo.common.DifficultyBO)
	 */
	public String saveDifficultie(DifficultyBO bo) throws CPException{
		return difficultyDAO.saveDifficultie(bo);
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDifficultyController#updateDifficultie(com.greenbuds.crunchprep.bo.common.DifficultyBO)
	 */
	public String updateDifficultie(DifficultyBO bo) throws CPException{
		return difficultyDAO.updateDifficultie(bo);
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IDifficultyController#deleteDifficultie(java.lang.String[])
	 */
	public String deleteDifficultie(String[] diff_id) throws CPException{
		//System.out.println("in controller");
		return difficultyDAO.deleteDifficultie(diff_id);
	}
}
