package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReferralMasterBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IReferralMasterDAO {
	public List<ReferralConditionBO> getReferralConditions() throws CPException;
	public String saveReferralMaster(ReferralMasterBO referralMasterBO) throws CPException;
	public List<ReferralMasterBO> getReferralMasters() throws CPException;
	public String editReferralMasters(ReferralMasterBO referralMasterBO) throws CPException;
	public String deleteReferralMasters(String[] deleterecords ) throws CPException;

}
