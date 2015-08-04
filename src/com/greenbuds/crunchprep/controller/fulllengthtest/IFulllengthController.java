package com.greenbuds.crunchprep.controller.fulllengthtest;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Section;

public interface IFulllengthController {
	public List<QuestionsUploadBO> getQuestionsForFulllegthTest(
			Section section, JSONObject object,int userId) throws ConnectionException,
			CommonExceptions, JSONException;
	public  boolean updateSectionStatusforNextSection(JSONObject json) throws ConnectionException, SQLException, JSONException;
	public double  getDifficultyLevel(org.json.JSONObject object) throws ConnectionException, JSONException;
	public List<QuestionsUploadBO>  getPredictedScoreDetails(int userid,int testNo,int testid)throws CPException;
	public List<ScoreBO>  getScaledScoreRanges()throws CPException;
}
