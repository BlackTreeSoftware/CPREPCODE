package com.greenbuds.crunchprep.bo.fulllengthtest;

public class FullLengthTestBO {

	private int userid;
	private int test_no;
	private int test_id;
	private int question_id;
	private String flag_status;
	private String pattern;
 
	private String status;
	private String section_status;
	
 
	public String getSection_status() {
		return section_status;
	}
	public void setSection_status(String section_status) {
		this.section_status = section_status;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	private int fulllenth_rowId;
 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
 
	public int getFulllenth_rowId() {
		return fulllenth_rowId;
	}
	public void setFulllenth_rowId(int fulllenth_rowId) {
		this.fulllenth_rowId = fulllenth_rowId;
	}
 
	public String getFlag_status() {
		return flag_status;
	}
	public void setFlag_status(String flag_status) {
		this.flag_status = flag_status;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	
	
	public String getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(String timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public int getQuestionIndex() {
		return questionIndex;
	}
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	
	private String timeRemaining;
	private int questionIndex;
	
	private String section_questions;
	private int sectionid;
	private int section_type;

	public int getSection_type() {
		return section_type;
	}
	public void setSection_type(int section_type) {
		this.section_type = section_type;
	}
	public String getSection_questions() {
		return section_questions;
	}
	public void setSection_questions(String section_questions) {
		this.section_questions = section_questions;
	}
	public int getSectionid() {
		return sectionid;
	}
	public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}
	
	
}
