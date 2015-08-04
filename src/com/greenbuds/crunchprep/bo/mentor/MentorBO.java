package com.greenbuds.crunchprep.bo.mentor;

public class MentorBO {
	
private int user_id;
private int mentor_id;
private int section_id;
private String  allocation_status;
private String studentName;
private String email;
private String mobile;
private String start_date;
private String end_date;
private String status;
private int lessonsTaken;
private int totalLessons;
private int questionsTaken;
private double percentageLessions;
private double percentageQuestions;

private String ipaddress;
private String loginDate;
private String logoutDate;
private int noLastLoginDays;


private String test_date;
private int previous_score;
private int target_score;


private String lesson_name;
private String lesson_date;
private String lesson_timespent;

public String getLesson_name() {
	return lesson_name;
}
public void setLesson_name(String lesson_name) {
	this.lesson_name = lesson_name;
}
public String getLesson_date() {
	return lesson_date;
}
public void setLesson_date(String lesson_date) {
	this.lesson_date = lesson_date;
}
public String getLesson_timespent() {
	return lesson_timespent;
}
public void setLesson_timespent(String lesson_timespent) {
	this.lesson_timespent = lesson_timespent;
}
public String getTest_date() {
	return test_date;
}
public void setTest_date(String test_date) {
	this.test_date = test_date;
}
public int getPrevious_score() {
	return previous_score;
}
public void setPrevious_score(int previous_score) {
	this.previous_score = previous_score;
}
public int getTarget_score() {
	return target_score;
}
public void setTarget_score(int target_score) {
	this.target_score = target_score;
}
public int getNoLastLoginDays() {
	return noLastLoginDays;
}
public void setNoLastLoginDays(int noLastLoginDays) {
	this.noLastLoginDays = noLastLoginDays;
}
public String getIpaddress() {
	return ipaddress;
}
public void setIpaddress(String ipaddress) {
	this.ipaddress = ipaddress;
}
public String getLoginDate() {
	return loginDate;
}
public void setLoginDate(String loginDate) {
	this.loginDate = loginDate;
}
public String getLogoutDate() {
	return logoutDate;
}
public void setLogoutDate(String logoutDate) {
	this.logoutDate = logoutDate;

}

public double getPercentageLessions() {
	return percentageLessions;
}
public void setPercentageLessions(double percentageLessions) {
	this.percentageLessions = percentageLessions;
}
public double getPercentageQuestions() {
	return percentageQuestions;
}
public void setPercentageQuestions(double percentageQuestions) {
	this.percentageQuestions = percentageQuestions;
}
public int getLessonsTaken() {
	return lessonsTaken;
}
public void setLessonsTaken(int lessonsTaken) {
	this.lessonsTaken = lessonsTaken;
}
public int getTotalLessons() {
	return totalLessons;
}
public void setTotalLessons(int totalLessons) {
	this.totalLessons = totalLessons;
}
public int getQuestionsTaken() {
	return questionsTaken;
}
public void setQuestionsTaken(int questionsTaken) {
	this.questionsTaken = questionsTaken;
}
public int getTotalQuestions() {
	return totalQuestions;
}
public void setTotalQuestions(int totalQuestions) {
	this.totalQuestions = totalQuestions;
}
private int totalQuestions;
public int getUser_id() {
	return user_id;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getMentor_id() {
	return mentor_id;
}
public void setMentor_id(int mentor_id) {
	this.mentor_id = mentor_id;
}
public int getSection_id() {
	return section_id;
}
public void setSection_id(int section_id) {
	this.section_id = section_id;
}
public String getAllocation_status() {
	return allocation_status;
}
public void setAllocation_status(String allocation_status) {
	this.allocation_status = allocation_status;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getStart_date() {
	return start_date;
}
public void setStart_date(String start_date) {
	this.start_date = start_date;
}
public String getEnd_date() {
	return end_date;
}
public void setEnd_date(String end_date) {
	this.end_date = end_date;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}