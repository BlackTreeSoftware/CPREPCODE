package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.dao.contentcreator.ReferralMasterDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class ReferralMasterController implements IReferralMasterController {
	
	ReferralMasterDAO referralMasterDAO;

	@Override
	public List<ReferralConditionBO> getReferralConditions() throws CPException {
		// TODO Auto-generated method stub
		referralMasterDAO=new ReferralMasterDAO();
		List<ReferralConditionBO> conditionBOs = 	referralMasterDAO.getReferralConditions();
		return conditionBOs;
	}

	@Override
	public String saveReferralMaster(ReferralMasterBO referralMasterBO)
			 {
		// TODO Auto-generated method stub
		String result="";
		try {
			
			referralMasterDAO = new  ReferralMasterDAO();
			result = 	referralMasterDAO.saveReferralMaster(referralMasterBO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ReferralMasterBO> getReferralMasters() throws CPException {
		// TODO Auto-generated method stub
		referralMasterDAO = new ReferralMasterDAO();
		List<ReferralMasterBO> referralMasterBOs = referralMasterDAO.getReferralMasters();		
		return referralMasterBOs;
	}

	@Override
	public String editReferralMasters(ReferralMasterBO referralMasterBO)
			throws CPException {
		// TODO Auto-generated method stub
		referralMasterDAO = new ReferralMasterDAO();
		String result = referralMasterDAO.editReferralMasters(referralMasterBO);		
		return result;
	}

	@Override
	public String deleteReferralMasters(String[] deleterecords)
			throws CPException {
		// TODO Auto-generated method stub
		referralMasterDAO = new ReferralMasterDAO();
		String result = referralMasterDAO.deleteReferralMasters(deleterecords);		
		return result;
	}

}
