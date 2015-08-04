package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.AdminRegistrationBO;
import com.greenbuds.crunchprep.dao.contentcreator.AdminRegistrationDAO;
import com.greenbuds.crunchprep.dao.contentcreator.IAdminRegistrationDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class AdminRegistrationController implements IAdminRegistrationController{

	private IAdminRegistrationDAO adminRegistrationDAO;
	
	@Override
	public boolean adminRegistrationSave(AdminRegistrationBO adminRegistrationBO)throws CPException {
		// TODO Auto-generated method stub
		adminRegistrationDAO = new AdminRegistrationDAO();
		return adminRegistrationDAO.adminRegistrationSave(adminRegistrationBO);
	}

	@Override
	public boolean adminRegistrationUpdate(AdminRegistrationBO adminRegistrationBO) throws CPException {
		// TODO Auto-generated method stub
		adminRegistrationDAO = new AdminRegistrationDAO();
		return adminRegistrationDAO.adminRegistrationUpdate(adminRegistrationBO);
	}

	@Override
	public boolean adminRegistrationDelete(AdminRegistrationBO adminRegistrationBO) throws CPException {
		// TODO Auto-generated method stub
		adminRegistrationDAO = new AdminRegistrationDAO();
		return adminRegistrationDAO.adminRegistrationDelete(adminRegistrationBO);
	}

	@Override
	public List<AdminRegistrationBO> getAdminDetails() throws CPException {
		// TODO Auto-generated method stub
		adminRegistrationDAO = new AdminRegistrationDAO();
		return adminRegistrationDAO.getAdminDetails();
	}

	
}
