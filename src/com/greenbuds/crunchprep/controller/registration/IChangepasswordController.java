package com.greenbuds.crunchprep.controller.registration;

import com.greenbuds.crunchprep.bo.registration.ChangePasswordBo;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.VrplBO;
import com.greenbuds.crunchprep.exception.DBException;

public interface IChangepasswordController {

	public String changePassword(ChangePasswordBo cPasswordBo) throws DBException;
	public String usersettings(RegistrationBO regbBo) throws DBException;
	public String vrplMethod(VrplBO vrplBO);
	
}
