package com.greenbuds.crunchprep.controller.fulllengthtest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullLengthTestBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.FullResultsBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.OverallPerformanceBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.QuestionAnalysisBO;
import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.dao.fulllengthTest.FulllengthDao;
import com.greenbuds.crunchprep.dao.practicesession.PracticeSessionDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.CommonExceptions;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Section;

public class FulllengthController implements IFulllengthController {
	
	private static FulllengthController fulllengthController;
	private FulllengthController(){
		
	}
	public static synchronized FulllengthController getInstance(){
		if(fulllengthController==null)
			fulllengthController=new FulllengthController();
		return fulllengthController;
	}
	
	
	private FulllengthDao dao;
	public boolean markingQuestion(FullLengthTestBO bo) throws CPException{
		dao=FulllengthDao.getInstance();
		return dao.markingQuestion(bo);
	}
	public boolean quittingTest(FullLengthTestBO bo) throws CPException{
		dao=FulllengthDao.getInstance();
		return dao.quittingTest(bo);
	}

	
	public int saveGreScore(ScoreBO bo) throws CPException{
		dao=FulllengthDao.getInstance();
		return dao.saveGreScore(bo);
	}

	
	
	
public List<QuestionsUploadBO> getQuestionsForFulllegthTest(
			Section section, JSONObject object,int userId) throws ConnectionException,
			CommonExceptions, JSONException {
	dao=FulllengthDao.getInstance();

		return 	dao.getQuestionsForFulllegthTest(section, object,userId);
	}
public void fullLengthTestQuestionSaving(JSONArray questionsData) throws ConnectionException, SQLException, JSONException {
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();
	dao.fullLengthTestQuestionSaving(questionsData);
}
 

public FullLengthTestBO verifyTest(LoginUserBO bo) throws CPException{
	dao=FulllengthDao.getInstance();
	return dao.verifyTest(bo);
}


public List<QuestionsUploadBO> resumeTest(FullLengthTestBO bo1){
	dao=FulllengthDao.getInstance();
	return dao.resumeTest(bo1);

}

public FullResultsBO resultPage(FullResultsBO fullResultsBO) {
	
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	fullResultsBO = dao.resultPage(fullResultsBO);
	return fullResultsBO;
}

public FullResultsBO updateReason(FullResultsBO fullResultsBO) {
	
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	return dao.updateReason(fullResultsBO);	
}
 
public JSONObject overallPerformanceData(JSONObject json) throws ConnectionException, SQLException, JSONException{
	
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	return dao.overallPerformanceData(json);
}

public ArrayList<OverallPerformanceBO> performanceSummary(JSONObject json) throws ConnectionException, SQLException, JSONException{
	
	dao=FulllengthDao.getInstance();	
	return dao.performanceSummary(json);
}
@Override
public boolean updateSectionStatusforNextSection(JSONObject json)
		throws ConnectionException, SQLException, JSONException {
	dao=FulllengthDao.getInstance();	
	return dao.updateSectionStatusforNextSection(json);
}
@Override
public double getDifficultyLevel(JSONObject object) throws ConnectionException,
		JSONException {
	dao=FulllengthDao.getInstance();	
	return dao.getDifficultyLevel(object);
}
public JSONObject questionAnalysis(JSONObject json) throws ConnectionException, SQLException, JSONException {
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	return dao.QuestionAnalysisData(json);

}


public boolean retrySave(QuestionsUploadBO uploadBO) throws CPException{
	dao=FulllengthDao.getInstance();	
	return dao.retrySave(uploadBO);
	
}
@Override
public List<QuestionsUploadBO> getPredictedScoreDetails(
		int userid, int testNo, int testid)throws CPException {
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	return dao.getPredictedScoreDetails(userid, testNo, testid);
}
@Override
public List<ScoreBO> getScaledScoreRanges() throws CPException {
	// TODO Auto-generated method stub
	dao=FulllengthDao.getInstance();	
	return dao.getScaledScoreRanges();
}
 
 

}

