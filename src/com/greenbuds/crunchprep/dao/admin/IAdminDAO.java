package com.greenbuds.crunchprep.dao.admin;


import java.util.List;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.adminbo.AdminBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;





public interface IAdminDAO {
	
	
	public List<AdminBO> noofQuestions_per_Day(AdminBO adminBO) throws DBException, SQLException;
	public List<AdminBO> noofLessons_per_Day(AdminBO adminBO) throws DBException, SQLException;
	public void noofUserGraph(JSONObject object) throws JSONException;
	
	public List<AdminBO> getViewLogs()throws CPException;
	public List<AdminBO> ipAddresses()throws CPException;
	public List<AdminBO> getMentorAccounts()throws CPException;
	public List<AdminBO> adminMentorStudents()throws CPException;
	
}
