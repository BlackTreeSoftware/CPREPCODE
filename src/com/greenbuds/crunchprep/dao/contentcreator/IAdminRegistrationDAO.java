package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;


import com.greenbuds.crunchprep.bo.contentcreator.AdminRegistrationBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IAdminRegistrationDAO {

	public boolean adminRegistrationSave(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public boolean adminRegistrationUpdate(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public boolean adminRegistrationDelete(AdminRegistrationBO adminRegistrationBO) throws CPException;
	public List<AdminRegistrationBO> getAdminDetails() throws CPException ;
}
