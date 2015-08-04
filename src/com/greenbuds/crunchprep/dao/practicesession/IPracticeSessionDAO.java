package com.greenbuds.crunchprep.dao.practicesession;


import com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultLessionBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsExcelBO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;


 
import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ResumeBo;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IPracticeSessionDAO.
 */
 
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;

 

public interface IPracticeSessionDAO {

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
	
	public boolean testSessions(QuestionsUploadBO bo)throws CPException;
	public boolean testSessionSaving(QuestionsUploadBO bo)throws CPException;
	public List<QuestionsUploadBO> gettingQuestionsforPracticeTest(String query) throws ConnectionException;
 

	
	//public String resultPage(ResultsBO resultsBO);
	/**
	 * Creates the test number.
	 *
	 * @param userid the userid
	 * @param testid the testid
	 * @return true, if successful
	 */
	public String isResumeTest(int userid);
	public int createTestNumber(int userid,int testid,String testname)throws CPException;
	public ResumeBo startResumeTest(int user_id);

	public String averageTimeCalcultation(QuestionsUploadBO questionUploadBO) throws DBException, SQLException;
	
	
	/**
	 * @param inputQuery
	 * @return
	 * @throws SQLException
	 * @throws DBException 
	 * @throws JSONException 
	 */
	public JSONObject getPercentageForCategories(String inputQuery) throws SQLException, DBException, JSONException;
	
	public JSONObject getUserPaceValues(String inputQuery) throws SQLException, DBException, JSONException;
	public List<ResultsBO> practiseSessionHistoryDetails(QuestionsUploadBO questionUploadBO) throws  ConnectionException;
	public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException;
	public List<ResultsExcelBO> getExcelsheetDataPreparing(ResultsExcelBO resultsExcelBO) throws DBException, SQLException;
	 public List<ResultLessionBO> getLessionExcelsheetData(ResultLessionBO resultLessionBO) throws DBException, SQLException;
}
