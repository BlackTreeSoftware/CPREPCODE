package com.greenbuds.crunchprep.bo.practicesession;

/**
 * The Class customizationBO.
 */
public class CustomizationBO {
	
	private int section_id;
	private int lesson_id;
	private int sublesson_id;
	private int diff_id;
	private String question_pool;
	private int questions_time_limit;
	private String questions_mode;
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public int getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(int lesson_id) {
		this.lesson_id = lesson_id;
	}
	public int getSublesson_id() {
		return sublesson_id;
	}
	public void setSublesson_id(int sublesson_id) {
		this.sublesson_id = sublesson_id;
	}
	public int getDiff_id() {
		return diff_id;
	}
	public void setDiff_id(int diff_id) {
		this.diff_id = diff_id;
	}
	public String getQuestion_pool() {
		return question_pool;
	}
	public void setQuestion_pool(String question_pool) {
		this.question_pool = question_pool;
	}
	public int getQuestions_time_limit() {
		return questions_time_limit;
	}
	public void setQuestions_time_limit(int questions_time_limit) {
		this.questions_time_limit = questions_time_limit;
	}
	public String getQuestions_mode() {
		return questions_mode;
	}
	public void setQuestions_mode(String questions_mode) {
		this.questions_mode = questions_mode;
	}
	public int getQuestions_count() {
		return questions_count;
	}
	public void setQuestions_count(int questions_count) {
		this.questions_count = questions_count;
	}
	private int questions_count;
	

}
