package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.SubscriptionBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface ISubscriptionController {

	public List<SubscriptionBO> getSubscriptionMasterData() throws CPException;
	public SubscriptionBO getSubscriptionBO(String type,String name) throws CPException;
}
