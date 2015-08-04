package com.greenbuds.crunchprep.controller.practicesession;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ResumeBo;
import com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultLessionBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsExcelBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

 
public interface IPracticeSessionController {
	
	/**
	 * @param practiceSessionQuestionsBO
	 * @return
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public PracticeSessionQuestionsBO getPerformanceBarValues(PracticeSessionQuestionsBO practiceSessionQuestionsBO) throws ConnectionException,DBException,SQLException;
	
	

	public ResultsBO resultPage(ResultsBO resultsBO);
	public ResultsBO updateReason(ResultsBO resultsBO);
 
	
	/**
	 * Submit practice session question.
	 *
	 * @param questionsUploadBO the questions upload bo
	 * @return true, if successful
	 */
	public boolean submitPracticeSessionQuestion(List<QuestionsUploadBO> questionsUploadBO)throws CPException;
	public boolean testSessions(QuestionsUploadBO bo) throws CPException;
	public boolean testSessionSaving(QuestionsUploadBO bo)throws CPException;
 

	//public String resultPage(ResultsBO resultsBO);
	
	/**
	 * Creates the test number.
	 *
	 * @param userid the userid
	 * @param testid the testid
	 * @return true, if successful
	 */
	public int createTestNumber(int userid,int testid,String testname)throws CPException;
	public String isResumeTest(int userid);
	public ResumeBo startResumeTest(int user_id);

/**
 * Questions for Practice Test
 */
	public List<QuestionsUploadBO> gettingQuestionsforPracticeTest(String query) throws ConnectionException;

	public String averageTimeCalcultation(QuestionsUploadBO questionUploadBO) throws DBException, SQLException;
	
	
	/**
	 * @author kkatikala
	 * @Method to get percentage for categories
	 * @param inputQuery
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 * @throws JSONException 
	 */
	
	public JSONObject getPercentageForCategories(String inputQuery) throws DBException, SQLException, JSONException;
	public JSONObject getUserPaceValues(String inputQuery) throws DBException, SQLException, JSONException;
	public List<ResultsBO> practiseSessionHistoryDetails(QuestionsUploadBO questionUploadBO) throws  ConnectionException;
	public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException;
	public List<ResultsExcelBO> getExcelsheetDataPreparing(ResultsExcelBO resultsExcelBO) throws DBException, SQLException;
	 public List<ResultLessionBO> getLessionExcelsheetData(ResultLessionBO resultLessionBO) throws DBException, SQLException;

}
