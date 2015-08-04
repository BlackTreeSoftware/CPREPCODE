package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.AddReferralQuestionBO;
import com.greenbuds.crunchprep.dao.contentcreator.AddReferralQuestionDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class AddReferralQuestionController implements IAddReferralQuestionController {
	@Autowired
	private AddReferralQuestionDAO addReferralQuestionDAO;
	public List<AddReferralQuestionBO> getConditions()throws CPException{
		return addReferralQuestionDAO.getConditions();
	}
	
	public String deleteDifficultie(int[]  action_id)throws CPException{
		return addReferralQuestionDAO.deleteDifficultie(action_id);
	}
	
	public String saveDifficultie(AddReferralQuestionBO bo)throws CPException{
		return addReferralQuestionDAO.saveDifficultie(bo);
	}
	public String updateDifficultie(AddReferralQuestionBO bo)throws CPException{
		return addReferralQuestionDAO.updateDifficultie(bo);
	}
}
