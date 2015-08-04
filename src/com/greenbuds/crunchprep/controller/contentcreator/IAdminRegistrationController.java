package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.AdminRegistrationBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IAdminRegistrationController {

	public boolean adminRegistrationSave(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public boolean adminRegistrationUpdate(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public boolean adminRegistrationDelete(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public List<AdminRegistrationBO> getAdminDetails() throws CPException ;
}
