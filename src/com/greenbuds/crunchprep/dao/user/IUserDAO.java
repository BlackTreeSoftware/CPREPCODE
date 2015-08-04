package com.greenbuds.crunchprep.dao.user;

import org.json.JSONArray;

import com.greenbuds.crunchprep.exception.ConnectionException;

public interface IUserDAO {
	public JSONArray getTop3Skillsdata(int userId,int testId) throws ConnectionException;

}
