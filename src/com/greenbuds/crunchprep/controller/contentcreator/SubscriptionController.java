package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.SubscriptionBO;
import com.greenbuds.crunchprep.dao.contentcreator.SubscriptionDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class SubscriptionController implements ISubscriptionController {

	@Autowired
	private SubscriptionDAO subscriptionDAO;
	@Override
	public List<SubscriptionBO> getSubscriptionMasterData() throws CPException {
		// TODO Auto-generated method stub
		return subscriptionDAO.getSubscriptionMasterData();
	}
	@Override
	public SubscriptionBO getSubscriptionBO(String type, String name)
			throws CPException {
		// TODO Auto-generated method stub
		return subscriptionDAO.getSubscriptionBO(type, name);
	}

}
