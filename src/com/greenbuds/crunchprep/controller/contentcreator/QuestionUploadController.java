package com.greenbuds.crunchprep.controller.contentcreator;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.AnswersBO;
import com.greenbuds.crunchprep.bo.contentcreator.ChoicesBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionLessonsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.dao.contentcreator.IQuestionUploadDAO;
import com.greenbuds.crunchprep.dao.contentcreator.QuestionUploadDAO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;


public class QuestionUploadController implements IQuestionUploadController {
      @Autowired
	public IQuestionUploadDAO questionUploadDAO;
	
	public Map<Integer, String> loadLessons(int section_id,int category_id) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer,String> map=new HashMap<Integer,String>();
		questionUploadDAO=new QuestionUploadDAO();
		map=questionUploadDAO.loadLessons(section_id,category_id);
		return map;
	}

 
	public Map<Integer, String> loadSections() throws Exception {
		// TODO Auto-gereturn nnerated method stub
		Map<Integer,String> map=new HashMap<Integer,String>();
		questionUploadDAO=new QuestionUploadDAO();
		map=questionUploadDAO.loadSections();
		return map;
	}

 
	public Map<Integer, String> loadDifficulties() throws Exception {
		// TODO Auto-generated method stub
		Map<Integer,String> map=new HashMap<Integer,String>();
		questionUploadDAO=new QuestionUploadDAO();
		map=questionUploadDAO.loadDifficulties();
		return map;
	}

	 
	public Map<Integer, String> loadSkills() throws Exception {
		// TODO Auto-generated method stub
		Map<Integer,String> map=new HashMap<Integer,String>();
		questionUploadDAO=new QuestionUploadDAO();
		map=questionUploadDAO.loadSkills();
		return map;
	}

 	 
	public List<QuestionsUploadBO> getQuestionsList(int section_id,int[] category_id,int[] type_id) throws Exception {
		// TODO Auto-generated method stub
		questionUploadDAO=new QuestionUploadDAO();
		List<QuestionsUploadBO> list=new ArrayList<QuestionsUploadBO>();
		list=questionUploadDAO.getQuestionsList(section_id,category_id,type_id);
		return list;
	}

 
	public boolean saveQuestions(List<QuestionsUploadBO> bo) throws DBException, ConnectionException {
		// TODO Auto-generated method stub
		boolean flag=false;
		questionUploadDAO=new QuestionUploadDAO();
		
		if(questionUploadDAO.saveQuestions(bo))
			flag=true;
		return flag;
	}

	 
	public boolean updateQuestions(List<QuestionsUploadBO> bo) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		questionUploadDAO=new QuestionUploadDAO();
		if(questionUploadDAO.updateQuestions(bo))
			flag=true;
		return flag;
	}

	 
	public boolean deleteQuestions(String[] s) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		questionUploadDAO=new QuestionUploadDAO();
		/*if(questionUploadDAO.deleteQuestions(s))
			flag=true;*/
		return questionUploadDAO.deleteQuestions(s);
	}

 
	public Map<Integer, String> loadTests(int section_id) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer,String> map=new HashMap<Integer,String>();
		questionUploadDAO=new QuestionUploadDAO();
		map=questionUploadDAO.loadTests(section_id);
		return map;
	}


	public List<ChoicesBO> getChoicesList() throws Exception {
		// TODO Auto-generated method stub
		questionUploadDAO=new QuestionUploadDAO();
		List<ChoicesBO> list=new ArrayList<ChoicesBO>();
		list=questionUploadDAO.getChoicesList();
		return list;
	}


	public List<AnswersBO> getAnswersList() throws Exception {
		// TODO Auto-generated method stub
		questionUploadDAO=new QuestionUploadDAO();
		List<AnswersBO> list=new ArrayList<AnswersBO>();
		list=questionUploadDAO.getAnswersList();
		return list;
	}


	/* (non-Javadoc)
	 * @see com.gbbuds.crunchprep.controllers.ContentCreController.IQuestionUploadController#updateQuestionStatus(java.lang.String[], java.lang.String, java.util.Map)
	 */
	@Override
	public boolean updateQuestionStatus(String[] s, String status,
			Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		questionUploadDAO=new QuestionUploadDAO();
		if(questionUploadDAO.updateQuestionStatus(s,status,map))
			flag=true;
		return flag;
	}


	/* (non-Javadoc)
	 * @see com.gbbuds.crunchprep.controllers.ContentCreController.IQuestionUploadController#getQuestionLessonsList()
	 */
	@Override
	public List<QuestionLessonsBO> getQuestionLessonsList() throws Exception {
		// TODO Auto-generated method stub
		questionUploadDAO=new QuestionUploadDAO();
		List<QuestionLessonsBO> list=new ArrayList<QuestionLessonsBO>();
		list=questionUploadDAO.getQuestionLessonsList();
		return list;
	}

	/** Sairam Section Begin **/
	/**
	 * @author rrajulapati
	 * @param jsonObject
	 * @throws JSONException
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void executeDeleteQuiree(JSONObject jsonObject) throws JSONException, ConnectionException, DBException{
		questionUploadDAO.executeDeleteQuiree(jsonObject);
	}
	
	/**
	 * @author rrajulapati
	 * @param 
	 */

	@Override
	public void verbalQuestionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		questionUploadDAO.verbalQuestionUploading(questionsUploadBO);
		
	}
	/**
	 * @author rrajulapati
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	@Override
	public void verbalQuestionUpDating(QuestionsUploadBO questionsUploadBO) throws ConnectionException, DBException{
		questionUploadDAO.verbalQuestionUpDating(questionsUploadBO);
	}

	@Override
	public void requiredTableDataInJSONFormate(JSONObject jsonObject)
			throws ConnectionException, DBException, JSONException {
		
		new QuestionUploadDAO().requiredTableDataInJSONFormate(jsonObject);
		
	}
	/** Sairam Section End **/
	
	
	/** Deepthi End **/	
	
	public void saveAwaQuestions(QuestionsUploadBO boUploadBO)
			throws DBException, ConnectionException {
		// TODO Auto-generated method stub
		questionUploadDAO.saveAwaQuestion(boUploadBO);
	}


	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController#quantComparisionUploading(com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO)
	 */
	@Override
	public void quantComparisionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		// TODO Auto-generated method stub
		questionUploadDAO.quantComparisionUploading(questionsUploadBO);
	}


	@Override
	public void quantComparisionUpDating(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException {
		// TODO Auto-generated method stub
		
		questionUploadDAO.quantComparisionUpDating(questionsUploadBO);
		
	}


	@Override
	public void getAwaQuestionsDetails(
			JSONObject jsonObject) throws ConnectionException, DBException, JSONException {
			 questionUploadDAO.getAwaquestionsList(jsonObject);
	}


	@Override
	public String deleteAwaQuestions(String parameter) throws ConnectionException, DBException {
		// TODO Auto-generated method stub
		return questionUploadDAO.deleteAwaQuestion(parameter);
	}


	

}
