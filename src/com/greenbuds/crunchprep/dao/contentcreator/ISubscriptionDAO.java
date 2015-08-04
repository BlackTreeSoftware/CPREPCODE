package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.SubscriptionBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface ISubscriptionDAO {
	
	public List<SubscriptionBO> getSubscriptionMasterData() throws CPException;
	public SubscriptionBO getSubscriptionBO(String type,String name) throws CPException;

}
