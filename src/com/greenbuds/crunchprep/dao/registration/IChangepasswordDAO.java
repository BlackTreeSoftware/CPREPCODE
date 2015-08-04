package com.greenbuds.crunchprep.dao.registration;

import com.greenbuds.crunchprep.bo.registration.ChangePasswordBo;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.VrplBO;
import com.greenbuds.crunchprep.exception.DBException;

public interface IChangepasswordDAO {
	
	public String changePassword(ChangePasswordBo changePasswordBo)throws DBException;
	public String usersettings(RegistrationBO regbo)throws DBException;
	public  String updateUserProfile(RegistrationBO registrationBO) ;
	public String vrplMethod(VrplBO vrplBO);
}
