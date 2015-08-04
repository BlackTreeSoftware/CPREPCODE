package com.greenbuds.crunchprep.controller.registration;

import com.greenbuds.crunchprep.bo.registration.ChangePasswordBo;
import com.greenbuds.crunchprep.bo.registration.RegistrationBO;
import com.greenbuds.crunchprep.bo.registration.VrplBO;
import com.greenbuds.crunchprep.dao.registration.ChangePasswordDAO;
import com.greenbuds.crunchprep.dao.registration.IChangepasswordDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

public class ChangepasswordController implements IChangepasswordController{
	
	private IChangepasswordDAO ichgpasdao;
	private ChangePasswordDAO chgpassdao;
	
	public String changePassword(ChangePasswordBo changePasswordBo) throws DBException {
		System.out.println("in change password controller.............................");
		// TODO Auto-generated method stub
		String ms=null; 
		chgpassdao = new ChangePasswordDAO();
	    ms=chgpassdao.changePassword(changePasswordBo);
		return ms;
	}
	

	public String usersettings(RegistrationBO regbBo) throws DBException {
		// TODO Auto-generated method stub
		System.out.println("in user settings controller.............................");
		String us=null;
		chgpassdao = new ChangePasswordDAO();
		us=chgpassdao.usersettings(regbBo);
		return us;
	}
	
	

	public String updateUserDetails(RegistrationBO registrationBO) {
		System.out.println("in change password controller.............................");
		// TODO Auto-generated method stub
		String ms=null; 
		chgpassdao = new ChangePasswordDAO();
	    ms=chgpassdao.updateUserProfile(registrationBO);
		return ms;
	}


	public RegistrationBO getUserDetails(Integer userid) {
		
		
		chgpassdao = new ChangePasswordDAO();
	    return chgpassdao.getUserProfile(userid);
	
	}


	@Override
	public String vrplMethod(VrplBO vrplBO) {
		System.out.println("in user settings controller.............................");
		String us=null;
		chgpassdao = new ChangePasswordDAO();
		us=chgpassdao.vrplMethod(vrplBO);
		return us;
	}
	
	public VrplBO getQuestions()  throws CPException{
		chgpassdao = new ChangePasswordDAO();
		return chgpassdao.getQuestions();
	}
	
}
