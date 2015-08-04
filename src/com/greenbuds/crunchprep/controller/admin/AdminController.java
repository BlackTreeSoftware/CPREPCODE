package com.greenbuds.crunchprep.controller.admin;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.adminbo.AdminBO;
import com.greenbuds.crunchprep.dao.admin.IAdminDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;




public class AdminController implements IAdminController {
	@Autowired
	private IAdminDAO adminDAO;
	public List<AdminBO> getViewLogs() throws CPException{
		return adminDAO.getViewLogs();
	}

	

	public List<AdminBO> ipAddresses() throws CPException{
		return adminDAO.ipAddresses();
	}
	public List<AdminBO> adminMentorStudents()throws CPException{
		return adminDAO.adminMentorStudents();
	}
	
	
	public List<AdminBO> noofQuestions_per_Day(AdminBO adminBO) throws DBException, SQLException{
		
		
		return adminDAO.noofQuestions_per_Day(adminBO);
		
		
	}
	
	public List<AdminBO> noofLessons_per_Day(AdminBO adminBO) throws DBException, SQLException{
		
		return adminDAO.noofLessons_per_Day(adminBO);
		
	}
	
	
	public void noofUserGraph(JSONObject object) throws JSONException{
		
		    adminDAO.noofUserGraph(object);
		
	}
	
	public List<AdminBO> getMentorAccounts()throws CPException{
		return adminDAO.getMentorAccounts();
		
	}


}
