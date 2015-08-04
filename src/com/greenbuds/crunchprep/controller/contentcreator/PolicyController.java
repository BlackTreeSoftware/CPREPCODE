package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.PolicyBO;
import com.greenbuds.crunchprep.dao.contentcreator.IPolicyDAO;
import com.greenbuds.crunchprep.dao.contentcreator.PolicyDAO;
import com.greenbuds.crunchprep.exception.CPException;



public class PolicyController implements IPolicyController{

	private IPolicyDAO policyDAO;
	
	@Override
	public boolean policySave(PolicyBO policyBO) throws CPException {
		// TODO Auto-generated method stub
		 policyDAO=new PolicyDAO();
		 return policyDAO.policySave(policyBO);
	}

	@Override
	public boolean policyUpdate(PolicyBO policyBO) throws CPException {
		// TODO Auto-generated method stub
		 policyDAO=new PolicyDAO();
		 return policyDAO.policyUpdate(policyBO);
	}

	@Override
	public boolean policyDelete(PolicyBO policyBO) throws CPException {
		// TODO Auto-generated method stub
		 policyDAO=new PolicyDAO();
		 return policyDAO.policyDelete(policyBO);
	}

	@Override
	public List<PolicyBO> getTermsPolicies() throws CPException {
		// TODO Auto-generated method stub
		 policyDAO=new PolicyDAO();
		 return policyDAO.getTermsPolicies();
	}

}
