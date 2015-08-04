package com.greenbuds.crunchprep.bo.adminbo;

public class AdminBO {
	
	private int Id;   
	private String name;
	private String startDate;
	private String endDate;
	private String status;
	private String ip;
	private String email;
	private int i;
	private String mobile;
	private String logtime;
	private String mentorName;
	private String login;
	private String logout;
	private String section_name;
	private String category_name;
	private String type_name;
	private String test_date;
	private String test_name;
	private int total_questions;
	private int correct_questions;
	private int skipped_questions;
	private int existed_users;
	private int new_users;
	private int total_users;
	private int no_lessons;
	
	public int getNo_lessons() {
		return no_lessons;
	}
	public void setNo_lessons(int no_lessons) {
		this.no_lessons = no_lessons;
	}
	public int getSkipped_questions() {
		return skipped_questions;
	}
	public void setSkipped_questions(int skipped_questions) {
		this.skipped_questions = skipped_questions;
	}
	public String getSection_name() {
		return section_name;
	}
	public int getExisted_users() {
		return existed_users;
	}
	public void setExisted_users(int existed_users) {
		this.existed_users = existed_users;
	}
	public int getNew_users() {
		return new_users;
	}
	public void setNew_users(int new_users) {
		this.new_users = new_users;
	}
	public int getTotal_users() {
		return total_users;
	}
	public void setTotal_users(int total_users) {
		this.total_users = total_users;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public int getTotal_questions() {
		return total_questions;
	}
	public void setTotal_questions(int total_questions) {
		this.total_questions = total_questions;
	}
	public int getCorrect_questions() {
		return correct_questions;
	}
	public void setCorrect_questions(int correct_questions) {
		this.correct_questions = correct_questions;
	}
	public int getIncorrect_questions() {
		return incorrect_questions;
	}
	public void setIncorrect_questions(int incorrect_questions) {
		this.incorrect_questions = incorrect_questions;
	}
	
	public String getTime_spend() {
		return time_spend;
	}
	public void setTime_spend(String time_spend) {
		this.time_spend = time_spend;
	}
	public String getLesson_taken_date() {
		return lesson_taken_date;
	}
	public void setLesson_taken_date(String lesson_taken_date) {
		this.lesson_taken_date = lesson_taken_date;
	}
	private int incorrect_questions;
	private String mainlesson_name;
	private String sublesson_name;
	public String getMainlesson_name() {
		return mainlesson_name;
	}
	public void setMainlesson_name(String mainlesson_name) {
		this.mainlesson_name = mainlesson_name;
	}
	public String getSublesson_name() {
		return sublesson_name;
	}
	public void setSublesson_name(String sublesson_name) {
		this.sublesson_name = sublesson_name;
	}
	private String time_spend;
	private String lesson_taken_date;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public String getMentorName() {
		return mentorName;
	}
	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogout() {
		return logout;
	}
	public void setLogout(String logout) {
		this.logout = logout;
	}

}
