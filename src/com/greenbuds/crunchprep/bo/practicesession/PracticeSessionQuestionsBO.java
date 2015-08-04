package com.greenbuds.crunchprep.bo.practicesession;

import java.util.List;

public class PracticeSessionQuestionsBO {
	
	
	private int questionid;
	private String question;
	
	
	/*** Variables for Performance Bar ***/
	private int user_id;	
	private int skillid1;
	private int skillid2;
	private int skillid3;
	private String skillname1;
	private String skillname2;
	private String skillname3;
	private int difficulty_id;
	private String difficulty_name;
	private String gotit_percent;
	private String total_users_avg_time;
	private List<String> lastFivequestions;
	private int test_no;
	
	
	
	
	
	
	
	
	
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getSkillid1() {
		return skillid1;
	}
	public void setSkillid1(int skillid1) {
		this.skillid1 = skillid1;
	}
	public int getSkillid2() {
		return skillid2;
	}
	public void setSkillid2(int skillid2) {
		this.skillid2 = skillid2;
	}
	public int getSkillid3() {
		return skillid3;
	}
	public void setSkillid3(int skillid3) {
		this.skillid3 = skillid3;
	}
	public String getSkillname1() {
		return skillname1;
	}
	public void setSkillname1(String skillname1) {
		this.skillname1 = skillname1;
	}
	public String getSkillname2() {
		return skillname2;
	}
	public void setSkillname2(String skillname2) {
		this.skillname2 = skillname2;
	}
	public String getSkillname3() {
		return skillname3;
	}
	public void setSkillname3(String skillname3) {
		this.skillname3 = skillname3;
	}
	public int getDifficulty_id() {
		return difficulty_id;
	}
	public void setDifficulty_id(int difficulty_id) {
		this.difficulty_id = difficulty_id;
	}
	public String getDifficulty_name() {
		return difficulty_name;
	}
	public void setDifficulty_name(String difficulty_name) {
		this.difficulty_name = difficulty_name;
	}
	public String getGotit_percent() {
		return gotit_percent;
	}
	public void setGotit_percent(String gotit_percent) {
		this.gotit_percent = gotit_percent;
	}
	public String getTotal_users_avg_time() {
		return total_users_avg_time;
	}
	public void setTotal_users_avg_time(String total_users_avg_time) {
		this.total_users_avg_time = total_users_avg_time;
	}
	public List<String> getLastFivequestions() {
		return lastFivequestions;
	}
	public void setLastFivequestions(List<String> lastFivequestions) {
		this.lastFivequestions = lastFivequestions;
	}
	

}
