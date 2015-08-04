/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.bo.contentcreator;



import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class QuestionsUploadBO.
 */
public class QuestionsUploadBO {
	
	
	/** The test_id. */
	private int test_id;
	
	/** The question_id. */
	private int question_id;
	
	/** The question_no. */
	private int question_no;
	
	/** The section_id. */
	private int section_id;
	
	/** The lesson_id. */
	private int lesson_id;
	
	
	/** The type id. */
	private int typeId;
	
	/** The questiontype_id. */
	private int questiontype_id;
	
	/** The category_id. */
	private int category_id;
	
	/** The question_directions. */
	private String question_directions;
	
	/** The question_title. */
	private String question_title;
	
	/** The passage_type. */
	private String passage_type;
	
	/** The passage. */
	private String passage;
	
	
	/** The user_id. */
	private int user_id;
	
	/** The user_date. */
	private String user_date;
	
	/** The fee type. */
	private String feeType;
	
	/** The refferal. */
	private String refferal;
	
	/** The operation success. */
	private boolean operationSuccess;
	/** The test_name. */
	private String test_name;
	
	/** The category_name. */
	private String category_name;
	
	/** The section_name. */
	private String section_name;
	
	/** The question_type. */
	private String question_type;
	
	/** The lession_name. */
	private String lession_name;
	
	/** The difficulty_name. */
	private String difficulty_name;
	
	private String question_time;

	
	private String[] userAnswer;
	
	
	
	public String getQuestion_time() {
		return question_time;
	}

	public void setQuestion_time(String question_time) {
		this.question_time = question_time;
	}


	/** The choices. */
	private List<String> choices;
	
	/** The answers. */
	private List<String> answers;
	
	/** The lessons. */
	private List<String> lessons;
	
	/** The tooltips. */
	private List<String> tooltips;
	
	/** The skills. */
	private List<String> skills;
	
	/** The question. */
	private String question;
	
	/** The skillid1. */
	private int skillid1;
	
	/** The skillid2. */
	private int skillid2;
	
	/** The skillid3. */
	private int skillid3;
	
	/** The difficulty_id. */
	private int difficulty_id;
	
	/** The avg_time. */
	private String avg_time;
	
	/** The quantity a. */
	private String quantityA;
	
	/** The quantity b. */
	private String quantityB;
	
	/** The question_image. */
	private String question_image;
	
	/** The solution_text. */
	private String solution_text;
	
	/** The solution_video. */
	private String solution_video;
	
	/** The access_type. */
	private String access_type;
	
	/** The status. */
	private String status;
	
	/** The passage id. */
	private int passageId;
	
	/** The graph id. */
	private int graphId;
	
	/** The action type. */
	private String actionType;
	
	/** The passage title. */
	private String passageTitle;
	
	/** The graph title. */
	private String graphTitle;
	
	private String reading_comprehension_text;
	
 
	public String getReading_comprehension_text() {
		return reading_comprehension_text;
	}

	public void setReading_comprehension_text(String reading_comprehension_text) {
		this.reading_comprehension_text = reading_comprehension_text;
	}


	private String questionPassage;
 
	/** The user_time. */
	private String user_time;
 
	
	
	private String guessed;
 

	
	private int question_index;

	private int choices_length;
	private int answers_length;



	private int passage_difficultyId;
	private String graph;
	
	private String isAnswered;
	private String isFlagged;
	private String answer;

 
	public String getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(String isAnswered) {
		this.isAnswered = isAnswered;
	}

	public String getIsFlagged() {
		return isFlagged;
	}

