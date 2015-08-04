package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.PolicyBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IPolicyDAO {
	
	public boolean policySave(PolicyBO policyBO) throws CPException;
	public boolean policyUpdate(PolicyBO policyBO) throws CPException;
	public boolean policyDelete(PolicyBO policyBO) throws CPException;
	public List<PolicyBO> getTermsPolicies() throws CPException ;
}
