package com.greenbuds.crunchprep.controller.user;

import org.json.JSONArray;

import com.greenbuds.crunchprep.exception.ConnectionException;

public interface IUserController {
	public JSONArray getTop3Skillsdata(int userId,int testId)  throws ConnectionException;
}