	public void setIsFlagged(String isFlagged) {
		this.isFlagged = isFlagged;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getGuessed() {
		return guessed;
	}

	public void setGuessed(String guessed) {
		this.guessed = guessed;
	}
 

	public int getQuestion_index() {
		return question_index;
	}

	public void setQuestion_index(int question_index) {
		this.question_index = question_index;
	}


	public String getGraph() {
		return graph;
	}

	public void setGraph(String graph) {
		this.graph = graph;
	}

	public int getPassage_difficultyId() {
		return passage_difficultyId;
	}

	public void setPassage_difficultyId(int passage_difficultyId) {
		this.passage_difficultyId = passage_difficultyId;
	}


 
	public String getQuestionPassage() {
		return questionPassage;
	}

	public int getChoices_length() {
		return choices_length;
	}

	public void setChoices_length(int choices_length) {
		this.choices_length = choices_length;
	}

	public int getAnswers_length() {
		return answers_length;
	}

	public void setAnswers_length(int answers_length) {
		this.answers_length = answers_length;
	}

	public void setQuestionPassage(String questionPassage) {
		this.questionPassage = questionPassage;
	}

 
	/** The test_date. */
	private String test_date;
	
	/** The result. */
	private String result;
	

	/** The user_answer. */

	private int activity_id;
	
	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}


	private List<String> user_answer;
	
	/** The session_type. */
	private String session_type;
	
	/**
	 * Gets the session_type.
	 *
	 * @return the session_type
	 */
	public String getSession_type() {
		return session_type;
	}

