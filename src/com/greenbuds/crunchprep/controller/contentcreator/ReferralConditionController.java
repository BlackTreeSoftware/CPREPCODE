package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.dao.contentcreator.IReferralConditionsDAO;
import com.greenbuds.crunchprep.dao.contentcreator.ISkillsDAO;
import com.greenbuds.crunchprep.dao.contentcreator.ReferralConditionDAO;
import com.greenbuds.crunchprep.dao.contentcreator.ReferralMasterDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class ReferralConditionController implements IReferralConditionController {
	
	
	
	
	//ReferralConditionDAO referralConditionDAO;
	private static ReferralConditionController controller;
	private static IReferralConditionsDAO referralConditionDAO;

	 public ReferralConditionController() {
		// TODO Auto-generated constructor stub
		 super();
	}

	/**
	 * @return
	 */
	public static ReferralConditionController getInstance() {
		if (controller == null)
			controller = new ReferralConditionController();
		return controller;
	}

	@Override
	public List<ReferralConditionBO> getReferralConditions() throws CPException {
		// TODO Auto-generated method stub
		referralConditionDAO=ReferralConditionDAO.getInstance();
		List<ReferralConditionBO> conditionBOs = 	referralConditionDAO.getReferralConditions();
		return conditionBOs;
	}

	@Override
	public String saveReferralConditions(ReferralConditionBO referralConditionBO)
			 {
		// TODO Auto-generated method stub
		String result="";
		try {
			
			referralConditionDAO=ReferralConditionDAO.getInstance();
			result = 	referralConditionDAO.saveReferralConditions(referralConditionBO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	

	@Override
	public String editReferralConditions(ReferralConditionBO referralConditionBO)
			throws CPException {
		// TODO Auto-generated method stub
		referralConditionDAO=ReferralConditionDAO.getInstance();
		String result = referralConditionDAO.editReferralConditions(referralConditionBO);		
		return result;
	}

	@Override
	public String deleteReferralConditions(String[] deleterecords)
			throws CPException {
		// TODO Auto-generated method stub
		referralConditionDAO=ReferralConditionDAO.getInstance();
		String result = referralConditionDAO.deleteReferralConditions(deleterecords);		
		return result;
	}

}
