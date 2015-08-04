package com.greenbuds.crunchprep.dao.contentcreator;




import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.contentcreator.AnswersBO;
import com.greenbuds.crunchprep.bo.contentcreator.ChoicesBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionLessonsBO;
import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;





public interface IQuestionUploadDAO {

	public Map<Integer,String> loadLessons(int section_id,int category_id) throws Exception;
	public Map<Integer,String> loadSections() throws Exception;
	public Map<Integer,String> loadDifficulties() throws Exception;
	public Map<Integer,String> loadSkills() throws Exception;
	public Map<Integer,String> loadTests(int section_id) throws Exception;
	
	public List<QuestionsUploadBO> getQuestionsList(int section_id,int[] category_id,int[] type_id) throws Exception;
	public List<ChoicesBO> getChoicesList() throws Exception;
	public List<AnswersBO> getAnswersList() throws Exception;
	
	public boolean saveQuestions(List<QuestionsUploadBO> bo) throws ConnectionException,DBException;
	public boolean updateQuestions(List<QuestionsUploadBO> bo) throws Exception;
	public boolean deleteQuestions(String[] s) throws Exception;
	public boolean updateQuestionStatus(String[] s,String status,Map<String,String> map) throws Exception;
	
	
	
	/** Sairam Sectino Begin **/
	public List<QuestionLessonsBO> getQuestionLessonsList() throws Exception;
	
	
	/**
	 * @author rrajulapati
	 * @param jsonObject
	 * @throws JSONException
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void executeDeleteQuiree(JSONObject jsonObject) throws JSONException, ConnectionException, DBException;
	
	/**
	 * @author rrajulapati
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	
	public void verbalQuestionUpDating(QuestionsUploadBO questionsUploadBO) throws ConnectionException, DBException;
	
	/**
	 * @author rrajulapati
	 * @param jsonObject
	 * @throws ConnectionException
	 * @throws DBException
	 * @throws JSONException 
	 */
		public void requiredTableDataInJSONFormate(JSONObject jsonObject) throws ConnectionException, DBException, JSONException;
	
	/**
	 * @author rrajulapati
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void verbalQuestionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException;
	/** Sairam Sectino End 
	 * @throws DBException 
	 * @throws ConnectionException **/
	public void saveAwaQuestion(QuestionsUploadBO questionsUploadBO) throws ConnectionException, DBException;
	public void getAwaquestionsList(
			JSONObject jsonObject) throws ConnectionException, DBException, JSONException;
	public String deleteAwaQuestion(String parameter) throws ConnectionException, DBException;
	
	
	
	
	
	/**
	 * @author kkatikala
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void quantComparisionUploading(QuestionsUploadBO questionsUploadBO)
			throws ConnectionException, DBException;
	
	/**
	 * @param questionsUploadBO
	 * @throws ConnectionException
	 * @throws DBException
	 */
	public void quantComparisionUpDating(QuestionsUploadBO questionsUploadBO) throws ConnectionException, DBException;
	
	
}