	/**
	 * Sets the session_type.
	 *
	 * @param session_type the new session_type
	 */
	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}

	/**
	 * Sets the user_answer.
	 *
	 * @param user_answer the new user_answer
	 */
	public void setUser_answer(List<String> user_answer) {
		this.user_answer = user_answer;
	}

 
	/**
	 * Gets the user_time.
	 *
	 * @return the user_time
	 */
	public String getUser_time() {
		return user_time;
	}

	/**
	 * Gets the user_answer.
	 *
	 * @return the user_answer
	 */
	public List<String> getUser_answer() {
		return user_answer;
	}

	/**
	 * Sets the user_time.
	 *
	 * @param user_time the new user_time
	 */
	public void setUser_time(String user_time) {
		this.user_time = user_time;
	}

	/**
	 * Gets the test_date.
	 *
	 * @return the test_date
	 */
	public String getTest_date() {
		return test_date;
	}

	/**
	 * Sets the test_date.
	 *
	 * @param test_date the new test_date
	 */
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	 

	/**
	 * Gets the flag_status.
	 *
	 * @return the flag_status
	 */
	public String getFlag_status() {
		return flag_status;
	}

	/**
	 * Sets the flag_status.
	 *
	 * @param flag_status the new flag_status
	 */
	public void setFlag_status(String flag_status) {
		this.flag_status = flag_status;
	}

	 /** The test_no. */
 	private int test_no;

	 
	 
	
	/** The flag_status. */
	private String flag_status;
	
	 
	  
	/**
	 * Gets the test_no.
	 *
	 * @return the test_no
	 */
	public int getTest_no() {
		return test_no;
	}

	/**
	 * Sets the test_no.
	 *
	 * @param test_no the new test_no
	 */
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}

	/**
	 * Gets the access_type.
	 *
	 * @return the access_type
	 */
	public String getAccess_type() {
		return access_type;
	}
	
	/**
	 * Sets the access_type.
	 *
	 * @param access_type the new access_type
	 */
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	
	/**
	 * Gets the category_name.
	 *
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	
	/**
	 * Sets the category_name.
	 *
	 * @param category_name the new category_name
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	/**
	 * Gets the lession_name.
	 *
	 * @return the lession_name
	 */
	public String getLession_name() {
		return lession_name;
	}
	
	/**
	 * Sets the lession_name.
	 *
	 * @param lession_name the new lession_name
	 */
	public void setLession_name(String lession_name) {
		this.lession_name = lession_name;
	}
	
	/**
	 * Gets the test_name.
	 *
	 * @return the test_name
	 */
	public String getTest_name() {
		return test_name;
	}
	
	/**
	 * Sets the test_name.
	 *
	 * @param test_name the new test_name
	 */
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	
	/**
	 * Gets the section_name.
	 *
	 * @return the section_name
	 */
	public String getSection_name() {
		return section_name;
	}
	
	/**
	 * Sets the section_name.
	 *
	 * @param section_name the new section_name
	 */
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	
	/**
	 * Gets the difficulty_name.
	 *
	 * @return the difficulty_name
	 */
	public String getDifficulty_name() {
		return difficulty_name;
	}
	
	/**
	 * Sets the difficulty_name.
	 *
	 * @param difficulty_name the new difficulty_name
	 */
	public void setDifficulty_name(String difficulty_name) {
		this.difficulty_name = difficulty_name;
	}
	
	/**
	 * Gets the test_id.
	 *
	 * @return the test_id
	 */
	public int getTest_id() {
		return test_id;
	}
	
	/**
	 * Sets the test_id.
	 *
	 * @param test_id the new test_id
	 */
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	
	/**
	 * Gets the question_id.
	 *
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}
	
	/**
	 * Sets the question_id.
	 *
	 * @param question_id the new question_id
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	/**
	 * Gets the section_id.
	 *
	 * @return the section_id
	 */
	public int getSection_id() {
		return section_id;
	}
	
	/**
	 * Sets the section_id.
	 *
	 * @param section_id the new section_id
	 */
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	
	/**
	 * Gets the lesson_id.
	 *
	 * @return the lesson_id
	 */
	public int getLesson_id() {
		return lesson_id;
	}
	
	/**
	 * Sets the lesson_id.
	 *
	 * @param lesson_id the new lesson_id
	 */
	public void setLesson_id(int lesson_id) {
		this.lesson_id = lesson_id;
	}
	
	/**
	 * Gets the questiontype_id.
	 *
	 * @return the questiontype_id
	 */
	public int getQuestiontype_id() {
		return questiontype_id;
	}
	
	/**
	 * Sets the questiontype_id.
	 *
	 * @param questiontype_id the new questiontype_id
	 */
	public void setQuestiontype_id(int questiontype_id) {
		this.questiontype_id = questiontype_id;
	}
	
	/**
	 * Gets the difficulty_id.
	 *
	 * @return the difficulty_id
	 */
	public int getDifficulty_id() {
		return difficulty_id;
	}
	
	/**
	 * Sets the difficulty_id.
	 *
	 * @param difficulty_id the new difficulty_id
	 */
	public void setDifficulty_id(int difficulty_id) {
		this.difficulty_id = difficulty_id;
	}
	
	/**
	 * Gets the category_id.
	 *
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}
	
	/**
	 * Sets the category_id.
	 *
	 * @param category_id the new category_id
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	
	
	/**
	 * Gets the user_id.
	 *
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	
	/**
	 * Sets the user_id.
	 *
	 * @param user_id the new user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * Gets the user_date.
	 *
	 * @return the user_date
	 */
	public String getUser_date() {
		return user_date;
	}
	
	/**
	 * Sets the user_date.
	 *
	 * @param user_date the new user_date
	 */
	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}
	
	/**
	 * Gets the question_directions.
	 *
	 * @return the question_directions
	 */
	public String getQuestion_directions() {
		return question_directions;
	}
	
	/**
	 * Sets the question_directions.
	 *
	 * @param question_directions the new question_directions
	 */
	public void setQuestion_directions(String question_directions) {
		this.question_directions = question_directions;
	}
	
	/**
	 * Gets the question_no.
	 *
	 * @return the question_no
	 */
	public int getQuestion_no() {
		return question_no;
	}
	
	/**
	 * Sets the question_no.
	 *
	 * @param question_no the new question_no
	 */
	public void setQuestion_no(int question_no) {
		this.question_no = question_no;
	}
	
	/**
	 * Gets the question_title.
	 *
	 * @return the question_title
	 */
	public String getQuestion_title() {
		return question_title;
	}
	
	/**
	 * Sets the question_title.
	 *
	 * @param question_title the new question_title
	 */
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	
	/**
	 * Gets the passage_type.
	 *
	 * @return the passage_type
	 */
	public String getPassage_type() {
		return passage_type;
	}
	
	/**
	 * Sets the passage_type.
	 *
	 * @param passage_type the new passage_type
	 */
	public void setPassage_type(String passage_type) {
		this.passage_type = passage_type;
	}
	
	/**
	 * Gets the passage.
	 *
	 * @return the passage
	 */
	public String getPassage() {
		return passage;
	}
	
	/**
	 * Sets the passage.
	 *
	 * @param passage the new passage
	 */
	public void setPassage(String passage) {
		this.passage = passage;
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public List<String> getChoices() {
		return choices;
	}
	
	/**
	 * Sets the choices.
	 *
	 * @param choices the new choices
	 */
	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
	
	/**
	 * Gets the answers.
	 *
	 * @return the answers
	 */
	public List<String> getAnswers() {
		return answers;
	}
	
	/**
	 * Sets the answers.
	 *
	 * @param answers the new answers
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Sets the question.
	 *
	 * @param question the new question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * Gets the skillid1.
	 *
	 * @return the skillid1
	 */
	public int getSkillid1() {
		return skillid1;
	}
	
	/**
	 * Sets the skillid1.
	 *
	 * @param skillid1 the new skillid1
	 */
	public void setSkillid1(int skillid1) {
		this.skillid1 = skillid1;
	}
	
	/**
	 * Gets the skillid2.
	 *
	 * @return the skillid2
	 */
	public int getSkillid2() {
		return skillid2;
	}
	
	/**
	 * Sets the skillid2.
	 *
	 * @param skillid2 the new skillid2
	 */
	public void setSkillid2(int skillid2) {
		this.skillid2 = skillid2;
	}
	
	/**
	 * Gets the skillid3.
	 *
	 * @return the skillid3
	 */
	public int getSkillid3() {
		return skillid3;
	}
	
	/**
	 * Sets the skillid3.
	 *
	 * @param skillid3 the new skillid3
	 */
	public void setSkillid3(int skillid3) {
		this.skillid3 = skillid3;
	}
	
	/**
	 * Gets the avg_time.
	 *
	 * @return the avg_time
	 */
	public String getAvg_time() {
		return avg_time;
	}
	
	/**
	 * Sets the avg_time.
	 *
	 * @param avg_time the new avg_time
	 */
	public void setAvg_time(String avg_time) {
		this.avg_time = avg_time;
	}
	
	/**
	 * Gets the quantity a.
	 *
	 * @return the quantity a
	 */
	public String getQuantityA() {
		return quantityA;
	}
	
	/**
	 * Sets the quantity a.
	 *
	 * @param quantityA the new quantity a
	 */
	public void setQuantityA(String quantityA) {
		this.quantityA = quantityA;
	}
	
	/**
	 * Gets the quantity b.
	 *
	 * @return the quantity b
	 */
	public String getQuantityB() {
		return quantityB;
	}
	
	/**
	 * Sets the quantity b.
	 *
	 * @param quantityB the new quantity b
	 */
	public void setQuantityB(String quantityB) {
		this.quantityB = quantityB;
	}
	
	/**
	 * Gets the question_image.
	 *
	 * @return the question_image
	 */
	public String getQuestion_image() {
		return question_image;
	}
	
	/**
	 * Sets the question_image.
	 *
	 * @param question_image the new question_image
	 */
	public void setQuestion_image(String question_image) {
		this.question_image = question_image;
	}
	
	/**
	 * Gets the solution_text.
	 *
	 * @return the solution_text
	 */
	public String getSolution_text() {
		return solution_text;
	}
	
	/**
	 * Sets the solution_text.
	 *
	 * @param solution_text the new solution_text
	 */
	public void setSolution_text(String solution_text) {
		this.solution_text = solution_text;
	}
	
	/**
	 * Gets the solution_video.
	 *
	 * @return the solution_video
	 */
	public String getSolution_video() {
		return solution_video;
	}
	
	/**
	 * Sets the solution_video.
	 *
	 * @param solution_video the new solution_video
	 */
	public void setSolution_video(String solution_video) {
		this.solution_video = solution_video;
	}
	
	/**
	 * Gets the lessons.
	 *
	 * @return the lessons
	 */
	public List<String> getLessons() {
		return lessons;
	}
	
	/**
	 * Sets the lessons.
	 *
	 * @param lessons the new lessons
	 */
	public void setLessons(List<String> lessons) {
		this.lessons = lessons;
	}
	
	/**
	 * Gets the tooltips.
	 *
	 * @return the tooltips
	 */
	public List<String> getTooltips() {
		return tooltips;
	}
	
	/**
	 * Sets the tooltips.
	 *
	 * @param tooltips the new tooltips
	 */
	public void setTooltips(List<String> tooltips) {
		this.tooltips = tooltips;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}
	
	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	/**
	 * Gets the question_type.
	 *
	 * @return the question_type
	 */
	public String getQuestion_type() {
		return question_type;
	}
	
	/**
	 * Sets the question_type.
	 *
	 * @param question_type the new question_type
	 */
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}

	/**
	 * Gets the fee type.
	 *
	 * @return the fee type
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * Sets the fee type.
	 *
	 * @param feeType the new fee type
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	/**
	 * Gets the refferal.
	 *
	 * @return the refferal
	 */
	public String getRefferal() {
		return refferal;
	}

	/**
	 * Sets the refferal.
	 *
	 * @param refferal the new refferal
	 */
	public void setRefferal(String refferal) {
		this.refferal = refferal;
	}


	/**
	 * Gets the passage id.
	 *
	 * @return the passage id
	 */
	public int getPassageId() {
		return passageId;
	}

	/**
	 * Sets the passage id.
	 *
	 * @param passageId the new passage id
	 */
	public void setPassageId(int passageId) {
		this.passageId = passageId;
	}

	/**
	 * Gets the graph id.
	 *
	 * @return the graph id
	 */
	public int getGraphId() {
		return graphId;
	}

	/**
	 * Sets the graph id.
	 *
	 * @param graphId the new graph id
	 */
	public void setGraphId(int graphId) {
		this.graphId = graphId;
	}

	/**
	 * Gets the action type.
	 *
	 * @return the action type
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * Sets the action type.
	 *
	 * @param actionType the new action type
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * Checks if is operation success.
	 *
	 * @return true, if is operation success
	 */
	public boolean isOperationSuccess() {
		return operationSuccess;
	}

	/**
	 * Sets the operation success.
	 *
	 * @param operationSuccess the new operation success
	 */
	public void setOperationSuccess(boolean operationSuccess) {
		this.operationSuccess = operationSuccess;
	}

	/**
	 * Gets the passage title.
	 *
	 * @return the passage title
	 */
	public String getPassageTitle() {
		return passageTitle;
	}

	/**
	 * Sets the passage title.
	 *
	 * @param passageTitle the new passage title
	 */
	public void setPassageTitle(String passageTitle) {
		this.passageTitle = passageTitle;
	}

	/**
	 * Gets the graph title.
	 *
	 * @return the graph title
	 */
	public String getGraphTitle() {
		return graphTitle;
	}

	/**
	 * Sets the graph title.
	 *
	 * @param graphTitle the new graph title
	 */
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}



public String answerstatus;
public String getAnswerstatus() {
	return answerstatus;
}

public void setAnswerstatus(String answerstatus) {
	this.answerstatus = answerstatus;
}


public String empty5;




public String getEmpty5() {
	return empty5;
}

public void setEmpty5(String empty5) {
	this.empty5 = empty5;
}

public String[] getUserAnswer() {
	return userAnswer;
}

public void setUserAnswer(String[] userAnswer) {
	this.userAnswer = userAnswer;
}






 
  
     	
	
}
