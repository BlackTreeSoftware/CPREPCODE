package com.greenbuds.crunchprep.controller.practicesession;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullResultsBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ResumeBo;
import com.greenbuds.crunchprep.bo.practicesession.PracticeSessionQuestionsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultLessionBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsExcelBO;
import com.greenbuds.crunchprep.dao.fulllengthTest.FulllengthDao;
import com.greenbuds.crunchprep.dao.practicesession.IPracticeSessionDAO;
import com.greenbuds.crunchprep.dao.practicesession.PracticeSessionDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

public class PracticeSessionController implements IPracticeSessionController {
	
	private IPracticeSessionDAO practiceSessionDAO=null;
	public static PracticeSessionController practiceSessionController;
	ResumeBo resultsBO = null;
	 
 	/**
 	 * Instantiates a new lessons controller.
 	 */
	 
 	/**
 	 * Gets the single instance of PracticeSessionController.
 	 *
 	 * @return single instance of {@link PracticeSessionController}
 	 */
 	public static synchronized PracticeSessionController getInstance(){
		
		 if(practiceSessionController==null) practiceSessionController = new PracticeSessionController();
			 
		   return practiceSessionController;
	 }

	@Override
	public PracticeSessionQuestionsBO getPerformanceBarValues(
			PracticeSessionQuestionsBO practiceSessionQuestionsBO)
			throws ConnectionException, DBException,SQLException {
		// TODO Auto-generated method stub
		PracticeSessionQuestionsBO practiceSessionBO=new PracticeSessionQuestionsBO();
		practiceSessionDAO=PracticeSessionDAO.getInstance();
		practiceSessionBO=practiceSessionDAO.getPerformanceBarValues(practiceSessionQuestionsBO);
		return practiceSessionBO;
	}
	
	
	
	@Override
	public ResultsBO resultPage(ResultsBO resultsBO) {
		
		// TODO Auto-generated method stub
		practiceSessionDAO = new PracticeSessionDAO();
	 resultsBO = practiceSessionDAO.resultPage(resultsBO);
		return resultsBO;
	}

	
	public ResultsBO updateReason(ResultsBO resultsBO) {
		
		// TODO Auto-generated method stub
		practiceSessionDAO=PracticeSessionDAO.getInstance();	
		return practiceSessionDAO.updateReason(resultsBO);	 
	}
	@Override
	public boolean submitPracticeSessionQuestion(
			List<QuestionsUploadBO> questionsUploadBO) throws CPException {
		practiceSessionDAO=new PracticeSessionDAO();
		return practiceSessionDAO.submitPracticeSessionQuestion(questionsUploadBO);  
	}

	@Override
	public int createTestNumber(int userid, int testid,String testname) throws CPException{
		// TODO Auto-generated method stub
		practiceSessionDAO=new PracticeSessionDAO();
		return practiceSessionDAO.createTestNumber(userid, testid,testname);
	} 
  

	public boolean testSessions(QuestionsUploadBO bo) throws CPException{
		practiceSessionDAO=PracticeSessionDAO.getInstance();
		return practiceSessionDAO.testSessions(bo);
	}
	
	public boolean testSessionSaving(QuestionsUploadBO bo)throws CPException{
		practiceSessionDAO=new PracticeSessionDAO();
		return practiceSessionDAO.testSessionSaving(bo);
	}
 
	@Override
	public List<QuestionsUploadBO> gettingQuestionsforPracticeTest(String query)
			throws ConnectionException {
		practiceSessionDAO=new PracticeSessionDAO();
		return practiceSessionDAO.gettingQuestionsforPracticeTest(query);
	}
public String averageTimeCalcultation(QuestionsUploadBO questionUploadBO) throws DBException, SQLException{
		
		practiceSessionDAO=new PracticeSessionDAO();
		return practiceSessionDAO.averageTimeCalcultation(questionUploadBO);

		
		
	}

@Override
public JSONObject getPercentageForCategories(String inputQuery) throws DBException, SQLException, JSONException {
	// TODO Auto-generated method stub
	practiceSessionDAO=new PracticeSessionDAO();
	return practiceSessionDAO.getPercentageForCategories(inputQuery);
	
}

@Override
public JSONObject getUserPaceValues(String inputQuery) throws DBException, SQLException, JSONException {
	// TODO Auto-generated method stub
	practiceSessionDAO=new PracticeSessionDAO();
	return practiceSessionDAO.getUserPaceValues(inputQuery);
	
}

@Override
public List<ResultsBO> practiseSessionHistoryDetails(
		QuestionsUploadBO questionUploadBO) throws ConnectionException {
	practiceSessionDAO=new PracticeSessionDAO();
	return practiceSessionDAO.practiseSessionHistoryDetails(questionUploadBO);
	
}

public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException{
	practiceSessionDAO=new PracticeSessionDAO();
	return practiceSessionDAO.retrySave(uploadBO);
	
}
 

public List<ResultsExcelBO> getExcelsheetDataPreparing(ResultsExcelBO resultsExcelBO) throws DBException, SQLException{
	
	practiceSessionDAO=new PracticeSessionDAO();
	
	return practiceSessionDAO.getExcelsheetDataPreparing(resultsExcelBO);
}

public List<ResultLessionBO> getLessionExcelsheetData(ResultLessionBO resultLessionBO) throws DBException, SQLException{
	
	practiceSessionDAO=new PracticeSessionDAO();
	
	return practiceSessionDAO.getLessionExcelsheetData(resultLessionBO);
	
	
}

@Override
public String isResumeTest(int userid) {
	// TODO Auto-generated method stub
practiceSessionDAO=new PracticeSessionDAO();
	
	return practiceSessionDAO.isResumeTest(userid);
}

@Override
public ResumeBo startResumeTest(int user_id) {
	practiceSessionDAO = new PracticeSessionDAO();
     resultsBO = practiceSessionDAO.startResumeTest(user_id);
	return resultsBO;
}


}
