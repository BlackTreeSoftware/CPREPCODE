package com.greenbuds.crunchprep.bo.mentor;

public class UserProgressBO {
private String totalQuantLessons;
private String totalVerbalLessons;
private String totalVerbalLessonsCompleted;
private String totalQuantLessonsCompleted;
private String totalSpentForQuantLessons;
private String totalSpentForVerbalLessons;

private String correctCount;
private String wrongCont;
private String questionType;

private String section_id;
public String getSection_id() {
	return section_id;
}
public void setSection_id(String section_id) {
	this.section_id = section_id;
}
public String getCorrectCount() {
	return correctCount;
}
public void setCorrectCount(String correctCount) {
	this.correctCount = correctCount;
}
public String getWrongCont() {
	return wrongCont;
}
public void setWrongCont(String wrongCont) {
	this.wrongCont = wrongCont;
}
public String getQuestionType() {
	return questionType;
}
public void setQuestionType(String questionType) {
	this.questionType = questionType;
}
public String getTotalQuantLessons() {
	return totalQuantLessons;
}
public void setTotalQuantLessons(String totalQuantLessons) {
	this.totalQuantLessons = totalQuantLessons;
}
public String getTotalVerbalLessons() {
	return totalVerbalLessons;
}
public void setTotalVerbalLessons(String totalVerbalLessons) {
	this.totalVerbalLessons = totalVerbalLessons;
}
public String getTotalVerbalLessonsCompleted() {
	return totalVerbalLessonsCompleted;
}
public void setTotalVerbalLessonsCompleted(String totalVerbalLessonsCompleted) {
	this.totalVerbalLessonsCompleted = totalVerbalLessonsCompleted;
}
public String getTotalQuantLessonsCompleted() {
	return totalQuantLessonsCompleted;
}
public void setTotalQuantLessonsCompleted(String totalQuantLessonsCompleted) {
	this.totalQuantLessonsCompleted = totalQuantLessonsCompleted;
}
public String getTotalSpentForQuantLessons() {
	return totalSpentForQuantLessons;
}
public void setTotalSpentForQuantLessons(String totalSpentForQuantLessons) {
	this.totalSpentForQuantLessons = totalSpentForQuantLessons;
}
public String getTotalSpentForVerbalLessons() {
	return totalSpentForVerbalLessons;
}
public void setTotalSpentForVerbalLessons(String totalSpentForVerbalLessons) {
	this.totalSpentForVerbalLessons = totalSpentForVerbalLessons;
}

}
