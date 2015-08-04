package com.greenbuds.crunchprep.dao.fulllengthTest;

 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.OverallPerformanceBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Section;

public interface IFulllengthDao {
	
	/**
	 * used to get the questions for the Full length test sections(verbal/Quant) 
	 * @param section is Enum Type of Quant/Verbal
	 *        object is the Json object which contains the difficulty range with the variable name as limit
	 * @return Arraylist of questions
	 * @throws ConnectionException,CommonException
	 * @throws JSONException 
	 * 
	 */
	
public List<QuestionsUploadBO> getQuestionsForFulllegthTest(Section section,JSONObject object,int userId) throws ConnectionException, CommonExceptions, JSONException;
public void fullLengthTestQuestionSaving(JSONArray questionsData) throws ConnectionException, SQLException, JSONException;

/* Overall Performance & Question Analysis Start */
public JSONObject overallPerformanceData(JSONObject json) throws ConnectionException, SQLException, JSONException;
public ArrayList<OverallPerformanceBO> performanceSummary(JSONObject json) throws ConnectionException, SQLException, JSONException;
/* Overall Performance & Question Analysis End */
public  boolean updateSectionStatusforNextSection(JSONObject json) throws ConnectionException, SQLException, JSONException;
public double  getDifficultyLevel(org.json.JSONObject object) throws ConnectionException, JSONException;

/*@Ram Kumar
 * Method to get Predicted Score
*/
public List<QuestionsUploadBO>  getPredictedScoreDetails(int userid,int testNo,int testid)throws CPException;
public List<ScoreBO>  getScaledScoreRanges()throws CPException;

}
