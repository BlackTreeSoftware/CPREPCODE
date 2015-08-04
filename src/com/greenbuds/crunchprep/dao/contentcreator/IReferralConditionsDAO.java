package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReferralConditionBO;
import com.greenbuds.crunchprep.exception.CPException;

/**
 * @author kkatikala
 *
 */
public interface IReferralConditionsDAO {
	
	/**
	 * @return
	 * @throws CPException
	 */
	public List<ReferralConditionBO> getReferralConditions() throws CPException;
	public String saveReferralConditions(ReferralConditionBO referralConditionBO) throws CPException;	
	public String editReferralConditions(ReferralConditionBO referralConditionBO) throws CPException;
	/**
	 * @param deleterecords
	 * @return
	 * @throws CPException
	 */
	public String deleteReferralConditions(String[] deleterecords ) throws CPException;

}
